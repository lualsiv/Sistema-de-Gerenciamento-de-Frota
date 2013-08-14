<!-- Box -->
<div class="box">
	<!-- Box Head -->
	<div class="box-head">
		<h2 class="left">Lista de veiculos</h2>
	</div>
	<!-- End Box Head -->	

<!-- Table -->
<div class="table">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th>Descrição</th>
			<th>Placa</th>
			<th>Opções</th>
		</tr>
		<c:forEach items="${veiculoList}" var="veiculo">
		<tr>
			<td>${veiculo.descricao}</td>
			<td>${veiculo.placa}</td>
			<td>
				<a href="${pageContext.request.contextPath}/veiculo/${veiculo.id}">editar</a>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<!-- Table -->

</div>
<!-- End Box -->