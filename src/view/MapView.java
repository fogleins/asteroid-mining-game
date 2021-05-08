package view;

import control.Game;
import map.Map;
import map.asteroid.Asteroid;
import map.entity.TeleportGate;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapView extends JPanel {
    /**
     * Map field, for accessing the asteroids, and their asteroidViews.
     */
    private Map map = Game.getInstance().getMap();

    /**
     * Height of the map (panel).
     */
    private int height = 3000;

    /**
     * Width of the map (panel).
     */
    private int width = 3000;

    /**
     * Determine which is the closest distance between two neighbouring asteroidView.
     */
    private int gap = 30;

    /**
     * AsteroidViews are generated in an imaginary box(container),
     * which means the distances between the asteroidViews are different.
     * Container size describe the height and width (same value) of the box.
     */
    private int containerSize = 300;

    /**
     * Describe how many pixels will the height and width of the teleport be.
     */
    private int teleportSize = 25;

    /**
     * Describe how many pixels will the height and width of the asteroid(View) be.
     */
    private int asteroidSize = 100;

    /**
     * Random generator object, being used in generation of the asteroids positions.
     */
    private Random rnd = new Random();

    /**
     * A flag, which is describe if repaint should be used.
     */
    private boolean clearPanel = false;


    /**
     * Constructor of the MapView. Sets the asteroids in position, connect the asteroids etc.
     */
    public MapView() {
        this.setPreferredSize(new Dimension(width,height));
        this.setLayout(null);

        /**
         * Delta variables change where the asteroids being placed.
         */
        int dX=0;
        int dY=0;
        for(Asteroid a : map.getAsteroids()){
            AsteroidView av = a.getAsteroidView();
            /**
             * Setting random position for the asteroidView in an imaginary container, and adding them to the panel.
             */
            int x = rnd.nextInt(containerSize-(gap+teleportSize+asteroidSize))+dX;
            int y = rnd.nextInt(containerSize-(gap+teleportSize+asteroidSize))+dY;
            av.setBounds(x, y,asteroidSize,asteroidSize);
            add(av);
            dX+=containerSize;
            if(dX>=width){
                dY+=containerSize;
                dX=0;
            }
        }



    }

    /**
     * Draws the neighbour connections and the teleport gates.
     * @param g Graphics object of the map(panel)
     */
    @Override
    protected void paintComponent(Graphics g) {
        /**
         * Repaints the panel if its necessary.
         */
        if(clearPanel){
            repaint();
            clearPanel=false;
        }
        super.paintComponent(g);
        drawNeighboursConnection(g);
//        drawTeleportGates(g); // a mapot nem frissítjük, amikor egy aszteroida változik, és felesleges is lenne;
        // emiatt viszont nem frissül a nézet, amikor lerakunk egy teleportot, úgyhogy egyelőre marad az asteroid
        // view-ban lévő megoldás a teleportkapu kijelzésére
    }

    /**
     * Draws the neighbour connections.
     * @param g Graphics object of the map(panel)
     */
    private void drawNeighboursConnection(Graphics g){
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));
        for (Asteroid a : map.getAsteroids()){
            for(Asteroid b: a.getNeighbours().getAsteroidNeighbours()){
                int x1 = a.getAsteroidView().getCenter().x;
                int y1 = a.getAsteroidView().getCenter().y;
                int x2 = b.getAsteroidView().getCenter().x;
                int y2 = b.getAsteroidView().getCenter().y;
                g2.drawLine(x1,y1,x2,y2);
            }
        }
    }

    /**
     * Draws the teleport gates.
     * @param g Graphics object of the map(panel)
     */
    private void drawTeleportGates(Graphics g){
        g.setColor(Color.BLUE);
        for (Asteroid a : map.getAsteroids()){
            if(a.getTeleportGate()!=null){
                Point p = a.getAsteroidView().getCenter();
                int dX = asteroidSize/2+5;
                int dY = asteroidSize/2+5;
                g.fillOval(p.x+dX,p.y-dY,25,25);
            }
        }
    }

    /**
     * Updates the map(panel).
     * @param asteroidView The AsteroidView object, who is called th update.
     */
    public void updateView(AsteroidView asteroidView){
        clearPanel=true;
    }

}
