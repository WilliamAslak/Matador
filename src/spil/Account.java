package spil;

public class Account {
    private int balance;

    public Account(int balance){
        this.balance = balance;
    }


    public int getBalance() {
        return balance;
    }

    public String updateBalance(int x){
        if (balance + x < 0) {
            return "You cannot withdraw this amount";
        }
        balance += x;
        return "Your new balance is: " + balance;
    }

    public String toString(){
        return "Your account balance is: " + balance;
    }

     /*
    public void setWallet(int newWallet) {
        if (newWallet < 0) {
            System.out.println("Wallet cannot be less than 0");
        }
        else {
            wallet = newWallet;
        }
    }
     */

}
