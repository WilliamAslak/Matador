package Controller;

import Fields.Field;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import spil.Game;
import View.GameGUI;

public class GameController {
    private Game game;
    private GameGUI gui;
    private int playerCount;

    public GameController(Game game, GameGUI gui) {
        this.game = game;
        this.gui = gui;
    }

    public void startGame() {
        initBoard();
        initPlayers();
        initChanceDeck();
    }

    private void initBoard() {
        game.initBoard();
        Field[] fields = game.getFields();

        GUI_Field[] fieldsGUI = new GUI_Field[24];
        for (int i = 0; i < fields.length ; i++) {
            fieldsGUI[i] = fields[i].toGui();
        }
        gui.initBoard(fieldsGUI);
    }

    private void initPlayers() {

        playerCount = gui.getNumberOfPlayers();
        String[] playerNames = new String[playerCount];

        playerNames[0] = gui.getYoungestPlayerName();

        for (int i = 1; i < playerCount; i++) {
            playerNames[i] = gui.getPlayerName(i);
        }

        game.initPlayers(playerNames);

        gui.makeCars();
        for(int i = 0; i < playerCount; i++) {
            int startMoney = game.getPlayers()[i].getAccount().getWallet();
            gui.addPlayer(playerNames[i], startMoney, i);
        }

    }

    private void initChanceDeck() {
        game.initChanceDeck();
    }


    public void playTurn() {
        boolean isGameOver = false;
        while (!isGameOver) {
            int playerNumber = 0;

            while (playerNumber < playerCount) {

                game.setCurrentPlayer(playerNumber);
                gui.setCurrentPlayer(playerNumber);

                game.newTurn();

                gui.action(game.getMessage(), game.getOption());

                int currentPos = game.getCurrentPlayer().getPosition();
                game.move();
                gui.showDie(game.getDiceValue());
                int newPos = game.getCurrentPlayer().getPosition();
                gui.move(currentPos, newPos);

                if(game.hasPassedStart()) {
                    gui.action(game.getMessage(), game.getOption());
                }
                currentPos = newPos;

                game.fieldAction();
                gui.action(game.getMessage(), game.getOption());


                if(game.hasLandedOnChance()) {
                    gui.action(game.getMessage(), game.getOption());

                    if (game.isChanceMove()) {
                        game.checkPassedStart(game.getCurrentPlayer().getPosition());
                        newPos = game.getCurrentPlayer().getPosition();
                        gui.action(game.getMessage(), game.getOption());
                        gui.move(currentPos, newPos);
                    }

                    if(game.isChanceMoneyFromOthers()) {
                        for (int i = 0; i < game.getPlayers().length ; i++) {
                            int newBalance = game.getPlayers()[i].getAccount().getWallet();
                            gui.updateBalance(i, newBalance);
                        }
                    }
                }

                if(game.getPaidPlayer() != null) {
                    int newBalance = game.getPaidPlayer().getAccount().getWallet();
                    gui.updateBalance(game.getPaidPlayerNumber(), newBalance);
                }


                int currentBalance = game.getCurrentPlayer().getAccount().getWallet();
                if (currentBalance != gui.getPlayer().getBalance()) {
                    gui.updateBalance(playerNumber, currentBalance);
                }


                if(game.checkIfPlayerLost()) {
                    isGameOver = true;
                    gui.showEndGame(game.getCurrentPlayer().getName());
                    break;
                }

                playerNumber++;
            }
        }
    }

}
