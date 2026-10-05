package no.dat152.libraryexample.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Book extends RepresentationModel<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "book_author",
        joinColumns = {@JoinColumn(name = "fk_book")},
        inverseJoinColumns = { @JoinColumn(name = "fk_author")})
    private Set<Author> authors = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

}
