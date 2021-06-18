Eureka Discovery Server-
------------------------
http://localhost:8761/

Eureka Discovery for User service-
-----------------------------
Welcome-(GET)
	http://localhost:9090/api/user-service/
Find all users-(GET)
	http://localhost:9090/api/user-service/users/
Find user by id-(GET)
	http://localhost:9090/api/user-service/users/101
Add new user-(POST)
	http://localhost:9090/api/user-service/users/
Update existing user-(PUT)
	http://localhost:9090/api/user-service/users/
Delete existing user by id-(Delete)
	http://localhost:9090/api/user-service/users/108
	
Eureka Discovery for Book service-
-----------------------------
Welcome-(GET)
	http://localhost:9090/api/book-service/
Find all books-(GET)
	http://localhost:9090/api/book-service/books/
Find book by id-(GET)
	http://localhost:9090/api/book-service/books/1001
Add new book-(POST)
	http://localhost:9090/api/book-service/books/
Update existing book-(PUT)
	http://localhost:9090/api/book-service/books/
Delete existing book by id-(Delete)
	http://localhost:9090/api/book-service/books/1006