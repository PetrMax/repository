<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UPDATE</title>

<style type="text/css">
body{
	text-align: center;
}
a{
font-size:2em;
color:blue;
}
a:HOVER {
	color:orange;
}

td {
	border: 0.1em solid black;
}

table {
	border: 0.1em solid black;
	width: 90%;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>	
	<form action="update_actions">
	Please input question text
		<input id="questionText" type="text" name="question_text" value="here"><br> 
		
		Please input sample question text
		<input id="exampleQuestionText" type="text" name="sample_question_text" value="here"><br> 		
		
		
		Please select a Category of Question: <br>
		C# <input  type="checkbox" name="category" value="C#" />
		Java <input  type="checkbox" name="category" value="Java" />
		C++ <input 	type="checkbox" name="category" value="C++" /> <br>	
		Insert another Category<br>
		 <input id="category"	type="text" name="category" value="" /><br>			
			 Please	select Level for Question<br>
			 1<input  type="radio"	name="question_level" value=1 checked="checked">
			 2<input  type="radio" name="question_level" value=2>
			 3<input  type="radio" name="question_level" value=3>
			 4<input  type="radio" name="question_level" value=4>
			 5<input id="levelChange" type="radio" name="question_level" value=5> <br> 
			
			
			
		Please input Answer to this Question<br>
		Answer 1 <input id="answ_t_1" type="text" name="answer_text_1" value="input text"> <br>
		Answer 2 <input id="" type="answ_t_2" name="answer_text_2" value="input text"> <br> 
		Answer 3 <input id="" type="answ_t_3" name="answer_text_3" value="input text"> <br>
		Answer 4 <input id="" type="answ_t_4" name="answer_text_4"value="input text"> <br>
		
		Please input number a right	question answer<br>
		 <input id="trueAnswNum" type="text" name="trueAnswerNumber"	value="1"><br> <input type="submit">
	</form>
	<br>
	
	<script type="text/javascript">
		document.write("${result}");
	</script>
	<br>
	<!--  ВАЖНО!!!!!!!!! замени порты для работы в своей среде !!!!! стандартный порт: http://localhost:8080/Test-tr-project -->
	<a href="http://localhost:8085/Test-tr-project/"> Home Page</a>
	<br>
	<a href="http://localhost:8085/Test-tr-project/search">Back to Search</a>
</body>
</html>