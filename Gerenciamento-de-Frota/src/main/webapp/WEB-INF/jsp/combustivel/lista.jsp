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
		<c:forEach items="${combustivelList}" var="veiculo">
		<tr>
			<td>${veiculo.descricao}</td>
			<td>${veiculo.valor}</td>
			<td>&nbsp;</td>
		</tr>
		</c:forEach>
	</table>
</div>

</div>
