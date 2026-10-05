package no.dat152.libraryexample.DTO;

import java.util.Set;

import no.dat152.libraryexample.DTO.summary.AuthorSummaryDTO;

public record BookDTO(
long id,
String title,
String isbn,
Set<AuthorSummaryDTO> authors
) {}
