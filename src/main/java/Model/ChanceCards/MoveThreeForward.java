package Model.ChanceCards;

import Model.Player;

public class MoveThreeForward extends ChanceCard {

    public MoveThreeForward(String description) {super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.setPosition(current.getPosition() + 3);
    }

}