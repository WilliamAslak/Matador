package Model;

import Model.Fields.Field;
import java.util.ArrayList;

public class Player {
    private String name;
    private Account acc;
    private boolean isInJail = false;
    private int playerPosition = 0;
    private ArrayList<Integer> ownedPropertiesPositions = new ArrayList<>();
    private ArrayList<Field> ownedProperties = new ArrayList<>();

    public Player(String name, int startMoney){
        this.name = name;
        this.acc = new Account(startMoney);

    }

    public String getName() {
        return name;
    }

    public void addPropertyPosition(Integer position) {
        ownedPropertiesPositions.add(position);
        addProperty(position);
    }
    public void addProperty(Integer position) {ownedProperties.add(GameBoard.getFields()[position]);}

    public ArrayList<Integer> getOwnedPropertiesPositions() {
        return ownedPropertiesPositions;
    }
    public ArrayList<Field> getOwnedProperties(){
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
