<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo funcionário</h2>
	</div>

<form action="${linkTo[FuncionarioController].novo}" method="post" name="form_funcionario" id="form_funcionario">
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

	<div class="coluna">
		<label for="funcionario.cargo">Cargo:</label> 
		<input type="text" name="funcionario.cargo" value="${funcionario.cargo}" />
	</div>
		
	<div class="coluna">
		<label for="funcionario.dataAdmissao">Data de admissão:</label>
		<joda:format value="${funcionario.dataAdmissao}" pattern="dd/MM/yyyy HH:mm" var="dataAdmissao" />
		<input type="text" name="funcionario.dataAdmissao" class="datetimepicker" value="${dataAdmissao}" />
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
	            },
	            "funcionario.cargo": {
	                required: true
	            }
	        },
	        messages: {
	            "funcionario.cadastro": {
	                required: "O campo cadastro é obrigatorio."
	           },
	           "funcionario.nome": {
	                required: "O campo nome é obrigatorio."
	           },
	           "funcionario.cargo": {
	                required: "O campo cargo é obrigatorio."
	           }
	       }
		});
		
		$("select, input").uniform();
		
	}); 
	</script>
	
	<tag:datetimepicker id=".datetimepicker" />
		
</content>