package model;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.persistence.EntityManager;

import log.logger;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class AddFromFile {	
	private static int j = 0;
	EntityManager em;
	String fileName;
	BufferedReader input;	
	public  AddFromFile(){}	
	public AddFromFile(String fileName, EntityManager em){
		this.em = em;
		this.fileName = fileName;
		try {
			readLocalFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//logger.log(question_Parts);
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	private void readLocalFile() throws Exception {
		
		input = new BufferedReader(new FileReader("D:/developer-workspaces/out_project/tr-project/"+fileName)); 
		String line; 
		while((line = input.readLine()) != null){ 
			String[] question_Parts = line.split(",,"); 
			logger.log(question_Parts);
			if(question_Parts.length == 8){
				String question = question_Parts[0];
				String category = question_Parts[1];
				int level = Integer.parseInt(question_Parts[2]);					
				int trueAnswerNumber = Integer.parseInt(question_Parts[7]); 
				if(insertQuestionToDB(question, category, level)){
					logger.log(question+" "+category+" "+level);
					//for (int i = 3; i < question_Parts.length; i++) {
						//insertAnswerToDB(question_Parts[i], trueAnswerNumber);
					//}
				}
			}else{
				System.out.println(" format this line not correct  "+ question_Parts.length);
			}
		}
	}
	//---------------------------------------------------------
	//@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	// работа с транзакциями 
	private boolean insertQuestionToDB(String ques,String cat, int level){
		Question qwtemp = new Question();
		qwtemp.setQuestion(ques);
		qwtemp.setCategory(cat);
		qwtemp.setLevel(level);	
		em.persist(qwtemp);// sending to database
		return true;	
	}


	private boolean insertAnswerToDB(String text,int numOk){
		Answer temp = new Answer();
		temp.setAnswerText(text);// adding text answer 
		if(numOk == (int)j){
			temp.setAnswer(true);// adding boolean if true/false this answer 
		}else{
			temp.setAnswer(false);// adding boolean if true/false this answer 
		}
		temp.setNumberOfAnswer(j++);		
		em.persist(temp);// добавляем данные в БД
		return true;
	}
}
