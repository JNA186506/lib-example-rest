package no.dat152.libraryexample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.dat152.libraryexample.model.Book;
import no.dat152.libraryexample.repositories.BookRepository;
import no.dat152.libraryexample.DTO.BookDTO;
import no.dat152.libraryexample.DTO.summary.AuthorSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private Set<AuthorSummaryDTO> getAuthors(Book book) {
        return book.getAuthors().stream()
        .map(author -> new AuthorSummaryDTO(
            author.getId(),
            author.getFirstname(),
            author.getLastname()))
        .collect(Collectors.toSet());
    }

    public BookDTO findBook(long id) {
        Book book = bookRepository.getReferenceById(id);
        return new BookDTO(book.getId(), book.getTitle(), this.getAuthors(book));
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
        .map(book -> new BookDTO(
            book.getId(),
            book.getTitle(),
            this.getAuthors(book)
        )).toList();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(long id) {
        bookRepository.findById(id)
            .ifPresentOrElse(
                book -> bookRepository.delete(book),
                () -> { throw new IllegalArgumentException("Book was not found"); }
            );
    }

}
