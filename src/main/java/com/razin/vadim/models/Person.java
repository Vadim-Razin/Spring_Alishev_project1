package com.razin.vadim.models;

import javax.validation.constraints.*;
import java.util.List;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 6, max = 100, message = "ФИО должно быть длинной между 6 и 100 символов")
    private String fio;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int year;

    private List<Book> personBookList;


    public Person() {
    }

    public Person(int id, String fio, int year) {
        this.id = id;
        this.fio = fio;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getPersonBookList() {
        return personBookList;
    }

    public void setPersonBookList(List<Book> personBookList) {
        this.personBookList = personBookList;
    }
}
