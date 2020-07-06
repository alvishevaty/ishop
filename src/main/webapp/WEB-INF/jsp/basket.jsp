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

<fmt:message bundle="${loc}" key="local.basket.message.empty"
	var="emptyBasket_message" />
<fmt:message bundle="${loc}" key="local.button.removeFromBasket"
	var="button_removeFromBasket" />

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
						<div class=fullPrice>

							<c:set var="fullPrice" value="${0}" />
							<c:forEach items="${requestScope.basketList}" var="product">
								<c:set var="fullPrice" value="${fullPrice + product.price}" />
							</c:forEach>
							<span>Full price: </span>
							<c:out value="${fullPrice}" />
							<span class=meas>BYN </span>
						</div>

						<c:forEach items="${requestScope.basketList}" var="product">
							<div class=oneGoodMiniContainer>

								<a href="Controller?command=showgoodspage&goodsId=${product.id}">
									<img src="img/${product.id}.jpg" />
								</a>
								<h4>
									<c:out value="${product.price}" />
									BYN
								</h4>
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

						<form action="Controller" method="post">

							<input type="hidden" name="command" value="checkout" /> 
							
							<span class="formTitle">Delivery Address: </span><br />
							
							<div class=orderParameter> 
								<span class="label">Country: </span> 
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Country" name="country" value="" />
							</div>
							<br/> 
							<div class=orderParameter>
								<span
								class="label">City: </span>  
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="City" name="city" value="" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">Street: </span>  
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Street" name="street" value="" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">House: </span> 
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="House" name="house" value="" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">Flat: </span> 
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Flat" name="flat" value="" />
							</div>
							<br /> 
							<div class=orderParameter>
								<span class="label">Post code: </span>
							</div>
							<div class=orderParameterValue>
								<input class="input" type="text" placeholder="Post code" name="postCode" value="" />
							</div>
							<br /> 
							
							<span class="formTitle">Delivery: </span><br />
							
							<div class=orderParameter> 
								<span class="deliveryType">Delivery type: </span> 
							</div>
							<div class=orderParameterValue>
						
								<input type="radio" id="pickup" name="deliveryType" value="Pickup">
								<label for="pickup">Pickup</label><br>
								<input type="radio" id=courierDelivery name="deliveryType" value="CourierDelivery">
								<label for="courierDelivery">Courier delivery</label><br>
							
							</div>
							<br/> 
							
							<div class=orderParameter>
								<span class="date">Delivery date: </span>
							</div>
							<div class=orderParameterValue>
								<input type=date id="date" name="deliveryDate" />
							</div>
							<br /> 
							
							<span class="formTitle">Payment: </span><br />
							
							<div class=orderParameter> 
								<span class="paymentType">Payment type: </span> 
							</div>
							<div class=orderParameterValue>
							
								<input type="radio" id="cash" name="paymentMethod" value="Cash">
								<label for="cash">Cash</label><br>
								<input type="radio" id="card" name="paymentMethod" value="Card">
								<label for="card">Card</label><br>
						
							</div>
							<br/> 
							
							<div class=orderParameter>
								<span class="amount">Amount: </span>
							</div>
							<div class=orderParameterValue>
								<input type="hidden" name="amount" value="${fullPrice}" />
								<span><c:out value="${fullPrice}"/> BYN</span>
							</div>
							<br/> 
							
							<c:if test="${user != null}">
								<input type="hidden" name="userId" value="${user.id}" />	
							</c:if>
							
							<input class="button orderSubmit" type="submit" value="Submit" />
							
						</form>



					</div>
				</c:if>
			</div>
		</div>
	</main>
</body>
</html>