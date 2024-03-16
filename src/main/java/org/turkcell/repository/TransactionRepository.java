package org.turkcell.repository;

import org.turkcell.Member;
import org.turkcell.entities.Book;

public interface TransactionRepository {
    void registerBookBorrowing(Member member, Book book);
    void registerBookReturning(Member member,Book book);
}
