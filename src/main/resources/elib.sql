CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL
);

CREATE TABLE book (
    id BIGSERIAL PRIMARY KEY,
    isbn VARCHAR NOT NULL,
    title VARCHAR NOT NULL
);

CREATE TABLE book_author (
    fk_book BIGINT NOT NULL REFERENCES book(id),
    fk_author BIGINT NOT NULL REFERENCES author(id),
    PRIMARY KEY (fk_book, fk_author)
);

CREATE TABLE lib_user (
    id BIGSERIAL PRIMARY KEY,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL
);

CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    exp_date TIMESTAMP NOT NULL,
    fk_book BIGINT NOT NULL REFERENCES book(id),
    fk_user BIGINT NOT NULL REFERENCES lib_user(id)
);


-- Sample data
INSERT INTO author(firstname, lastname)
VALUES ('J.R.R', 'Tolkien'),
       ('Stephen', 'King'),
       ('Susanna', 'Clarke');

INSERT INTO book(title, isbn)
VALUES ('Lord of the rings 1', 'abcd1234'),
       ('IT', 'bdcd3234'),
       ('Piranesi', 'rtsf324');

INSERT INTO book_author(fk_book, fk_author)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO lib_user(firstname, lastname)
VALUES  ('jane', 'doe'),
        ('john', 'doe');

INSERT INTO orders (exp_date, fk_book, fk_user)
VALUES  ('2026-11-05 12:00:00', 1, 1),
        ('2026-11-05 12:00:00', 2, 2);
