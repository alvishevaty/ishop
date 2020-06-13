<%@ page language="java" import="by.home.project.bean.User"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MainPage</h1>

	<a href=""><label>Profile</label></a>

	<c:set var="registrationStatus" scope="session"
		value="${requestScope.registrationStatus}" />

	<c:choose>
         
         <c:when test = "${registrationStatus == true}">
            <c:redirect context = "index.jsp"/>
         </c:when>
         
         <c:when test = "${registrationStatus == false}">
            <c:redirect context = "/Controller?command=gotoregistrationpage&message='User already exists'"/>
         </c:when>
         
      </c:choose>


	<h1>
		Hello,
		<c:out value="${requestScope.user.name}" />
	</h1>

</body>
</html>