package com.epam.eurekadiscoveryclient.model;

import java.util.Date;

public class Book {

	private long bookId;
	
	private String bookName;
	
	private String bookAuthor;
	
	private String bookCategory;
	
	private String bookDescription;
	
	private Date bookIssuedDate = new Date();

	public Book() {
		super();
	}

	public Book(long bookId, String bookName, String bookAuthor, String bookCategory, String bookDescription,
			Date bookIssuedDate) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.bookDescription = bookDescription;
		this.bookIssuedDate = bookIssuedDate;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public Date getBookIssuedDate() {
		return bookIssuedDate;
	}

	public void setBookIssuedDate(Date bookIssuedDate) {
		this.bookIssuedDate = bookIssuedDate;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookCategory="
				+ bookCategory + ", bookDescription=" + bookDescription + ", bookIssuedDate=" + bookIssuedDate + "]";
	}
	
}
