<html>

	<div style="width:100%;height:100%;background-color:#4CAF50;text-align:center;margin:0 auto;">
		<h3><a href="acessoliberado.jsp" style="text-decoration:none;color:black;">Página Inicial</a> | Logado como 
		<%= session.getAttribute("login") %></h3>
		<img src='<%= session.getAttribute("avatar") %>' style="width:100px; height:100px;border-radius:50px;">
	</div>
</html>