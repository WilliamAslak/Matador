package spil;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        run();
    }

    public static void run(){

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Rafflecup rc = new Rafflecup();
        Field[] fields = initializeFields();
        Player p1 = new Player();
        Player p2 = new Player();
        Scanner scan = new Scanner(System.in);


        System.out.println("Starting game...");
        System.out.println("Press enter to shake the raffle cup and roll the dice.");

        while(true){
            roll(rc, d1, d2, p1, fields, scan);
        }
    }


    public static void roll(Rafflecup rc, Dice d1, Dice d2, Player p, Field[] fields, Scanner scan) {
        System.out.println("Player " + p.getPlayerNumber() + "'s turn to shake and roll the dice!");
        scan.nextLine();

        rc.roll(d1, d2);
        int rollValue = d1.getFaceValue() + d2.getFaceValue();
        System.out.println("You rolled: " + rollValue);
        System.out.println();

        p.getAccount().deposit(fields[rollValue-2].getValue());
        System.out.println();

        if (rollValue == 10) {
            System.out.println("You got an extra turn!");
            roll(rc,d1,d2,p, fields, scan);
        }



    }
    /*
    public static boolean hasWon(Player p) {
        if (p.getPoints() >= 3000) {
            System.out.println("Player " + p.getPlayerNumber() + " has won the game!");
            return true;
        }
        return false;
    }*/


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
