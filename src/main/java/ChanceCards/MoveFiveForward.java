package ChanceCards;

import spil.Player;

public class MoveFiveForward extends ChanceCard {

    public MoveFiveForward(String description) {
        super(description);
    }

    public void process(Player[] players, Player current){
        current.setPosition(current.getPosition()+5);
    }
}
