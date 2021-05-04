package map;

import map.asteroid.Asteroid;
import map.asteroid.BaseAsteroid;

import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Random object, for random generation.
     */
    private static Random rnd = new Random();

    /**
     * Constructor of the map, this will initialize the asteroids, and neighbour connections.
     */
    public Map() {
        baseAsteroid = new BaseAsteroid();
        asteroids.add(baseAsteroid);
        for(int i = 1;i<3;i++){
            Asteroid a = new Asteroid("A"+i,rnd.nextBoolean(), rnd.nextInt(7), rnd.nextInt(5) );
            asteroids.add(a);
        }
        for(Asteroid a : asteroids){
            for(Asteroid b : asteroids){
                if(a!=b){
                    a.addNeighbour(b);
                }
            }
        }
    }

    /**
     * Default getter of the BaseAsteroid, not used in the test.
     * @return baseAsteroid
     */
    public Asteroid getBaseAsteroid() {
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
        for (Asteroid a : asteroids) {
            if(rnd.nextInt(100)<20){
                a.hitBySunflare();
            }
        }
    }

    /**
     * Calls changePerihelionState function in some asteroids at the end of the round.
     */
    public void changePerihelion() {
        for (Asteroid a : asteroids) {
            if(rnd.nextInt(100)<40){
                a.changePerihelionState();
            }
        }
    }

    /**
     * Tells the map that a round has passed
     * Exposes the necessary resource on the map
     */
    public void roundPassed() {
        for (Asteroid a : asteroids) {
            a.expose();
        }
    }
}
