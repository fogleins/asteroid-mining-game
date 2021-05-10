package map.asteroid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class map.asteroid.Neighbours
 */
public class Neighbours implements Serializable {

    private static final long serialVersionUID = 4109286614855861924L;
    private final ArrayList<Asteroid> asteroidNeighbours;
    private final ArrayList<Asteroid> teleportGateNeighbours;

    public Neighbours(ArrayList<Asteroid> asteroidNeighbours, ArrayList<Asteroid> teleportGateNeighbours) {
        this.asteroidNeighbours = asteroidNeighbours;
        this.teleportGateNeighbours = teleportGateNeighbours;
    }

    /**
     * Get the value of asteroidNeighbours
     * @return the value of asteroidNeighbours
     */
    public ArrayList<Asteroid> getAsteroidNeighbours() {
        return asteroidNeighbours;
    }

    /**
     * Get the value of teleportGateNeighbours
     * @return the value of teleportGateNeighbours
     */
    public ArrayList<Asteroid> getTeleportGateNeighbours() {
        return teleportGateNeighbours;
    }

}
