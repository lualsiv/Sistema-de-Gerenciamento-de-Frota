<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<!-- Box -->
<div class="box">
	<!-- Box Head -->
	<div class="box-head">
		<h2 class="left">Cadastro/edição de veiculos</h2>
	</div>
	<!-- End Box Head -->	

<form action="${linkTo[VeiculoController].salva}" method="post" name="form_veiculo" id="form_veiculo">
		<input type="hidden" name="veiculo.id" value="${veiculo.id}"/>

		<label for="veiculo.descricao">Descrição:</label>
		<input type="text" name="veiculo.descricao" value="${veiculo.descricao}"/></li>

		<label for="veiculo.placa">Placa:</label>
		<input type="text" name="veiculo.placa" value="${veiculo.placa}"/></li>
						
		<label for="veiculo.marca">Marca:</label>
		<input type="text" id="veiculo.marca" name="veiculo.marca" value="${veiculo.marca}"/></li>
		
		<br />
		<label for="veiculo.modelo">Modelo:</label>
		<input type="text" name="veiculo.modelo" value="${veiculo.modelo}"/></li>
		
		<label for="veiculo.anoModelo">Ano do modelo:</label>
		<input type="text" name="veiculo.anoModelo" value="${veiculo.anoModelo}"/></li>
	
		<label for="veiculo.anoFabricacao">Ano de fabricação:</label>
		<input type="text" name="veiculo.anoFabricacao" value="${veiculo.anoFabricacao}"/></li>

		<br />				
		<label for="veiculo.cor">Cor:</label>
		<input type="text" name="veiculo.cor" value="${veiculo.cor}"/></li>
		
		<label for="veiculo.chassi">Chassi:</label>
		<input type="text" name="veiculo.chassi" value="${veiculo.chassi}"/></li>
		
		<label for="veiculo.renavam">Renavam:</label>
		<input type="text" name="veiculo.renavam" value="${veiculo.renavam}"/></li>
	
		<br />
		<label for="veiculo.observacao">Observação:</label>
		<textarea name="veiculo.observacao" rows="10" cols="40">${veiculo.observacao}</textarea>
		
	<input type="submit" value="Salvar" />
</form>

</div>
<!-- End Box -->

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
		
		$("select, input").uniform();
	}); 
	</script>

</content>