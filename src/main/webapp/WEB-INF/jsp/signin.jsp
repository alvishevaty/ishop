<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Authorization</title>
		
		<link rel="stylesheet" href="style.css">

		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="local" var="loc" />

		<fmt:message bundle="${loc}" key="local.signin.message.welcome" var="welcome_message" />
		<fmt:message bundle="${loc}" key="local.signin.message.error" var="signin_error_message" />
		<fmt:message bundle="${loc}" key="local.label.email" var="email_label" />
		<fmt:message bundle="${loc}" key="local.label.password" var="password_label" />
		<fmt:message bundle="${loc}" key="local.enter.button" var="enter_button" />
		<fmt:message bundle="${loc}" key="local.registration.button" var="registration_button" />
		
		

	</head>
	<body>
	
		<header>
     		<jsp:include page="/WEB-INF/jsp/header.jsp" />
  	 	</header>
  	 	
  	 	<main>
  	 		<div class="formcontainer">

				<div class="signinContainer">
				
					<form class=loginForm action="Controller" method="get">
						<span class="title">${welcome_message}</span>
						
						<input type="hidden" name="command" value="signin" /> 
				
						<span class="email"><c:out value="${email_label}: " /></span><br /> 
						<input class="input1" type="email" name="email" value="" /><br /> 
				
						<span class="password"><c:out value="${password_label}: " /></span><br /> 
						<input class="input1" type="password" name="password" value="" /><br /> 
						<br /> 
				
						<input class="button" type="submit" value="${enter_button}" />
					</form>
	
	
					<form action="Controller" method="get">
						<input type="hidden" name="command" value="gotoregistrationpage" /> 
						<input class="button" type="submit" value="${registration_button}" />
					</form>
					
					<br>
					
					<c:if test="${param.signin == 'error'}">
	           			<span class="errorMessage">${signin_error_message}</span>
	       			</c:if>
				
				</div>
			</div>
		</main>
		
	</body>
	
</html>