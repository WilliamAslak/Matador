package Fields;

import gui_fields.GUI_Field;
import spil.Player;

public abstract class Field {

    String name;

    public Field(String name){
        this.name=name;
    }

    abstract public GUI_Field toGui();

    public String getName() {
        return name;
    }

    abstract public String action();


}
