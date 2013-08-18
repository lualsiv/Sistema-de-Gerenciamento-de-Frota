<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<div class="box">
	<div class="box-head">
		<h2 class="left">Editar informações do veículo</h2>
	</div>
	
	<form action="${pageContext.request.contextPath}/veiculo/${veiculo.id}" method="post" name="form_veiculo" id="form_veiculo">
		<c:import url="_form.jsp" />
		<input type="hidden" name="_method" value="put" />
		
		<br />
		<input type="submit" value="Salvar" />
	</form>
	
</div>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$('#form_veiculo').validate({
		    rules: {
		    	"veiculo.descricao": {
	                required: true
	            },
	            "veiculo.marca": {
	                required: true
	            },
	            "veiculo.anoModelo": {
	                required: true,
	                digits: true
	            },
				"veiculo.anoFabricacao": {
	                required: true,
	                digits: true
	            }
	        },
			messages: {
				"veiculo.descricao": {
					required: "O campo descrição é obrigatorio."
	           },
	           "veiculo.marca": {
	                required: "O campo marca é obrigatorio."
	           },
	           "veiculo.anoModelo": {
	                required: "O campo ano do modelo é obrigatorio.",
	                digits: "Número inválido"
	           },
	           "veiculo.anoFabricacao": {
	                required: "O campo ano de fabricação é obrigatorio.",
	                digits: "Número inválido"
	           }
	       }
		});
		
		$("select, input").uniform();
	}); 
	</script>
	
</content>