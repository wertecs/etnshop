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
	<h2>Product Search</h2>

	<c:if test="${param.message != null}">${param.message}</c:if>

	<a class="btn btn-primary btn-lg" href="/etnshop/product/list"
		role="button">Products</a>

	<form action="search" method="post" action="POST">
		<label for="query">Query</label> <input type="text"
			placeholder="Search Query" name="query" /> <input type="submit"
			class="btn btn-primary btn" value="Search" />

	</form>
	<c:choose>
		<c:when test="${products == null || products.size() == 0}">
       No products match the given criteria
    </c:when>
		<c:otherwise>
			<table class="table">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Serial Number</th>
						<th>Edit</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<td>${product.id}</td>
							<td>${product.name}</td>
							<td>${product.serialNumber}</td>
							<td><a href="/etnshop/product/edit/${product.id}">${product.id}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>


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