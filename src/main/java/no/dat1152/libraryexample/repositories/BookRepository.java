package no.dat1152.libraryexample.repositories;

import no.dat1152.libraryexample.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
