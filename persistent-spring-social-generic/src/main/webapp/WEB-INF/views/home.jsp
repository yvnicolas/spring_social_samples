<%@page import="org.springframework.social.quickstart.config.Uris"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
	<ul>
		<li><a href="<c:url value="<%=Uris.SIGNOUT%>" />"> Application Sign Out</a></li>
		<li><a href="<c:url value="<%=Uris.PARTIALSIGNOUT%>" />"> Disconnect from current service provider</a></li>
	</ul>
	
	<%@ include file="serviceproviderChoice.jsp"%>
<!-- 	<h3>Your Facebook Friends</h3> -->
<!-- 	<ul> -->
<%-- 	<c:forEach items="${friends}" var="friend"> --%>
<%-- 		<li><img src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture" align="middle"/><c:out value="${friend.name}"/></li> --%>
<%-- 	</c:forEach> --%>
<!-- 	</ul>	 -->
   <h3>Showing your Connections : <c:out value="${serviceProvider}" /> </h3>
    <ul>
    <c:forEach items="${connections}" var="connection">
        <li><c:out value="${connection.firstName}"/> <c:out value="${connection.lastName}"/></li>
    </c:forEach>
    </ul>
	</body>
</html>