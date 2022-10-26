package spil;

public class Player {

    private Account acc;
    private int playerNumber;
    private static int nextPlayerNumber = 1;

    public Player(){
        this.acc = new Account();
        this.playerNumber = Player.nextPlayerNumber;
        Player.nextPlayerNumber++;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Account getAccount(){
        return acc;
    }

}
