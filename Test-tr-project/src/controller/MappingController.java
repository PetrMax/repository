package controller;
import java.util.List;

import log.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/"})
public class MappingController {	
	@Autowired
	ApplicationAction apl;

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
	public String addProcessingPage(String question_text,String category,int question_level,String answer_text ,int trueAnswerNumber,Model model){
		model.addAttribute("result",question_text+category+question_level+answer_text+trueAnswerNumber );
		logger.log(trueAnswerNumber);
		
		List<String> answer = null;
		apl.addQuestionToDataBase(question_text, category, question_level, answer, trueAnswerNumber);
		return "AddingPage";

	}

	/**
	 * 
	 * @param test_text
	 * @param model
	 * @return
	 */
	@RequestMapping({"/update_actions"})
	public String updateProcessingPage(String test_text, Model model){	
		model.addAttribute("result", test_text);
		logger.log(test_text);		
		return "UpdatePage";
	}

	/**
	 * @param file_name
	 * @param model
	 * @return
	 */
	@RequestMapping({"/add_from_file_actions"})
	public String addFromFileProcessingPage(String file_name, Model model){
		String res = "addFromFile";
		model.addAttribute("result", file_name);
		logger.log(file_name);
		res = "HomePage";
		return 	res;
	}

}
