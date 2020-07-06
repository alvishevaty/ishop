<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

<link rel="stylesheet" href="style.css">

</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/header.jsp" />
	</header>

	<main>
		<div id="leftColumn">
			<div class="categoryList">
				<ul>
					<li><a href="">Clothes</a>
						<ul>
							<li>Jeans</li>
							<li>Sweatshirts</li>
							<li>Shorts</li>
							<li>Outerwear</li>
						</ul>
					</li>
					<li><a href="">Footwear</a>
						<ul>
							<li>Boots</li>
							<li>Sneakers</li>
							<li>Slippers</li>
							<li>Shoes</li>
						</ul>
					</li>
					<li><a href="">Accessories</a>
						<ul>
							<li>Headgear</li>
							<li>Umbrellas</li>
							<li>Glasses</li>
							<li>Gloves</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="listContainer">
			<div class="parametersTable">
				<span>
					<span>Material</span>
					<div class="dropdown">
				 		<button class="btn"></button>
				  		<div class="dropdown-content">
				   			<a href="#">Link 1</a>
				    		<a href="#">Link 2</a>
				    		<a href="#">Link 3</a>
				  		</div>
					</div>
				</span>
				</div>
				
			</div>
		
			<c:forEach items="${requestScope.goodsList}" var="product">
				<div class="oneGoodContainer">
					<c:out value="${product.name}" />
					<c:out value="${product.description}" />
				</div>
				
			</c:forEach>
	
	</main>
</body>
</html>