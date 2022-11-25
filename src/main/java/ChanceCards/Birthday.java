package ChanceCards;

import spil.Game;
import spil.Player;

public class Birthday extends ChanceCard {

    public Birthday(String description) {
        super(description);
    }

    public void process(Player[] players, Player current){
        for (int i = 0; i < players.length; i++) {
            if (!players[i].getName().equals(current.getName())) {
                players[i].getAccount().withdraw(1);
            }

            else current.getAccount().deposit(1 * players.length -1);
        }
    }
}
