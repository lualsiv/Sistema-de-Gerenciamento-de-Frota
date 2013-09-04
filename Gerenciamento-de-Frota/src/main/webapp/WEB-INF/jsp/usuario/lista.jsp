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
				<th>Situação</th>
				<th>Cadastro</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${usuarioList}" var="usuario">
			<tr>
				<td>${usuario.funcionario.nome}</td>
				<td>${usuario.login}</td>
				<td><c:out value="${usuario.perfil}" /></td>
				<td>
					${usuario.situacao == true ? 'ativo' : 'inativo' }
				</td>
				<td>
					<joda:format value="${usuario.dataCadastro}" pattern="dd/MM/yyyy" />
				</td>
				<td>
					<c:if test="${usuario.situacao == true }">
						<a href="${linkTo[UsuarioController].bloquear[usuario.id]}">bloquear</a>
					</c:if>
					<c:if test="${usuario.situacao == false }">
						<a href="${linkTo[UsuarioController].desbloquear[usuario.id]}">desbloquear</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</div>
