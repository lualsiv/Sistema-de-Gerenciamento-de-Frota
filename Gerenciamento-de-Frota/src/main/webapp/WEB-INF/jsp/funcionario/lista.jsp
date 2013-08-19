<div class="box">
	<div class="box-head">
		<h2 class="left">Lista de funcionários</h2>
	</div>

	<div class="table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th>Cadastro</th>
				<th>Nome</th>
				<th>Cargo</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${funcionarioList}" var="funcionario">
			<tr>
				<td>${funcionario.cadastro}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.cargo}</td>
				<td>
					<a href='<c:url value='/funcionario/${funcionario.id}/usuario/novo' />'>novo usuário</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</div>

<content tag="breadcrumb">
	<div class="small-nav">
		<a href="${pageContext.request.contextPath}/funcionario/novo">Cadastrar funcionário</a>
	</div>
</content>
