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
		<form action="${linkTo[VeiculoController].salva}" method="post" name="form_veiculo" id="form_veiculo">
				<input type="hidden" name="veiculo.id" value="${veiculo.id}"/>

				<li>Descrição: <br/>
				<input type="text" name="veiculo.descricao" value="${veiculo.descricao}"/></li>

				<li>Placa: <br/>
				<input type="text" name="veiculo.placa" value="${veiculo.placa}"/></li>
								
				<li>Marca: <br/>
				<input type="text" id="veiculo.marca" name="veiculo.marca" value="${veiculo.marca}"/></li>
				
				<li>Modelo: <br/>
				<input type="text" name="veiculo.modelo" value="${veiculo.modelo}"/></li>
				
				<li>Ano do modelo: <br/>
				<input type="text" name="veiculo.anoModelo" value="${veiculo.anoModelo}"/></li>
			
				<li>Ano de fabricação: <br/>
				<input type="text" name="veiculo.anoFabricacao" value="${veiculo.anoFabricacao}"/></li>
				
				<li>Cor: <br/>
				<input type="text" name="veiculo.cor" value="${veiculo.cor}"/></li>
				
				<li>Chassi: <br/>
				<input type="text" name="veiculo.chassi" value="${veiculo.chassi}"/></li>

				<li>Renavam: <br/>
				<input type="text" name="veiculo.renavam" value="${veiculo.renavam}"/></li>
			
				<li>Observação: <br/>
				<textarea name="veiculo.observacao" rows="10" cols="40">${veiculo.observacao}</textarea>
				
			<input type="submit" value="Salvar" />
		</form>
	</div>
</div>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$('#form_veiculo').validate({
		    rules: {
		    	"veiculo.marca": {
	                required: true
	            }
	        },
	        messages: {
	            "veiculo.marca": {
	                required: "O campo marca é obrigatorio."
	           }
	       }
		});
		
		$("select, input, a.button, button").uniform();
	}); 
	</script>

</content>