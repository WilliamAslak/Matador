package spil;

public class Player {

    private Account acc;
    private int playerNumber;
    private static int nextPlayerNumber = 1;
    private int playerPosition= 0;

    public Player(){
        this.playerPosition=playerPosition;
        this.acc = new Account();
        this.playerNumber = Player.nextPlayerNumber;
        Player.nextPlayerNumber++;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getPlayerPosition(){
        return playerPosition;
    }
    public void setPlayerPosition(int x ){
        playerPosition=x;
    }
    public Account getAccount(){
        return acc;
    }

}
