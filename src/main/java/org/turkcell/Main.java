package org.turkcell;

import org.turkcell.entities.Book;
import org.turkcell.entities.Employee;
import org.turkcell.entities.Member;
import org.turkcell.entities.Transaction;
import org.turkcell.logger.BaseLogger;
import org.turkcell.logger.FileLogger;
import org.turkcell.repository.TransactionRepositoryImpl;
import org.turkcell.service.BookService;
import org.turkcell.service.BookServiceImpl;


import java.util.ArrayList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //members
        Member member1 = new Member(1, "member1", new ArrayList<Book>(), 0);
        Member member2 = new Member(2, "member2", new ArrayList<Book>(), 0);

        //employees
        Employee employee1 = new Employee(1, "employee1", new ArrayList<Transaction>());
        Employee employee2 = new Employee(2, "employee2", new ArrayList<Transaction>());

        //books
        Book book1 = new Book(1, "book1", true);
        Book book2 = new Book(2, "book2", true);

        //Logger
        BaseLogger fileLogger = new FileLogger();
        BaseLogger databaseLogger = new FileLogger();

        //book service with file logger and employee1
        BookService bookService1 = new BookServiceImpl(employee1, fileLogger, new TransactionRepositoryImpl());
        bookService1.addBook(member1, book1);
        bookService1.addBook(member1, book2);
        bookService1.updateBook(member1, book1);


        //book service with database logger and employee2
        BookService bookService2 = new BookServiceImpl(employee2, databaseLogger, new TransactionRepositoryImpl());
        bookService2.addBook(member2, book1);
        bookService2.addBook(member2, book2);
        bookService2.updateBook(member2, book2);

        //List member1 books
        System.out.println("Books of member ID: " + member1.getId());
        List<Book> books = member1.getBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).getId());
        }

        //List employee1 transactions
        System.out.println("Transactions of employee ID: " + employee1.getId());
        List<Transaction> transactions = employee1.getTransactions();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(transactions.get(i).getId());
        }

    }
}