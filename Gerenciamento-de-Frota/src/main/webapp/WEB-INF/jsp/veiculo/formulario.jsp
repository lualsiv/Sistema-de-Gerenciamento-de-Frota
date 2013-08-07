<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<form action="${linkTo[VeiculoController].salva }" method="post">
	<h2>Formulário de cadastro de veiculos</h2>
	
		<input type="hidden" name="veiculo.id" value="${veiculo.id}"/>
		
		<li>Placa: <br/>
		<input type="text" name="veiculo.placa" value="${veiculo.placa}"/></li>
	
		<li>Ano do modelo: <br/>
		<input type="text" name="veiculo.anoModelo" value="${veiculo.anoModelo}"/></li>
	
		<li>Ano de fabricação: <br/>
		<input type="text" name="veiculo.anoFabricacao" value="${veiculo.anoFabricacao}"/></li>
		
	<input type="submit" value="Salvar" />
</form>
