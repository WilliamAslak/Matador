package ChanceCards;

import spil.Player;

public class EatenTooMuch extends ChanceCard {

    public EatenTooMuch(String description) {
        super(description);
    }
    public void process(Player[] players, Player current){
        current.getAccount().withdraw(2);
    }
}
