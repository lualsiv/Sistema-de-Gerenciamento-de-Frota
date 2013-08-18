<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo funcionário</h2>
	</div>

<form action="${linkTo[FuncionarioController].salva}" method="post" name="form_funcionario" id="form_funcionario">
	<input type="hidden" name="funcionario.id" value="${funcionario.id}" />
	
	<div class="coluna">
		<label for="funcionario.cadastro">Cadastro:</label> 
		<input type="text" name="funcionario.cadastro" value="${funcionario.cadastro}" />
	</div>

	<div class="coluna">
		<label for="funcionario.nome">Nome:</label> 
		<input type="text" name="funcionario.nome" value="${funcionario.nome}" />
	</div>

	<div class="separator">&nbsp;</div>

	<br />
	<input type="submit" value="Salvar" />
</form>

</div>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$('#form_funcionario').validate({
		    rules: {
		    	"funcionario.cadastro": {
	                required: true
	            },
	            "funcionario.nome": {
	                required: true
	            }
	        },
	        messages: {
	            "funcionario.cadastro": {
	                required: "O campo cadastro é obrigatorio."
	           },
	           "funcionario.nome": {
	                required: "O campo nome é obrigatorio."
	           }
	       }
		});
		
		$("select, input").uniform();
	}); 
	</script>

</content>