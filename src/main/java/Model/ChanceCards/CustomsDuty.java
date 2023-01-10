package Model.ChanceCards;

import Model.Player;

public class CustomsDuty extends ChanceCard {

    public CustomsDuty(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(200);
    }
}
