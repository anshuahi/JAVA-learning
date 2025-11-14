package day1.bank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

abstract class Account {
    private final String id;
    private final String owner;
    private double balance;
    private final List<Transaction> statements = new ArrayList<>();

    protected Account(String owner, double balance){
        if(balance < 0) {
            throw new InvalidAmountException("Account can not be initiated with negative amount");
        }
        this.id = UUID.randomUUID().toString();
        this.owner = owner;
        this.balance = balance;
        this.statements.add(new Transaction(Transaction.Type.DEPOSIT, balance, "Account initiated"));
    }

    String getId(){ return this.id; }
    String getOwner(){ return this.owner; }

    public synchronized void deposit(double amt) {
        if(amt < 0) {
            throw new InvalidAmountException("Deposit > 0 is required");
        }
        this.balance += amt;
        this.statements.add(new Transaction(Transaction.Type.DEPOSIT, amt, "Deposit"));
    }

    public void addStatement(Transaction t){
        this.statements.add(t);
    }

    public synchronized void withdraw(double amt){
        if(amt < 0) {
            throw new InvalidAmountException("Withdraw > 0 is required");
        }
        if(this.balance < amt){
            throw new InsufficientFundsException("Insufficient Funds");
        }
        this.balance -= amt;
        this.statements.add(new Transaction(Transaction.Type.WITHDRAW, amt, "Withdraw"));
    }

    public synchronized double getBalance(){
        return this.balance;
    }

    protected synchronized void changeBalance(double delta, Transaction t){
        this.balance += delta;
        this.statements.add(t);
    }

    public List<Transaction> getStatements(){
        return Collections.unmodifiableList(this.statements);
    }

    public abstract void applyMonthlyUpdate();
}
