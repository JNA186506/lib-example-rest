package no.dat1152.libraryexample.repositories;

import no.dat1152.libraryexample.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
