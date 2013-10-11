<title>Consulta mais longa</title>

<c:forEach items="${queries}" var="query">
	<p>${query}</p>
</c:forEach>

<p><strong>Consulta mais longa</strong></p>
<p>${consultamaislonga}</p>

<p><strong>Connect count</strong></p>
<p>${connectCount}</p>

<p><strong>Query execution max time</strong></p>
<p>${queryExecutionMaxTime}</p>

<p><strong>Entity names</strong></p>
<c:forEach items="${entityNames}" var="enames">
	<p>${enames}</p>
</c:forEach>

<p><strong>Second level cache hit count</strong></p>
<p>${secondLevelCacheHitCount}</p>
