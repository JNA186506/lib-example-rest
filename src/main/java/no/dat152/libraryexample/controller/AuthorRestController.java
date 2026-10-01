package no.dat152.libraryexample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import no.dat152.libraryexample.model.Author;
import no.dat152.libraryexample.services.AuthorService;
import no.dat152.libraryexample.DTO.AuthorDTO;

@Controller
@RequestMapping("elib/api/v1")
public class AuthorRestController {

    private AuthorService authorService;

    public AuthorRestController(AuthorService authorservice) {
        this.authorService = authorservice;
    }

    @GetMapping(value = {"/authors/", "/authors" })
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.findAllAuthors());
    }


    @GetMapping(value = {"/authors/{id}", "/authors/{id}/"})
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long id) {
        try {
            AuthorDTO author = authorService.findAuthorById(id);
            return ResponseEntity.ok(author);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = {"/authors/", "/authors"})
    public ResponseEntity<AuthorDTO> createNewAuthor(@RequestBody Author author) {
        if (author.getFirstname().isEmpty() ||
            author.getLastname().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

            authorService.saveAuthor(author);

            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthorDTO(
                author.getId(),
                author.getFirstname(),
                author.getLastname(),
                authorService.getBookSummary(author)
            ));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<AuthorDTO> changeAuthor(@PathVariable long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);

        if (updatedAuthor == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new AuthorDTO(updatedAuthor.getId(),
            updatedAuthor.getFirstname(),
            updatedAuthor.getLastname(),
            authorService.getBookSummary(author)
        ));

    }

    @DeleteMapping(value = {"/authors/", "/authors"})
    public ResponseEntity<AuthorDTO> deleteAuthor(@RequestBody long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
