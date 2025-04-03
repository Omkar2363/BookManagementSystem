package com.BMS.BookManagementSystem;


import com.BMS.BookManagementSystem.exceptions.BookNotFoundException;
import com.BMS.BookManagementSystem.exceptions.ValidationException;
import com.BMS.BookManagementSystem.models.Book;
import com.BMS.BookManagementSystem.repository.BookRepository;
import com.BMS.BookManagementSystem.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
		import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookManagementSystemApplicationTests {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		// Clear the repository before each test
		bookRepository.clear();
	}

	@Test
	void testAddBook_Success() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		Book savedBook = bookService.addBook(book);

		assertNotNull(savedBook);
		assertEquals("B001", savedBook.getBookId());
	}

	@Test
	void testAddBook_DuplicateId_ThrowsException() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		bookService.addBook(book);

		Book duplicateBook = new Book("B001", "Another Book", "Another Author", "Non-Fiction");
		assertThrows(ValidationException.class, () -> bookService.addBook(duplicateBook));
	}

	@Test
	void testGetBookById_Success() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		bookService.addBook(book);

		Book foundBook = bookService.getBookById("B001");

		assertNotNull(foundBook);
		assertEquals("Test Book", foundBook.getTitle());
	}

	@Test
	void testGetBookById_NotFound_ThrowsException() {
		assertThrows(BookNotFoundException.class, () -> bookService.getBookById("B999"));
	}

	@Test
	void testUpdateBook_Success() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		bookService.addBook(book);

		Book updateBook = new Book("B001", "Updated Book", "Updated Author", null);
		Book updatedBook = bookService.updateBook("B001", updateBook);

		assertEquals("Updated Book", updatedBook.getTitle());
		assertEquals("Updated Author", updatedBook.getAuthor());
	}

	@Test
	void testDeleteBook_Success() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		bookService.addBook(book);

		bookService.deleteBook("B001");

		assertThrows(BookNotFoundException.class, () -> bookService.getBookById("B001"));
	}

	@Test
	void testAddBook_DefaultAvailability() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		Book savedBook = bookService.addBook(book);

		assertEquals("Available", savedBook.getAvailabilityStatus());
	}

	@Test
	void testAddBook_CustomAvailability() {
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction", "Checked Out");
		Book savedBook = bookService.addBook(book);

		assertEquals("Checked Out", savedBook.getAvailabilityStatus());
	}

	@Test
	void testUpdateBook_AvailabilityStatus() {
		// Add a book
		Book book = new Book("B001", "Test Book", "Test Author", "Fiction");
		bookService.addBook(book);

		// Update availability
		Book updateBook = new Book("B001", "Updated Book", "Updated Author", null, "Checked Out");
		Book updatedBook = bookService.updateBook("B001", updateBook);

		assertEquals("Checked Out", updatedBook.getAvailabilityStatus());
	}

}