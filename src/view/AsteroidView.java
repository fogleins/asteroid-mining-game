package view;

import map.asteroid.Asteroid;

import javax.swing.*;

/**
 * Displays an asteroid on the map.
 */
public class AsteroidView extends JButton {
    /**
     * The Asteroid object to display.
     */
    private Asteroid asteroid;
//    private AsteroidStatusView statusView; // TODO

    /**
     * Creates an AsteroidView object.
     */
    public AsteroidView() {
        this.addActionListener(actionEvent -> selected());
    }

    /**
     * Updates the asteroid's view.
     */
    public void updateView() {
        // TODO
    }

//    public void setStatusView(AsteroidStatusView statusView) {
//        this.statusView = statusView;
//    }

    public void selected() {
        // TODO: implement callback method
    }
}
