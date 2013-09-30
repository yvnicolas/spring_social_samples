<%@page import="org.springframework.social.quickstart.config.Uris"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h3>Bienvenue ${nom}!</h3>
	<ul>
		<li><a href="<c:url value="<%=Uris.SIGNOUT%>" />">
				Application Sign Out</a></li>
		<%-- 		<li><a href="<c:url value="<%=Uris.PARTIALSIGNOUT%>" />"> Disconnect from current service provider</a></li> --%>
	</ul>

	<%-- 	<%@ include file="serviceproviderChoice.jsp"%> --%>
	<!-- 	<h3>Your Facebook Friends</h3> -->
	<!-- 	<ul> -->
	<%-- 	<c:forEach items="${friends}" var="friend"> --%>
	<%-- 		<li><img src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture" align="middle"/><c:out value="${friend.name}"/></li> --%>
	<%-- 	</c:forEach> --%>
	<!-- 	</ul>	 -->
	<h3>
		Showing available Service Providers :
		<c:out value="${serviceProvider}" />
	</h3>
	<ul>
		<c:forEach items="${serviceProviders}" var="sp">
			<li><c:out value="${sp.name}" /> <c:if test="${sp.connected}"> : Connected with Permissions : <c:out
						value="${sp.permissions}" />
					<form action="<c:url value="<%=Uris.DISCONNECT%>" />" method="POST">
						<button type="submit">Disconnect</button>
						<input type="hidden" name="sp" value="${sp.name}" />
					</form>
				</c:if> 
				<c:if test="${!sp.connected}"> : Disconnected  
				<form action="<c:url value="${sp.URL }" />" method="POST">
                        <button type="submit">Connect</button>
                        <input type="hidden" name="scope" value="${sp.permissions}" />
                    </form></c:if></li>
		</c:forEach>
	</ul>
</body>
</html>

