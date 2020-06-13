<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="local" var="loc" />

	<fmt:message bundle="${loc}" key="local.registration.label.name" var="registration_name_label" />
	<fmt:message bundle="${loc}" key="local.registration.label.surname" var="registration_surname_label" />
	<fmt:message bundle="${loc}" key="local.registration.label.phoneNumber" var="registration_phoneNumber_label" />
	<fmt:message bundle="${loc}" key="local.label.email" var="registration_email_label" />
	<fmt:message bundle="${loc}" key="local.label.password" var="registration_password_label" />
	<fmt:message bundle="${loc}" key="local.registration.button" var="registration_button" />
	<fmt:message bundle="${loc}" key="local.registration.message.error" var="registration_error_message" />

</head>
<body>

	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>

	<main>
		<div class="formcontainer">

			<div class="registrationContainer">

				<form action="Controller" method="get">

					<span class="title"> ${registration_button} </span> 
					
					<input type="hidden" name="command" value="registration" /> 
						<label for="name">${registration_name_label}: </label><br /> 
					<input type="text" name="name" value="" /><br /> 
						<label for="surname">${registration_surname_label}: </label><br /> 
					<input	type="text" name="surname" value="" /><br /> 
						<label for="phoneNumber">${registration_phoneNumber_label}: </label> <br />
					<input type="text" name="phoneNumber" value="" maxlength="19"
						placeholder="+375 (__) ___-__-__"> <br /> 
						<label for="email">${registration_email_label}: </label><br />
					<input type="text" name="email" value="" /><br /> 
						<label for="password">${registration_password_label}: </label><br /> 
					<input type="password" name="password" value="" /><br />
					<br /> 
					
					<input class="button" type="submit" value="${registration_button}" /><br />

				</form>

				<br>

				<c:if test="${param.registration == 'error'}">
					<span class="errorMessage">${registration_error_message}</span>
				</c:if>


			</div>
		</div>
	</main>
</body>
</html>