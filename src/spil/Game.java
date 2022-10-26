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
        //TODO ændre blabla til ægte descriptions
        String[][] fieldInfo = {
                {"Tower","blabla"},
                {"Crater", "You fell in the crater and broke your leg in the fall, you spend 100 gold on the hospital-bill."},
                {"Palace gates", "blabla"},
                {"Cold Desert", "You're feeling slightly chilly and couldn't find your jacket so you get a little bit sick, you spend 20 gold on cough medicine."},
                {"Walled city", "blabla"},
                {"Monastery", "You meditate with the monks, as they dont care for money you spend nothing."},
                {"Black cave", "blabla"},
                {"Huts in the mountain", "You help the locals hunt down a mountain lion, Gain 60 gold for your efforts"},
                {"The Werewall (werewolf-wall)", "blabla"},
                {"The pit", "You fall down a steep pit, there's a vending machine in the corner of the pit selling ladders, you buy one for 50 gold and climb out."},
                {"Goldmine", "blabla"}
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
        int playerNumber = p.getPlayerNumber();
        System.out.println("Player " + playerNumber + "'s turn to shake and roll the dice!");

        scan.nextLine();

        rc.roll(d1, d2);

        int rollValue = d1.getFaceValue() + d2.getFaceValue();

        String title = info[rollValue-2][0];

        int value = values[rollValue-2];

        String description = info[rollValue-2][1];

        Account account = p.getAccount();

        System.out.println("You rolled: " + rollValue + "\n" + "You landed on the field: " + title + "\n" + description);

        if (value >= 0) {
            System.out.println(account.deposit(value));
        } else {
            System.out.println(account.withdraw(value));
        }

        if (rollValue == 10) {
            System.out.println("You got an extra turn!");
            playTurn(rc, d1, d2, p, info, values, scan);
        }
        System.out.println();

        if (account.getWallet() >= 3000) {
            System.out.println("Player " + playerNumber + " has won the game!");
            return true;
        }
        return false;
    }

}
