package spil;

import java.sql.SQLOutput;

public class Game {
    public static void main(String[] args) {

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Rafflecup rc = new Rafflecup();
        Field[] fields = initializeFields();


    }

    public static Field[] initializeFields() {
        Field[] fields = new Field[11];
        fields[0] = new Field("Tower", 250);
        fields[1] = new Field("Crater", -100);
        fields[2] = new Field("Palace gates", 100);
        fields[3] = new Field("Cold Desert", -20);
        fields[4] = new Field("Walled city ", 180);
        fields[5] = new Field("Monastery", 0);
        fields[6] = new Field("Black cave", -70);
        fields[7] = new Field("Huts in the mountain", 60);
        fields[8] = new Field("The Werewall (werewolf-wall)", -80);
        fields[8].setExtraTurn(true);
        fields[9] = new Field("The pit", -50);
        fields[10] = new Field("Goldmine", 650);
        return fields;
    }

}
