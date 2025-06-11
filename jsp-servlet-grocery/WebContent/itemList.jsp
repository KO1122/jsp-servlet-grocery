<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Grocery Store</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
	crossorigin="anonymous"></script>
</head>

<body>

	<div class="container mt-5">
		<h1 class="text-center">Shopping Cart</h1>
		<hr>
		<div class="text-center">
			<input type="button" value="Add Item"
				onclick="window.location.href='addItemForm.jsp'; return false;"
				class="btn btn-link mb-2" />
			
			<input type="button"
			value="List Items"
			onclick="window.location.href='ItemControllerServlet'; return false;"
			class="btn btn-link mb-2" />
			
			<form>
				<input type="hidden" name="command" value="search" /> Search item:
				<input type="text" name="searchName" /> <input type="submit"
					value="Search" class="mb-3"/>
			</form>
		</div>

		<div class="row justify-content-center">
			<table class="table table-bordered table-hover table-striped w-auto">
				<thead>
					<tr class="text-center">
						<th>Item</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Actions</th>
					</tr>
				</thead>

				<c:forEach var="item" items="${items}">

					<!-- Link variable -->
					<c:url var="editLink" value="ItemControllerServlet">
						<c:param name="command" value="edit" />
						<c:param name="itemId" value="${item.id}" />
					</c:url>
					<c:url var="deleteLink" value="ItemControllerServlet">
						<c:param name="command" value="delete" />
						<c:param name="itemId" value="${item.id}" />
					</c:url>

					<tr>
						<td>${item.name}</td>
						<td>${item.quantity}</td>
						<td>${item.price}</td>
						<td><a href="${editLink}">Edit</a> | <a href="${deleteLink}"
							onclick="if (!(confirm('Do you want to delete this item?'))) return false;">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>