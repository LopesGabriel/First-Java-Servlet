<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
<link rel="stylesheet" href="resources/css/cadastro.css" />
</head>
<body>
<div class="container">
		<form id="contact" action="salvarProduto" method="post" onsubmit="return validarCampos()? true : false">
			<h3>Cadastro / Editar Produto</h3>
			<h4>
				<a href="acessoliberado.jsp">Página inicial</a>
			</h4>
			<h4>
				<a href="ListarProdutos">Lista de produtos cadastrados</a>
			</h4>
			<fieldset>
				<p style="color: red;">${msg}</p>
				<input id="nome" name="nome" placeholder="Nome do produto" type="text"
					value="${prod.nome}" tabindex="1" required autofocus>
			</fieldset>
			<fieldset>
				<input id="quantidade" name="quantidade" placeholder="Quantidade" type="text"
					value="${prod.quantidade}" tabindex="2" required>
			</fieldset>
			<fieldset>
				<input id="valor" name="valor" placeholder="Preço (Use '.' para casas decimais)" type="text"
					value="${prod.valor}" tabindex="3" required>
			</fieldset>
			<fieldset>
				<input id="id" name="id" placeholder="Id (ReadOnly)" type="text"
					value="${prod.id}" readonly="readonly" tabindex="4">
			</fieldset>
			<fieldset>
				<button name="submit" type="submit" id="contact-submit"
					data-submit="...Sending">Confirmar</button>
			</fieldset>
		</form>
	</div>
	
	<script type="text/javascript">
		function validarCampos(){
			if(document.getElementById("nome").value == ''){
				alert('Informe o nome!');
				return false;
			}else if(document.getElementById("quantidade").value == ''){
				alert('Informe a quantidade!');
				return false;
			}else if(document.getElementById("valor").value == ''){
				alert('Informe o valor!');
				return false;
			}else if(document.getElementById("quantidade").value == '0'){
				alert('Quantidade não pode ser 0!');
				return false;
			}else if(document.getElementById("valor").value == '0' || document.getElementById("valor").value == '0.0'){
				alert('Valor não pode ser 0!');
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>