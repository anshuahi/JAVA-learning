package day1.bank;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        String s1 = bank.createSavingAccount("Anshu", 107.34, 0.04);
        String s2 = bank.createCheckingAccount("Sahil", 120.23, 30);

        bank.find(s1).deposit(500);
        for (Transaction t : bank.find(s1).getStatements()){
            System.out.println(t.toString());
        }
        System.out.println(bank.find(s1).getBalance());
        try {
            bank.transfer(s1, s2, 500);
        }
        catch (RuntimeException ex){
            System.err.println("Transfer failed: " + ex.getMessage());
        }

        System.out.println(bank.find(s2).getBalance());
    }
}
