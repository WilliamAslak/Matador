package Model;

import Model.Fields.*;
import gui_fields.GUI_Tax;

import java.awt.*;
import java.util.ArrayList;

public class GameBoard {
    private static Field[] fields;

    public GameBoard() {
        initFields();
    }

    private void initFields() {
        Color darkYellow    = new Color(200, 200, 0);
        Color streetRed = new Color(200,0,0);
        Color purple = new Color(128, 0, 128);
        fields = new Field[]{
                new Start(4000),
                new Street("RØDOVREVEJ", Color.cyan, 1200,1000, new int[]{50, 250,750,2250,4000,6000}),
                new Chance(),
                new Street("Hvidovrevej", Color.cyan, 1200,1000, new int[]{50,250,400,750,2250,6000}),
                new Tax("Betal indkomstskat: 10% eller 4000", 4000),
                new Metro(),
                new Street("Roskildevej", Color.orange, 2000,1000,new int[]{100,600,1800,5400,8000,11000}),
                new Chance(),
                new Street("Valby Langgade",Color.orange, 2000,1000,new int[]{100,600,1800,5400,8000,11000}),
                new Street("Allégade",Color.orange, 2400,1000,new int[]{150,800,2000,6000,9000,12000}),
                new Jail(),
                new Street("Frederiksberg Allé",darkYellow, 2800,2000,new int[]{200,1000,3000,9000,12500,15000}),
                new Street("Squash", Color.red,3000,0,new int[]{3000}),
                new Street("Büllowsvej",darkYellow, 2800,2000,new int[]{200,1000,3000,9000,12500,15000}),
                new Street("Gl. Kongevej",darkYellow, 3200,2000,new int[]{250,1250,3750,10000,14000,18000}),
                new Metro(),
                new Street("Bernstoftsvej",Color.gray, 3600,2000,new int[]{300,1400,4000,11000,15000,19000}),
                new Chance(),
                new Street("Hellerupvej",Color.gray,3600,2000,new int[]{300,1400,4000,11000,15000,19000}),
                new Street("Strandvejen",Color.gray, 4000,2000,new int[]{350,1600,4400,12000,16000,20000}),
                new Parking(),
                new Street("Trianglen", streetRed,4000,3000,new int[]{350,1800,5000,14000,17500,21000}),
                new Chance(),
                new Street("Østerbrogade",streetRed, 4000,3000,new int[]{350,1800,5000,14000,17500,21000}),
                new Street("Grønningen",streetRed, 4800,3000,new int[]{400,2000,6000,15000,18500,22000}),
                new Metro(),
                new Street("Bredgade",Color.WHITE, 5200,3000,new int[]{450,2200,6600,16000,19500,23000}),
                new Street("Kgs. Nytorv",Color.WHITE, 5200,3000,new int[]{450,2200,6600,16000,19500,23000}),
                new Street("Coca Cola",Color.red, 3000,0,new int[]{3000}),
                new Street("Østergade",Color.WHITE, 5600,3000,new int[]{500,2400,7200,17000,20500,24000}),
                new GoToJail(),
                new Street("Amagertorv",Color.YELLOW, 6000,4000,new int[]{550,2600,7800,18000,22000,25000}),
                new Street("Vimmelskaftet",Color.YELLOW, 6000,4000,new int[]{550,2600,7800,18000,22000,25000}),
                new Chance(),
                new Street("Nygade",Color.YELLOW, 6400,4000,new int[]{600,3000,9000,20000,24000,28000}),
                new Metro(),                new Chance(),
                new Street("Frederiksberggade",purple, 7000,4000,new int[]{700,3500,10000,22000,26000,30000}),
                new Tax("Ekstraordinær statsskat betal kr. 2000", 2000),
                new Street("Rådhuspladsen",purple, 8000,4000,new int[]{1000,4000,12000,28000,34000,40000})
        };

    }

    public static Field[] getFields() {
        return fields;
    }
    public Field[] getFieldOfColor(Color color){
        //bruger arraylist da jeg ikke ved om der er 2 eller 3 elementer i arrayet
        ArrayList<Field> fieldOfColor = new ArrayList<>();
        for (int i = 0; i < getFields().length; i++){
            if(getFields()[i].getClass().equals(Street.class))
                if(((Street) getFields()[i]).getColor().equals(color) && ((Street) getFields()[i]).getHouse() != 5)
                    fieldOfColor.add(getFields()[i]);
        }
        //midlertidig scuffed løsning
        Field[] fields1 = new Field[fieldOfColor.size()];
        for(int j = 0; j < fieldOfColor.size(); j++)
            fields1[j] = fieldOfColor.get(j);
        return fields1;
    }


}