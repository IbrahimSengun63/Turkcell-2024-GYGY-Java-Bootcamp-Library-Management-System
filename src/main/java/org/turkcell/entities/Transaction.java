package org.turkcell.entities;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private Member member;
    private Employee employee;
    private Book book;
    private LocalDate startDate;
    private LocalDate endDate;
    private String operation;

    public Transaction() {
    }

    public Transaction(int id, Member member, Employee employee, Book book, LocalDate startDate, LocalDate endDate, String operation) {
        this.id = id;
        this.member = member;
        this.employee = employee;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
        this.operation = operation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
