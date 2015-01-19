<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choice Step</title>

<style type="text/css" >
body{
	text-align: center;
}
</style>

</head>
<body>

<form action="add">
<input type="submit" value="1. create new question">
</form>

<form action="update">
<input type="submit" value="2. Update Question"/>
</form>

<form action="addfromfile">
<input type="submit" value="3. Adding questions from file">
</form>
<br>
<script type="text/javascript">
		document.write("${result}");
	</script>
</body>
</html>