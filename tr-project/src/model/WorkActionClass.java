package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import log.logger;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class WorkActionClass implements ApplicationAction {

	@PersistenceContext(unitName="springHibernate", type=PersistenceContextType.EXTENDED)
	protected EntityManager em;// наш менеджер для работы с добавлением  и обновлением базы данных вопросов и других параметров
	protected int j=1;// счетчик правильного вопроса 
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	// работа с транзакциями 
	public boolean createQuestion(String question,String sample_question_text, String category,	int level, List<String> answers, int trueAnswerNumber) {
		boolean result = false; 
		if(em.find(Question.class, question) == null){// searching  if question not exist
			Question qwtemp = new Question();// creating table question and setting data
			qwtemp.setQuestion(question);
			qwtemp.setQuestion(sample_question_text);
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
		str.append("<table>");
		for( Object obj:res){			
			str.append("<tr><td onclick='test(value)' value='"+obj.toString()+"'>"+obj.toString()+"</tr></td>");
		}	
		str.append("</table><br>");	
		//logger.log(str);
		return str.toString();
	}	

	@Override	
	public boolean AddQuestionsFromFile(String FileName) {
		boolean res = false;	
		try {
			readLocalFile();//logger.log(FileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	private void readLocalFile() throws Exception {		
		BufferedReader input = new BufferedReader(new FileReader("D:/developer-workspaces/out_project/tr-project/bild.txt")); 
		String line; 
		while((line = input.readLine()) != null){ 
			String[] question_Parts = line.split("/,,/"); 
			logger.log(question_Parts);
			int trueAnswerNumber = Integer.parseInt(question_Parts[8]); 
			List<String> answers = new ArrayList<String>();
			answers.add(question_Parts[4]);		answers.add(question_Parts[5]);
			answers.add(question_Parts[6]);		answers.add(question_Parts[7]);
			logger.log(answers);
			int level = Integer.parseInt(question_Parts[3]);
			createQuestion(question_Parts[0], question_Parts[1], question_Parts[2], level, answers, trueAnswerNumber);

		}
	}

}

