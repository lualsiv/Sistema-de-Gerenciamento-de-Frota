<div class="box">
	<div class="box-head">
		<h2 class="left">Pesquisa avançada</h2>
	</div>
	
	<div class="table">
		<form method="get">
			<input type="text" name="nome" id="nome" value="${nome}" />
			<input type="submit" value="Pesquisar" />
		</form>
	</div>

</div>

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
				<th>Situação</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${funcionarios.list}" var="funcionario">
			<tr>
				<td>${funcionario.cadastro}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.cargo}</td>
				<td>${funcionario.situacao == true ? 'ativo' : 'inativo' }</td>
				<td>
					<a href='<c:url value='/funcionario/${funcionario.id}/usuario/novo' />'>novo usuário</a>
					<a href='<c:url value='/funcionario/${funcionario.id}/motorista/novo' />'>novo motorista</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
		
</div>

<tag:paginacao 	totalPaginas="${funcionarios.totalPage}" 
				link="${linkTo[FuncionarioController].lista}?nome=${nome}&pagina=#" 
				paginaAtual="${funcionarios.pageNum}" />

<content tag="breadcrumb">
	<div class="small-nav">
		<a href="${pageContext.request.contextPath}/funcionario/novo">Cadastrar funcionário</a>
	</div>
</content>
