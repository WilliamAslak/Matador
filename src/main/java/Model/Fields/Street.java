package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

public class Street extends Field{
    private Color color;
    private int price;
    private boolean isOwned = false;

    private int[] rent;
    private int house;
    private int housePrice;

    public Street(String name, Color color, int price,int housePrice,int[] rent){
        super(name);
        this.color=color;
        this.price=price;
        this.housePrice = housePrice;
        //mængden af huse der er på det bestemte felt
        //0-4 = mængden af huse; 5 = hotel.
        this.house = 0;
        this.rent = rent;
    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Street(super.getName(), "" + price,
                super.getName(), price + "", color, Color.black);

    }

    @Override
    public String action() {
        if (isOwned) return "ejet";
        return "ledig";
    }

    public void buildHouse(int amount){
        house+=amount;
    }
    public int getHouse(){
        return house;
    }
    public void setIsOwned(boolean owned) {
        isOwned = owned;
    }

    public int getPrice() {
        return price;
    }
    public int getRent(){
        return rent[house];
    }
    public int getHousePrice(){
        return housePrice;
    }

    public Color getColor(){return color;}
}
