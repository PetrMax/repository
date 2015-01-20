package model;

import java.util.List;

public interface ApplicationAction {

	boolean createQuestion(String question,String category,int level,List<String> answers,int trueAnswerNumber);
	boolean UpdateQuestionInDataBase(String question, String category, int level,List<String> answers, int trueAnswerNumber);
	boolean AddQuestionsFromFile(String FileName);

	String[]getAnySingleQuery(String strQuery);
	String[]getAnyMultipleQuery(String strQuery);
}
