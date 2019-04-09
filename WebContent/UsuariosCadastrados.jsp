<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuários</title>
<link rel="stylesheet" href="resources/css/table.css" />
</head>
<body>
	<jsp:include page="Cabecalho.jsp"/>
	<br>
	<%
		if(request.getAttribute("msg") == "Usuário deletado com sucesso!"){
			out.println("<h2 style='text-align:center;color:red;'>"+ request.getAttribute("msg") + "</h2>");
		}else if(request.getAttribute("msg") == "Sucesso!"){
			out.println("<h2 style='text-align:center;color:green;'>"+ request.getAttribute("msg") + "</h2>");
		}else if(request.getAttribute("msg") == "Já existe um usuário com esse login!"){
			out.println("<h2 style='text-align:center;color:red;'>"+ request.getAttribute("msg") + "</h2>");
		}else if(request.getAttribute("msg") == "Usuário atualizado com sucesso!"){
			out.println("<h2 style='text-align:center;color:green;'>"+ request.getAttribute("msg") + "</h2>");
		}
	%>
	
	<div style="width:80%;height:100%;background-color:#4CAF50;margin: 0 auto;text-align:center; padding:5px;">
		<h2>Usuários cadastrados</h2>
		<p><a href="cadastroUsuario.jsp" style="text-decoration:none; color:black;">Cadastrar usuário</a></p>
		
		<table class="redTable">
		<thead>
			<tr>
				<th>Avatar</th>
			    <th>Login</th>
			    <th>Senha</th> 
			    <th>Email</th>
			    <th>Estado</th>
			    <th>Cidade</th>
			    <th>Endereço</th>
			    <th>Opções</th>
		    </tr>
	    </thead>
		<c:forEach items="${usuarios}" var="user">
			<tr>
				<td><img src='<c:out value="${user.tempFotoUser}"></c:out>' style="width:50px; height:50px;" alt="Avatar"></td>
				<td><c:out value="${user.login}"></c:out></td>
				<td><c:out value="${user.senha}"></c:out></td>
				<td><c:out value="${user.email}"></c:out></td>
				<td><c:out value="${user.uf}"></c:out></td>
				<td><c:out value="${user.cidade}"></c:out></td>
				<td><c:out value="${user.rua}"></c:out></td>
				<td>
					<a href="salvarUsuario?acao=delete&user=${user.id}"><img src="resources/img/icondelete.png" style="width:20px; height:20px;margin:5px;" title="Excluir"></a> 
					<a href="salvarUsuario?acao=editar&user=${user.id}"><img src="resources/img/iconedit.png" style="width:20px; height:20px;margin:5px;" title="Editar"></a>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	<script>
		${msg}
	</script>
</body>
</html>