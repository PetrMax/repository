<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,java.net.Inet4Address"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="D:\developer-workspaces\GitHub\repository\repository\Test-tr-project\WebContent\WEB-INF\css\style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding From File</title>
</head>
<body>
<!--  ВАЖНО!!!!!!!!! замени порты для работы в своей среде !!!!! стандартный порт: http://localhost:8080/Test-tr-project -->
	<a href="http://localhost:8080/Test-tr-project/"> Home Page</a>
	
    <p> Auto Complete Data Base from external file</p>
	<br> Please choice specific file to fill data base
	<br>
	<form action="add_from_file_actions"><!-- action='http://localhost...' method=post enctype='multipart/form-data' -->
		<input type="file" name="file_name"><br>
		<input type="submit">
	</form>	
	<br>
	<script type="text/javascript">
		document.write("${result}");//вывод текста
	</script>
		
</body>
</html>
