package no.dat152.libraryexample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import no.dat152.libraryexample.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
