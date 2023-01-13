package Model.ChanceCards;

import Model.Player;

public class MoveThreeBack extends ChanceCard {

    public MoveThreeBack(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.setPosition(current.getPosition()-3);
    }
}
