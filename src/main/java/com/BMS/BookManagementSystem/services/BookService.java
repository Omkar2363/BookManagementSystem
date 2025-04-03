package com.BMS.BookManagementSystem.services;

import com.BMS.BookManagementSystem.exceptions.BookNotFoundException;
import com.BMS.BookManagementSystem.exceptions.ValidationException;
import com.BMS.BookManagementSystem.models.Book;
import com.BMS.BookManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add a new book
    public Book addBook(Book book) {
        // Validate book details
        validateBook(book);

        // Check if book with same ID already exists
        if (bookRepository.existsById(book.getBookId())) {
            throw new ValidationException("Book with ID " + book.getBookId() + " already exists");
        }

        // Assigning default availability if not specified
        if (book.getAvailabilityStatus() == null) {
            book.setAvailabilityStatus("Available");
        }

        return bookRepository.save(book);
    }

    // View all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Search book by ID
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ID: " + bookId));
    }

    // Search book by title
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book not found with title: " + title));
    }

    // Update book details
    public Book updateBook(String bookId, Book updatedBook) {
        Book existingBook = getBookById(bookId);

        // Update fields that are provided
        Optional.ofNullable(updatedBook.getTitle()).ifPresent(existingBook::setTitle);
        Optional.ofNullable(updatedBook.getAuthor()).ifPresent(existingBook::setAuthor);
        Optional.ofNullable(updatedBook.getGenre()).ifPresent(existingBook::setGenre);

        // Update availability status with validation
        if (updatedBook.getAvailabilityStatus() != null) {
            existingBook.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
        }

        return bookRepository.save(existingBook);
    }

    // Delete a book
    public void deleteBook(String bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }

    // Validate book details
    private void validateBook(Book book) {
        if (book.getBookId() == null || book.getBookId().trim().isEmpty()) {
            throw new ValidationException("Book ID cannot be empty");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new ValidationException("Book title cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new ValidationException("Book author cannot be empty");
        }

        // Validate availability status
        if (book.getAvailabilityStatus() != null) {
            if (!("Available".equals(book.getAvailabilityStatus()) ||
                    "Checked Out".equals(book.getAvailabilityStatus()))) {
                throw new ValidationException("Availability status must be either 'Available' or 'Checked Out'");
            }
        }
    }
}
