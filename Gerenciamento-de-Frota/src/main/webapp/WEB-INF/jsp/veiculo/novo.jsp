<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo veículo</h2>
	</div>

<form action="${linkTo[VeiculoController].salva}" method="post" name="form_veiculo" id="form_veiculo">
	<c:import url="_form.jsp" />
	
	<br />
	<input type="submit" value="Salvar" />
</form>

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
		
		$("select, input").uniform();
	}); 
	</script>

</content>