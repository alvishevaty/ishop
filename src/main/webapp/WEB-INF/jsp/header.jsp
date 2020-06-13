<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		<link rel="stylesheet" href="style.css">
		
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
		<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
		<fmt:message bundle="${loc}" key="local.button.page.main" var="mainPage_button" />
		<fmt:message bundle="${loc}" key="local.button.page.signin" var="signinPage_button" />
		<fmt:message bundle="${loc}" key="local.button.page.signout" var="signoutPage_button" />
		<fmt:message bundle="${loc}" key="local.button.page.men" var="men_button" />
		<fmt:message bundle="${loc}" key="local.button.page.women" var="women_button" />
		<fmt:message bundle="${loc}" key="local.button.page.basket" var="basket_button" />
		<fmt:message bundle="${loc}" key="local.button.hi" var="hi_button" />
		
	</head>
	<body>
		<div id="header">
			
	
			<div class="header-nav headerChild">
				<div class="header-nav">
					<a href="Controller?command=gotocatalogpage&gender=Men">${men_button}</a>
					<a href="Controller?command=gotocatalogpage&gender=Women">${women_button}</a>
				</div>
			</div>
			
			<div class="shopName headerChild">
				<a class="siteName" href="Controller?command=gotomainpage">iShop</a>
			</div>
		
	
			<div class="localisation headerChild">
				<table>
					<tr>
						<td>
							<c:if test="${user == null}">
								<a href="Controller?command=gotobasketpage">${basket_button}
									<c:if test="${basketSize <= 0}">
										<sup><c:out value = " "/></sup>
									</c:if>
									
									<c:if test="${basketSize > 0}">
										<sup><c:out value = "${basketSize}"/></sup>
									</c:if>
								</a>
								
								<a href="Controller?command=gotosigninpage">${signinPage_button}</a>
							</c:if>
					
							<c:if test="${user != null}">
								<a href="Controller?command=gotouserinfopage">${hi_button}, <c:out value = "${user.name}"/></a>
								<a href="Controller?command=gotobasketpage">${basket_button}
									<c:if test="${basketSize <= 0}">
										<sup><c:out value = " "/></sup>
									</c:if>
									
									<c:if test="${basketSize > 0}">
										<sup><c:out value = "${basketSize}"/></sup>
									</c:if>
								</a>
								<a href="Controller?command=signout">${signoutPage_button}</a>
							</c:if>
						</td>
						<td>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="changeLocale" /> 
								<input type="hidden" name="local" value="en" /> 
								<input type="submit" value="${en_button}" />
							</form>
						</td>
						<td>
							<form action="Controller" method="post">
								<input type="hidden" name="command" value="changeLocale" /> 
								<input type="hidden" name="local" value="ru" /> 
								<input type="submit" value="${ru_button}" />
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>