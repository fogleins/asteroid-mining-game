import java.util.ArrayList;


/**
 * Class Map
 */
public class Map {

    //
    // Fields
    //


    private final ArrayList<Asteroid> asteroids = new ArrayList<>();
    private Asteroid baseAsteroid;

    //
    // Constructors
    //
    public Map(Asteroid baseAsteroid) {
        this.baseAsteroid = baseAsteroid;
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //


    /**
     * Get the List of BaseAsteroid objects held by baseasteroidVector
     *
     * @return List of BaseAsteroid objects held by baseasteroidVector
     */
    private Asteroid getBaseAsteroid() {
        return baseAsteroid;
    }


    /**
     * Add a Asteroids object to the asteroidsVector List
     */
    private void addAsteroids(Asteroid asteroid) {
        asteroids.add(asteroid);
    }

    /**
     * Remove a Asteroids object from asteroidsVector List
     */
    private void removeAsteroids(Asteroid asteroid) {
        asteroids.remove(asteroid);
    }

    /**
     * Get the List of Asteroids objects held by asteroidsVector
     *
     * @return List of Asteroids objects held by asteroidsVector
     */
    private ArrayList<Asteroid> getAsteroidsList() {
        return asteroids;
    }


    //
    // Other methods
    //

    /**
     * @param asteroid
     * @return Asteroid
     */
    public Asteroid removeAsteroid(Asteroid asteroid) {
        return null; // TODO: implementálni
    }


    /**
     *
     */
    public void sunflare() {
    }


    /**
     *
     */
    public void changePerihelion() {
    }


}
