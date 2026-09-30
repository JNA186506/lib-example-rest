package no.dat1152.libraryexample.controller;

import jakarta.persistence.EntityNotFoundException;
import no.dat1152.libraryexample.model.Author;
import no.dat1152.libraryexample.model.Book;
import no.dat1152.libraryexample.services.BookService;
import org.apache.tomcat.util.json.JSONParser;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("elib/api/v1")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookId(@PathVariable long id) {
        try {
            Book book = bookService.findBook(id);
            return ResponseEntity.ok(book);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/books/")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        if (book.getTitle().trim().isEmpty() || book.getAuthors().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        bookService.saveBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
}
