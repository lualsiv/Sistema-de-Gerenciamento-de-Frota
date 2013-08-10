<br />

<div class="box box-small">
	<div class="box-header">
		<h3 class="box-title">Lista de Veiculos</h3>
	</div>
	<div class="box-body">
		<ul>
			<table border="1" width="95%" align="center">
				<tr>
					<td>Descricao</td>
					<td></td>
				</tr>
				<c:forEach items="${veiculoList}" var="veiculo">
				<tr>
					<td>
						${veiculo.placa}
					</td>
					<td>
						<form action="<c:url value='/veiculo/${veiculo.id}'/>" method="post">
							<input type="hidden" name="veiculo.id" value="${veiculo.id}" />
							<input type="hidden" name="_method" value="put" />
							<input type="submit" value="editar" class="submitLink" />
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
		</ul>
	</div>
</div>

<content tag="scripts">
	<script type="text/javascript">
		$(document).ready(function(){
			$("select, input, a.button, button").uniform();
		}); 
	</script>
</content>