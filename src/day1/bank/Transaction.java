package day1.bank;

import java.time.LocalDateTime;
import java.util.*;

final class Transaction {
    enum Type { DEPOSIT, WITHDRAW, TRANSFER };
    private final UUID uuid = UUID.randomUUID();
    private final LocalDateTime time = LocalDateTime.now();
    private final Type type;
    private final double amount;
    private final String note;

    Transaction(Type type, double amount, String note){
        this.type = type;
        this.amount = amount;
        this.note = note;
    }

    @Override
    public String toString(){
        return time + " | " + type + " | " + amount + " | " + note;
    }
}
