package model;

import java.util.List;

public interface ApplicationAction {//общий интерфейс 
	//Сюда добавляем свои методы для работы с ними через веб. 
	//ВАЖНО ЭТО ИНТЕРФЕЙС !!!-СЕРВИС-!!! при изменениях ихменить инрерфейс !!!-ТЕСТ-!!!.

	boolean createQuestion(String questionText,String descriptionText,String category,int level,List<String> answers,int trueAnswerNumber);
	public String UpdateQuestionInDataBase(String questionKey, String actionKey,String questionText,String descriptionText,String category, int level,List<String> answers,int trueAnswerNumber);
	boolean AddQuestionsFromFile(String FileName);
	String SearchQuestionInDataBase(String question, String category);
}
