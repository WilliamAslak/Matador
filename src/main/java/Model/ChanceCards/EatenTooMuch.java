package Model.ChanceCards;

import Controller.GameController;
import Model.Player;

public class EatenTooMuch extends ChanceCard {

    public EatenTooMuch(String description) {
        super(description);
    }
    public void process(Player[] players, Player current){
        current.getAccount().withdraw(2000);
        GameController.updateMoneyCounter(2000);
    }
}
