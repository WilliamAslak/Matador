import Controller.GameController;
import View.GameGUI;
import Model.Game;

public class main {
    public static void main(String[] args) {
        Game game = new Game();
        GameGUI gui = new GameGUI();
        GameController gameController = new GameController(game, gui);
        gameController.startGame();
        gameController.playTurn();
    }
}
