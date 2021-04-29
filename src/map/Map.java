package map;

import map.asteroid.Asteroid;
import map.asteroid.BaseAsteroid;

import java.util.ArrayList;

/**
 * Class Map
 */
public class Map {

    /**
     * The asteroids in the map.
     */
    private final ArrayList<Asteroid> asteroids = new ArrayList<>();

    /**
     * The baseasteroid, where all settlers must be to win the game (with enough resources).
     */
    private final Asteroid baseAsteroid;

    /**
     * Those asteroids, what affected by sunflare.
     */
    public static ArrayList<Asteroid> sunflareAsteroids = new ArrayList<>();

    public Map() {
        baseAsteroid = new BaseAsteroid();
        // todo: map init, create field
    }

    /**
     * Default getter of the BaseAsteroid, not used in the test.
     * @return baseAsteroid
     */
    private Asteroid getBaseAsteroid() {
        return baseAsteroid;
    }

    /**
     * Adds an asteroid to the asteroids.
     */
    public void addAsteroid(Asteroid asteroid) {
        for (Asteroid a : asteroids){
            if(a == asteroid){
                return;
            }
        }
        asteroids.add(asteroid);
    }

    /**
     * Getter of the asteroid list, not used in the test.
     * @return List of Asteroids objects held by asteroidsVector
     */
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    /**
     * Removes an asteroid from the asteroids list.
     * @param asteroid need to remove from the asteroids list.
     */
    public void removeAsteroid(Asteroid asteroid) {
        asteroids.remove(asteroid);
    }

    /**
     * Spreads sunflare all across the asteroids in the list.
     */
    public void sunflare() {
        // todo: selective sunflare
        for (Asteroid a : asteroids) {
            a.hitBySunflare();
        }
    }

    /**
     * Calls changePerihelionState function in all asteroids at the end of the round.
     */
    public void changePerihelion() {
        for (Asteroid a : asteroids) {
            a.changePerihelionState();
        }
    }

}
