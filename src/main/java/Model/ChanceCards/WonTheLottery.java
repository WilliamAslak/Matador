package Model.ChanceCards;

import Model.Player;

public class WonTheLottery extends ChanceCard {
    public WonTheLottery(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().deposit(500);
    }
}
