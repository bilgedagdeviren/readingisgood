# ReadingIsGood

ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. That is why stock consistency is the first priority for their
vision operations.

### Technologies
* Java 11
* Spring Boot Application
* Spring boot Starter Web
* Spring Data JPA/ Data Rest
* Spring Security for Basic Auth
* H2 In Memory database
* Lombok
* Maven
* Docker

### Design
All the APIs as per design broken down into 4 controllers:

APIs
* BookController - For Book Operations: add a new book, update book stocks,get all books
  * Add New Book
    * Post:localhost:8080/v1/book/add 
      * {
        "name": "Sapiens: A Brief History of Humankind",
        "author": "Yuval Noah Harari",
        "year": 2011,
        "price": 7.50,
        "count": 500
        }
  * Get All Books
      * Get: localhost:8080/v1/book/get
  * Update Book Statistics 
    * PUT: localhost:8080/v1/book/update/1/200
* CustomerController - For Customer Operations: create a new customer, get all defined customers with orders
  * Add New Customer
    * Post: localhost:8080/v1/customer/add
      * {
        "email": "bilge@g.com",
        "name": "Bilge"
        }
  * Get all defined customers with orders
    * http://localhost:8080/v1/customer/get?page=1,size=10
* OrderController - For Order Operations: create a order, get order, list orders by date interval,
  * Create order
    *Post: localhost:8080/v1/order/add
      * {
        "email": "bilge@g.com",
        "bookId": 1,
        "quantity": 20
        }
  * Get order details
    * Get: localhost:8080/v1/order/get/3
  * Get orders Between Dates
    * Get:localhost:8080/v1/order/getByDateInterval/?startdate=01032022&endDate=01062022&page=1,size=10 
* StatisticsController - For Statistics Operations: get customers monthly order statistics
  * Get: localhost:8080/v1/statistics/monthly/1

DB
* SQL DB used since data was `structured` already and there was no complexity in its structure.
* Other reason since the requirement includes `transactional` behaviour for db updates so SQL again become better choice. 
* Note: DB in security currently on basic auth use with username, password as `(user, password)` and H2 DB login `(sa, password)` for http://localhost:8080/h2-console/ 

### How to set up

with IDE
 * Import in any IDE of choice
 * Go to `ReadingIsGoodApplication.java` and run main method.
 * Visit 'localhost:8080' on browser and enter username: `user` and password: `password`.
* Try exploring APIs via on Browser or open Postman and Import collection

with Maven
 * Import in any location of choice.
 * Run command to start application `mvn clean spring-boot:run`.
 * Visit 'localhost:8080' on browser and enter username: `user` and password: `password`.
 * Try exploring APIs via on Browser or open Postman and Import collection
 
Or you can compile project to Jar via `mvn package` and run jar using java. 
