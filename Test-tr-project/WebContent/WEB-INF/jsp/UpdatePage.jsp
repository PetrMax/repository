<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="*\Test-tr-project\WebContent\WEB-INF\css\style.css"/> -->
<link rel="stylesheet" type="text/css" href="D:\developer-workspaces\out_project\repository\Test-tr-project\WebContent\WEB-INF\css\style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPDATE</title>
<script type="text/javascript">

var formText = new Array();

function test(questionId) {		
		alert(questionId+"<- num ID of question. This alert from line 11 UpdatePage.jsp ");
		formText = requestFormsFromDB(questionId);
	var FORM_C = document.getElementsByName("formTag")[0];
	var att = document.createAttribute("style");
	att.value = "display:block";
	FORM_C.setAttributeNode(att);
	
	var SEARCH_Q = document.getElementsByName("searchCODE")[0];
	var att = document.createAttribute("style");
	att.value = "display:none";
	SEARCH_Q.setAttributeNode(att);
	
	var RES_TQ = document.getElementsByTagName("div")[0];
	var att = document.createAttribute("style");
	att.value = "display:none";
	RES_TQ.setAttributeNode(att);
	
	fillQuestionText();
	fillAnswerText();
	}
//надо сделать снова запрос в БД чтоб она вернула параметры выбранного вопроса !!  
	function requestFormsFromDB(questionId){
	alert("this alert from function requestFormsFromDB(questionId){ ,line 37 UpdatePage.jsp >>>>val>> "+questionId);
		var res = ["question","description","JAVA","2","answer","answer","answer","answer","2"];
		return res;
	}
 // все работает 
function fillQuestionText(){	
	// question fill case
	var QT = document.getElementsByName("questionText")[0];
	 var att = document.createAttribute("value");
	att.value = formText[0];
	QT.setAttributeNode(att); 
	// description fill case
	var DT = document.getElementsByName("descriptionText")[0];
	 var att = document.createAttribute("value");
	att.value = formText[1];
	DT.setAttributeNode(att);
	// category fill case
	var CAT = document.getElementsByName("category")[0];
	 var att = document.createAttribute("value");
	att.value = formText[2];
	CAT.setAttributeNode(att);
	// question level fill case
	var QL = document.getElementsByName("question_level")[formText[3]];// сюда надо положить цифру из вопроса, учитываем что отсчет идет с '0'
	var att = document.createAttribute("checked");
	att.value = "checked";
	QL.setAttributeNode(att);	
}

function fillAnswerText(){	
	// ----------------------
	var AT_1 = document.getElementsByName("answer_text_1")[0];
	 var att = document.createAttribute("value");
	att.value = formText[4];
	AT_1.setAttributeNode(att); 
	// -------------------
	var AT_2 = document.getElementsByName("answer_text_2")[0];
	 var att = document.createAttribute("value");
	att.value = formText[5];
	AT_2.setAttributeNode(att);
	// -------------------
	var AT_3 = document.getElementsByName("answer_text_3")[0];
	 var att = document.createAttribute("value");
	att.value = formText[6];
	AT_3.setAttributeNode(att);
	// -----------------------------
	var AT_4 = document.getElementsByName("answer_text_4")[0];
	var att = document.createAttribute("value");
	att.value = formText[7];
	AT_4.setAttributeNode(att);
	//-------- true answer number --
	var RA = document.getElementsByName("trueAnswerNumber")[0];
	var att = document.createAttribute("value");
	att.value = formText[8];
	RA.setAttributeNode(att);	
} 

</script>
</head>
<body>	
	<!--  ВАЖНО!!!!!!!!! замени порты для работы в своей среде !!!!! стандартный порт: http://localhost:8080/Test-tr-project -->
<a href="http://localhost:8080/Test-tr-project/">Home Page</a><br>
	<p onclick="test('test JS')">Update - Change  issues</p>
	<form  name="searchCODE" action="search_actions">
		<input  type="text" name="free_question" size="50">
		 <input type="submit"	value="SEARCH"><br> 	
	</form>
	<br>

	<form name="formTag" action="update_actions" class="addingClassCss">
		Question text<br>
		 <input type="text" name="questionText" value=""><br>
		Description text<br> 
		<input type="text"	name="descriptionText" value=""><br>
		Question Category<br>
		 <input type="text" name="category" value="" /><br> 
		Question Level<br>	
		<input type="radio" name="question_level" value=1>1	<!-- checked="checked" -->
		<input type="radio" name="question_level" value=2>2 <!-- если параметер не выбран будет ошибка 500 !!! -->
		<input type="radio" name="question_level" value=3>3
		<input type="radio" name="question_level" value=4>4
		<input type="radio" name="question_level" value=5>5 
		<br> Answers for Question <br>
		 Answer 1 <input type="text" name="answer_text_1" value=""> <br>
		 Answer 2 <input type="text" name="answer_text_2" value=""> <br> 
		 Answer 3 <input type="text" name="answer_text_3" value=""> <br>
		 Answer 4 <input type="text" name="answer_text_4" value=""> <br>

		 Please input number a right question answer<br>
		 <input	type="text" name="trueAnswerNumber" value="" size="2"><br> 
		 <input type="submit" value="changeQuestion" name="GHANGEQUESTION">
	</form>
	<br>

	<div >
	<script type="text/javascript">
		document.write("${result}");
	</script>
	</div>
	
	<br>
	<br>

</body>
</html>
