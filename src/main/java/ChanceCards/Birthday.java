package ChanceCards;

import spil.Game;
import spil.Player;

public class Birthday extends ChanceCard {

    public Birthday(String description) {
        super(description);
    }

    public void process(Player[] in, Player with){
        for (int i = 0; i < in.length; i++) {
            if (!in[i].getName().equals(with.getName())) {
                in[i].getAccount().withdraw(1);
                System.out.println(in[i].getName());
                System.out.println(with.getName());
            }
            with.getAccount().deposit(1 * in.length -1);
        }
        System.out.println("Birthday");

    }
}
