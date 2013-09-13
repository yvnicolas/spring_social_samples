<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sign In</title>
	</head>
	<body>
	Bonjour tout le monde
		  <ul class="right">
			<li class="has-form"><a id="login" href="<c:url value='/signinconfirm'/>" class="button">Se connecter</a></li>
			<li class="has-form"><a id="signin" href="<c:url value='/signinconfirm'/>" class="button">S'inscrire</a></li>
		</ul>
		
	</body>
</html>
