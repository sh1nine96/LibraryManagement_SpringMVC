<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	
<title>Update Book Detail</title>

</head>
<body>

		<h3>Add/Update the following Book</h3>

	<div class="container">
		<form action = "/LibraryManagement/books/addorupdate" method = "POST">
	
			<input type = "hidden" name = "bookId" value = "${tempBook.id}">
		
			<div class="form-inline">
				<input type = "text" name = "name" value = "${tempBook.name}" 
				placeholder="The name of the book"/>				
			</div>

			<div class="form-inline">	
				<input type = "text" name = "author" value = "${tempBook.author}" 
				placeholder="The author of the book"/>	
			</div>
			
			<div class="form-inline">		
				<input type = "text" name = "category" value = "${tempBook.category}" 
				placeholder="The category of the book"/>	
			</div>
				
			<button class = "btn btn-info" type = "submit">Add/Update</button>
		
		</form>
	
	</div>

</body>
</html>