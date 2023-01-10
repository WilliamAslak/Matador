package Model.ChanceCards;

import Model.Player;

public class DentistBill extends ChanceCard{
    public DentistBill(String description){super(description);}


    @Override
    public void process(Player[] players, Player current) {
        current.getAccount().withdraw(2000);
    }
}
