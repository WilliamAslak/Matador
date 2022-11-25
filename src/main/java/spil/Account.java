package spil;

public class Account {
    private int wallet;
    private boolean isEmpty;

    public Account(int wallet){
        this.wallet = wallet;
        isEmpty = false;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int newWallet) {
        if (newWallet < 0) {
            System.out.println("Wallet cannot be less than 0");
        }
        else {
            wallet = newWallet;
        }
    }

    public String deposit(int x){
        wallet += x;
        return "Your new balance is: " + wallet;
    }

    public void withdraw(int x){
        if (wallet - x < 0) {
            isEmpty = true;
            wallet = 0;
        }
        else {
            wallet -= x;
        }
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public String toString(){
        return "Current player wallet: " + wallet;
    }


}
