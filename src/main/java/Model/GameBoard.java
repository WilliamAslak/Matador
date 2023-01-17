package Model;

import Model.Fields.*;
import gui_fields.GUI_Tax;

import java.awt.*;

public class GameBoard {
    private Field[] fields;

    public GameBoard() {
        initFields();
    }

    private void initFields() {
        fields = new Field[]{
                new Start(4000),
                new Street("RØDOVREVEJ", Color.BLUE, 1200),
                new Chance(),
                new Street("Hvidovrevej", Color.BLUE, 1200),
                new Tax("Betal indkomstskat: 10% eller 4000", 4000),
                new Metro(),
                new Street("Roskildevej", Color.orange, 2000),
                new Chance(),
                new Street("Valby Langgade",Color.orange, 2000),
                new Street("Allégade",Color.orange, 2400),
                new Jail(),
                new Street("Frederiksberg Allé",Color.yellow, 2800),
                new Street("Squash", Color.red,3000),
                new Street("Büllowsvej",Color.yellow, 2800),
                new Street("Gl. Kongevej",Color.yellow, 3200),
                new Metro(),
                new Street("Bernstoftsvej",Color.gray, 3600),
                new Chance(),
                new Street("Hellerupvej",Color.gray,3600),
                new Street("Strandvejen",Color.gray, 4000),
                new Parking(),
                new Street("Trianglen", Color.red,4000),
                new Chance(),
                new Street("Østerbrogade",Color.red, 4000),
                new Street("Grønningen",Color.red, 4800),
                new Metro(),
                new Street("Bredgade",Color.WHITE, 5200),
                new Street("Kgs. Nytorv",Color.WHITE, 5200),
                new Street("Coca Cola",Color.red, 3000),
                new Street("Østergade",Color.WHITE, 5600),
                new GoToJail(),
                new Street("Amagertorv",Color.yellow, 6000),
                new Street("Vimmelskaftet",Color.yellow, 6000),
                new Chance(),
                new Street("Nygade",Color.yellow, 6400),
                new Metro(),
                new Chance(),
                new Street("Frederiksberggade",Color.pink, 7000),
                new Tax("Ekstraordinær statsskat betal kr. 2000", 2000),
                new Street("Rådhuspladsen",Color.pink, 8000)
        };

    }

    public Field[] getFields() {
        return fields;
    }


}