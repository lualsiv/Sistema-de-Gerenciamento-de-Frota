<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo motorista</h2>
	</div>

<form action="${linkTo[MotoristaController].salva}" method="post" name="form_motorista" id="form_motorista">
	
	<div class="coluna">
		<label for="motorista.funcionario.cadastro">Cadastro do funcionário:</label>
		<input type="text" id="motorista.funcionario.cadastro" class="cadastro" name="motorista.funcionario.cadastro" value="${motorista.funcionario.cadastro}" />
	</div>
	
	<div class="coluna">
		 <div id="texto"></div>
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
	
	<div class="coluna">
		<label for="motorista.observacao">Observação:</label>
		<textarea name="motorista.observacao" rows="10" cols="40">${motorista.observacao}</textarea>
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

	<script type="text/javascript">
	$(document).ready(function(){
        $(".cadastro").keyup(function () {
            var $input = $(this);
            if ($input.val() != "") {
            	$.ajax({
                    type: "GET",
                    dataType  : 'json',
                    data: { cadastro: $input.val() },
                    url: '${pageContext.request.contextPath}/funcionario/busca.json',
					success: function (data) {
                        $("#texto").html(data.funcionario.nome);
                    },
                    error: function () {
                    	$("#texto").html("");
                    }
                });
            } else {
                $("#texto").html("");
            }
        });
    });
	</script>
	
</content>