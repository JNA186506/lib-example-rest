package no.dat152.libraryexample.services;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import no.dat152.libraryexample.model.Author;
import no.dat152.libraryexample.repositories.AuthorRepository;
import no.dat152.libraryexample.DTO.AuthorDTO;
import no.dat152.libraryexample.DTO.summary.BookSummaryDTO;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Set<BookSummaryDTO> getBookSummary(Author author) {
            return author.getBooks().stream()
                   .map(book -> new BookSummaryDTO(
                       book.getId(),
                       book.getIsbn(),
                       book.getTitle()
                   ))
                   .collect(Collectors.toSet());

    }

    public AuthorDTO findAuthorById(long id) {
        Author author = authorRepository.getReferenceById(id);

        return new AuthorDTO(
            author.getId(),
            author.getFirstname(),
            author.getLastname(),
            this.getBookSummary(author)
        );
    }

    public List<AuthorDTO> findAllAuthors() {
        return authorRepository.findAll().stream().
            map(author -> new AuthorDTO(
                author.getId(),
                author.getFirstname(),
                author.getLastname(),
                this.getBookSummary(author)
            )).toList();
    }

    public Author updateAuthor(long id, Author author) {
        if (authorRepository.existsById(id)) {
            return null;
        }

        return authorRepository.save(author);
    }
    public Author saveAuthor(Author author) {
        if (authorRepository.existsById(author.getId())) {
            return null;
        }
        return authorRepository.save(author);
    }

    public void deleteAuthor(long id) {
        try {
            Author author = authorRepository.getReferenceById(id);
            authorRepository.delete(author);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Author not found");
        }
    }
}
