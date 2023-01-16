package Model.ChanceCards;

import Model.Player;
import Controller.GameController;

abstract public class ChanceCard {
    private String description;

    public ChanceCard(String description) {
        this.description = description;
    }

    abstract public void process(Player[] players, Player current);

    public String getDescription() {
        return description;
    }

}
