package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Start;

import java.awt.*;

public class Start extends Field {
    private int receiveMoney;

    public Start(int receiveMoney){
        super ("START");
        this.receiveMoney=receiveMoney;
    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Start(super.getName(),"Modtag 2M", "Modtag 2M", Color.red, Color.BLACK);
    }

    @Override
    public String action() {
        return "";
    }
}
