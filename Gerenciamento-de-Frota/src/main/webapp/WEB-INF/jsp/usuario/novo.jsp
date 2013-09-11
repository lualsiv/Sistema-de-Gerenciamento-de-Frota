<div class="box">
	<div class="box-head">
		<h2 class="left">Cadastrar novo usuário</h2>
	</div>

<form action="${linkTo[UsuarioController].novo}" method="post" name="form_usuario" id="form_usuario">

	<div class="coluna">
		<label for="usuario.funcionario.cadastro">Cadastro do funcionário:</label>
		<input type="text" id="usuario.funcionario.cadastro" class="cadastro" name="usuario.funcionario.cadastro" value="${usuario.funcionario.cadastro}" />
	</div>
	
	<div class="coluna">
		 <div id="texto"></div>
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
		<tag:enumtocombobox name="usuario.perfil" valor="${usuario.perfil}" />
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