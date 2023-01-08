package Model.ChanceCards;

import Model.Player;

public class MoveFiveForward extends ChanceCard {

    public MoveFiveForward(String description) {
        super(description);
    }

    public void process(Player[] players, Player current){
        if (current.getPosition() == 20){
            current.setPosition(1);
            current.getAccount().deposit(2);
        }
        else
            current.setPosition(current.getPosition()+5);
    }
}
