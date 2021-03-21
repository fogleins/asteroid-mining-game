package map.asteroid;

import java.util.ArrayList;

/**
 * Class map.asteroid.Neighbours
 */
public class Neighbours {

    //
    // Fields
    //

    private ArrayList<Asteroid> asteroidNeighbours;
    private ArrayList<Asteroid> teleportGateNeighbours;

    //
    // Constructors
    //
    public Neighbours(ArrayList<Asteroid> asteroidNeighbours, ArrayList<Asteroid> teleportGateNeighbours) {
        this.asteroidNeighbours = asteroidNeighbours;
        this.teleportGateNeighbours = teleportGateNeighbours;
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the value of asteroidNeighbours
     *
     * @return the value of asteroidNeighbours
     */
    public ArrayList<Asteroid> getAsteroidNeighbours() {
        return asteroidNeighbours;
    }

    /**
     * Set the value of asteroidNeighbours
     *
     * @param asteroid the new value of asteroidNeighbours
     */
    public void setAsteroidNeighbours(ArrayList<Asteroid> asteroid) {
        asteroidNeighbours = asteroid;
    }

    /**
     * Get the value of teleportGateNeighbours
     *
     * @return the value of teleportGateNeighbours
     */
    public ArrayList<Asteroid> getTeleportGateNeighbours() {
        return teleportGateNeighbours;
    }

    /**
     * Set the value of teleportGateNeighbours
     *
     * @param asteroids the new value of teleportGateNeighbours
     */
    public void setTeleportGateNeighbours(ArrayList<Asteroid> asteroids) {
        teleportGateNeighbours = asteroids;
    }

    //
    // Other methods
    //

}
