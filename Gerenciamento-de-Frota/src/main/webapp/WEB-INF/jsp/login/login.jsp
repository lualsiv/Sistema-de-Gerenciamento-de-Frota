<div class="box">
	<div class="box-head">
		<h2 class="left">Entrar no sistema</h2>
	</div>

<form action="${linkTo[LoginController].login}" method="post" name="form_login" id="form_login">
	
	<div class="coluna">
		<label for="usuario.login">Login:</label> 
		<input type="text" name="usuario.login" value="" />
	</div>

	<div class="coluna">
		<label for="usuario.senha">Senha:</label> 
		<input type="password" name="usuario.senha" value="" />
	</div>

	<div class="separator">&nbsp;</div>

	<br />
	<input type="submit" value="Salvar" />
</form>

</div>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$('#form_login').validate({
		    rules: {
		    	"usuario.login": {
	                required: true
	            },
	            "usuario.senha": {
	                required: true
	            }
	        },
	        messages: {
	            "usuario.login": {
	                required: "O campo login é obrigatorio."
	           },
	           "usuario.senha": {
	                required: "O campo senha é obrigatorio."
	           }
	       }
		});
		
		$("select, input").uniform();
	}); 
	</script>

</content>