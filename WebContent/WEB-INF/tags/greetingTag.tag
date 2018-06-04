<%@ attribute name="size" required="true" %>
<%@ attribute name="color" required="false" %>
<!--  jsp Tag -->
<h1 style="color:${color}; font-size:${size}em;"> <jsp:doBody/> </h1>
