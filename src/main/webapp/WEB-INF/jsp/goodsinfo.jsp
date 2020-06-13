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
		<fmt:message bundle="${loc}" key="local.goods.description" var="description" />
		<fmt:message bundle="${loc}" key="local.goods.brand" var="brand" />
		<fmt:message bundle="${loc}" key="local.goods.size" var="size" />
		<fmt:message bundle="${loc}" key="local.goods.season" var="season" />
		<fmt:message bundle="${loc}" key="local.goods.itemNumber" var="itemNumber" />
		<fmt:message bundle="${loc}" key="local.button.addToBasket" var="button_addToBasket" />
		<fmt:message bundle="${loc}" key="local.catalog.Clothes" var="clothes" />
		<fmt:message bundle="${loc}" key="local.catalog.Footwear" var="footwear" />
		<fmt:message bundle="${loc}" key="local.catalog.Accessories" var="accessories" />
		
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

		<div class="goodsContainer">

			<div class="goodsImg gCchild">
				<img src="img/${product.id}.jpg" />
			</div>

			<div class="goodsInfoColumn gCchild">
				<div class="goodsHeader">

					<table class="goodsInfo">
						<tr>
							<td><h2>
									<c:out value="${product.name}" />
								</h2></td>
						</tr>
						<tr>
							<td><h3>${price}:</h3></td>
							<td><c:out value="${product.price}" /></td>
						</tr>
						<tr>
							<td><h3>${description}:</h3></td>
							<td><c:out value="${product.description}" /></td>
						</tr>
						<tr>
							<td><h3>${brand}:</h3></td>
							<td><c:out value="${product.manufacturer}" /></td>
						</tr>
						<tr>
							<td><h3>${size}:</h3></td>
							<td><c:out value="${product.size}" /></td>
						</tr>
						<tr>
							<td><h3>${season}:</h3></td>
							<td><c:out value="${product.season}" /></td>
						</tr>
						<tr>
							<td><h3>${itemNumber}:</h3></td>
							<td><c:out value="${product.vendorCode}" /></td>
						</tr>
						<tr>
							<td>
								<div class="basketbutton">
									<form action="Controller" method="get">
										<input type="hidden" name="command" value="putIntoBasket" />
										<input type="hidden" name="good" value="${product.id}" /> 
										<input class=button type="submit" value="${button_addToBasket}" />
									</form>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</main>
</body>
</html>