package day1.bank;

class InsufficientFundsException extends RuntimeException{
    InsufficientFundsException(String msg){
        super(msg);
    }
}
