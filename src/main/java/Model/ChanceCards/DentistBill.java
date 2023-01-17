package Model.ChanceCards;

import Controller.GameController;
import Model.Player;

public class DentistBill extends ChanceCard{
    public DentistBill(String description){super(description);}


    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(2000);
        GameController.updateMoneyCounter(2000);
    }
}
