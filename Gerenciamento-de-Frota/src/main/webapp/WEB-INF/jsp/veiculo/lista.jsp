<h3>Lista de Veiculos</h3>

<ul>
	<c:forEach items="${veiculoList}" var="veiculo">
		<li>
			${veiculo.placa}
			<a href="veiculo/${veiculo.id}/editar">editar</a>
		</li>
	</c:forEach>
</ul>