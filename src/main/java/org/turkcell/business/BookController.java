package org.turkcell.business;

import org.turkcell.entities.Book;

public class BookController {

    public Boolean checkStatus(Book book) {
        return book.getStatus();
    }
}
