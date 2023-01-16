package Model;

import Controller.GameController;
import Model.ChanceCards.ChanceCard;
import Model.Fields.Field;
import Model.Fields.Street;

import java.util.Scanner;

public class Game {
    private GameBoard gameBoard;
    private ChanceDeck chanceDeck;
    private Player[] players;
    private Player currentPlayer;
    private Player paidPlayer = null;
    private int paidPlayerNumber;
    private Dice dice1, dice2;
    private boolean landedOnChance = false;
    private boolean landedOnTax = false;
    private boolean landedOnParking = false;
    private boolean chanceMove = false;
    private boolean chanceMoneyUpdate = false;
    private boolean passedStart = false;
    private String message;
    private String option;

    public Game() {
        dice1 = new Dice();
        dice2 = new Dice();
    }

    public void initBoard() {
        gameBoard = new GameBoard();
    }

    public void initChanceDeck() {
        chanceDeck = new ChanceDeck();
    }

    public void initPlayers(String[] playerNames) {
        int startMoney = 30000;
        int playerCount = playerNames.length;
        players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            players[i] = new Player(playerNames[i], startMoney);
        }
    }

    public boolean checkIfPlayerInJail() {
        if (currentPlayer.isInJail() && currentPlayer.getAccount().getWallet() > 1000) {
            newReleased();
            currentPlayer.getAccount().withdraw(1000);
            currentPlayer.setInJail(false);
            return true;
        }
        return false;
    }


    public void newReleased() {
        message = "" + currentPlayer.getName() + " har betalt 1000 og er blevet løsladt!";
        option = "Betal 1000";
    }
    public void newTurn() {
        landedOnChance = false;
        chanceMove = false;
        chanceMoneyUpdate = false;
        passedStart = false;
        paidPlayer = null;
        message = "" + currentPlayer.getName() + "'s tur";
        option = "Kast terning";
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
                currentPlayer.setPosition(10);

                break;

            case "chance":
                option = "Tag chance kort";
                landedOnChance = true;
                takeChance();
                break;

            case "skat":
                option = "Betal skat";
                landedOnTax = true;
                payTax();
                break;

            case "parkering":
                option = "Fri parkering, høst hella moneros";
                landedOnParking = true;
                payoutParking();
                break;

            default:

        }
    }

    public void move() {
        dice1.roll();
        dice2.roll();
        if (currentPlayer.getName().contains("test")){
            Scanner scanner = new Scanner(System.in);
            dice1.setFaceValue(scanner.nextInt());
            dice2.setFaceValue(1);
        }
        int diceValue = dice1.getFaceValue() + dice2.getFaceValue();
        int currentPosition = currentPlayer.getPosition();
        int newPosition = currentPosition + diceValue;
        checkPassedStart(newPosition);
    }

    private void pay(Player player, Integer position) {
        int amount = ((Street) gameBoard.getFields()[position]).getPrice();
        currentPlayer.getAccount().withdraw(amount);
        player.getAccount().deposit(amount);
        paidPlayer = player;

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
       switch(cardName) {
           case "Model.ChanceCards.MoveToStart":
               chanceMove=true;
               break;

           case "Model.ChanceCards.MoveThreeForward":
               chanceMove=true;
               break;

           case "Model.ChanceCards.MoveThreeBack":
               chanceMove=true;
               break;

           case "Model.ChanceCards.MoveFiveForward":
               chanceMove=true;
               break;

           case "Model.ChanceCards.FullStopTicket":
               chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.CarInsurance":
               chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.CarRepair":
              chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.CarWash":
              chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.CustomsDuty":
              chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.DentistBill":
              chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.NewTires":
               chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.ParkingTicket":
              chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.ReceiveDividend":
               chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.WonTheLottery":
               chanceMoneyUpdate=true;
               break;

           case "Model.ChanceCards.Birthday":
               chanceMoneyUpdate = true;
               break;

           default:

       }
    }

    //tax
    private void payTax(){
        if (currentPlayer.getPosition() == 4){
            System.out.println(currentPlayer.getAccount().getWallet());
            currentPlayer.getAccount().withdraw(4000);
            message = "Du betaler 4000 i skat";
            option = "Betal skat";
            System.out.println(currentPlayer.getAccount().getWallet());
        }
        else if (currentPlayer.getPosition() == 38){
            currentPlayer.getAccount().withdraw(2000);
            message = "Du betaler 2000 i skat";
            option = "Betal skat";
            System.out.println(currentPlayer.getAccount().getWallet());
        }
    }

    public void checkPassedStart(int newPosition) {

        if (newPosition >= 40) {
            currentPlayer.setPosition(newPosition - 40);
            currentPlayer.getAccount().deposit(4000);
            message = "Du passerede start. Modtag 4000";
            option = "OK";
            passedStart = true;
        } else {
            currentPlayer.setPosition(newPosition);
            message = "";
            option = "";
        }
    }

    public void payoutParking(){
        int moneys = GameController.collectMoneyFromParkingField();
        currentPlayer.getAccount().deposit(moneys);
        message = "Congratz du har høstet "+moneys+" kr";
        option = "cool";
    }


    public boolean hasLandedOnTax(){
        return landedOnTax;
    }
    public boolean hasLandedOnChance() {
        return landedOnChance;
    }

    public boolean hasLandedOnParking(){return landedOnParking;}

    public boolean isChanceMove() {
        return chanceMove;
    }

    public boolean isChanceMoneyUpdate() {
        return chanceMoneyUpdate;
    }

    public int getDiceValue1() {
        return dice1.getFaceValue();
    }
    public int getDiceValue2() {
        return dice2.getFaceValue();
    }

    public boolean checkIfPlayerLost() {
        return currentPlayer.getAccount().getIsEmpty();
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

    public void setCurrentPlayer(int playerNumber) {
        currentPlayer = players[playerNumber];
    }
}



