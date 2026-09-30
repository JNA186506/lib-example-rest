package no.dat152.libraryexample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;

import no.dat152.libraryexample.model.Author;
import no.dat152.libraryexample.services.AuthorService;
import no.dat152.libraryexample.DTO.AuthorDTO;

@Controller
@RequestMapping("elib/api/v1")
public class AuthorRestController {

    private AuthorService authorservice;

    @Autowired
    public AuthorRestController(AuthorService authorservice) {
        this.authorservice = authorservice;
    }

    @GetMapping(value = {"/authors/", "/authors" })
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorservice.findAllAuthors());
    }


    @GetMapping(value = {"/authors/{id}", "/authors/{id}/"})
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable long id) {
        try {
            AuthorDTO author = authorservice.findAuthorById(id);
            return ResponseEntity.ok(author);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = {"/authors/", "/authors"})
    public ResponseEntity<Author> createNewAuthor(@RequestBody Author author) {
        if (author.getFirstname().isEmpty() ||
            author.getLastname().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            authorservice.saveAuthor(author);

            return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

}
