package view;

import map.asteroid.Asteroid;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Displays an asteroid on the map.
 */
public class AsteroidView extends JButton {
    /**
     * The Asteroid object to display.
     */
    private final Asteroid asteroid;
//    private AsteroidStatusView statusView; // TODO
    private Point coordinates;

    /**
     * Creates an AsteroidView object.
     */
    public AsteroidView(Asteroid asteroid) {
        this.asteroid = asteroid;
        this.addActionListener(actionEvent -> selected());
    }

    /**
     * Updates the asteroid's view.
     */
    public void updateView() {
        // TODO
//        statusView.updateView(asteroid);
    }

//    public void setStatusView(AsteroidStatusView statusView) {
//        this.statusView = statusView;
//    }

    public void selected() {
        // TODO: implement callback method
//        statusView.updateView(asteroid);
    }
}
