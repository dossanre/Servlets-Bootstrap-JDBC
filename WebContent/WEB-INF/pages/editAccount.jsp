<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
		<link rel="stylesheet" type="text/css" href="css/styles.css">
	</head>
	<body>
		<div class="header"><jsp:include page="/WEB-INF/snippets/header.jsp"/></div>
		<div class="msgError"><jsp:include page="/WEB-INF/snippets/msgError.jsp"/></div>
		<div class="content"><jsp:include page="/WEB-INF/snippets/editAccountPage.jsp"/></div>
		<div class="content"><jsp:include page="/WEB-INF/snippets/back.jsp"/></div>
		<div class="footer"><jsp:include page="/WEB-INF/snippets/footer.jsp"/></div>
	</body>
</html>