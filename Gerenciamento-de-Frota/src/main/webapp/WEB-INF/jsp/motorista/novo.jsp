<ul class="errors">
	<c:forEach items="${errors}" var="error">
		<li>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
			${error.message} <!-- a mensagem de erro de validação -->
		</li>
	</c:forEach>
</ul>

<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo motorista</h2>
	</div>

<form action="${linkTo[MotoristaController].salva}" method="post" name="form_motorista" id="form_motorista">
	
	<input type="hidden" name="motorista.funcionario.id" value="${funcionario.id}" />

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
		<label for="motorista.numero">Número:</label>
		<input type="text" name="motorista.numero" value="${motorista.numero}" />
	</div>
	
	<div class="coluna">
		<label for="motorista.registro">Registro:</label>
		<input type="text" name="motorista.registro" value="${motorista.registro}" />
	</div>
	
	<div class="separator">&nbsp;</div>
	
	<div class="coluna">
		<label for="motorista.categoria">Categoria:</label>
		<select name="motorista.categoria">
			<option value="A" ${motorista.categoria == 'A' ? 'selected' : ''}>A</option>
			<option value="B" ${motorista.categoria == 'B' ? 'selected' : ''}>B</option>
			<option value="AB" ${motorista.categoria == 'AB' ? 'selected' : ''}>AB</option>
		</select>
	</div>

	<div class="separator">&nbsp;</div>
	
	<label for="motorista.observacao">Observação:</label>
	<textarea name="motorista.observacao" rows="10" cols="40">${motorista.observacao}</textarea>
		
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