package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import spil.Player;

import java.awt.*;
import java.util.ArrayList;

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
