<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acesso Liberado</title>
</head>
<body>
	<% 
	boolean sessionOn = false;
	if(sessionOn == false){
		Object login = session.getAttribute("login");
		if(login == null){
			session.setAttribute("login", request.getParameter("login"));
			session.setAttribute("avatar", request.getAttribute("img"));
		}else{
			sessionOn = true;
		}
	}
	
	%>
	<jsp:include page="Cabecalho.jsp"/>

	<div style="width:50%;height:100%;background-color:#4CAF50;text-align:center;margin:0 auto;border-radius:50px;">
		<%= "<h2>Seja bem-vindo " + session.getAttribute("login") + ".</h2>" %>
		<div style="width:50%;height:100%;float:left;">
			<a href="cadastroUsuario.jsp" title="Cadastrar usuário"><img src="resources/img/usuario.png" style="width:150px;heigth:150px;"></a>
			<p>Cadastrar Usuário</p>
		</div>
		<div style="width:50%;height:100%;float:left;">
			<a href="cadastroProduto.jsp" title="Cadastrar produto"><img src="resources/img/icone-produtos-png.png" style="width:150px;heigth:150px;"></a>
			<p>Cadastrar Produto</p>
		</div>
		<a href="SessionClose.jsp" style="color:black;text-decoration:none;font-size:20px;" title="Finalizar sessão">
			<div style="width:50%;height:50%;clear:both;background-color:#4CCF00;margin-left:25%;padding-top:2px;border-radius:30px;">
				<p>Deslogar</p>
			</div>
		</a>
	</div>
</body>
</html>