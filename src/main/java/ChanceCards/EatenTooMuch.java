package ChanceCards;

import spil.Player;

public class EatenTooMuch extends ChanceCard {

    public EatenTooMuch(String description) {
        super(description);
    }
    public void process(Player[] in, Player with){
        with.getAccount().withdraw(2);
        System.out.println("Æde");
    }
}
