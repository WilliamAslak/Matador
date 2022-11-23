package spil;

import Fields.*;

import java.util.ArrayList;

public class Gameboard {
    ArrayList<Field> fields;

    public Gameboard() {
        fields = new ArrayList<>();
    }

    public void initFields() {

        fields.add(new Start(2));

        fields.add(new Street("BURGERBAREN","brown", 1));
        fields.add(new Street("PIZZERIAET","brown", 1));

        fields.add(new Chance());

        fields.add(new Street("SLIKBUTIKKEN","grey", 1));
        fields.add(new Street("ISKIOSKEN","grey", 1));

        fields.add(new Jail());

        fields.add(new Street("MUSEET","pink", 2));
        fields.add(new Street("BIBLIOTEKET","pink", 2));

        fields.add(new Chance());

        fields.add(new Street("SKATERPARKEN","orange", 2));
        fields.add(new Street("SWIMMINGPOOLEN","orange", 2));

        fields.add(new Parking());

        fields.add(new Street("SPILLEHALLEN","red", 3));
        fields.add(new Street("BIOGRAFEN","red", 3));

        fields.add(new Chance());

        fields.add(new Street("LEGETØJSBUTIKKEN","yellow", 3));
        fields.add(new Street("DYREHANDLEN","yellow", 3));

        fields.add(new GoToJail());

        fields.add(new Street("BOWLINGHALLEN","green", 4));
        fields.add(new Street("ZOO","green", 4));

        fields.add(new Chance());

        fields.add(new Street("VANDLANDET","blå", 5));
        fields.add(new Street("STRANDPROMENADEN","blå", 5));

    }

    public Field getField(int pos) {
        return fields.get(pos);
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

}