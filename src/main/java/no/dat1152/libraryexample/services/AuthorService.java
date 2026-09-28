package no.dat1152.libraryexample.services;

import no.dat1152.libraryexample.model.Author;
import no.dat1152.libraryexample.repositories.AuthorRepository;
import no.dat1152.libraryexample.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findAuthorById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void deleteAuthor(long id) {
        authorRepository.findById(id)
            .ifPresentOrElse(
                author -> authorRepository.delete(author),
                () -> {
                    throw new IllegalArgumentException("Book not found");
                }

            );
    }
}
