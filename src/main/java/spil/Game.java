package spil;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        run();
    }

    public static void run(){

        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Rafflecup rc = new Rafflecup();
        Player p1 = new Player();
        Player p2 = new Player();
        Scanner scan = new Scanner(System.in);


        String[][] fieldInfo = {
                {"Start", "You start here"},
                {"Tower","You climbed the tower and found a chest on the top floor, you found 250 coins"},
                {"Crater", "You fell in the crater and broke your leg in the fall, you spend 100 gold on the hospital-bill."},
                {"Palace gates", "You arrive at the palace gates, and are greeted by the guards, for your hard travel you recieve 150 coins"},
                {"Cold Desert", "You're feeling slightly chilly and couldn't find your jacket so you get a little bit sick, you spend 20 gold on cough medicine."},
                {"Walled city", "You find the treasure hidden within the Walled city, you recieve 180 coins"},
                {"Monastery", "You meditate with the monks, as they do not care for money you spend nothing."},
                {"Black cave", "You seem to get lost within the Black cave, you lose 70 points"},
                {"Huts in the mountain", "You help the locals hunt down a mountain lion, Gain 60 gold for your efforts"},
                {"The Werewall (werewolf-wall)", "You fight off the vampires on the Werewall, but in the battle you drop 80 coins, however you are allowed to roll again"},
                {"The pit", "You fall down a steep pit, there's a vending machine in the corner of the pit selling ladders, you buy one for 50 gold and climb out."},
                {"Goldmine", "JACKPOT! You find your way to the Goldmine and mine the a big chunk of gold, you recieve 650 coins!"}
        };
        int[] fieldValues = {0,250, -100, 100, -20, 180, 0, -70, 60, -80, -50, 650};

        //Creating all the GUI
        GUI_Field[] fields = new GUI_Field[12];
        for (int i = 0; i < 12; i++) {
            fields[i] = new GUI_Street();
            fields[i].setTitle(String.valueOf(fieldInfo[i][0]));
            fields[i].setSubText(String.valueOf(fieldInfo[i][1]));
        }
        GUI gui = new GUI(fields);

        GUI_Car[] cars = new GUI_Car[2];
        cars[0] = new GUI_Car(Color.WHITE, Color.BLACK, GUI_Car.Type.UFO, GUI_Car.Pattern.CHECKERED);
        cars[1] = new GUI_Car(Color.CYAN, Color.PINK, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.DOTTED);

        GUI_Player[] gui_players = new GUI_Player[2];
        for(int i = 0; i < 2; i++) {
            //Creating the players
            gui_players[i] = new GUI_Player("P" + (i+1), 0, cars[i]);
            //Adding players to the board
            gui.addPlayer(gui_players[i]);
        }

        System.out.println("Starting game...");
        System.out.println("Press enter to shake the raffle cup and roll the dice.");

        //putting both cars on Start
        fields[0].setCar(gui_players[0], true);
        fields[0].setCar(gui_players[1], true);

        int[] saveLocation = {0,0};
        while(true){

            if (playTurn(rc, d1, d2, p1, fieldInfo, fieldValues, scan)) break;
            //gui
            fields[saveLocation[0]].removeAllCars();
            if(saveLocation[0] == saveLocation[1]) fields[saveLocation[0]].setCar(gui_players[1],true);
            saveLocation[0] = rc.getSum(d1,d2)-1;


            fields[rc.getSum(d1,d2)-1].setCar(gui_players[0], true);
            gui_players[0].setBalance(p1.getAccount().getWallet());


            if (playTurn(rc, d1, d2, p2, fieldInfo, fieldValues, scan)) break;
            //gui
            fields[saveLocation[1]].removeAllCars();
            if(saveLocation[0] == saveLocation[1]) fields[saveLocation[1]].setCar(gui_players[0],true);
            saveLocation[1] = rc.getSum(d1,d2)-1;

            fields[rc.getSum(d1,d2)-1].setCar(gui_players[1], true);
            gui_players[1].setBalance(p2.getAccount().getWallet());


        }

        scan.close();
    }


    public static boolean playTurn(Rafflecup rc, Dice d1, Dice d2, Player p, String[][] info, int[] values, Scanner scan) {

        System.out.println("Player " + p.getPlayerNumber() + "'s turn to shake and roll the dice!");

        scan.nextLine();

        rc.roll(d1, d2);

        int rollValue = rc.getSum(d1,d2);

        String title = info[rollValue-1][0];

        int value = values[rollValue-1];

        String description = info[rollValue-1][1];



        if(p.getAccount().getWallet()+value >= 0)
            System.out.println("You rolled: " + rollValue + "\n" + "You landed on the field: " + title + "\n" + description);
        else System.out.println("You rolled: "+rollValue+"\n"+"You landed on field: "+title+"\n"+"" +
                                "As you have no money your guardian angel came to the rescue and saved you by picking up the tab");


        if (value >= 0) {
            System.out.println(p.getAccount().deposit(value));
        } else if(p.getAccount().getWallet()+value>=0){
            System.out.println(p.getAccount().withdraw(value));
        } else {
            System.out.println("You gave your remaining balance to your guardian angel\n" +
                               "Your new balance is: 0");
            p.getAccount().setWallet(0);

        }

        if (rollValue == 10) {
            System.out.println("You got an extra turn!");
            playTurn(rc, d1, d2, p, info, values, scan);
        }
        System.out.println();

        if (p.getAccount().getWallet() >= 3000) {
            System.out.println("Player " + p.getPlayerNumber() + " has won the game!");
            return true;
        }
        return false;
    }

}
