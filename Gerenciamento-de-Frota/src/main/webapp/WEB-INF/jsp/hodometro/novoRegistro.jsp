<content tag="breadcrumb">
	<div class="small-nav">
		<c:if test="${errors != null}">
			<div class="notice-error">
				<c:forEach items="${errors}" var="error">
					<p>${error.category}: <!-- o campo em que ocorreu o erro, ou o tipo do erro -->
						${error.message} <!-- a mensagem de erro de validação -->
					</p>
				</c:forEach>
			</div>
		</c:if>
	</div>
</content>

<div class="box">
	<div class="box-head">
		<h2 class="left">Registrar nova quilometragem</h2>
	</div>

<form action="${linkTo[HodometroController].novoRegistro}" method="post" name="form_quilometragem" id="form_quilometragem">

	<div class="coluna">
		<label for="hodometro.veiculo.placa">Placa:</label> 
		<input type="text" name="hodometro.veiculo.placa" value="${hodometro.veiculo.placa}" />
	</div>
	
	<div class="coluna">
		<label for="hodometro.dataLeitura">Data da leitura: </label>
		<input type="text" name="hodometro.dataLeitura" value="${hodometro.dataLeitura}" />
	</div>
	
	<div class="separator">&nbsp;</div>
		
	<div class="coluna">
		<label for="hodometro.quilometragem">Quilometro: </label> 
		<input type="text" name="hodometro.quilometragem" value="" />
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