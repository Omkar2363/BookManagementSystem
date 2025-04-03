package com.BMS.BookManagementSystem.repository;

import com.BMS.BookManagementSystem.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository
{
    // Using ConcurrentHashMap to ensure thread-safety
    private final ConcurrentHashMap<String, Book> bookCollection = new ConcurrentHashMap<>();

    // Add a book
    public Book save(Book book) {
        bookCollection.put(book.getBookId(), book);
        return book;
    }

    // Find all books
    public List<Book> findAll() {
        return new ArrayList<>(bookCollection.values());
    }

    // Find book by ID
    public Optional<Book> findById(String bookId) {
        return Optional.ofNullable(bookCollection.get(bookId));
    }

    // Find book by title
    public Optional<Book> findByTitle(String title) {
        return bookCollection.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    // Check if book exists
    public boolean existsById(String bookId) {
        return bookCollection.containsKey(bookId);
    }

    // Delete a book
    public void deleteById(String bookId) {
        bookCollection.remove(bookId);
    }

    // Clear all books (useful for testing)
    public void clear() {
        bookCollection.clear();
    }
}