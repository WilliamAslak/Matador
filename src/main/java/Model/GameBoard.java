package Model;

import Model.Fields.*;
import gui_fields.GUI_Tax;

import java.awt.*;
import java.util.ArrayList;

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
                new Street("Scandilines",Color.BLUE, 4000),
                new Street("Roskildevej", Color.orange, 2000),
                new Chance(),
                new Street("Valby Langgade",Color.orange, 2000),
                new Street("Allégade",Color.orange, 2400),
                new Jail(),
                new Street("Frederiksberg Allé",Color.yellow, 2800),
                new Street("Squash", Color.red,3000),
                new Street("Büllowsvej",Color.yellow, 2800),
                new Street("Gl. Kongevej",Color.yellow, 3200),
                new Street("Mols. Linien", Color.red,4000),
                new Street("Bernstoftsvej",Color.gray, 3600),
                new Chance(),
                new Street("Hellerupvej",Color.gray,3600),
                new Street("Strandvejen",Color.gray, 4000),
                new Parking(),
                new Street("Trianglen", Color.red,4000),
                new Chance(),
                new Street("Østerbrogade",Color.red, 4000),
                new Street("Grønningen",Color.red, 4800),
                new Street("Scandilines",Color.blue, 4000),
                new Street("Bredgade",Color.WHITE, 5200),
                new Street("Kgs. Nytorv",Color.WHITE, 5200),
                new Street("Coca Cola",Color.red, 3000),
                new Street("Østergade",Color.WHITE, 5600),
                new GoToJail(),
                new Street("Amagertorv",Color.YELLOW, 6000),
                new Street("Vimmelskaftet",Color.YELLOW, 6000),
                new Chance(),
                new Street("Nygade",Color.YELLOW, 6400),
                new Street("Scandilines",Color.blue, 4000),
                new Chance(),
                new Street("Frederiksberggade",Color.pink, 7000),
                new Tax("Ekstraordinær statsskat betal kr. 2000", 2000),
                new Street("Rådhuspladsen",Color.pink, 8000)
        };

    }

    public Field[] getFields() {
        return fields;
    }
    public Field[] getFieldOfColor(Color color){
        //bruger arraylist da jeg ikke ved om der er 2 eller 3 elementer i arrayet
        ArrayList<Field> fieldOfColor = new ArrayList<>();
        for (int i = 0; i < getFields().length; i++){
            if(getFields()[i].getClass().equals(Street.class))
                if(((Street) getFields()[i]).getColor().equals(color))
                    fieldOfColor.add(getFields()[i]);
        }
        //midlertidig scuffed løsning
        Field[] fields1 = new Field[fieldOfColor.size()];
        for(int j = 0; j < fieldOfColor.size(); j++)
            fields1[j] = fieldOfColor.get(j);
        return fields1;
    }


}