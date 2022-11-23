package Fields;

import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;

import java.awt.*;

public class Chance extends Field{

    public Chance(){
        super ("Chance");

    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Chance("?", "Chance" , "", Color.white, Color.black);
    }
}
