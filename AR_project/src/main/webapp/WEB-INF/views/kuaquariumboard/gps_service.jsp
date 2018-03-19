<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			
<c:set var="gps" value = "${gpsArray}"/>
<%
String convert = pageContext.getAttribute("gps").toString();
convert = convert.replaceAll("\\\\","");
out.print(convert);
%>
</body>
</html>
