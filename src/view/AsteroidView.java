package view;

import control.Game;
import map.asteroid.Asteroid;
import map.entity.Entity;
import map.entity.Settler;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Displays an asteroid on the map.
 */
public class AsteroidView extends JButton {
    /**
     * The currently selected asteroid.
     */
    private static Asteroid selected;
    /**
     * The Asteroid object to display.
     */
    private final Asteroid asteroid;
    /**
     * The radius of the asteroid.
     */
    private final int radius;
    /**
     * The center of the asteroid in the window.
     */
    private final Point coordinates;
    /**
     * Reference to asteroid status view.
     */
    private AsteroidStatusView statusView;
    /**
     * Used for hit detection.
     */
    private Shape shape;

    /**
     * Creates an AsteroidView object.
     */
    public AsteroidView(Asteroid asteroid) {
        this.asteroid = asteroid;
        this.addActionListener(actionEvent -> selected());
        this.radius = 50;
        this.coordinates = new Point(getX() + radius, getY() + radius);
        setFocusable(false);

	    /*
	    todo
	     These statements enlarge the button so that it
	     becomes a circle rather than an oval.
	    */
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

	    /*
	    todo
	     This call causes the JButton not to paint the background.
	     This allows us to paint a round background.
	    */
        setContentAreaFilled(false);
    }

    /**
     * Updates the asteroid's view.
     */
    public void updateView() {
        // TODO
        // we update the AsteroidStatusView panel if the selected asteroid's property changes, but not if an asteroid -
        // which is not selected - explodes, for example
        if (asteroid == selected) {
            statusView.updateView(asteroid);
        }
        repaint();
    }

    // TODO: jelenleg nem használt
    public void setStatusView(AsteroidStatusView statusView) {
        this.statusView = statusView;
    }

    /**
     * Event handler. Called when a player selects an asteroid.
     */
    public void selected() {
        selected = asteroid;
        this.statusView = GameWindow.getAsteroidStatusView();
        statusView.updateView(asteroid);
    }

    /**
     * Calculates the center coordinates of the asteroid.
     */
    private void calculateCenterCoordinates() {
        this.coordinates.x = getX() + radius;
        this.coordinates.y = getY() + radius;
    }

    /**
     * Returns the asteroid's center coordinates.
     *
     * @return A Point object with the asteroid's center coordinates.
     */
    public Point getCenter() {
        calculateCenterCoordinates();
        return coordinates;
    }

    @Override
    public void paint(Graphics g) {
        paintComponent(g);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // todo: honnan tudjuk, hogy a telepes melyik aszteroidán áll?
        // the asteroid on which the current settler stands, is painted light pink
        if (asteroid.getEntities().contains(Game.getInstance().getCurrentSettler())) {
            setBackground(new Color(255, 186, 209)); // light pink
        } else if (asteroid.getName() == null || asteroid.getName().equals("BASE")) { // the base asteroid is purple
            setBackground(new Color(128, 25, 128, 255)); // purple
        } else { // if none of the above conditions are met, we paint the asteroid based on its surface thickness
            int surfaceThickness = asteroid.getSurfaceThickness();
            if (surfaceThickness >= 0 && surfaceThickness <= 2)
                setBackground(Color.GREEN);
            else if (surfaceThickness >= 3 && surfaceThickness <= 4)
                setBackground(Color.YELLOW);
            else if (surfaceThickness >= 5 && surfaceThickness <= 6)
                setBackground(Color.RED);
        }

        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        setSize(radius * 2, radius * 2);
        g.fillOval(0, 0, getSize().width, getSize().height);
        super.paintComponent(g);

        // if there's a teleport on the asteroid, we indicate it by writing a small text next to the asteroid
        if (asteroid.getTeleportGate() != null) {
            g.setColor(Color.BLUE); // the asteroid's name is written in blue
            drawCenteredString(g, "TG", 20);
        }

        // we count the number of settlers on the asteroid, and if there's at least one on it, we display the number
        int settlers = 0;
        for (Entity entity : asteroid.getEntities()) {
            if (entity instanceof Settler)
                settlers++;
        }
        if (settlers != 0) {
            g.setColor(Color.BLACK);
            String settlerStr = settlers + (settlers == 1 ? " settler" : " settlers");
            drawCenteredString(g, settlerStr, -20);
        }

        this.setText(asteroid.getName());
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.darkGray);
        g.drawOval(0, 0, getSize().width, getSize().height);
    }

    @Override
    public boolean contains(int x, int y) {
        // If the button has changed size, make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

    public void explosionNotification() {
        JOptionPane.showMessageDialog(GameWindow.getInstance(), "Asteroid " + asteroid.getName() + " exploded!");
    }

    /**
     * Draws a horizontally centered string.
     *
     * @param g The graphics object to draw with.
     * @param text The text to be drawn.
     * @param yOffset The number of pixels the string should be drawn vertically from the center.
     */
    private void drawCenteredString(Graphics g, String text, int yOffset) {
        Rectangle rect = new Rectangle(radius * 2, radius * 2);
        FontMetrics metrics = g.getFontMetrics(this.getFont());
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent() + yOffset;
        g.setFont(this.getFont());
        g.drawString(text, x, y);
    }
}
