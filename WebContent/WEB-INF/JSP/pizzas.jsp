<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html lang="nl">
<head>
<vdab:head title='pizzas'/>
</head>
<body>
<vdab:menu/>
	<h1>
		Pizza"s
		<c:forEach begin="1" end="5">
			&#9733; <%-- de HTML code van een ster --%>
		</c:forEach>
	</h1>
	<ul class="zebra">
		<c:forEach var='pizza' items='${pizzas}'>
			<li>${pizza.id}:
				<c:out value='${pizza.naam}'/> ${pizza.prijs} &euro;
				${pizza.pikant ? "pikant" : "niet pikant"}
				<c:url value='/pizzas/detail.htm' var='detailURL'>
				<c:param name='id' value="${pizza.id}"/>
				</c:url>
				<a href="<c:out value='${detailURL}'/>">Detail</a>
				<c:if test='${pizzaIdsMetFoto.contains(pizza.id)}'>
					<c:url value='/pizzafotos/${pizza.id}.jpg' var='fotoURL'/>
					<a href='${fotoURL}'>Foto</a>
				</c:if>
			</li>
		</c:forEach>
		<%-- 
		<c:forEach var="entry" items="${pizzas}">
			<li>
				${entry.key}: <c:out value="${entry.value.naam}"/>  ${entry.value.prijs}&euro;
				<%-- <c:if test="${entry.value.pikant}">pikant</c:if> --%>
				<%-- <c:choose>
					<c:when test="${entry.value.pikant}">pikant</c:when>
					<c:otherwise>niet pikant</c:otherwise>
				</c:choose>
				--%><%-- 
				${entry.value.pikant ? "pikant":"niet pikant"}	
				<c:url value="/pizzas/detail.htm" var="detailURL">
					<c:param name="id" value="${entry.key}"/>
				</c:url>
				<a href="${detailURL}">Detail</a>		
			</li>
		</c:forEach>
		--%>
	</ul>
	<div>context param(initParam.aantalKinderen) aantal kinderen : ${initParam.aantalKinderen}</div>
</body>
</html>