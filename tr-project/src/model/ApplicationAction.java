package model;

import java.util.List;

public interface ApplicationAction {
	boolean addQuestionToDataBase(String question,String category,int level,List<String> answers,int trueAnswerNumber);
	boolean UpdateQuestionInDataBase(String question, String category, int level,List<String> answers, int trueAnswerNumber);
	boolean AddFromFile(String FileName);

}
