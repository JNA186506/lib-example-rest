package no.dat152.libraryexample.model;

import java.sql.Timestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column(name = "exp_date", nullable = false)
    private Timestamp expiryDate;

    @ManyToOne
    @JoinColumn(name = "fk_book", nullable = false)
    private Book Book;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User User;

}
