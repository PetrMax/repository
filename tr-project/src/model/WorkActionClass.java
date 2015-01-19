package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import log.logger;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class WorkActionClass implements ApplicationAction{
	@PersistenceContext(unitName="springHibernate", type=PersistenceContextType.EXTENDED)
	EntityManager em;
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public boolean addQuestionToDataBase(String question,String category,int level,List<String> answers,int trueAnswerNumber) {
		logger.log(category);
		boolean result = false;
		/*if(em.find(TableQuestions.class, question)==null){
			TableQuestions questions=new TableQuestions();
			questions.setQuestion(question);
			questions.setKategory(category);
			questions.setLevel(level);
			questions.setAnswer(answers);

			em.persist(questions);
			result= true;
		}*/
		return result;
	}


	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean UpdateQuestionInDataBase(String question,String category,int level,List<String> answers,int trueAnswerNumber) {
		logger.log("good");
		boolean result = false;
		// TODO Auto-generated method stub
		return result;
	}


	@Override
	//@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean AddFromFile(String FileName) {
		logger.log("good");
		boolean result = false;
		// TODO Auto-generated method stub
		return result;
	}
}
