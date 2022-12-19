package com.razin.vadim.dao;

import com.razin.vadim.models.Person;
import com.razin.vadim.models.PersonBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonBookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonBookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT person_id AS id, fio, year FROM Person", new BeanPropertyRowMapper<>(Person.class));
//
//    }

    public List<PersonBook> showByPersonId(int id) {

        List<PersonBook> personBookList = jdbcTemplate.query("SELECT person.person_id AS personId, Person_Book.book_id AS bookId, " +
                        "fio, title, author, book.year AS bookYear FROM person LEFT JOIN Person_book ON\n" +
                        "    Person.person_id = Person_Book.person_id join Book on\n" +
                        "    Book.book_id = Person_Book.book_id WHERE person.person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(PersonBook.class));
        return personBookList;
    }

    public PersonBook showByBookId(int id) {

        return jdbcTemplate.query("SELECT person.person_id AS personId, Person_Book.book_id AS bookId, " +
                        "fio, title, author, book.year AS bookYear FROM person LEFT JOIN Person_book ON\n" +
                        "    Person.person_id = Person_Book.person_id join Book on\n" +
                        "    Book.book_id = Person_Book.book_id WHERE book.book_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(PersonBook.class)).stream().findAny().orElse(null);

//        return jdbcTemplate.query("SELECT person.person_id AS personId, Person_Book.book_id AS bookId, " +
//                                "title, author, book.year AS bookYear FROM person LEFT JOIN Person_book ON\n" +
//                                "    Person.person_id = Person_Book.person_id join Book on\n" +
//                                "    Book.book_id = Person_Book.book_id WHERE person.person_id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(PersonBook.class))
//                .stream().findAny().orElse(null);
    }

    public void release(int id) {
        jdbcTemplate.update("DELETE FROM Person_Book WHERE book_id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("INSERT INTO Person_Book VALUES (?, ?)", person.getId(), id);
    }

//            return jdbcTemplate.query("SELECT person.person_id AS personId, Person_Book.book_id AS bookId, " +
//                    "title, author, book.year AS bookYear FROM person LEFT JOIN Person_book ON\n" +
//                    "    Person.person_id = Person_Book.person_id join Book on\n" +
//                    "    Book.book_id = Person_Book.book_id WHERE person.person_id=?", new Object[]{id},
//            new BeanPropertyRowMapper<>(PersonBook.class))
//            .stream().findAny().orElse(null);

//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT person_id AS id, fio, year FROM Person WHERE person_id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }

//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(fio, year) VALUES (?, ?)", person.getFio(), person.getYear());
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET fio=?, year=? WHERE person_id=?", updatedPerson.getFio(),
//                updatedPerson.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
//    }
}


