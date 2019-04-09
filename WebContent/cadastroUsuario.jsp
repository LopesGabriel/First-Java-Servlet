<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuários</title>
<link rel="stylesheet" href="resources/css/cadastro.css" />

<!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
            
</head>
<body>

	<div class="container">
		<form id="contact" action="salvarUsuario" method="post" onsubmit="return validarCampos()? true : false"
			enctype="multipart/form-data" >
			<h3>Cadastro / Editar Usuário</h3>
			<h4>
				<a href="acessoliberado.jsp">Página inicial</a>
			</h4>
			<h4>
				<a href="ListarUsuarios">Lista de usuários cadastrados</a>
			</h4>
			<fieldset>
				<p style="color: red;">${msg}</p>
				<input id="login" name="login" placeholder="Login" type="text"
					value="${user.login}" tabindex="1" required autofocus>
			</fieldset>
			<fieldset>
				<input id="email" name="email" placeholder="Email" type="email"
					value="${user.email}" tabindex="2" required>
			</fieldset>
			<fieldset>
				<input id="senha" name="senha" placeholder="Senha" type="password"
					value="${user.senha}" tabindex="3" required>
			</fieldset>
			<fieldset>
				<input id="cep" name="cep" placeholder="Cep" type="text"
				 tabindex="4" value="${user.cep}" onblur="consultaCep()" required>
			</fieldset>
			<fieldset>
				<input id="rua" name="rua" placeholder="Rua" type="text"
				 tabindex="5" value="${user.rua}" required>
			</fieldset>
			<fieldset>
				<input id="bairro" name="bairro" placeholder="Bairro" type="text"
				 tabindex="6" value="${user.bairro}" required>
			</fieldset>
			<fieldset>
				<input id="cidade" name="cidade" placeholder="Cidade" type="text"
				 tabindex="7" value="${user.cidade}" required>
			</fieldset>
			<fieldset>
				<input id="uf" name="uf" placeholder="Estado EX: DF" type="text"
				 tabindex="8" value="${user.uf}" required>
			</fieldset>
			<fieldset>
			<p><small>Foto</small></p>
				<input name="img" type="file" tabindex="9">
			</fieldset>
			<fieldset>
				<input id="id" name="id" placeholder="Id (ReadOnly)" type="hidden"
					value="${user.id}" readonly="readonly" tabindex="10">
			</fieldset>
			<fieldset>
				<button name="submit" type="submit" id="contact-submit"
					data-submit="...Sending">Confirmar</button>
			</fieldset>
		</form>
	</div>
	
	<script type="text/javascript">
		function validarCampos(){
			if(document.getElementById("login").value == ''){
				alert('Informe o login!');
				return false;
			}else if(document.getElementById("email").value == ''){
				alert('Informe o email!');
				return false;
			}else if(document.getElementById("senha").value == ''){
				alert('Informe a senha!');
				return false;
			}
			
			return true;
		}
		
		function consultaCep(){
			var cep = $("#cep").val();
			
			$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                if (!("erro" in dados)) {
                    //Atualiza os campos com os valores da consulta.
                    $("#rua").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
                    $("#uf").val(dados.uf);
                } //end if.
                else {
                    //CEP pesquisado não foi encontrado.
                    alert("CEP não encontrado.");
                }
            });
			
		}
	</script>

</body>
</html>