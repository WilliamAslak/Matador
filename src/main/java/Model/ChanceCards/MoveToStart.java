package Model.ChanceCards;

import Model.Player;

public class MoveToStart extends ChanceCard {

    public MoveToStart(String description) {
        super(description);
    }

    public void process(Player[] players, Player current){
        if (current.getPosition() == 0) return;
        current.setPosition(0);
        current.getAccount().deposit(4000);
    }
}
