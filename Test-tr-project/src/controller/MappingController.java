package controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/"})
public class MappingController {	
	@Autowired
	ApplicationAction DBservice;

	/** when application is run this method getting home page !! */
	@RequestMapping({"/"})
	public String homePage() {return "HomePage";}


	/** when pressed button add question! this method is only calling a adding page !! */
	@RequestMapping({"/add"})
	public String addingPage() {return "AddingPage";}


	/** when pressed button update question! this method is only calling a updating view table page !! */
	@RequestMapping({"/update"})
	public String UpdatePage(){return "UpdatePage";}


	/** when pressed button add from file! this method is only calling a adding page !! */
	@RequestMapping({"/addfromfile"})
	public String specificDataPage(){return "addFromFile";}

	/**
	 * Adding Question method Action
	 * @param question_text
	 * @param category
	 * @param question_level
	 * @param answer_text
	 * @param trueAnswerNumber
	 * @param model
	 * @return
	 */
	@RequestMapping({"/add_actions"})
	public String addProcessingPage(String question_text,String category,int question_level,String answer_text_1,String answer_text_2,String answer_text_3,String answer_text_4 ,int trueAnswerNumber,Model model){

		model.addAttribute("result",question_text+category+question_level+answer_text_1+trueAnswerNumber );// text on page for testing

		/**
		 * the way from client to service this method is returning boolean
		 */

		List<String> answer = new ArrayList<String>();
		answer.add(answer_text_1);
		answer.add(answer_text_2);
		answer.add(answer_text_3);
		answer.add(answer_text_4);

		boolean res = DBservice.createQuestion(question_text, category, question_level, answer, trueAnswerNumber);


		return "AddingPage"; // return too page after action
	}

	/**
	 * 
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping({"/update_actions"})
	public String updateProcessingPage(String category, String free_question, Model model){	
		/**
		 * the way from client to service this method is returning boolean
		 */
		boolean res = DBservice.UpdateQuestionInDataBase( free_question,category);		
		model.addAttribute("result", "you'r choice ->"+ category +"  "+ res);// text on page for testing
		
		return "UpdatePage";// returning too page after action
	}
	/**
	 * @param file_name
	 * @param model
	 * @return
	 */
	@RequestMapping({"/add_from_file_actions"})
	public String addFromFileProcessingPage(String file_name, Model model){
		String res = "addFromFile";		
		boolean actionRes = DBservice.AddQuestionsFromFile(file_name);
		model.addAttribute("result"," Adding Questions is - "+actionRes);// text on page for testing	
		res = "HomePage";
		return 	res;// return too page after action
	}
}
