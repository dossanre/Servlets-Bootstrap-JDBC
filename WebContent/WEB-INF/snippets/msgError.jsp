<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://something.com/iwnat/this" prefix="myJspTag" %>
                
	<div align=center>
		<myJspTag:mytags color="red" size="2"> ${param.msg}</myJspTag:mytags>
	</div>