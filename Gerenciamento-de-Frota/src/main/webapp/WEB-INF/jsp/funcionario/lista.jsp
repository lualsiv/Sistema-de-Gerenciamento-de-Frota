<div class="box">
	<div class="box-head">
		<h2 class="left">Pesquisa avançada</h2>
	</div>
	
	<div class="table">
		<form method="get" id="form_pesquisa">
			<label for="nome" >Nome: </label>
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
				<th>
					<a href="${linkTo[FuncionarioController].lista}?nome=${nome}&ordem=cadastro&pagina=${funcionarios.pageNum}" >Cadastro</a>
				</th>
				<th>
					<a href="${linkTo[FuncionarioController].lista}?nome=${nome}&ordem=nome&pagina=${funcionarios.pageNum}" >Nome</a>
				</th>
				<th>
					<a href="${linkTo[FuncionarioController].lista}?nome=${nome}&ordem=cargo&pagina=${funcionarios.pageNum}" >Cargo</a>
				</th>
				<th>
					<a href="${linkTo[FuncionarioController].lista}?nome=${nome}&ordem=situacao&pagina=${funcionarios.pageNum}" >Situação</a>
				</th>
				<th>Opções</th>
			</tr>
			<c:forEach items="${funcionarios.list}" var="funcionario">
			<tr>
				<td>${funcionario.cadastro}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.cargo}</td>
				<td>${funcionario.situacao == true ? 'ativo' : 'inativo' }</td>
				<td>&nbsp;</td>
			</tr>
			</c:forEach>
		</table>
	</div>
		
</div>

<tag:paginacao 	totalPaginas="${funcionarios.totalPage}" 
				link="${linkTo[FuncionarioController].lista}?nome=${nome}&ordem=${ordem}&pagina=#" 
				paginaAtual="${funcionarios.pageNum}" />

<content tag="breadcrumb">
	<div class="small-nav">
		<a href="${pageContext.request.contextPath}/funcionario/novo">Cadastrar funcionário</a>
	</div>
</content>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$("select, input").uniform();
	}); 
	</script>

</content>
