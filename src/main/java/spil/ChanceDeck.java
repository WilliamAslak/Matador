package spil;

import ChanceCards.*;

import java.util.ArrayList;
import java.util.Collections;

public class ChanceDeck {
    private ArrayList<ChanceCard> chanceDeck = new ArrayList<>();

    public ChanceDeck() {
        initDeck();
    }

    private void initDeck() {
        for (int i = 0; i < 4; i++) {
            chanceDeck.add(new Birthday("Det er din fødselsdag. Modtag 1M fra alle spillere"));
            chanceDeck.add(new EatenTooMuch("Du har spist for meget. Betal 2M"));
            chanceDeck.add(new MoveToStart("Ryk frem til start og modtag 2M"));
            chanceDeck.add(new MoveFiveForward("Ryk 5 felter frem"));
        }

        Collections.shuffle(chanceDeck);
    }

    public ChanceCard drawCard() {
        ChanceCard topCard = chanceDeck.remove(0);
        chanceDeck.add(topCard);
        return topCard;
    }
}
