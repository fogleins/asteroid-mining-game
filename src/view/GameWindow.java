package view;

import control.Game;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The game's main window.
 */
public class GameWindow extends JFrame {
    /**
     * The instance of the singleton GameWindow class.
     */
    private static final GameWindow instance = new GameWindow();

    /*
        private GameStatusView gameStatusView;
        private MapView mapView;
        private AsteroidStatusView asteroidStatusView;
        private SettlerActionsView settlerActionView; // TODO: docsban actionView-ként szerepel
        private SettlerInventoryView settlerInventoryView;
     */

    /**
     * Creates a GameWindow object.
     */
    private GameWindow() {
        super("Asteroid mining game");
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null); // place the window in the center of the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: initialize views, set layout
        //  this.pack();
    }

    /**
     * Starts the game.
     *
     * @param names A list containing the players' names.
     */
    public static void init(ArrayList<String> names) {
        Game.start(names);
    }

    /**
     * This method can be used to access the main window object outside this class.
     *
     * @return The instance of this singleton class.
     */
    public static GameWindow getInstance() {
        return instance;
    }
}
