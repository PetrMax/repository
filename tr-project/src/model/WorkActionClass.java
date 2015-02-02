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
	private EntityManager em;// наш менеджер для работы с добавлением  и обновлением базы данных вопросов и других параметров
	private int j=1;// счетчик  номера правильного вопроса 
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	// работа с транзакциями 
	public boolean createQuestion(String questionText,String descriptionText, String category,	int level, List<String> answers, int trueAnswerNumber) {
		// creating table question and setting data
		boolean result = false;		
		List<Question> res = em.createQuery(// searching  if question not exist
				"SELECT c FROM Question c WHERE c.questionText LIKE :custName").setParameter("custName",questionText).getResultList();
		if(res.size() == 0){			
			//	System.out.println("Adding is true !!!");			
			Question qwtemp = new Question();			
			qwtemp.setQuestion(questionText);
			qwtemp.setDescription(descriptionText);
			qwtemp.setCategory(category);
			qwtemp.setLevel(level);
			em.persist(qwtemp);// sending to database (commit)

			long keyQuestion = qwtemp.getId();
			//обходим лист стрингов который пришел как параметер  List<String> answers и добавляем ответы в БД
			for (String str : answers) {				
				addAnswersList(str, trueAnswerNumber, keyQuestion); // с помощью Этого метода // adding answer
				j++;
			}
			j = 1;

			result = true;// return to client result of operation
		}else{
			em.clear();
			//	System.out.println("Question already exist\n !!!");	
		}
		//System.out.println("small test case for showing a work transfer protocols create");
		//System.out.println(questionText + "| "+descriptionText+ "| "+category+"| "+level+"| "+answers+"| "+trueAnswerNumber);
		return result;
	}

	/** method for Creating Table Answer in DB 	 */
	private void addAnswersList(String answer, int trueAnswerNumber, long keyQuestion) {		
		Answer temp = new Answer();// creating table answer
		temp.setKeyQuestion(keyQuestion);
		temp.setAnswerText(answer);// adding text answer 
		if(trueAnswerNumber == (int)j){
			temp.setAnswer(true);// adding boolean if true/false this answer 
		}else{
			temp.setAnswer(false);// adding boolean if true/false this answer 
		}
		em.persist(temp);// добавляем данные в БД
	}
	//////////////////////////////////////////////////////////////////////////////////////
	/** Метод апдейт , берет вопрос и обновляет его данными полученными от администратора CHANGE Question */
	@SuppressWarnings("unchecked")
	@Override	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	// работа с транзакциями //logger.log(str);
	public String UpdateQuestionInDataBase(String questionID,String questionText,String descriptionText,String category, int level,List<String> answers,int trueAnswerNumber) {
		StringBuffer str = new StringBuffer();
		str.append("<p style='border:0.1em solid black;'>This text from WorkActionClass line 71 </p>");
		long id = (long)Integer.parseInt(questionID);
		List<Question> res = em.createQuery(
				"SELECT c FROM Question c WHERE c.id LIKE :custName").setParameter("custName",id).getResultList();// return to client result of operation
		List<Question> testing = em.createQuery(
				"SELECT c FROM Question c WHERE c.questionText LIKE :custName").setParameter("custName",questionText).getResultList();
		if(testing.size() == 0){
			for(Question elem:res){	
				elem.setQuestion(questionText);
				elem.setDescription(descriptionText);
				elem.setCategory(category);
				elem.setLevel(level);			
				em.persist(elem);
				str.delete(0, str.length());
				str.append("<p>Changed Question successfully added</p>");
			}
		}else{
			str.delete(0, str.length());
			str.append("<p>ERROR !!!  This Question already exist</p>");
		}
		//System.out.println("small test case for showing a work transfer protocols update");
		//System.out.println(questionID+ "| "+questionText + "| "+descriptionText+ "| "+category+"| "+level+"| "+answers+"| "+trueAnswerNumber);
		return str.toString();
	}	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** ЗАПРОС В БД По вопросу, словам из вопроса, или букве(нескольким буквам типа  J2EE) SEARCH Question  */
	@SuppressWarnings("unchecked")
	@Override	
	public String SearchQuestionInDataBase(String question, String category) {
		StringBuffer str;		
		List<Question> res = em.createQuery(
				"SELECT c FROM Question c WHERE c.questionText LIKE :custName").setParameter("custName","%"+question+"%").getResultList();// return to client result of operation			
		str = new StringBuffer();
		str.append("<table>");
		for( Question questionLine :res){	
			String line = questionLine.toString();
			String[] element = line.split(":");
			str.append("<tr><td onclick='test(value)' value='"+element[0]+"'>"+element[0]+". "+element[1]+"</td></tr>");
		}	
		str.append("</table><br>");			
		return str.toString();
	}	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Override	
	public boolean AddQuestionsFromFile(String FileName) {
		boolean res = false;	
		try {
			readLocalFile(FileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("resource")
	private void readLocalFile(String fileName) throws Exception {	
		String[] res = fileName.split(":");
		if(res.length <= 1){
			String temp = "D:/developer-workspaces/out_project/repository/tr-project/"+fileName;
			fileName = temp;
		}
		BufferedReader input = new BufferedReader(new FileReader(fileName)); 
		String line; 
		while((line = input.readLine()) != null){ 
			String[] question_Parts = line.split(":;;:"); 				
			Integer trueAnswerNumber = Integer.parseInt(question_Parts[8]); 
			List<String> answers = new ArrayList<String>();
			answers.add(question_Parts[4]);		answers.add(question_Parts[5]);
			answers.add(question_Parts[6]);		answers.add(question_Parts[7]);			
			int level = Integer.parseInt(question_Parts[3]);
			createQuestion(question_Parts[0], question_Parts[1], question_Parts[2], level, answers, trueAnswerNumber);
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public String getInformation(String questionKey) {
		StringBuffer  outRes = new StringBuffer();
		long id = (long)Integer.parseInt(questionKey);
		List<Question> question = em.createQuery(
				"SELECT c FROM Question c WHERE c.id LIKE :custName").setParameter("custName",id).getResultList();// return to client result of operation
		List<Answer> answers = em.createQuery(
				"SELECT c FROM Answer c WHERE c.keyQuestion LIKE :custName").setParameter("custName",id).getResultList();// return to client result of operation	
		for(Question q: question){
			outRes.append(q);
		}
		for(Answer an:answers){
			outRes.append(an);
		}
	
		logger.log(outRes.toString());		
		return outRes.toString();	
	}
}

