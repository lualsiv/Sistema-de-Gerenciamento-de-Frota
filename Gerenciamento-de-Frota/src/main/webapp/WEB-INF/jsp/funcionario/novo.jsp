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

	<div class="coluna">
		<label for="funcionario.cargo">Cargo:</label> 
		<input type="text" name="funcionario.cargo" value="${funcionario.cargo}" />
	</div>
	
	<div class="coluna">
		<label for="funcionario.dataAdmissao">Data de admissão:</label> 
		<input type="text" name="funcionario.dataAdmissao" class="datetimepicker" value='<joda:format value="${funcionario.dataAdmissao}"/>' />
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
		
// 		$("select, input").uniform();
		
	}); 
	</script>
	
	<script type="text/javascript">
	$(function() {
		$( ".datepicker" ).datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
	
		$(".datetimepicker").datetimepicker();
		
	});
	</script>
	
</content>
