package Model.ChanceCards;

import Controller.GameController;
import Model.Player;

public class CarRepair extends ChanceCard{

    public CarRepair (String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(3000);
        GameController.updateMoneyCounter(3000);
    }


}
