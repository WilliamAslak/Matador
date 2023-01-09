package Model.ChanceCards;

import Model.Player;

public class NewTires extends ChanceCard{
    public NewTires(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(1000);
    }
}
