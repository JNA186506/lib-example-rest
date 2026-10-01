package no.dat152.libraryexample.controller;

import jakarta.persistence.EntityNotFoundException;
import no.dat152.libraryexample.model.Book;
import no.dat152.libraryexample.services.BookService;
import no.dat152.libraryexample.DTO.BookDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("elib/api/v1")
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"/books/", "/books"})
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookId(@PathVariable long id) {
        try {
            BookDTO book = bookService.findBook(id);
            return ResponseEntity.ok(book);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = {"/books/", "/books"})
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        if (book.getTitle().trim().isEmpty() || book.getAuthors().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long id, Book book) {
       Book updatedBook =  bookService.updateBook(id, book);

       if (updatedBook == null) {
           return ResponseEntity.badRequest().build();
       }

        return ResponseEntity.ok(new BookDTO(
            updatedBook.getId(),
            updatedBook.getTitle(),
            bookService.getAuthors(book)
        ));
    }

    @DeleteMapping(value = {"/books/", "/books"})
    public ResponseEntity<Book> deleteBook(@RequestBody long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
