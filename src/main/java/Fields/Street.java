package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import spil.Player;

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
        return new GUI_Street(super.getName(), "",
                "", price + "M", color, Color.black);

    }

   public void purchase (Player p){

   }

   public void sell (Player p){

   }
}
