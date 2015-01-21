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
	EntityManager em;
int j=0;
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)	
	public boolean createQuestion(String question, String category,	int level, List<String> answers, int trueAnswerNumber) {
		boolean result = false;
		if(em.find(Question.class, question) == null){
			Question qwtemp = new Question();
			qwtemp.setQuestion(question);
			qwtemp.setCategory(category);
			qwtemp.setLevel(level);
			Answer temp = new Answer();
			addAnswersList(answers,temp);
			em.persist(qwtemp);
			result = true;
		}	
		System.out.println(question + " "+category+" "+level+" "+answers+" "+trueAnswerNumber);
		return result;
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)	
	private void addAnswersList(List<String> answers,Answer answer) {
		int i = 0;	
		String[]str = new String[answers.size()];
		for(String r:answers){
			str[i++] = r;
			answer.setSearchKey("key_"+j);
		}
		answer.setAnswer_1(str[0]);
		answer.setAnswer_2(str[1]);
		answer.setAnswer_3(str[2]);
		answer.setAnswer_4(str[3]);
		j++;
		em.persist(answer);
	}

	@Override
	//@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean UpdateQuestionInDataBase(String question, String category,
			int level, List<String> answers, int trueAnswerNumber) {
		// TODO Auto-generated method stub
		// в этот метод надо  вписать действия по проверкам перед обновлением и само обновление  базы данных
		System.out.println(question + " "+category+" "+trueAnswerNumber+" "+answers);		
		return false;
	}

	@Override
	//@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public boolean AddQuestionsFromFile(String FileName) {
		// TODO Auto-generated method stub
		return false;
	}

	// методы которым надо придумать применение для возврата других результатов согласно проэкту
	// возможно использование при обновлении чтоб вернуть выбранный вопрос администратору для работы с ним
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

