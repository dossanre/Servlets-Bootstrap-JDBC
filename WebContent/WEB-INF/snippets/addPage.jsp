<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<form method=post>
	<table class="center">
		<tr><td colspan=2><h2>Add Item</h2></td></tr>
		<tr>
			<td >Item: </td>
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<td>	
				<select name=name>
					<c:forEach var="itemsProd" items="${itemsProd}">
				  		<option value="${itemsProd}">${itemsProd}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td align=left>Quantity: </td>
			<td>
				<img border="0" src="images/less.png" width="20" height="20" onclick="myDecrease()">
				<input type=number name=qty id="number" value=1 readonly>
				<img border="0" src="images/plus.png" width="20" height="20" onclick="myIncrease()">
			</td>
		</tr>
		<tr>
			<td colspan=2><input type=submit value=Save></td>
		</tr>
	</table>
</form>
