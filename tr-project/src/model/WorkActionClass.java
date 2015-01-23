package model;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class WorkActionClass implements ApplicationAction {

	@PersistenceContext(unitName="springHibernate", type=PersistenceContextType.EXTENDED)
	protected EntityManager em;
	protected int j=1;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	
	public boolean createQuestion(String question, String category,	int level, List<String> answers, int trueAnswerNumber) {
		boolean result = false;
		if(em.find(Question.class, question) == null){// searching  if question not exist
			Question qwtemp = new Question();// creating table question and setting data
			qwtemp.setQuestion(question);
			qwtemp.setCategory(category);
			qwtemp.setLevel(level);	
			
			for(String str :answers){
				addAnswersList(str,trueAnswerNumber); // adding answer
			}
			j=1;			
			em.persist(qwtemp);// sending to database
			result = true;// return to client result of operation
		}	// else flow 
		
		// small test case for showing a work transfer protocols
		System.out.println(question + "<-->"+category+"<-->"+level+"<-->"+answers+"<-->"+trueAnswerNumber);
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
		em.persist(temp);
	}

	@Override
	//@Transactional(readOnly=false, propagation=Propagation.REQUIRED)  //<<---это надо разкоментировать когда будет написан метод !!!!!! WARNING
	public boolean UpdateQuestionInDataBase(String question, String category) {
		
		// method for Paula and Oleg	
		
		boolean result = false;		
		//TO DO: method generated stub
		System.out.println(question + " "+category);		
		return result;
	}
		
	@Override	
	public boolean AddQuestionsFromFile(String FileName) {
		boolean res = false;		
		try {
			new AddFromFile(FileName);
			res = true;
		} catch (Exception e) {		
			e.printStackTrace();
		}		
		return res;
	}
	//===== mysql query methods 
	// методы которым надо придумать применение для возврата других результатов согласно проэкту
	// возможно использование при обновлении чтоб вернуть выбранный вопрос администратору для работы с ним
	@Override
	public List<Object> getAnySinglQuery(String strQuery) {
		Query query=em.createQuery(strQuery);		
		List <Object> result =query.getResultList();		
		return result;
	}

	@Override
	public List<Object[]> getAnyMultiplQuery(String strQuery ) {
		Query query=em.createQuery(strQuery);
		List <Object[]> result =query.getResultList();		
		return result;
	}	
	
	// то же самое только возвращает массив стринг 
	@Override
	public String[] getAnySingleQuery(String strQuery) {
		Query query=em.createQuery(strQuery);		
		List <Object> result =query.getResultList();
		String [] array= new String[result.size()];
		int index=0;
		for( Object obj:result)
			array[index++]=obj.toString();
		return array;
	}

	@Override
	public String[] getAnyMultipleQuery(String strQuery ) {
		Query query=em.createQuery(strQuery);
		List <Object[]> result =query.getResultList();
		String [] array= new String[result.size()];
		int ind=0;
		for(Object[] arObj:result){
			String strResult =arObj[0].toString();
			for(int i=1;i<arObj.length;i++)
				strResult+=" "+arObj[i].toString();
			array[ind++]=strResult;
		}
		return array;
	}	
	
	
}

