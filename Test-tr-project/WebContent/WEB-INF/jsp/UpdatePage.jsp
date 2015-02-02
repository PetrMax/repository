<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPDATE</title>
<script type="text/javascript">

var formText = new Array();

    function test(questionId) {	
    	var EDIT_Q = document.getElementsByName("editing")[0];
    	var att = document.createAttribute("style");
    	att.value = "display:block";
    	EDIT_Q.setAttributeNode(att);	
    	
    	var FORM_C = document.getElementsByName("questionKey")[0];
    	var att = document.createAttribute("value");
    	att.value = questionId;
    	FORM_C.setAttributeNode(att);	
    	
      }
  //  alert(formText); 
	function work(){	
		formText = ("${textArray}");
    var FORM_C = document.getElementsByName("formTag")[0];
	var att = document.createAttribute("style");
	att.value = "display:block";
	FORM_C.setAttributeNode(att);
	
	var SEARCH_Q = document.getElementsByName("searchCODE")[0];
	var att = document.createAttribute("style");
	att.value = "display:none";
	SEARCH_Q.setAttributeNode(att);
	
	var EDIT_Q = document.getElementsByName("editing")[0];
	var att = document.createAttribute("style");
	att.value = "display:block";
	EDIT_Q.setAttributeNode(att);	
	
	var RES_TQ = document.getElementsByTagName("div")[0];
	var att = document.createAttribute("style");
	att.value = "display:none";
	RES_TQ.setAttributeNode(att);
			
	fillQuestionText();
   fillAnswerText();
	}

	//-------------------------------------------------------------------------------------------------------------
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
// все работает 
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
	// hidden input 
	var HI_ID = document.getElementsByName("questionID")[0];
	var att = document.createAttribute("value");
	att.value = formText[9];
	HI_ID.setAttributeNode(att);
	} 

</script>
<style type="text/css">
*{
	text-align: center;
}
a {
	font-size: 1.35em;
	color: blue;
}

a:HOVER {
	color: orange;
}
input:HOVER {
	background-color: yellow;
}
.addingClassCss{
	 display: none; 
}
td:hover {
	background-color: yellow;
	border:none;
}

td {
	border: 0.01em solid black;
}

table {
	border: 0.01em solid black;
	width: 100%;
}
p{
	color: blue;
	border-bottom: 0.01em solid black;	
}
.editingAction{
display: none;
}
</style>
</head>
<body>	
<a href="http://localhost:8080/Test-tr-project/">Home Page</a><br>
	<p onclick="test('1')">Update - Change  issues</p><!-- test working java script in this jsp file -->
	<form  name="searchCODE" action="search_actions">
		 <input  type="text" name="free_question" size="50">
		 <input type="submit"	value="SEARCH"><br> 	
	</form>
	<br>
	<form name="editing"  class="editingAction" action="getArrayFromDB">
	<input	type="text" name="questionKey" size="8">&nbsp;&nbsp; 
    <input type="submit" value="Edit Question" >
    </form>
	

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
		 <input	type="text" name="questionID" value="" style="visibility: hidden;"><br> 
		 <input type="submit" value="Change Question" >
	</form>
	<br>	
	<div>
	<script type="text/javascript">
		document.write("${result}");
	</script>
	</div>	
	<br>
	<br>

</body>
</html>
