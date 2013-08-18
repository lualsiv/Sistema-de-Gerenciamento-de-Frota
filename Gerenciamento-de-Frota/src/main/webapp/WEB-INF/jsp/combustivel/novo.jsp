<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo combustível</h2>
	</div>

<form action="${linkTo[CombustivelController].salva}" method="post" name="form_combustivel" id="form_combustivel">
	<input type="hidden" name="combustivel.id" value="${combustivel.id}" />
	
	<div class="coluna">
		<label for="combustivel.descricao">Descrição:</label> 
		<input type="text" name="combustivel.descricao" value="${combustivel.descricao}" />
	</div>

	<div class="coluna">
		<label for="combustivel.preco">Preço:</label> 
		<input type="text" name="combustivel.preco" value="${combustivel.preco}" />
	</div>

	<div class="separator">&nbsp;</div>

	<br />
	<input type="submit" value="Salvar" />
</form>

</div>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$('#form_combustivel').validate({
		    rules: {
		    	"combustivel.descricao": {
	                required: true
	            },
	            "combustivel.preco": {
	                required: true
	            }
	        },
	        messages: {
	            "combustivel.descricao": {
	                required: "O campo descrição é obrigatorio."
	           },
	           "combustivel.preco": {
	                required: "O campo preço é obrigatorio."
	           }
	       }
		});
		
		$("select, input").uniform();
	}); 
	</script>

</content>