package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Refuge;

import java.awt.*;

public class Metro extends Field {
    public Metro(){
        super("METRO");

    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Refuge(null, super.getName(), "Metro", "Metro station", Color.white, Color.black);
    }

    @Override
    public String action() {
        return "metro";
    }
}