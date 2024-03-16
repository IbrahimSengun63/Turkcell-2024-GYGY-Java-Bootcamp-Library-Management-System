package org.turkcell.service;

import org.turkcell.entities.Member;
import org.turkcell.entities.Book;

public interface BookService {
    void addBook(Member member, Book book);
    void updateBook(Member member,Book book);
}
