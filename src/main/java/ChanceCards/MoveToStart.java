package ChanceCards;

import spil.Player;

public class MoveToStart extends ChanceCard {

    public MoveToStart(String description) {
        super(description);
    }

    public void process(Player[] in, Player with){
        System.out.println("Du skal rykke tilbage til Start-feltet og modtage 2M");
        with.setPosition(0);
        with.getAccount().deposit(2);
    }
}
