<div class="box">
	<div class="box-head">
		<h2 class="left">Lista de usuários</h2>
	</div>

	<div class="table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th>Nome</th>
				<th>Login</th>
				<th>Perfil</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${usuarioList}" var="usuario">
			<tr>
				<td>${usuario.funcionario.nome}</td>
				<td>${usuario.login}</td>
				<td>${usuario.perfil}</td>
				<td>&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</div>
