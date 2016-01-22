<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html lang='nl'>
<head>
	<vdab:head title='headers'/>
</head>
<body>
	<vdab:menu/>
	Je browser: ${browser}
	
	<dl>
		<c:forEach var='h' items='${headers}'>
		<dt>${h.key}</dt><dd>${h.value}</dd>
		</c:forEach>
	</dl>
</body>
</html>