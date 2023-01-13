package Model.ChanceCards;

import Model.Player;

public class MoveFiveForward extends ChanceCard {

    public MoveFiveForward(String description) {
        super(description);
    }

    public void process(Player[] players, Player current){
        if (current.getPosition() + 5 >= 40) {
            current.setPosition(current.getPosition() + 5 - 40);
            current.getAccount().deposit(4000);
        }
        else
            current.setPosition(current.getPosition()+5);
    }
}
