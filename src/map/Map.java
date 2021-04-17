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
    private BaseAsteroid baseAsteroid;

    /**
     * Constructor of the map, which is initialize some asteroids.
     *
     * @param baseAst Where all settlers must be to win the game (with enough resources).
     */
    public Map(BaseAsteroid baseAst) {
        baseAsteroid = baseAst;
        asteroids.add(baseAsteroid);
        for (int i = 1; i < 3; i++) {
            Asteroid a = new Asteroid();
            a.setName("a" + i);
            asteroids.add(a);
        }
        for (Asteroid a : asteroids) {
            for (Asteroid b : asteroids) {
                if (a != b) {
                    a.addNeighbour(b);

                }
            }
        }
    }

    /**
     * Default getter of the BaseAsteroid, not used in the test.
     *
     * @return baseAsteroid
     */
    private Asteroid getBaseAsteroid() {
        return baseAsteroid;
    }


    /**
     * Adds an asteroid to the asteroids.
     */
    public void addAsteroid(Asteroid asteroid) {
        // TODO: itt le kell ellenőrizni, hogy nincs-e már hozzáadva
        for (Asteroid a : asteroids){
            if(a==asteroid){
                return;
            }
        }
        asteroids.add(asteroid);
    }

    /**
     * Getter of the asteroid list, not used in the test.
     *
     * @return List of Asteroids objects held by asteroidsVector
     */
    public ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    /**
     * Removes an asteroid from the asteroids list.
     *
     * @param asteroid need to remove from the asteroids list.
     * @return with the reference of the removed asteroid.
     */
    public Asteroid removeAsteroid(Asteroid asteroid) {
        asteroids.remove(asteroid);
        return asteroid;
    }


    /**
     * Spreads sunflare all across the asteroids in the list.
     */
    public void sunflare() {
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
