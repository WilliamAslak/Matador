package Model.ChanceCards;

import Model.Player;

public class CarRepair extends ChanceCard{

    public CarRepair (String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(3000);
    }


}
