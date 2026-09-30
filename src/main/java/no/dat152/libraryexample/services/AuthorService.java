package no.dat152.libraryexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.dat152.libraryexample.model.Author;
import no.dat152.libraryexample.repositories.AuthorRepository;
import no.dat152.libraryexample.DTO.AuthorDTO;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO findAuthorById(long id) {
        Author author = authorRepository.getReferenceById(id);

        return new AuthorDTO(author.getId(),
            author.getFirstname(),
            author.getLastname());
    }

    public List<AuthorDTO> findAllAuthors() {
        return authorRepository.findAll().stream().
            map(author -> new AuthorDTO(
                author.getId(),
                author.getFirstname(),
                author.getLastname()
            )).toList();
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
