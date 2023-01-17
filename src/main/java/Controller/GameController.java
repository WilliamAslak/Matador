package Controller;

import Model.Fields.Field;
import gui_fields.GUI_Field;
import Model.Game;
import View.GameGUI;

import java.awt.*;

public class GameController {
    private Game game;
    private GameGUI gui;
    private int playerCount;
    private static int moneyCounter=0;
    private int ensCounter = 0;

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

                //LOSE
                if (game.checkIfPlayerLost()) {
                    isGameOver = true;
                    gui.showEndGame(game.getCurrentPlayer().getName());
                    break;
                }

                //hvis spiller sidder i fængsel skal der spørges om han vil slå eller betale på hans næste tur.
                if (game.checkIfPlayerInJail()) {
                    //vil du betale eller kaste for at komme ud?
                    boolean corrupt = !GameGUI.choiceAction(game.getCurrentPlayer().getName() + " vil du slå dig ud af fængsel?", "ja", "nej, betal 1000");
                    //hvis du gerne vil betale, bliver funktionen kaldt
                    if (corrupt) {
                        game.newReleased(true, 3);
                        gui.action(game.getMessage(), game.getOption());
                        gui.updateBalance(playerNumber, game.getCurrentPlayer().getAccount().getWallet());
                    }
                    //ellers begynder while loopet der tæller ned fra hvert kast, når den rammer 0 bliver det næste spillers tur
                    else {
                        //man har i antal kast
                        int i = 3;
                        while (i >= 0) {
                            //sig man har i antal kast tilbage
                            game.newReleased(false, i);
                            gui.action(game.getMessage(), game.getOption());
                            System.out.println("i = " + i);
                            if (i == 0) {
                                playerNumber++;
                                //det her kan blive simplified da det praktisk talt er en kopi fra starten af playTurn()
                                if (playerNumber >= playerCount)
                                    playerNumber = 0;
                                game.setCurrentPlayer(playerNumber);
                                gui.setCurrentPlayer(playerNumber);
                                break;
                            }
                            //efter man har klikket på knappen, kast så terningerne og vis dem.
                            game.diceRoll();
                            gui.showDie(game.getDiceValue1(), game.getDiceValue2());

                            if (game.getDiceValue1() == game.getDiceValue2()) {
                                //-1 er det man kalder newReleased funktionen med for at vise man har fået 2 ens.
                                game.newReleased(false, -1);
                                gui.action(game.getMessage(), game.getOption());
                                //bryd loopet
                                break;
                            }
                            //du har slået, i er ikke 0, eller du fik ikke 2 ens.
                            i--;


                        }

                    }
                }

                game.newTurn();
                if(ensCounter==0)
                    gui.action(game.getMessage(), game.getOption());
                else gui.action(game.getMessage()+" igen, da du slog 2 ens", game.getOption());

                int currentPos = game.getCurrentPlayer().getPosition();
                game.move();
                gui.showDie(game.getDiceValue1(), game.getDiceValue2());
                int newPos = game.getCurrentPlayer().getPosition();
                gui.move(currentPos, newPos);

                //Check om passeret start
                if (game.hasPassedStart()) {
                    gui.action(game.getMessage(), game.getOption());
                }
                currentPos = newPos;

                //Felt handling
                game.fieldAction();

                //Pantsætning
                if (game.getOption().equals("Pantsæt")) {
                    if (GameGUI.choiceAction(game.getMessage() + ", vil du pantsætte din grund?", "ja", "nej")) {
                        game.mortgage(game.getCurrentPlayer().getPosition());
                    }
                } else

                //Hæv panteret felt
                if (game.getOption().equals("Hævpant")) {
                    if (GameGUI.choiceAction(game.getMessage() + ", vil du hæve pantsætningen?", "ja", "nej")) {
                        game.mortgageRelease(game.getCurrentPlayer().getPosition());
                    }
                } else

                //hvis spilleren lander på et ledigt felt han kan købe, bliver han spurgt om han vil købe det eller ej.
                if (game.getOption().equals("Køb")) {
                    if(game.canAfford(game.getCurrentPlayer().getPosition())) {
                        if (GameGUI.choiceAction(game.getMessage() + ", vil du købe grunden?", "ja", "nej"))
                            game.purchase(game.getCurrentPlayer().getPosition());
                    }
                    else gui.action("Du har desværre ikke råd til grunden","Øv");
                } else
                    gui.action(game.getMessage(), game.getOption());

                //Så fængsel gui virker
                newPos = game.getCurrentPlayer().getPosition();
                gui.move(currentPos, newPos);

                //CHANCE
                if (game.hasLandedOnChance()) {
                    //gui.action(game.getMessage(), game.getOption());

                    if (game.isChanceMove()) {
                        game.checkPassedStart(game.getCurrentPlayer().getPosition());
                        newPos = game.getCurrentPlayer().getPosition();
                       // gui.action(game.getMessage(), game.getOption());
                        gui.move(currentPos, newPos);
                    }

                    if (game.isChanceMoneyUpdate()) {
                        for (int i = 0; i < game.getPlayers().length; i++) {
                            int newBalance = game.getPlayers()[i].getAccount().getWallet();
                            gui.updateBalance(i, newBalance);
                        }
                    }
                }
                Field[] f = game.getFieldOfColor(Color.yellow);
                for (Field field : f) System.out.println(field.getName());

                //BETAL
                if (game.getPaidPlayer() != null) {
                    int newBalance = game.getPaidPlayer().getAccount().getWallet();
                    gui.updateBalance(game.getPaidPlayerNumber(), newBalance);
                }
                //TAX
                if (game.hasLandedOnTax()) {
                    //gui.action(game.getMessage(), game.getOption());
                    int newBalance;
                    if (game.getCurrentPlayer().getPosition() == 4) {
                        newBalance = game.getCurrentPlayer().getAccount().getWallet() - 4000;
                        gui.updateBalance(playerNumber, newBalance);
                    } else if (game.getCurrentPlayer().getPosition() == 38) {
                        newBalance = game.getCurrentPlayer().getAccount().getWallet() - 2000;
                        gui.updateBalance(playerNumber, newBalance);
                    }

                }
                //Gratis Parkering
                if (game.hasLandedOnParking()) {
                    //gui.action(game.getMessage(), game.getOption());
                }


                //Opdater saldo
                int currentBalance = game.getCurrentPlayer().getAccount().getWallet();
                if (currentBalance != gui.getPlayer().getBalance()) {
                    gui.updateBalance(playerNumber, currentBalance);
                }




                //Ny spiller slår når der ikke er ens terninger eller 3 ens i træk
                if (game.getDiceValue1() != game.getDiceValue2() || ensCounter == 3){
                    playerNumber++;
                    ensCounter = 0;
                } else
                    ensCounter++;
            }
            }
        }

    // Gratis Parkering
    public static void updateMoneyCounter(int moneyLost) {
        moneyCounter += moneyLost;
    }
    public static int collectMoneyFromParkingField() {
        int collectedMoney = moneyCounter;
        moneyCounter = 0;
        return collectedMoney;
    }
}
