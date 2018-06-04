<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<form method=post>
	<table class="center">
		<tr><td colspan=2><h2>Account Maintenance</h2></td></tr>
		<tr>
			<td align=left>Item: </td>
			<td><input type=text name=name value="${name}" required></td>
		</tr>
		<tr>
			<td colspan=2><input type=submit value=Save></td>
		</tr>
	</table>
</form>
