<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<jsp:useBean id="iView" type="testpack.Item" scope="request"/>
<form method=post>
	<table class="center">
		<tr>
			<td colspan=2>
				<h2>View Item</h2>
			</td>
		</tr>
		<tr>
			<td align=left>Item: </td>
			<td>
				<input type=text value="${iView.name}" readonly>
			</td>
		</tr>
		<tr>
			<td align=left>Quantity: </td>
			<td><input type=text value="${iView.qty}" readonly></td>
		</tr>
	</table>
</form>
