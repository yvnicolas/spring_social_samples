<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ca Marche</title>
</head>
<body>
	Bienvenue ${nom}!
	<form action="<c:url value="/" />">
		<button type="submit">continuer</button>
	</form>
</body>
</html>
