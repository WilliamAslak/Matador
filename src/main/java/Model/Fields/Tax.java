package Model.Fields;

import gui_fields.GUI_Field;
import gui_fields.GUI_Tax;

import java.awt.*;

public class Tax extends Field {
    private int amount;

    public Tax(String name, int amount) {
        super(name);
        this.amount = amount;
    }

    @Override
    public GUI_Field toGui() {
        return new GUI_Tax(this.name, super.getName(),
                "skat", Color.white, Color.BLACK);
    }

    @Override
    public String action() {
        return "skat";
    }
}
