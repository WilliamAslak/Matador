package Model;

import Model.ChanceCards.ChanceCard;
import Model.Fields.Field;
import Model.Fields.Street;

import java.util.Objects;

public class Game {
    private GameBoard gameBoard;
    private ChanceDeck chanceDeck;
    private Player[] players;
    private Player currentPlayer;
    private Player paidPlayer = null;
    private int paidPlayerNumber;
    private Dice dice1, dice2;
    private boolean landedOnChance = false;
    private boolean chanceMove = false;
    private boolean chanceMoneyFromOthers = false;
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
        // Bruges til accept test K1
        for (int i = 0; i < playerCount; i++) {
            if (players[i].getName().contains("test")){
                players[i].setPosition(19);
            }
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

            default:

        }
    }

    public void move() {
        dice1.roll();
        dice2.roll();
        if (currentPlayer.getName().contains("test")){
            dice1.setFaceValue(6);
            dice2.setFaceValue(6);
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

        switch (chanceCard.getClass().getName()) {
            case "Model.ChanceCards.MoveToStart", "Model.ChanceCards.MoveThreeForward", "Model.ChanceCards.MoveThreeBack", "Model.ChanceCards.FullStopTicket", "Model.ChanceCards.CarInsurance", "Model.ChanceCards.CarRepair", "Model.ChanceCards.CarWash", "Model.ChanceCards.CustomsDuty", "Model.ChanceCards.DentistBill", "Model.ChanceCards.NewTires", "Model.ChanceCards.ParkingTicket", "Model.ChanceCards.ReceiveDividend", "Model.ChanceCards.WonTheLottery", "Model.ChanceCards.MoveFiveForward" ->
                    chanceMove = true;
            case "Model.ChanceCards.Birthday" -> chanceMoneyFromOthers = true;
            default -> {
            }
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

    public boolean hasLandedOnChance() {
        return landedOnChance;
    }

    public boolean isChanceMove() {
        return chanceMove;
    }

    public boolean isChanceMoneyFromOthers() {
        return chanceMoneyFromOthers;
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



