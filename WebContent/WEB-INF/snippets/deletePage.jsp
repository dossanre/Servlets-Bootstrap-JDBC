<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="idel" type="testpack.Item" scope="request"/>
<br>
<%@ taglib tagdir="/WEB-INF/tags" prefix="simple" %>
	<h2>Do you want to delete the item?<simple:productTag color="purple" size="3"> ${idel.name} </simple:productTag></h2>
	<form method=post>
		<input type=hidden  name=iid value="${idel.id}">
		<input  type=submit value=Yes>
	</form>
	<br>
	<form action=Home>
		<input type=submit value=No>
	</form>



