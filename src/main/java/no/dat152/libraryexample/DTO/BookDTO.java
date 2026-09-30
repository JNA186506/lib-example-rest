package no.dat152.libraryexample.DTO;

import java.util.Set;

public record BookDTO(
long id,
String title,
Set<AuthorDTO> authors
) {}
