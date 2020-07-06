<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>Catalog</title>

<link rel="stylesheet" href="style.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local" var="loc" />

<fmt:message bundle="${loc}" key="local.catalog.Clothes" var="clothes" />
<fmt:message bundle="${loc}" key="local.catalog.Footwear" var="footwear" />
<fmt:message bundle="${loc}" key="local.catalog.Accessories"
	var="accessories" />
<fmt:message bundle="${loc}" key="local.catalog.Jeans" var="Jeans" />
<fmt:message bundle="${loc}" key="local.catalog.Sweatshirts"
	var="Sweatshirts" />
<fmt:message bundle="${loc}" key="local.catalog.Shorts" var="Shorts" />
<fmt:message bundle="${loc}" key="local.catalog.Outerwear"
	var="Outerwear" />
<fmt:message bundle="${loc}" key="local.catalog.Boots" var="name_Boots" />
<fmt:message bundle="${loc}" key="local.catalog.Sneakers"
	var="name_Sneakers" />
<fmt:message bundle="${loc}" key="local.catalog.Slippers"
	var="name_Slippers" />
<fmt:message bundle="${loc}" key="local.catalog.Shoes" var="name_Shoes" />
<fmt:message bundle="${loc}" key="local.catalog.Headgear" var="Headgear" />
<fmt:message bundle="${loc}" key="local.catalog.Umbrellas"
	var="Umbrellas" />
<fmt:message bundle="${loc}" key="local.catalog.Glasses" var="Glasses" />
<fmt:message bundle="${loc}" key="local.catalog.Gloves" var="Gloves" />
<fmt:message bundle="${loc}" key="local.button.addToBasket"
	var="button_addToBasket" />

</head>
<body>
	<div> <jsp:include page="/WEB-INF/jsp/header.jsp" /> </div>

	<div class=main>
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
									href="Controller?command=getgoodslist&gender=${sessionScope.gender}&category=Footwear&subcategory=${footwearsubcategory.name}">
										<c:out value="${footwearsubcategory.name}" />
								</a></li>
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

	<div class="listContainer">
		<c:forEach items="${requestScope.goodsList}" var="product">
			<div class="oneGoodContainer">

				<a href="Controller?command=showgoodspage&goodsId=${product.id}"><img
					src="img/${product.id}.jpg" /></a>
				<h3>
					<c:out value="${product.price}" />
					BYN
				</h3>
				<a href="Controller?command=showgoodspage&goodsId=${product.id}">
					<c:out value="${product.name}" />
				</a>
				<form action="Controller" method="get">
					<input type="hidden" name="command" value="putIntoBasket" /> <input
						type="hidden" name="good" value="${product.id}" /> <input
						class=button type="submit" value="${button_addToBasket}" />
				</form>
			</div>

		</c:forEach>
	</div>

	</div>
	

</body>
</html>