package no.dat1152.libraryexample.services;

import no.dat1152.libraryexample.model.Book;
import no.dat1152.libraryexample.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findBook(long id) {
        return bookRepository.getReferenceById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
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
