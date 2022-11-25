package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;
import spil.Player;

import java.awt.*;

public class GoToJail extends Field {

    public GoToJail(){
       super ("FÆNGSEL");

    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Jail("default", super.getName(), "Gå i fængsel", "Gå i fængsel", Color.white, Color.BLACK);
    }

    @Override
    public String action() {
        return "fængsel";
    }
}
