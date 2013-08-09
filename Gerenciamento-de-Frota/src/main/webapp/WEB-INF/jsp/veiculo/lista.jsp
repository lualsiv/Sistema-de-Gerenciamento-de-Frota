<br />

<div class="box box-small">
	<div class="box-header">
		<h3 class="box-title">Lista de Veiculos</h3>
	</div>
	<div class="box-body">
		<ul>
			<c:forEach items="${veiculoList}" var="veiculo">
				<li>
					${veiculo.placa}
					
					<!--  <a href="veiculo/${veiculo.id}/editar">editar</a>  -->
					
					<form action="<c:url value='/veiculo/${veiculo.id}'/>" method="post">
						<input type="hidden" name="veiculo.id" value="${veiculo.id}" />
						<input type="hidden" name="_method" value="put" />
						<input type="submit" value="editar" class="submitLink" />
					</form>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
