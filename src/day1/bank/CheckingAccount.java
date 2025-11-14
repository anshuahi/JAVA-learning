package day1.bank;

public class CheckingAccount extends Account {
    private final double overdraftLimit;
    protected CheckingAccount(String owner, double balance, double overdraftLimit) {
        super(owner, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public synchronized void withdraw(double amt){
        if(amt <= 0) throw new InvalidAmountException("Withdraw > 0 required!");
        double allowed = this.getBalance() + overdraftLimit;
        if(amt > allowed) throw new InsufficientFundsException("Exceeding overdraft limit!");
        changeBalance(-amt, new Transaction(Transaction.Type.WITHDRAW, amt, "Withdraw"));
    }

    @Override
    public void applyMonthlyUpdate() {
        if(this.getBalance() < 0) {
            double fee = 20.0;
            changeBalance(-fee, new Transaction(Transaction.Type.WITHDRAW, fee, "Overdraft fee"));
        }
    }
}
