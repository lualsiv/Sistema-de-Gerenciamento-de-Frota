<div class="box">
	<div class="box-head">
		<h2 class="left">Lista de motoristas</h2>
	</div>

	<div class="table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th>Nome</th>
				<th>Número</th>
				<th>Registro</th>
				<th>Situação</th>
				<th>Categoria</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${motoristaList}" var="usuario">
			<tr>
				<td>${usuario.funcionario.nome}</td>
				<td>${usuario.numero}</td>
				<td>${usuario.registro}</td>
				<td>${usuario.situacao == true ? 'ativo' : 'inativo' }</td>
				<td>${usuario.categoria}</td>
				<td>&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</div>

<content tag="breadcrumb">
	<div class="small-nav">
		<a href="${pageContext.request.contextPath}/motorista/novo">Cadastrar motorista</a>
	</div>
</content>
