<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Edit Item</title>
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
	<div class="container mt-5 text-center">
		<h1>Shopping Cart</h1>
		<hr>
	</div>

	<div class="text-center">
		<input type="button" value="Add Item" class="btn btn-link mb-3"
			onclick="window.location.href='addItemForm.jsp'; return false;" /> <input
			type="button" value="List Items" class="btn btn-link mb-3"
			onclick="window.location.href='ItemControllerServlet'; return false;" />
			
		
	</div>

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header">
						<h2>Edit Item</h2>
					</div>
					<div class="card-body">
						<form action="ItemControllerServlet" method="GET">
							<input type="hidden" name="command" value="update" /> <input
								type="hidden" name="itemId" value="${item.id}" />

							<div class="form-group">
								<label>Name:</label> <input type="text" name="name"
									class="form-control" value="${item.name}" />
							</div>

							<div class="form-group mt-2">
								<label>Quantity:</label> <input type="text" name="quantity"
									class="form-control" value="${item.quantity}" />
							</div>

							<div class="form-group mt-2">
								<label>Price:</label> <input type="text" name="price"
									class="form-control" value="${item.price}" />
							</div>

							<button type="submit" class="btn btn-primary mt-2">Save</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>