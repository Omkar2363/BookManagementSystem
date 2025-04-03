package com.BMS.BookManagementSystem.models;

import java.util.Objects;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    // Constructors
    public Book() {
        this.availabilityStatus = "Available"; // Default status
    }

    public Book(String bookId, String title, String author, String genre) {
        this(bookId, title, author, genre, "Available");
    }

    public Book(String bookId, String title, String author, String genre, String availabilityStatus) {
        validateBookId(bookId);
        validateTitle(title);
        validateAuthor(author);
        validateAvailabilityStatus(availabilityStatus);

        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    // Required Validations :
    private void validateBookId(String bookId) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty");
        }
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Book author cannot be empty");
        }
    }

    private void validateAvailabilityStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Availability status cannot be null");
        }

        // Only allow "Available" or "Checked Out"
        if (!("Available".equals(status) || "Checked Out".equals(status))) {
            throw new IllegalArgumentException("Availability status must be either 'Available' or 'Checked Out'");
        }
    }

    // Getters and Setters
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        validateBookId(bookId);
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        validateAuthor(author);
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        validateAvailabilityStatus(availabilityStatus);
        this.availabilityStatus = availabilityStatus;
    }

    // Equals and HashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                '}';
    }
}