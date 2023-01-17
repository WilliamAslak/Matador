package Model;

import Model.ChanceCards.*;

import java.util.ArrayList;
import java.util.Collections;

public class ChanceDeck {
    private ArrayList<ChanceCard> chanceDeck = new ArrayList<>();

    public ChanceDeck() {
        initDeck();
    }

    private void initDeck() {
        for (int i = 0; i < 15; i++) {
            chanceDeck.add(new Birthday("Det er din fødselsdag. Modtag 1000kr fra alle spillere"));
            chanceDeck.add(new EatenTooMuch("Du har spist for meget. Betal 2000 kr"));
            chanceDeck.add(new MoveToStart("Ryk frem til start og modtag 4000 kr"));
            chanceDeck.add(new MoveFiveForward("Ryk 5 felter frem"));
            chanceDeck.add(new FullStopTicket("Du har kørt frem for “fuldt stop”, Betal 1000 kr i bøde "));
            chanceDeck.add(new CarRepair("Betal 3000 kr for reparation af deres vogn"));
            chanceDeck.add(new NewTires("Du har købt 4 nye dæk til Deres vogn, betal 1000 kr"));
            chanceDeck.add(new ParkingTicket("Du har fået en parkeringsbøde, betal 200 kr i bøde"));
            chanceDeck.add(new CarInsurance("Betal din bilforsikring, 1000 kr"));
            chanceDeck.add(new CustomsDuty("Du har været udenlands og købt for mange smøger, betal 200 kr i told."));
            chanceDeck.add(new DentistBill("Tandlægeregning, betal 2000 kr"));
            chanceDeck.add(new WonTheLottery("Du har vundet i klasselotteriet. Modtag 500 kr"));
            chanceDeck.add(new ReceiveDividend("Du har modtaget aktieudbytte. Modtag 1000 kr af banken"));
            chanceDeck.add(new MoveThreeForward("Ryk tre felter frem"));
            chanceDeck.add(new MoveThreeBack("Ryk tre felter tilbage"));
            chanceDeck.add(new CarWash("Betal 300 kr for vognvask og smøring."));
        }

        Collections.shuffle(chanceDeck);
    }

    public ChanceCard drawCard() {
        ChanceCard topCard = chanceDeck.remove(0);
        chanceDeck.add(topCard);
        return topCard;
    }
}
