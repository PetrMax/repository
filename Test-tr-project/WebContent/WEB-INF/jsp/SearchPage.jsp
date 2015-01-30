<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SEARCH</title>
<style type="text/css">
body {
	text-align: center;
}

td:hover {
	background-color: yellow;
}

td {
	border: 0.1em solid black;
}

table {

	border: 0.1em solid black;
	width: 90%;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function test(val) {		
			//alert("test");
	window.open("http://localhost:8085/Test-tr-project/update","parent","width=450px height=600px");
		}
	</script>
	<form action="search_actions">
		<input onclick="test(value)" type="text" name="free_question"
			value="free question" size="50"> <input type="submit"
			value="SEARCH"><br> 	
	</form>
	<br>
	<script type="text/javascript">
		document.write("${result}");
	</script>
<br><br>

<!--  ВАЖНО!!!!!!!!! замени порты для работы в своей среде !!!!! стандартный порт: http://localhost:8080/Test-tr-project -->
<a href="http://localhost:8085/Test-tr-project/"> Home Page</a>

</body>
</html>