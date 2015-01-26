package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import database.DatabaseConnection;

public class WorkActionClass implements ApplicationAction {

	@PersistenceContext(unitName="springHibernate", type=PersistenceContextType.EXTENDED)
	protected EntityManager em;// наш менеджер для работы с добавлением  и обновлением базы данных вопросов и других параметров
	protected int j=1;// счетчик правильного вопроса 
	static private final String USER="root";
	static private String PASSWORD ="12345.com";
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	// работа с транзакциями 
	public boolean createQuestion(String question, String category,	int level, List<String> answers, int trueAnswerNumber) {
		boolean result = false;
		if(em.find(Question.class, question) == null){// searching  if question not exist
			Question qwtemp = new Question();// creating table question and setting data
			qwtemp.setQuestion(question);
			qwtemp.setCategory(category);
			qwtemp.setLevel(level);	
			//обходим лист стрингов который пришел как параметер  List<String> answers и добавляем ответы в БД
			for(String str :answers){
				addAnswersList(str,trueAnswerNumber); // с помощью Этого метода // adding answer
			}
			j=1;			
			em.persist(qwtemp);// sending to database
			result = true;// return to client result of operation
		}		
		// small test case for showing a work transfer protocols
		System.out.println(question + " "+category+" "+level+" "+answers+" "+trueAnswerNumber);
		return result;
	}
	int flag = 0;
	private void addAnswersList(String answer, int trueAnswerNumber) {		
		Answer temp = new Answer();// creating table answer
		temp.setAnswerText(answer);// adding text answer 
		if(trueAnswerNumber == (int)j){
			temp.setAnswer(true);// adding boolean if true/false this answer 
		}else{
			temp.setAnswer(false);// adding boolean if true/false this answer 
		}
		temp.setNumberOfAnswer(j++);
		em.persist(temp);// добавляем данные в БД
	}
	@SuppressWarnings("unchecked")
	@Override	
	public String UpdateQuestionInDataBase(String question, String category) {
		StringBuffer str;		
		List<Object> res = em.createQuery(
				"SELECT c FROM Question c WHERE c.question LIKE :custName").setParameter("custName","%"+question+"%").getResultList();// return to client result of operation
			str = new StringBuffer();
		str.append("<table style='border:0.1em solid black; width:100%;'>");
		for( Object obj:res){			
			str.append("<tr><td style='border:0.1em solid black;'>"+obj.toString()+"</tr></td>");
		}	
		str.append("</table><br>");
		return str.toString();
	}
	@SuppressWarnings("resource")
	@Override	
	public boolean AddQuestionsFromFile(String FileName) {
		BufferedReader input;		
		boolean res = false;		
		try {			
			input = new BufferedReader(new FileReader("D:/developer-workspaces/out_project/tr-project/"+FileName)); 
			String line; 
			while((line = input.readLine()) != null){ 
				String[] question_Parts = line.split(",,"); 
				res = true;
				if(question_Parts.length == 9){
					int level = Integer.parseInt(question_Parts[3]);
					int trueAnswerNumber = Integer.parseInt(question_Parts[8]); 
					List<String> answer = new ArrayList<String>();
					answer.add(question_Parts[4]);
					answer.add(question_Parts[5]);
					answer.add(question_Parts[6]);
					answer.add(question_Parts[7]);
					createQuestion(question_Parts[1], question_Parts[2], level, answer, trueAnswerNumber);
				}else{
					res = false;
					System.out.println(" format this line not correct  "+ question_Parts.length);
				}
			}			
			
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		return res;
	}

}

