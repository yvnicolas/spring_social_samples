<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Signin Forme</title>

</head>
<body>

	<p>Please Signin</p>

	<form action="/persistent-spring-social/inscription" method="POST">

		<table>
			<tr>
				<td>Id:</td>
				<td><input type='text' name="id" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
