package controller;

import java.util.List;

public interface ApplicationAction {//общий интерфейс 
	//Сюда добавляем свои методы для работы с ними через веб. 
	//ВАЖНО ЭТО ИНТЕРФЕЙС !!!-ТЕСТ-!!! при изменениях ихменить инрерфейс !!!-СЕРВИС-!!!.

	boolean createQuestion(String question,String sample_question_text,String category, int level,List<String> answers,int trueAnswerNumber);
	String UpdateQuestionInDataBase(String question, String category);
	boolean AddQuestionsFromFile(String FileName);
	}
