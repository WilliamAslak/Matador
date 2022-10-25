package spil;

public class Account {
    private int balance;

    public Account(int balance){
        this.balance = balance;
    }


    public int getBalance() {
        return balance;
    }

    public String deposit(int x){
        balance += x;
        return "Transaction successful. " + toString();
    }

    public String withdraw(int x){
        if (balance + x < 0 ) {
            balance = 0;
            return "Transaction failed due to insufficient funds. Your balance has been set to 0";
        }
        balance += x;
        return "Transaction successful. " + toString();
    }

    public String toString(){
        return "Your account balance is: " + balance;
    }


    public void setBalance(int newBalance) {
        if (newBalance < 0) {
            System.out.println("Wallet cannot be less than 0");
        }
        else {
            balance = newBalance;
        }
    }


}
