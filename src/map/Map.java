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
     * Random object, for random generation.
     */
    private final Random rnd = new Random();
    /**
     * The asteroids in the map.
     */
    private final ArrayList<Asteroid> asteroids = new ArrayList<>();
    /**
     * The baseasteroid, where all settlers must be to win the game (with enough resources).
     */
    private final Asteroid baseAsteroid;

    /**
     * It's declare how many "row" or "column" will there be.
     * For example mapBound=10 means that will be total of 10x10=100 asteroids.
     */
    private int mapBound;

    /**
     * Constructor of the map, this will initialize the asteroids, and neighbour connections.
     */
    public Map() {
        mapBound=10;
        baseAsteroid = new BaseAsteroid();
        asteroids.add(baseAsteroid);
        /**
         * 2D array, which is being used in asteroid generations, and in connecting them
         * as neighbours.
         */
        Asteroid [][] asteroidsMatrix = new Asteroid[mapBound][mapBound];
        asteroidsMatrix[0][0]=baseAsteroid;

        /**
         * Generation of the asteroids.
         */
        for (int i = 0;i<mapBound;i++){
            for(int j = 0;j<mapBound;j++){
                if(!(i==0 && j==0)){
                    Asteroid a = new Asteroid("A" + i +""+ j, rnd.nextBoolean(), rnd.nextInt(7), rnd.nextInt(5));
                    asteroidsMatrix[i][j]=a;
                    asteroids.add(a);
                }
            }
        }

        /**
         * Connects the asteroids as neighbours. The asteroids are connected only with
         * the upper, lower, right, left side asteroids.
         */
        for (int i = 0;i<mapBound;i++){
            for(int j = 0;j<mapBound;j++){
                /**
                 * The purpose of the try-catches is to connect the upper, lower rows,
                 * and side columns asteroids with each other. IndexOutOfBounds exceptions are ignored for purpose.
                 */
                try {
                    asteroidsMatrix[i][j].addNeighbour(asteroidsMatrix[i-1][j]);
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    asteroidsMatrix[i][j].addNeighbour(asteroidsMatrix[i+1][j]);
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    asteroidsMatrix[i][j].addNeighbour(asteroidsMatrix[i][j-1]);
                } catch (ArrayIndexOutOfBoundsException e) {}
                try {
                    asteroidsMatrix[i][j].addNeighbour(asteroidsMatrix[i][j+1]);
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
    }

    /**
     * Default getter of the BaseAsteroid, not used in the test.
     *
     * @return baseAsteroid
     */
    public Asteroid getBaseAsteroid() {
        return baseAsteroid;
    }

    /**
     * Adds an asteroid to the asteroids.
     */
    public void addAsteroid(Asteroid asteroid) {
        for (Asteroid a : asteroids) {
            if (a == asteroid) {
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
     */
    public void removeAsteroid(Asteroid asteroid) {
        asteroids.remove(asteroid);
    }

    /**
     * Spreads sunflare all across the asteroids in the list.
     */
    public void sunflare() {
        for (Asteroid a : asteroids) {
            if (rnd.nextInt(100) < 20) {
                a.hitBySunflare();
            }
        }
    }

    /**
     * Calls changePerihelionState function in some asteroids at the end of the round.
     */
    public void changePerihelion() {
        for (Asteroid a : asteroids) {
            if (rnd.nextInt(100) < 40) {
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
