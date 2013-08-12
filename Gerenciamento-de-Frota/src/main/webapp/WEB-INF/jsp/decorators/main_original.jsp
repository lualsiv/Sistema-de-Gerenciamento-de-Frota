<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><decorator:title default="Sistema de Gerenciamento de Frota"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/all.min.css"/>
	
	<decorator:head/>
</head>

<body>

	<header>
		<h1>Sistema de Gerenciamento de Frota</h1>
	</header>
	
	<nav>
		<a href="${pageContext.request.contextPath}/veiculo/novo">Novo veiculo</a>
		<a href="${pageContext.request.contextPath}/veiculo">Lista de veiculos</a>
	</nav>
	
	<aside class="box box-small">
		<div class="box-header">
			<h3 class="box-title">Menu secundario</h3>
		</div>
		<div class="box-body">
			<ul>
				<li>1</li>
				<li>2</li>
				<li>3</li>
			</ul>
		</div>
	</aside>
	
	<section id="content">
		<decorator:body/>
	</section>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascripts/all.min.js"></script>

	<decorator:getProperty property="page.scripts"/>
	
  	<footer>
    	<p>This footer contains the declaration position:relative; to give Internet Explorer 6 hasLayout for the footer and cause it to clear correctly. If you're not required to support IE6, you may remove it.</p>
  	</footer>

</body>
</html>
