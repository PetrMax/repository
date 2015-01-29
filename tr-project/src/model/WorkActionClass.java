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
		Question qwtemp = new Question();
		// searching  if question not exist
		if(em.find(Question.class, question) == null){
		// creating table question and setting data
		qwtemp.setQuestion(question);
		qwtemp.setSampleQuestion(sample_question_text);
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
		System.out.println("small test case for showing a work transfer protocols");
		System.out.println(question + "| "+sample_question_text+ "| "+category+"| "+level+"| "+answers+"| "+trueAnswerNumber);
		return result;
	}
	/** method for Creating Table Answer in DB */
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
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	// работа с транзакциями //logger.log(str);
	public String UpdateQuestionInDataBase(String question, String category) {
		StringBuffer str;		
		List<Question> res = em.createQuery(
				"SELECT c FROM Question c WHERE c.question LIKE :custName").setParameter("custName",""+question+"%").getResultList();// return to client result of operation			
		str = new StringBuffer();	
		for( Question obj:res){	
			obj.setQuestion(question);
			obj.setSampleQuestion(question);// WRONG FILTER !!!!!!!!!!!!!!!!!!!!!!
			obj.setCategory(category);
			obj.setLevel(5);
			//em.persist(obj);
			/*Query res = em.createQuery(
			"SELECT c FROM Question c WHERE c.question=?1");
	List out = res.setParameter(1, question).getResultList();

	Object f = out.get(0);
	String s = f.toString();
	String[] r = s.split(":", 1);
	logger.log(r);
	//System.out.println(f);
	if(f.equals(question)){
		System.out.println("sdfghjklkjhgfd");
	}*/
			
		}			
		
		return str.toString();
	}	

	/** ЗАПРОС В БД По вопросу, словам из вопроса, или букве(нескольким буквам типа  J2EE) */
	@SuppressWarnings("unchecked")
	@Override	
	public String SearchQuestionInDataBase(String question, String category) {
		StringBuffer str;		
		List<Question> res = em.createQuery(
				"SELECT c FROM Question c WHERE c.question LIKE :custName").setParameter("custName","%"+question+"%").getResultList();// return to client result of operation			
		str = new StringBuffer();
		str.append("<table>");
		for( Question obj:res){				
			str.append("<tr><td onclick='test(value)' value='"+obj.getId()+"'>"+obj.toString()+"</tr></td>");
		}	
		str.append("</table><br>");			
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

