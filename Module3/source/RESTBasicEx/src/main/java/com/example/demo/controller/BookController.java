package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller to Demonstrate REST API to HTTP mappings.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    // GET all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // GET book by ID
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // POST - Create book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    // PUT - Update book
    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book updatedBook) {

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        books.removeIf(b -> b.getId().equals(id));
        return "Book deleted";
    }

    // Search with query parameter
    @GetMapping("/search")
    public List<Book> search(@RequestParam String title) {
        return books.stream()
                .filter(b -> b.getTitle().contains(title))
                .toList();
    }
}