package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

public class Street extends Field{
    private Color color;
    private int price;
    private boolean isOwned = false;

    public Street(String name, Color color, int price){
        super(name);
        this.color=color;
        this.price=price;
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

    public void setIsOwned(boolean owned) {
        isOwned = owned;
    }

    public int getPrice() {
        return price;
    }

    public Color getColor(){return color;}
}
