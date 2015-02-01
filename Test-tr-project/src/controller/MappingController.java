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
	@Autowired// аннотация делающая автоматический  вызов через тег в бин.хмл файле.
	ApplicationAction DBservice;// Это связь интерфейсов , эта переменная дает нам все методы интерфейса !!!-СЕРВИС-!!!  DBservice. + горячие клавиши Ctrl+пробел. и далее выбираем из списка.

	/**когда запускаем аппликации, метод дает домашнюю страницу !! */
	@RequestMapping({"/"})
	public String homePage() {return "HomePage";}

	/** когда нажимаем на кнопку add question! этот метод только вызывает страницу adding page здесь писать ничего не надо!! */
	@RequestMapping({"/add"})
	public String addingPage() {return "AddingPage";}	

	/** когда нажимаем на кнопку  update question!  этот метод только вызывает страницу updating view table page здесь писать ничего не надо!! !! */
	@RequestMapping({"/update"})
	public String UpdatePage(){return "UpdatePage";}

	/** когда нажимаем на кнопку add from file! этот метод только вызывает страницу adding page  здесь писать ничего не надо!!!! */
	@RequestMapping({"/addfromfile"})
	public String specificDataPage(){return "AutoComplete";}

	/** ДОБАВЛЕНИЕ НОВОГО ВОПРОСА В БАЗУ ДАННЫХ */
	@RequestMapping({"/add_actions"})
	public String addProcessingPage(String question_text,String sample_question_text,String category,int question_level,String answer_text_1,String answer_text_2,String answer_text_3,String answer_text_4 ,int trueAnswerNumber,Model model){
		/**Имена переменных приходящих в этот метод !!! ВАЖНО!!! Это Аттрибут тага: name="" должно быть соответствие полное!!!
		 * количество неограничено.
		 * Имя  же самого метода public String addProcessingPage() и других методов этого класса,
		 * пока нигде не применялось и существенно роль походу не играет//спросить Юрия//
		 */
		List<String> answer = new ArrayList<String>();
		answer.add(answer_text_1);		answer.add(answer_text_2);
		answer.add(answer_text_3);		answer.add(answer_text_4);

		boolean actionRes = false; // флаг работы апликации
		try {
			actionRes = DBservice.createQuestion(question_text,sample_question_text,
					category, question_level, answer, trueAnswerNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// альтернативный путь , если ответа от сервиса небыло по любым причинам.
		if (actionRes) {
			// написать альтернативный путь !!!
			model.addAttribute("result","<p> Question successfully added</p>");// вывод текста
			actionRes=false;
		}else{
			model.addAttribute("result","<p> Error adding the question, the question already exists. Try again</p>");// вывод текста
		}
		/**ВАЖНО!!! Принимает параметер только стринг и стрингБуфер!!!, метод фреймворка Спринг!! 
		 * Метод вывода текста на  ХТМЛ  страницу через джава скрипт  model.addAttribute("result",ВАЖНО!! чтобы имя написанное в методе как 1 параметр, 
		 * И написанное на ХТМЛ странице в скипте имя в фигурных скобках  document.write("${result}"); совпадали полностью !!!
		 * */
		return "AddingPage"; // return too page after action
	}
	////////////////////////////////////////////////////////////////////////////////////////
	/***  ОБНОВЛЕНИЕ ВОПРОСОВ действия разрешены Администратору системы  */
	@RequestMapping({"/update_actions"})
	public String updateProcessingPage(String questionText,String descriptionText,
			String category,int question_level,
			String answer_text_1,String answer_text_2,String answer_text_3,String answer_text_4 ,
			int trueAnswerNumber,Model model,String GHANGEQUESTION){	

		/** this value comming from javascript in page SearchPage.jsp  !!!!! not work correctly  !!!!*/
		// string to int, column ID - integer number from table Question for search question in DB and change it./*autoincrement genereted value */ 
		String questionKey = "1"; /// тут надо передать айди обьекта для замены, получаем его когда жмем на вопрос в таблице 

		//when you submit form getting query to DB, and update form with parameters and text of question 
		String actionKey = GHANGEQUESTION;
		List<String> answer = new ArrayList<String>();
		answer.add(answer_text_1);		answer.add(answer_text_2);
		answer.add(answer_text_3);		answer.add(answer_text_4);

		String result = DBservice.UpdateQuestionInDataBase(questionKey, actionKey, questionText, descriptionText, category, question_level, answer, trueAnswerNumber);	
		model.addAttribute("result", result);// text on page for testing
		return "UpdatePage";// return too page after action		
	}
	///////////////////////////////////////////////////////////////////////////////////////
	/***  ПОИСК ВОПРОСОВ действия разрешены Администратору системы  */
	@RequestMapping({"/search_actions"})
	public String searchProcessingPage(String category, String free_question, Model model){	
		/** это метод обновления вопроса, принимает String free_question: Это текст в свободной форме, для поиска вопроса.*/
		String result = DBservice.SearchQuestionInDataBase(free_question, category);		
		model.addAttribute("result", result);// text on page for testing
		return "UpdatePage";// return too page after action		
	}

	/** ДОБАВЛЕНИЕ БОЛЬШОГО КОЛИЧЕСТВА ВОПРОСОВ ОДНОВРЕМЕННО С ПОМОЩЬЮ ФАЙЛА */
	@RequestMapping({"/add_from_file_actions"})
	public String addFromFileProcessingPage(String file_name, Model model){
		String res = "addFromFile";		
		boolean actionRes = DBservice.AddQuestionsFromFile(file_name);
		model.addAttribute("result"," Adding Questions is - "+actionRes);// вывод текста
		res = "HomePage";
		return 	res;// return too page after action
	}
}
