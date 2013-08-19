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

<form action="${linkTo[UsuarioController].salva}" method="post" name="form_usuario" id="form_usuario">
	
	<input type="hidden" name="usuario.funcionario.id" value="${funcionario.id}" />

	<div class="coluna">
		<label for="funcionario.nome">Funcionário:</label>
		<input type="text" id="funcionario.nome" name="funcionario.nome" value="${funcionario.nome}" disabled="disabled" />
	</div>
	
	<div class="coluna">
		<label for="funcionario.cargo">Cargo:</label>
		<input type="text" id="funcionario.cargo" name="funcionario.cargo" value="${funcionario.cargo}" disabled="disabled" />
	</div>
	
	<div class="separator">&nbsp;</div>
	
	<div class="coluna">
		<label for="usuario.login">Login:</label>
		<input type="text" name="usuario.login" value="${usuario.login}" />
	</div>
	
	<div class="coluna">
		<label for="usuario.senha">Senha:</label>
		<input type="password" name="usuario.senha" value="${usuario.senha}" />
	</div>
	
	<div class="separator">&nbsp;</div>

	<div class="coluna">
		<label for="usuario.perfil">Perfil:</label>
		<select name="usuario.perfil">
			<option value="CONSULTA" ${usuario.perfil == 'CONSULTA' ? 'selected' : ''}>consulta</option>
			<option value="USUARIO" ${usuario.perfil == 'USUARIO' ? 'selected' : ''}>usuário</option>
			<option value="ADMINISTRADOR" ${usuario.perfil == 'ADMINISTRADOR' ? 'selected' : ''}>administrador</option>
		</select>
	</div>
	
	<div class="separator">&nbsp;</div>
	
	<br />
	<input type="submit" value="Salvar" />
</form>

</div>

<content tag="scripts">

	<script type="text/javascript">
	$(document).ready(function(){
		$("select, input").uniform();
	}); 
	</script>
	
</content>