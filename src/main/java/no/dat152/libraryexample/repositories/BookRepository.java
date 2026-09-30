package no.dat152.libraryexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import no.dat152.libraryexample.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
