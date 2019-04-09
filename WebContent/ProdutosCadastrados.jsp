<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produtos</title>
<link rel="stylesheet" href="resources/css/table.css" />
</head>
<body>
<jsp:include page="Cabecalho.jsp"/>
	<br>
	
	<%
		if(request.getAttribute("msg") == "Produto deletado com sucesso!"){
			out.println("<h2 style='text-align:center;color:red;'>"+ request.getAttribute("msg") + "</h2>");
		}else if(request.getAttribute("msg") == "Produto adicionado com sucesso!"){
			out.println("<h2 style='text-align:center;color:green;'>"+ request.getAttribute("msg") + "</h2>");
		}else if(request.getAttribute("msg") == "Já existe um produto com esse nome!"){
			out.println("<h2 style='text-align:center;color:red;'>"+ request.getAttribute("msg") + "</h2>");
		}else if(request.getAttribute("msg") == "Produto atualizado com sucesso!"){
			out.println("<h2 style='text-align:center;color:green;'>"+ request.getAttribute("msg") + "</h2>");
		}
	%>
	
	<div style="width:700px;height:100%;background-color:#4CAF50;margin: 0 auto;text-align:center; padding:5px;">
		<h2>Produtos cadastrados</h2>
		<p><a href="cadastroProduto.jsp" style="text-decoration:none; color:black;">Cadastrar produto</a></p>
		
		<table class="redTable">
		<thead>
			<tr>
			    <th>Nome</th>
			    <th>Quantidade</th> 
			    <th>Valor</th>
			    <th>Opções</th>
		    </tr>
	    </thead>
		<c:forEach items="${produtos}" var="prod">
			<tr>
				<td><c:out value="${prod.nome}"></c:out></td>
				<td><c:out value="${prod.quantidade}"></c:out></td>
				<td><c:out value="${prod.valor}"></c:out></td>
				<td>
					<a href="salvarProduto?acao=delete&prod=${prod.id}"><img src="resources/img/icondelete.png" style="width:20px; height:20px;margin:5px;" title="Excluir"></a> 
					<a href="salvarProduto?acao=editar&prod=${prod.id}"><img src="resources/img/iconedit.png" style="width:20px; height:20px;margin:5px;" title="Editar"></a>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>