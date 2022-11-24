package spil;

public class Account {
    private int wallet;

    public Account(int wallet){
        this.wallet = wallet;
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

    public String withdraw(int x){
        if (wallet - x < 0) {
            return "You cannot withdraw this amount";
        }
        else {
            wallet -= x;
            return "Your new balance is: " + wallet;
        }
    }

    public String toString(){
        return "Current player wallet: " + wallet;
    }


}
