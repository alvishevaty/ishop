<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
		<title>Basket</title>
		<link rel="stylesheet" href="style.css">

		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="local" var="loc" />
		
		<fmt:message bundle="${loc}" key="local.basket.message.empty" var="emptyBasket_message" />
		<fmt:message bundle="${loc}" key="local.button.removeFromBasket" var="button_removeFromBasket" />

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>
	<main>
		<div class="fullContainer">
			<div class="emptyBasketContainer">
				<c:if test="${basketSize <= 0 || basket==null}">
					<div class="emptyBasketMessage">
						<span>${emptyBasket_message}</span>
					</div>
				</c:if>
			</div>
			<div class="basketContainer">
				<c:if test="${basketSize > 0}">
					<div class=goodsWindow>
						<c:forEach items="${requestScope.basketList}" var="product">
							<div class="goodsContainer">
								
								<div class="goodsNameInfo">
									<span><a href="Controller?command=showgoodspage&goodsId=${product.id}"><c:out value="${product.name}" /></a></span>
									<span class=price ><c:out value="${product.price}" /> BYN</span>
								</div>
								<div class="deleteButton">
									<form action="Controller" method="get">
										<input type="hidden" name="command" value="delFromBasket" /> 
										<input type="hidden" name="good" value="${product.id}" /> 
										<input class=button type="submit" value="${button_removeFromBasket}" />
									</form>
								</div>
							</div>
							
							
			
						</c:forEach>
					</div>
					<div class="orderFormcontainer">
							
					</div>
					
				</c:if>
			</div>
		</div>
	</main>
</body>
</html>