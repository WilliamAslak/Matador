package View;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.util.ArrayList;

public class GameGUI {
    private static GUI gui;
    private ArrayList<GUI_Player> players = new ArrayList<>();
    private ArrayList<GUI_Car> cars = new ArrayList<>();
    private GUI_Player currentPlayer;

    public void initBoard(GUI_Field[] fieldsGUI) {
        gui = new GUI(fieldsGUI);
    }

    public int getNumberOfPlayers() {
       return gui.getUserInteger("Antal spillere? (mellem 3 og 6)",3,6);
    }

    public String getYoungestPlayerName() {
        return gui.getUserString("Yngste spiller navn?");
    }

    public String getPlayerName(int i) {
        return gui.getUserString("Indtast navn på spiller nr. " + (i+1));
    }

    public void addPlayer(String name, int startMoney, int number) {
        GUI_Player player = new GUI_Player(name, startMoney, cars.get(number));
        players.add(player);
        gui.addPlayer(player);
        gui.getFields()[0].setCar(player,true);
    }

    public void makeCars () {
        cars.add(new GUI_Car(Color.WHITE, Color.BLACK, GUI_Car.Type.UFO, GUI_Car.Pattern.CHECKERED));
        cars.add(new GUI_Car(Color.CYAN, Color.PINK, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.DOTTED));
        cars.add(new GUI_Car(Color.BLUE, Color.GREEN, GUI_Car.Type.CAR, GUI_Car.Pattern.ZEBRA));
        cars.add(new GUI_Car(Color.GREEN, Color.GRAY, GUI_Car.Type.RACECAR, GUI_Car.Pattern.FILL));
        cars.add(new GUI_Car(Color.BLACK, Color.GREEN, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.HORIZONTAL_LINE));
        cars.add(new GUI_Car(Color.CYAN, Color.GRAY, GUI_Car.Type.RACECAR, GUI_Car.Pattern.DOTTED));
    }

    public void setCurrentPlayer(int playerNumber) {
        currentPlayer = players.get(playerNumber);
    }


    public void updateBalance(int playerNumber, int newBalance) {
        players.get(playerNumber).setBalance(newBalance);
    }

    public GUI_Player getPlayer() {
        return currentPlayer;
    }

    public void move(int currentPos, int newPos) {
        gui.getFields()[currentPos].setCar(currentPlayer, false);
        gui.getFields()[newPos].setCar(currentPlayer, true);

    }

    public void action(String message, String button) {
        gui.getUserButtonPressed(message, button);
    }
    public static boolean choiceAction(String msg, String leftBtn, String rightBtn){
        return gui.getUserLeftButtonPressed(msg,leftBtn,rightBtn);
    }
    public void showDie(int faceValue1, int faceValue2) {
        gui.setDice(faceValue1, faceValue2);
    }

    public void showEndGame(String playerName){
        gui.showMessage(playerName + " has lost. \n The game has ended");
        gui.close();
    }
}
