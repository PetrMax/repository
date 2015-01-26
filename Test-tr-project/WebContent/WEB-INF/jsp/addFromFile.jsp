<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,java.net.Inet4Address"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding From File</title>

<style type="text/css" >
body{
	text-align: center;
}
</style>

</head>
<body>
	<br> Please choice specific file to fill data base
	<br>
	<form action="add_from_file_actions">
		<input type="file" name="file_name"><br>
		<input type="submit">
	</form>	
	<br>
	<script type="text/javascript">
		document.write("${result}");//вывод текста
	</script>
</body>
</html>