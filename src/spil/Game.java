package spil;

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
                {"Tower","You have reached the top of The Tower and miraculously found a bag with 250 gold in it."},
                {"Crater", "You fell in the crater and broke your leg in the fall, you spend 100 gold on the hospital-bill."},
                {"Palace gates", "You had a nice conversation with the King of the palace, therefore you received 100 gold."},
                {"Cold Desert", "You're feeling slightly chilly and couldn't find your jacket so you get a little bit sick, you spend 20 gold on cough medicine."},
                {"Walled city", "You managed to sell some items inside The Walled City, you earned 180 gold."},
                {"Monastery", "You meditate with the monks, as they do not care for money you spend nothing."},
                {"Black cave", "You have entered the black cave, it's too dark inside so you spend 70 gold on a flashlight."},
                {"Huts in the mountain", "You help the locals hunt down a mountain lion, Gain 60 gold for your efforts"},
                {"The Werewall (werewolf-wall)", "You got bit by a werewolf and you spend 80 gold to buy bandage."},
                {"The pit", "You fall down a steep pit, there's a vending machine in the corner of the pit selling ladders, you buy one for 50 gold and climb out."},
                {"Goldmine", "You have arrived at the Goldmine, you hit the jackpot and gained 650 gold."}
        };
        int[] fieldValues = {250, -100, 100, -20, 180, 0, -70, 60, -80, -50, 650};

        System.out.println("Starting game...");
        System.out.println("Press enter to shake the raffle cup and roll the dice.");

        while(true){

            if (playTurn(rc, d1, d2, p1, fieldInfo, fieldValues, scan)) break;

            if (playTurn(rc, d1, d2, p2, fieldInfo, fieldValues, scan)) break;

        }

        scan.close();
    }


    public static boolean playTurn(Rafflecup rc, Dice d1, Dice d2, Player p, String[][] info, int[] values, Scanner scan) {

        System.out.println("Player " + p.getPlayerNumber() + "'s turn to shake and roll the dice!");

        scan.nextLine();

        rc.roll(d1, d2);

        int rollValue = rc.getSum(d1,d2);

        String title = info[rollValue-2][0];

        int value = values[rollValue-2];

        String description = info[rollValue-2][1];


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
