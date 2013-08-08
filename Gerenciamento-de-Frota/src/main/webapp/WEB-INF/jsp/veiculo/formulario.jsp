<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<div class="box box-small">
	<div class="box-header">
		<h3 class="box-title">Formulário de cadastro de veiculos</h3>
	</div>
	<div class="box-body">
		<form action="${linkTo[VeiculoController].salva }" method="post">
				<input type="hidden" name="veiculo.id" value="${veiculo.id}"/>
				
				<li>Marca: <br/>
				<input type="text" name="veiculo.marca" value="${veiculo.marca}"/></li>
				
				<li>Modelo: <br/>
				<input type="text" name="veiculo.modelo" value="${veiculo.modelo}"/></li>
				
				<li>Chassi: <br/>
				<input type="text" name="veiculo.chassi" value="${veiculo.chassi}"/></li>
				
				<li>Placa: <br/>
				<input type="text" name="veiculo.placa" value="${veiculo.placa}"/></li>
			
				<li>Ano do modelo: <br/>
				<input type="text" name="veiculo.anoModelo" value="${veiculo.anoModelo}"/></li>
			
				<li>Ano de fabricação: <br/>
				<input type="text" name="veiculo.anoFabricacao" value="${veiculo.anoFabricacao}"/></li>
				
			<input type="submit" value="Salvar" />
		</form>
	</div>
</div>
