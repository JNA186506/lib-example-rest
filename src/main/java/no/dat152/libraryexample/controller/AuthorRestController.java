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
import no.dat152.libraryexample.repositories.AuthorRepository;
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
        return ResponseEntity.ok(authorService.findAuthorById(id));
    }

    @PostMapping(value = {"/authors/", "/authors"})
    public ResponseEntity<AuthorDTO> createNewAuthor(@PathVariable long id, @RequestBody Author author) {
        if (author.getFirstname().isEmpty() ||
            author.getLastname().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Author newAuthor = authorService.saveAuthor(id, author);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthorDTO(
            newAuthor.getId(),
            newAuthor.getFirstname(),
            newAuthor.getLastname(),
            authorService.getBookSummary(newAuthor)
        ));
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<AuthorDTO> changeAuthor(@PathVariable long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);

        return ResponseEntity.ok(new AuthorDTO(updatedAuthor.getId(),
            updatedAuthor.getFirstname(),
            updatedAuthor.getLastname(),
            authorService.getBookSummary(author)
        ));

    }

    @DeleteMapping(value = {"/authors/", "/authors"})
    public ResponseEntity<AuthorDTO> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.ok().build();
    }

}
