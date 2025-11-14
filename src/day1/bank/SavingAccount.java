package day1.bank;

// Saving account with interest
public class SavingAccount extends Account {
    private final double annualInterestRate;
    protected SavingAccount(String owner, double balance, double annualInterestRate) {
        super(owner, balance);
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public void applyMonthlyUpdate() {
        double monthlyRate = this.annualInterestRate/12.0;
        double interest = this.getBalance()*monthlyRate;

        if(interest > 0){
            changeBalance(interest, new Transaction(Transaction.Type.DEPOSIT, interest, "Monthly interest deposited"));
        }
    }
}
