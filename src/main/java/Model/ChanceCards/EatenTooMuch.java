package Model.ChanceCards;

import Model.Player;

public class EatenTooMuch extends ChanceCard {

    public EatenTooMuch(String description) {
        super(description);
    }
    public void process(Player[] players, Player current){
        current.getAccount().withdraw(2);
    }
}
