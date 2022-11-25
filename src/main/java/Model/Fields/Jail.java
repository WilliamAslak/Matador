package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import Model.Player;

import java.awt.*;

public class Jail extends Field {


    public Jail(){
        super("FÆNGSEL");
    }

    public void release(Player p){
        p.setInJail(false);
    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Jail("default", super.getName(), "fængsel",
                "fængsel", Color.white, Color.BLACK);
    }

    @Override
    public String action() {
        return "";
    }
}
