package Model.ChanceCards;

import Model.Player;

public class CarInsurance extends ChanceCard{
    public CarInsurance(String description){super(description);}

    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(1000);
    }





}
