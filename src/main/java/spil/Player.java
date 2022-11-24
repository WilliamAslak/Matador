package spil;

import Fields.Street;

import java.util.ArrayList;

public class Player {
    private String name;
    private Account acc;
    private boolean isInJail = false;
    private int playerPosition = 0;
    private ArrayList<Street> ownedProperties = new ArrayList<>();

    public Player(String name, int startMoney){
        this.name = name;
        this.acc = new Account(startMoney);

    }

    public String getName() {
        return name;
    }

    public void addProperty(Street street) {
        ownedProperties.add(street);
    }

    public ArrayList<Street> getOwnedProperties() {
        return ownedProperties;
    }
    public int getPosition(){
        return playerPosition;
    }
    public void setPosition(int x ){
        playerPosition=x;
    }

    public void setInJail(Boolean inJail) {
        isInJail = inJail;
    }

    public boolean isInJail() {return isInJail;}

    public Account getAccount(){
        return acc;
    }

}
