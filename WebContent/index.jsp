<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>First Servlet Page</title>
<link rel="stylesheet" href="resources/css/login.css" />
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="LoginServlet" method="post" class="login-form">

				<label for="login">Login</label><br> 
				<input type="text" id="login" name="login" placeholder="Login"><br> 
				<label for="senha">Senha</label><br> 
				<input type="password" id="senha" name="senha" placeholder="Senha"><br> 
				<button type="submit">Entrar</button>

			</form>
		</div>
	</div>

</body>
</html>