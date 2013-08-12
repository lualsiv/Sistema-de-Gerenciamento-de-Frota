<!-- Box -->
<div class="box">
	<!-- Box Head -->
	<div class="box-head">
		<h2 class="left">Lisa de veiculos</h2>
	</div>
	<!-- End Box Head -->	

<!-- Table -->
<div class="table">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<th>Descricao</th>
			<th>Placa</th>
			<th>Opções</th>
		</tr>
		<c:forEach items="${veiculoList}" var="veiculo">
		<tr>
			<td>${veiculo.descricao}</td>
			<td>${veiculo.placa}</td>
			<td>
				<form action="<c:url value='/veiculo/${veiculo.id}'/>" method="post">
					<input type="hidden" name="veiculo.id" value="${veiculo.id}" />
					<input type="hidden" name="_method" value="put" />
					<input type="submit" value="editar" class="submitLink ico edit" />
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>
<!-- Table -->

</div>
<!-- End Box -->