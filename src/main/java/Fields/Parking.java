package Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Refuge;

import java.awt.*;

public class Parking extends Field {
    public Parking(){
        super("PARKERING");

    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Refuge("default", super.getName(), "Gratis parkering", "", Color.white, Color.black);
    }
}
