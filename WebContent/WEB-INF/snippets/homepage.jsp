<%@ page import="testpack.Item,java.util.ArrayList" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<table class="center" width="70%">
	<tr>
	<%@ taglib tagdir="/WEB-INF/tags" prefix="simple" %>
				
		<td align=left id="username">
			<simple:greetingTag color="green" size="2">Welcome: ${name}</simple:greetingTag>
		</td>
		<td align=right>
			<a href="EditAccount" class="viewl">Edit Account</a> - 
			<a href="ChangePassword" class="viewl">Change Password</a> - 
			<a href="Logout" class="viewl">LogOut</a>
		</td>
	</tr>
	<div class="msgError"><jsp:include page="/WEB-INF/snippets/msgError.jsp"/></div>
	<tr>
		<td colspan=2 align=center>
			<h2>List of Items</h2>
			
			<table class="center">
				<tr><th>Actions</th><th>Item Name</th><th>Quantity</th></tr>
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				<c:forEach var="item" items="${allItems}">
							<tr>
								<td>
									<a href="ViewItem?id=${item.id}">
										<img border="0" src="images/View.png" width="20" height="20">
									</a> 
									<a href="EditItem?id=${item.id}">
										<img border="0" src="images/Edit.png" width="20" height="20">
									</a> 								
									<a href="DeleteItem?id=${item.id}&name=${item.name}">
										<img border="0" src="images/delete.png" width="20" height="20">
									</a>
								</td>
								<td>
									<a href="ViewItem?id=${item.id}" class="viewl">${item.name}</a>
								</td>
								<td id="qty">${item.qty}</td>
							</tr>
				</c:forEach>
			</table>
			<br>
			<a href="AddItem" class="viewl">Add a new item</a> 	
		</td>
		
	</tr>
</table>