package org.turkcell;

import org.turkcell.entities.Transaction;

import java.util.List;

public class Employee extends User{
    private List<Transaction> transactions;

    public Employee() {

    }

    public Employee(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Employee(int id, String name, List<Transaction> transactions) {
        super(id, name);
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
