/**
 * Class Neighbours
 */
public class Neighbours {

    //
    // Fields
    //

    private Asteroid asteroidNeighbours;
    private Asteroid teleportGateNeighbours;

    //
    // Constructors
    //
    public Neighbours() {
    }

    ;

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
    public Asteroid getAsteroidNeighbours() {
        return asteroidNeighbours;
    }

    /**
     * Set the value of asteroidNeighbours
     *
     * @param asteroid the new value of asteroidNeighbours
     */
    public void setAsteroidNeighbours(Asteroid asteroid) {
        asteroidNeighbours = asteroid;
    }

    /**
     * Get the value of teleportGateNeighbours
     *
     * @return the value of teleportGateNeighbours
     */
    public Asteroid getTeleportGateNeighbours() {
        return teleportGateNeighbours;
    }

    /**
     * Set the value of teleportGateNeighbours
     *
     * @param asteroid the new value of teleportGateNeighbours
     */
    public void setTeleportGateNeighbours(Asteroid asteroid) {
        teleportGateNeighbours = asteroid;
    }

    //
    // Other methods
    //

}
