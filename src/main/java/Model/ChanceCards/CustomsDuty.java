package Model.ChanceCards;

import Controller.GameController;
import Model.Player;

public class CustomsDuty extends ChanceCard {

    public CustomsDuty(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(200);
        GameController.updateMoneyCounter(200);
    }
}
