package org.turkcell;

import org.turkcell.entities.Book;
import org.turkcell.entities.Transaction;
import org.turkcell.logger.DatabaseLogger;
import org.turkcell.logger.FileLogger;
import org.turkcell.logger.Logger;
import org.turkcell.repository.TransactionRepositoryImpl;
import org.turkcell.service.BookService;
import org.turkcell.service.BookServiceImpl;


import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        //members
        User member1 = new Member(1,"member1", new ArrayList<Book>(),0);
        User member2 = new Member(2,"member2", new ArrayList<Book>(),0);

        //employees
        User employee1 = new Employee(1,"employee1",new ArrayList<Transaction>());
        User employee2 = new Employee(1,"employee2",new ArrayList<Transaction>());

        //books
        Book book1 = new Book(1,"book1",true);
        Book book2 = new Book(2,"book2",true);

        //Logger


        BookService bookService = new BookServiceImpl((Employee) employee1,new DatabaseLogger(),new TransactionRepositoryImpl());
        bookService.addBook((Member) member1,book1);
        bookService.updateBook((Member) member1,book1);
    }
}