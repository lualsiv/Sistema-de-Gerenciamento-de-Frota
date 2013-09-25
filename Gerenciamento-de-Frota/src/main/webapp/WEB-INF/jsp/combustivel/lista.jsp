<div class="box">
	<div class="box-head">
		<h2 class="left">Lista de combustíveis</h2>
	</div>

	<div class="table">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th>Descrição</th>
				<th>Valor</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${combustiveis.list}" var="combustivel">
			<tr>
				<td>${combustivel.descricao}</td>
				<td><fmt:formatNumber>${combustivel.preco}</fmt:formatNumber></td>
				<td>
					<a href="${pageContext.request.contextPath}/combustivel/${combustivel.id}">editar</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>

</div>

<tag:paginacao 	totalPaginas="${combustiveis.totalPage}" 
				link="${linkTo[CombustivelController].lista}?pagina=#" 
				paginaAtual="${combustiveis.pageNum}" />
				
<content tag="breadcrumb">
	<div class="small-nav">
		<a href="${pageContext.request.contextPath}/combustivel/novo">Cadastrar combustível</a>
	</div>
</content>
