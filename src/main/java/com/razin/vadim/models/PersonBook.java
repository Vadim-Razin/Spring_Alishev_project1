package com.razin.vadim.models;

public class PersonBook {

    private int personId;
    private int bookId;
    private String fio;
    private String title;
    private String author;
    private int bookYear;

    public PersonBook() {
    }


    public PersonBook(int personId, int bookId, String fio, String title, String author, int bookYear) {
        this.personId = personId;
        this.bookId = bookId;
        this.fio = fio;
        this.title = title;
        this.author = author;
        this.bookYear = bookYear;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }
}
