package spil;

import ChanceCards.*;
import Fields.*;

public class Game {
    private GameBoard gameBoard;
    private ChanceDeck chanceDeck;
    private Player[] players;
    private Player currentPlayer;
    private Player paidPlayer = null;
    private int paidPlayerNumber;
    private Dice dice;
    private boolean landedOnChance = false;
    private boolean chanceMove = false;
    private boolean chanceMoneyFromOthers = false;
    private boolean passedStart = false;
    private String message;
    private String option;


    public Game() {
        dice = new Dice();
    }

    public void initBoard() {
        gameBoard = new GameBoard();
    }

    public void initChanceDeck() {
        chanceDeck = new ChanceDeck();
    }

    public void initPlayers(String[] playerNames) {
        int[] startMoney = {20, 18, 16};
        int playerCount = playerNames.length;
        players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(playerNames[i], startMoney[playerCount - 2]);
        }

    }

    public boolean checkIfPlayerInJail() {
        if (currentPlayer.isInJail()) {
            newReleased();
            currentPlayer.getAccount().withdraw(1);
            currentPlayer.setInJail(false);
            return true;
        }
        return false;
    }


    public void newReleased() {
        message = "" + currentPlayer.getName() + " er blevet løsladt!";
        option = "Betal 1M";
    }
    public void newTurn() {
        landedOnChance = false;
        chanceMove = false;
        chanceMoneyFromOthers = false;
        passedStart = false;
        paidPlayer = null;
        message = "" + currentPlayer.getName() + "'s tur";
        option = "Kast terning";
    }
    public void setCurrentPlayer(int playerNumber) {
        currentPlayer = players[playerNumber];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Field[] getFields() {
        return gameBoard.getFields();
    }

    public Player[] getPlayers() {
        return players;
    }

    public String getMessage() {
        return message;
    }

    public String getOption() {
        return option;
    }

    public void release(Player p){
        p.setInJail(false);
    }

    public void playTurn() {
        move();
        fieldAction();
    }


    public void fieldAction() {

        Integer position = currentPlayer.getPosition();

        message = "Du landede på " + gameBoard.getFields()[position].getName();
        option = "Ok";

        switch (gameBoard.getFields()[position].action()) {

            case "ejet":
                int i = 0;


                while(i < players.length) {
                    if (players[i].getOwnedPropertiesPositions().contains(position)) break;
                    i++;
                }

                if (!players[i].getName().equals(currentPlayer.getName())) {
                    option = "Betal";
                    paidPlayerNumber = i;
                    pay(players[i], position);
                }
                break;

            case "ledig":
                option = "Køb";
                purchase(position);
                break;

            case "fængsel":
                option = "Du bliver sparket i fængsel";
                currentPlayer.setInJail(true);
                currentPlayer.setPosition(position-12);
                break;

            case "chance":
                option = "Tag chance kort";
                landedOnChance = true;
                takeChance();
                break;

            default:

        }
    }

    public void move() {
        dice.roll();
        int diceValue = dice.getFaceValue();
        int currentPosition = currentPlayer.getPosition();
        int newPosition = currentPosition + 18;
        checkPassedStart(newPosition);
    }

    private void pay(Player player, Integer position) {
        int amount = ((Street) gameBoard.getFields()[position]).getPrice();
        currentPlayer.getAccount().withdraw(amount);
        player.getAccount().deposit(amount);
        paidPlayer = player;

    }

    public Player getPaidPlayer() {
        return paidPlayer;
    }

    public boolean hasPassedStart() {
        return passedStart;
    }

    public int getPaidPlayerNumber() {
        return paidPlayerNumber;
    }

    private void purchase(Integer position) {

        int amount = ((Street) gameBoard.getFields()[position]).getPrice();

        currentPlayer.addPropertyPosition(currentPlayer.getPosition());

        currentPlayer.getAccount().withdraw(amount);

        ((Street) gameBoard.getFields()[position]).setIsOwned(true);
    }

    private void takeChance() {
        ChanceCard chanceCard = chanceDeck.drawCard();
        chanceCard.process(players, currentPlayer);
        message = chanceCard.getDescription();
        option = "ok";

        String cardName = chanceCard.getClass().getName();
        System.out.println(cardName);
        if (cardName.equals("ChanceCards.MoveToStart")) chanceMove = true;
        if (cardName.equals("ChanceCards.MoveFiveForward")) {
            chanceMove = true;
        }
        if (cardName.equals("ChanceCards.Birthday")) chanceMoneyFromOthers = true;

    }

    public void checkPassedStart(int newPosition) {

        if (newPosition >= 24) {
            currentPlayer.setPosition(newPosition - 24);
            currentPlayer.getAccount().deposit(2);
            message = "Du passerede start. Modtag 2M";
            option = "OK";
            passedStart = true;
        } else {
            currentPlayer.setPosition(newPosition);
            message = "";
            option = "";
        }
    }

    public boolean hasLandedOnChance() {
        return landedOnChance;
    }

    public boolean isChanceMove() {
        return chanceMove;
    }

    public boolean isChanceMoneyFromOthers() {
        return chanceMoneyFromOthers;
    }

    public int getDiceValue() {
        return dice.getFaceValue();
    }

    public boolean checkIfPlayerLost() {
        return currentPlayer.getAccount().getIsEmpty();
    }



}
/*
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();

        //Creating all the GUI
        GUI_Field[] fields = gameBoard.getFieldsGUI();
        GUI gui = new GUI(fields);
        int playerCount = gui.getUserInteger("Hvor mange spillere?",2,4);

        Player[] players = new Player[playerCount];
        for(int i=0; i<playerCount; i++)
            players[i] = new Player();

        GUI_Car[] cars = new GUI_Car[4];
        cars[0] = new GUI_Car(Color.WHITE, Color.BLACK, GUI_Car.Type.UFO, GUI_Car.Pattern.CHECKERED);
        cars[1] = new GUI_Car(Color.CYAN, Color.PINK, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.DOTTED);
        cars[2] = new GUI_Car(Color.BLUE, Color.GREEN, GUI_Car.Type.CAR, GUI_Car.Pattern.ZEBRA);
        cars[3] = new GUI_Car(Color.GREEN, Color.GRAY, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL);

        GUI_Player[] gui_players = new GUI_Player[playerCount];
        for(int i = 0; i < playerCount; i++) {
            //Creating the players
            gui_players[i] = new GUI_Player("P" + (i+1), 0, cars[i]);
            //Adding players to the board
            gui.addPlayer(gui_players[i]);
            gui_players[i].setBalance(players[i].getAccount().getWallet());
        }


        //run();
    }


    /*
    public static void run(){


        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Rafflecup rc = new Rafflecup();

        //Finding out how many players are playing.
        Scanner scan = new Scanner(System.in);

        Player[] players = new Player[playerCount];
        for(int i=0; i<playerCount; i++)
            players[i] = new Player();
        //Fix for player1 automatically rolling first run.
        scan.nextLine();
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


        GUI_Car[] cars = new GUI_Car[4];
        cars[0] = new GUI_Car(Color.WHITE, Color.BLACK, GUI_Car.Type.UFO, GUI_Car.Pattern.CHECKERED);
        cars[1] = new GUI_Car(Color.CYAN, Color.PINK, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.DOTTED);
        cars[2] = new GUI_Car(Color.BLUE, Color.GREEN, GUI_Car.Type.CAR, GUI_Car.Pattern.ZEBRA);
        cars[3] = new GUI_Car(Color.GREEN, Color.GRAY, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL);


        GUI_Player[] gui_players = new GUI_Player[playerCount];
        for(int i = 0; i < playerCount; i++) {
            //Creating the players
            gui_players[i] = new GUI_Player("P" + (i+1), 0, cars[i]);
            //Adding players to the board
            gui.addPlayer(gui_players[i]);
            gui_players[i].setBalance(players[i].getAccount().getWallet());
        }

        System.out.println("Starting game...");
        System.out.println("Press enter to shake the raffle cup and roll the dice.");


        //putting cars on Start
        //ps. made it into for loop for 2-4 cars
        for (int i = 0; i < playerCount; i++) {
            fields[0].setCar(gui_players[i], true);
        }

        boolean cont = true;
        while(cont) {


            for(int i = 0; i < playerCount; i++) {
                if (playTurn(rc, d1, d2, players[i], fieldInfo, fieldValues, scan)) {cont=false; break;}
                //gui
                //remove car from old space
                fields[players[i].getPlayerPosition()].setCar(gui_players[i], false);
                //calculate new space
                players[i].setPlayerPosition(players[i].getPlayerPosition() + rc.getSum(d1, d2));
                if (players[i].getPlayerPosition() >= fieldValues.length)
                    players[i].setPlayerPosition(players[i].getPlayerPosition() - fieldValues.length);
                //add car to new space
                fields[players[i].getPlayerPosition()].setCar(gui_players[i], true);

                //updating gui wallet
                gui_players[i].setBalance(players[i].getAccount().getWallet());
            }

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
*/


