package day1.bank;

class AccountNotFoundException extends RuntimeException{
    AccountNotFoundException(String msg){
        super(msg);
    }
}
