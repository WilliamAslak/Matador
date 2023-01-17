package Model.ChanceCards;

import Controller.GameController;
import Model.Player;

public class CarWash extends ChanceCard{
    public CarWash(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(300);
        GameController.updateMoneyCounter(300);
    }
}
