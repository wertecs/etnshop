<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>etnShop</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<div class="container">
	<h2>Add a new product</h2>

	<form action="/etnshop/product/save/${product.id}" method="post"
		action="POST">
		<label for="productId">Product id:</label> <input type="text"
			name="productId" value="${product.id}" disabled /> <label
			for="productName">Product name:</label> <input type="text"
			placeholder="Product Name" name="productName" value="${product.name}" />
		<label for="serialNo">Serial number:</label> <input type="text"
			placeholder="Serial Number" name="serialNo"
			value="${product.serialNumber}" /> <input type="submit" />
	</form>
	<hr>
	<footer>
		<p>&copy; Etnetera a.s. 2015</p>
	</footer>
</div>

<spring:url value="/resources/core/css/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>