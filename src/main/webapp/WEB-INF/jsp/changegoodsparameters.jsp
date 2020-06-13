<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="goodsInfoColumn gCchild">

		<form action="Controller" method="get">

			<span class="title"> Change goods parameters </span> 
			<input type="hidden" name="command" value="executegoodsinfoupdate" /> 
			
			<label for="category">Category: </label><br /> 
			<input type="text" name="category" value="${product.category}" /><br /> 
			
			<select>
				<option label="${product.category}" value="${product.category}" selected>Господин Электроник</option>
				<option>Сыроежкин</option>
				<option disabled>Чижиков</option>
				<option>Кукушкина</option>
			</select>
			
			<label for="subcategory">Subcategory: </label><br /> 
			<input type="text" name="subcategory" value="${product.subcategory}" /><br /> 
			
			<label for="brand">Brand: </label> <br /> 
			<input type="text" name="brand" value="${product.brand}" /><br /> 
			
			<label for="name">Name: </label> <br /> 
			<input type="text" name="name" value="${product.name}" /><br />

			<label for="description">Description: </label> <br /> 
			<input type="text" name="description" value="${product.description}" /><br />

			<label for="material">Material: </label> <br /> 
			<input type="text" name="material" value="${product.material}" /><br /> 
			
			<label for="gender">Gender: </label> <br /> 
			<input type="text" name="gender" value="${product.gender}" /><br /> 
			
			<label for="size">Size: </label> <br /> 
			<input type="text" name="size" value="${product.size}" /><br />
			
			<label for="season">Season: </label> <br /> 
			<input type="text" name="season" value="${product.season}" /><br /> 
			
			<label for="vendorCode">Vendor Code: </label> <br /> 
			<input type="text" name="vendorCode" value="${product.vendorCode}" /><br /> 
			
			<label for="price">Price: </label> <br /> 
			<input type="text" name="price" value="${product.price}" /><br /> 
				
			<input class="button" type="submit" value="Update" /><br />

		</form>


	</div>
</body>
</html>