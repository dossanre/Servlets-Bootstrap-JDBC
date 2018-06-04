<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<form method=post>
	<table class="center">
		<tr><td colspan=2><h2>Password Form</h2></td></tr>
		<tr>
			<td align=left>Old Password: </td>
			<td><input type=password name=oldpass required></td>
		</tr>
		<tr>
			<td align=left>New Password: </td>
			<td><input type=password name=newpass required></td>
		</tr>
		<tr>
			<td align=left>Confirm Password: </td>
			<td><input type=password name=confpass required></td>
		</tr>
		<tr>
			<td colspan=2><input type=submit value=Save></td>
		</tr>
	</table>
</form>
