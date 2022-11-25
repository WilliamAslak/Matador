package Model.Fields;

import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;

import java.awt.*;

public class Chance extends Field{

    public Chance(){
        super ("Chance");

    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Chance("?", "Chance" , "Tag et chance kort", Color.white, Color.black);
    }

    @Override
    public String action() {
        return "chance";
    }
}
