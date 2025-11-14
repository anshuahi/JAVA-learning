package day1.bank;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    public String createSavingAccount(String owner, double amount, double annualInterest){
        Account a = new SavingAccount(owner, amount, annualInterest);
        accounts.put(a.getId(), a);
        return a.getId();
    }

    public String createCheckingAccount(String owner, double amount, double overdraftLimit){
        Account a = new CheckingAccount(owner, amount, overdraftLimit);
        accounts.put(a.getId(), a);
        return a.getId();
    }

    public Account find(String id){
        Account account = accounts.get(id);
        if (account == null) throw new AccountNotFoundException("Account not found!");
        return account;
    }

    public void transfer(String fromId, String toId, double amount){
        if(amount <= 0) throw new InvalidAmountException("Amount should be > 0");
        Account from = find(fromId);
        Account to = find(toId);

        Account first = fromId.compareTo(toId) < 0 ? from : to;
        Account second = first == from ? to : from;

        synchronized (first) {
            synchronized (second) {
                // withdraw
                from.withdraw(amount);
                // deposit
                to.deposit(amount);

                from.addStatement(new Transaction(Transaction.Type.TRANSFER, amount,
                        "Amount " + amount + " transferred to " + to.getOwner()));
                to.addStatement(new Transaction(Transaction.Type.TRANSFER, amount,
                        "Amount " + amount + " received from " + from.getOwner()));
            }
        }
    }

    public void processMonthly(){
        for (Account a: accounts.values()){
            a.applyMonthlyUpdate();
        }
    }

    public Collection<Account> getAllAcounts(){
        return accounts.values();
    }
}
