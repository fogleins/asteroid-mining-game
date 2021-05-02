package control;

import view.GameWindow;

public class Main {
    private static final Game game = Game.getInstance();

    public static void main(String[] args) {
        GameWindow gameWindow = GameWindow.getInstance();
        gameWindow.setVisible(true);
    }
}
