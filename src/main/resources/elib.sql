CREATE TABLE author (
                        id BIGSERIAL PRIMARY KEY,
                        firstname VARCHAR NOT NULL,
                        lastname VARCHAR NOT NULL
);

CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
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


-- Sample data
INSERT INTO author(firstname, lastname)
VALUES ('J.R.R', 'Tolkien'),
       ('Stephen', 'King'),
       ('Susanna', 'Clarke');

INSERT INTO book(title)
VALUES ('Lord of the rings 1'),
       ('IT'),
       ('Piranesi');

INSERT INTO book_author(fk_book, fk_author)
VALUES (1, 1),
       (2, 2),
       (3, 3);