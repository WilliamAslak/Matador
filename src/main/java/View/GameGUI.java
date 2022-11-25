package View;

import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

import java.awt.*;
import java.util.ArrayList;

public class GameGUI {
    private GUI gui;
    private ArrayList<GUI_Player> players = new ArrayList<>();
    private ArrayList<GUI_Car> cars = new ArrayList<>();

    public void initBoard(GUI_Field[] fieldsGUI) {
        gui = new GUI(fieldsGUI);
    }

    public int getNumberOfPlayers() {
       return gui.getUserInteger("Antal spillere?",2,4);
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
    }

    public void test(String valg) {

    }

    public void updateBalance(int playerNumber, int newBalance) {
        players.get(playerNumber).setBalance(newBalance);
    }

    public GUI_Player getPlayer(int playerNumber) {
        return players.get(playerNumber);
    }

    public void move(int currentPos, int newPos, int playerNumber) {
        gui.getFields()[currentPos].setCar(players.get(playerNumber), false);
        gui.getFields()[newPos].setCar(players.get(playerNumber), true);

    }

    public void action(String message, String button) {
        gui.getUserButtonPressed(message, button);
    }
    public void showDie(int faceValue) {
        gui.setDie(faceValue);
    }

    public void showEndGame(String playerName){
        gui.showMessage(playerName + "has lost. \n The game has ended");
        gui.close();
    }
}
