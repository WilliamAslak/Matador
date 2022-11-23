package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import spil.Player;

import java.awt.*;
import java.util.ArrayList;

public class Jail extends Field {

    ArrayList<Player> inmates ;

    public Jail(){
    super("FÆNGSEL");
        inmates = new ArrayList<Player>();
    }
    public void putInJail(Player p){
    inmates.add(p);
    }

    public void release(Player p){
    inmates.remove(p);
    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Jail("default", super.getName(), "Gå i fængsel",
                "", Color.white, Color.BLACK);
    }
}
