package no.dat152.libraryexample.DTO;

import java.util.Set;

import no.dat152.libraryexample.DTO.summary.BookSummaryDTO;

public record AuthorDTO(
    long id,
    String firstname,
    String lastname,
    Set<BookSummaryDTO> books
) {}
