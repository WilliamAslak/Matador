package Model.ChanceCards;

import Model.Player;

public class ReceiveDividend extends ChanceCard {
    public ReceiveDividend (String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().deposit(1000);
    }
}
