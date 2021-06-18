package com.epam.eurekadiscoveryclient.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.epam.eurekadiscoveryclient.model.Book;

@RestController
@RequestMapping("/api/book-service")
public class BookServiceDiscoveryClient {

	@Autowired
	RestTemplate template;
	
	private static final String WELCOME_URL = "http://BOOK-SERVICE/bookservice/";
	private static final String FIND_ALL_BOOKS_URL = "http://BOOK-SERVICE/bookservice/books";
	private static final String FIND_BOOK_BY_ID_URL = "http://BOOK-SERVICE/bookservice/books/{bookId}";
	private static final String ADD_NEW_BOOK_URL = "http://BOOK-SERVICE/bookservice/books";
	private static final String UPDATE_EXISTING_BOOK_URL = "http://BOOK-SERVICE/bookservice/books";
	private static final String DELETE_BOOK_BY_ID_URL = "http://BOOK-SERVICE/bookservice/books/{bookId}";
	
	@GetMapping(path = "/")
	public String welcomeBookService() {
		return template.getForObject(WELCOME_URL, String.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/books")
	public ResponseEntity<List<Book>> findAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			books = template.getForObject(FIND_ALL_BOOKS_URL, List.class);
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/books/{bookId}")
	public ResponseEntity<Book> findBookById (@PathVariable long bookId) {
		Book book = null;
		try {
			book = template.getForObject(FIND_BOOK_BY_ID_URL, Book.class, bookId);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@PostMapping(path = "/books", consumes = "application/json")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
		Book result = null;
		try {
			Book newBook = new Book(0, book.getBookName(), book.getBookAuthor(),
					book.getBookCategory(), book.getBookDescription(), book.getBookIssuedDate());
			result = template.postForObject(ADD_NEW_BOOK_URL, newBook, Book.class);
			return new ResponseEntity<Book>(result, HttpStatus.CREATED);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(path = "/books", consumes = "application/json")
	public ResponseEntity<Book> updateExistingBook(@RequestBody Book book) {
		ResponseEntity<Book> entity = null;
		try {
			template.put(UPDATE_EXISTING_BOOK_URL, book);
			entity = findBookById(book.getBookId());
			return entity;
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(path = "/books/{bookId}")
	public ResponseEntity<Book> deleteBookById(@PathVariable long bookId) {
		try {
			template.delete(DELETE_BOOK_BY_ID_URL, bookId);
			return new ResponseEntity<Book>(HttpStatus.ACCEPTED);
		} catch (RestClientException e) {
			e.printStackTrace();
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
