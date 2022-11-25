package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;

import java.awt.*;

public class Street extends Field{
    Color color;
    int price;
    boolean isOwned = false;

    public Street(String name, Color color, int price){
        super(name);
        this.color=color;
        this.price=price;
    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Street(super.getName(), "" + price + "M",
                super.getName(), price + "M", color, Color.black);

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


}
