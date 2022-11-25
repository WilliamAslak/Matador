package Model;

import Model.Fields.*;

import java.awt.*;

public class GameBoard {
    private Field[] fields;

    public GameBoard() {
        initFields();
    }

    private void initFields() {
        fields = new Field[]{
                new Start(2),
                new Street("BURGERBAREN", new Color(153,102,0), 1),
                new Street("PIZZERIAET",new Color(153,102,0), 1),
                new Chance(),
                new Street("SLIKBUTIKKEN",Color.lightGray, 1),
                new Street("ISKIOSKEN",Color.lightGray, 1),
                new Jail(),
                new Street("MUSEET",Color.pink, 2),
                new Street("BIBLIOTEKET",Color.pink, 2),
                new Chance(),
                new Street("SKATERPARKEN",Color.orange, 2),
                new Street("SWIMMINGPOOLEN",Color.orange, 2),
                new Parking(),
                new Street("SPILLEHALLEN",Color.red, 3),
                new Street("BIOGRAFEN",Color.red, 3),
                new Chance(),
                new Street("LEGETØJSBUTIKKEN",Color.yellow, 3),
                new Street("DYREHANDLEN",Color.yellow, 3),
                new GoToJail(),
                new Street("BOWLINGHALLEN",Color.green, 4),
                new Street("ZOO",Color.green, 4),
                new Chance(),
                new Street("VANDLANDET",Color.blue, 5),
                new Street("STRANDPROMENADEN",Color.blue, 5)
        };

    }

    public Field[] getFields() {
        return fields;
    }


}