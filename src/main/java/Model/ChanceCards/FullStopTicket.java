package Model.ChanceCards;

import Model.Player;

public class FullStopTicket extends ChanceCard {

    public FullStopTicket (String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(1000);}
}
