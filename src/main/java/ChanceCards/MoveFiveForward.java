package ChanceCards;

import spil.Player;

public class MoveFiveForward extends ChanceCard {

    public MoveFiveForward(String description) {
        super(description);
    }

    public void process(Player[] in, Player with){
        with.setPosition(with.getPosition()+5);
    }
}
