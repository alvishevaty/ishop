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
		<div class="mainPage">
			<div id="leftColumn">
				<div class="categoryList">
					<ul>
						<li><a
							href="Controller?command=getgoodslist&gender=Men&category=Clothes"
							class=category>Clothes</a>
							<div class="subcategoryList">
								<ul>
									<c:forEach items="${requestScope.clothessubcategoryList}"
										var="clothessubcategory">
										<li><a
											href="Controller?command=getgoodslist&gender=Men&category=Clothes&subcategory=${clothessubcategory.name}"><c:out
													value="${clothessubcategory.name}" /></a></li>
									</c:forEach>
								</ul>
							</div></li>
						<li><a
							href="Controller?command=getgoodslist&gender=Men&category=Footwear"
							class=category>Footwear</a>
							<div class="subcategoryList">
								<ul>
									<c:forEach items="${requestScope.footwearsubcategoryList}"
										var="footwearsubcategory">
										<li><a
											href="Controller?command=getgoodslist&gender=Men&category=Footwear&subcategory=${footwearsubcategory.name}"><c:out
													value="${footwearsubcategory.name}" /></a></li>
									</c:forEach>
								</ul>
							</div></li>
						<li><a
							href="Controller?command=getgoodslist&gender=Men&category=Accessories"
							class=category>Accessories</a>
							<div class="subcategoryList">
								<ul>
									<c:forEach items="${requestScope.accessoriessubcategoryList}"
										var="accessoriessubcategory">
										<li><a
											href="Controller?command=getgoodslist&gender=Men&category=Accessories&subcategory=${accessoriessubcategory.name}"><c:out
													value="${accessoriessubcategory.name}" /></a></li>
									</c:forEach>
								</ul>
							</div></li>
					</ul>
				</div>
			</div>

			<div class="listContainer">
				<div class="filters">
					<form
						action="Controller?command=getgoodslist&gender=Men&category=Clothes">
						<label><input type="checkbox" name="size" value=41>41</label><br>
						<label><input type="checkbox" name="size" value=38>38</label>
						<input type="submit" name="parameter" value="Submit">
					</form>

					<form>
						<input type="checkbox" name="" value="" />
					</form>


				</div>

				<c:forEach items="${requestScope.goodsList}" var="product">
					<div class="oneGoodContainer">
						<c:out value="${product.name}" />
						<c:out value="${product.description}" />
					</div>

				</c:forEach>
			</div>
		</div>
	</main>
</body>
</html>