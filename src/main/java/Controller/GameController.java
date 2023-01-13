package Controller;

import Model.Fields.Field;
import gui_fields.GUI_Field;
import Model.Game;
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

        GUI_Field[] fieldsGUI = new GUI_Field[40];
        for (int i = 0; i < fields.length ; i++) {
            fieldsGUI[i] = fields[i].   toGui();
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

                if(game.checkIfPlayerInJail()) {
                    gui.action(game.getMessage(), game.getOption());
                    gui.updateBalance(playerNumber, game.getCurrentPlayer().getAccount().getWallet() );
                }

                //LOSE
                if(game.checkIfPlayerLost()) {
                    isGameOver = true;
                    gui.showEndGame(game.getCurrentPlayer().getName());
                    break;
                }

                game.newTurn();

                gui.action(game.getMessage(), game.getOption());

                int currentPos = game.getCurrentPlayer().getPosition();
                game.move();
                gui.showDie(game.getDiceValue1(), game.getDiceValue2());
                int newPos = game.getCurrentPlayer().getPosition();
                gui.move(currentPos, newPos);

                //Check om passeret start
                if(game.hasPassedStart()) {
                    gui.action(game.getMessage(), game.getOption());
                }
                currentPos = newPos;

                //Felt handling
                game.fieldAction();
                //hvis spilleren lander på et ledigt felt han kan købe, bliver han spurgt om han vil købe det eller ej.
                if(game.getOption().equals("Køb")){
                    if(GameGUI.choiceAction(game.getMessage()+" vil du købe grunden?","ja","nej"))
                        game.purchase(game.getCurrentPlayer().getPosition());
                } else
                    gui.action(game.getMessage(), game.getOption());

                //Så fængsel gui virker
                newPos = game.getCurrentPlayer().getPosition();
                gui.move(currentPos,newPos);

                //CHANCE
                if(game.hasLandedOnChance()) {
                    //gui.action(game.getMessage(), game.getOption());

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

                //BETAL
                if(game.getPaidPlayer() != null) {
                    int newBalance = game.getPaidPlayer().getAccount().getWallet();
                    gui.updateBalance(game.getPaidPlayerNumber(), newBalance);
                }
                //TAX
                if(game.hasLandedOnTax()){
                    gui.action(game.getMessage(), game.getOption());
                    int newBalance;
                    if (game.getCurrentPlayer().getPosition() == 4) {
                        newBalance = game.getCurrentPlayer().getAccount().getWallet() - 4000;
                        gui.updateBalance(playerNumber, newBalance);
                    }
                    else if (game.getCurrentPlayer().getPosition() == 38){
                        newBalance = game.getCurrentPlayer().getAccount().getWallet() - 2000;
                        gui.updateBalance(playerNumber, newBalance);
                    }

                }

                //Opdater saldo
                int currentBalance = game.getCurrentPlayer().getAccount().getWallet();
                if (currentBalance != gui.getPlayer().getBalance()) {
                    gui.updateBalance(playerNumber, currentBalance);
                }
                if (game.getDiceValue1() != game.getDiceValue2())
                    playerNumber++;
            }
        }
    }

}
