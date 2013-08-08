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
					<a href="veiculo/${veiculo.id}/editar">editar</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
