package org.turkcell;

import org.turkcell.entities.Book;

import java.util.List;

public class Member extends User{
    private List<Book> books;
    private int debt;

    public Member() {

    }

    public Member(List<Book> books, int debt) {
        this.books = books;
        this.debt = debt;
    }

    public Member(int id, String name, List<Book> books, int debt) {
        super(id, name);
        this.books = books;
        this.debt = debt;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
}
