<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><c:out value="${product.name}" /></title>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="local.goods.price" var="price" />
<fmt:message bundle="${loc}" key="local.goods.description"
	var="description" />
<fmt:message bundle="${loc}" key="local.goods.brand" var="brand" />
<fmt:message bundle="${loc}" key="local.goods.size" var="size" />
<fmt:message bundle="${loc}" key="local.goods.season" var="season" />
<fmt:message bundle="${loc}" key="local.goods.itemNumber"
	var="itemNumber" />
<fmt:message bundle="${loc}" key="local.button.addToBasket"
	var="button_addToBasket" />
<fmt:message bundle="${loc}" key="local.catalog.Clothes" var="clothes" />
<fmt:message bundle="${loc}" key="local.catalog.Footwear" var="footwear" />
<fmt:message bundle="${loc}" key="local.catalog.Accessories"
	var="accessories" />

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>

	<main>

		<div class="leftColumn">
			<div class="categoryList">
				<ul>
					<li><a
						href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Clothes"
						class=category>${clothes}</a>
						<div class="subcategoryList">
							<ul>
								<c:forEach items="${requestScope.clothessubcategoryList}"
									var="clothessubcategory">
									<li><a
										href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Clothes&subcategory=${clothessubcategory.name}"><c:out
												value="${clothessubcategory.name}" /></a></li>
								</c:forEach>
							</ul>
						</div></li>
					<li><a
						href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Footwear"
						class=category>${footwear}</a>
						<div class="subcategoryList">
							<ul>
								<c:forEach items="${requestScope.footwearsubcategoryList}"
									var="footwearsubcategory">
									<li><a
										href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Footwear&subcategory=${footwearsubcategory.name}"><c:out
												value="${footwearsubcategory.name}" /></a></li>
								</c:forEach>
							</ul>
						</div></li>
					<li><a
						href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Accessories"
						class=category>${accessories}</a>
						<div class="subcategoryList">
							<ul>
								<c:forEach items="${requestScope.accessoriessubcategoryList}"
									var="accessoriessubcategory">
									<li><a
										href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Accessories&subcategory=${accessoriessubcategory.name}"><c:out
												value="${accessoriessubcategory.name}" /></a></li>
								</c:forEach>
							</ul>
						</div></li>
				</ul>
			</div>
		</div>
		<div class="mainPage">

			<div class="goodsContainer">

				<div class="goodsImg gCchild">
					<img src="img/${product.id}.jpg" />
				</div>

				<div class="goodsInfoColumn gCchild">
					<div class="goodsHeader">
						<h2>
							<c:out value="${product.name}" />
						</h2>
					</div>
					<div class=goodsInfo>

						<div class=parameter>
							<span>${price}:</span>
						</div>
						<div class=parameterValue>
							<span>${product.price} BYN</span>
						</div>
						<br>
						<div class=parameter>
							<span>${description}:</span>
						</div>
						<div class=parameterValue>
							<span>${product.description}</span>
						</div>
						<!-- <br> -->
						<div class=parameter>
							<span>${brand}:</span>
						</div>
						<div class=parameterValue>
							<span>${product.manufacturer}</span>
						</div>
						<br>
						<div class=parameter>
							<span>${size}:</span>
						</div>
						<div class=parameterValue>
							<span>${product.size}</span>
						</div>
						<br>
						<div class=parameter>
							<span>${season}:</span>
						</div>
						<div class=parameterValue>
							<span>${product.season}</span>
						</div>
						<br>
						<div class=parameter>
							<span>${itemNumber}:</span>
						</div>
						<div class=parameterValue>
							<span>${product.vendorCode}</span>
						</div>
						<br>
						<div class="basketbutton">
							<form action="Controller" method="get">
								<input type="hidden" name="command" value="putIntoBasket" /> <input
									type="hidden" name="good" value="${product.id}" /> <input
									class=button type="submit" value="${button_addToBasket}" />
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>