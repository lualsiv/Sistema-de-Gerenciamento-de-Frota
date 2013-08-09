<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><decorator:title default="Gerenciamento de Frota"/></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/all.min.css"/>
    <decorator:head/>
  </head>
  <body>

	<header>
		<nav>
			<a href="/veiculo/novo">Novo veiculo</a>
			<a href="/veiculo">Lista de veiculos</a>
		</nav>
	</header>

	<decorator:body/>
  	
  	<footer>
  	</footer>
  	
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascripts/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascripts/application.js"></script>
  </body>
</html>
