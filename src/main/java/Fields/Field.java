package Fields;

import gui_fields.GUI_Field;

public abstract class Field {

    String name;

    public Field(String name){
        this.name=name;
    }

    abstract public GUI_Field toGui();

    public String getName() {
        return name;
    }

}
