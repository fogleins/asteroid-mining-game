package map;

import map.asteroid.Asteroid;
import map.asteroid.BaseAsteroid;
import utility.OutputFormatter;

import java.util.ArrayList;


/**
 * Class Map
 */
public class Map {

    //
    // Fields
    //

    /**
     * The asteroids in the map.
     */
    private final ArrayList<Asteroid> asteroids = new ArrayList<>();
    /**
     * The baseasteroid, where all settlers must be to win the game (with enough resources).
     */
    private BaseAsteroid baseAsteroid;

    //
    // Constructors
    //

    /**
     * Constructor of the map, which is initialize some asteroids.
     * @param baseAst Where all settlers must be to win the game (with enough resources).
     */
    public Map(BaseAsteroid baseAst) {
        OutputFormatter.OutputCall("create - " + this.toString());
        baseAsteroid=baseAst;
        asteroids.add(baseAsteroid);
        for(int i = 1;i<3;i++){
            Asteroid a = new Asteroid();
            a.setName("a"+ i);
            asteroids.add(a);
        }
        for(Asteroid a : asteroids){
            for(Asteroid b : asteroids){
                if(a!=b){
                    a.addNeighbour(b);

                }
            }
        }
        OutputFormatter.OutputReturn("return");
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //


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
    private void addAsteroids(Asteroid asteroid) {
        asteroids.add(asteroid);
    }

    /**
     * Remove an asteroid from the asteroids.
     */


    /**
     * Getter of the asteroid list, not used in the test.
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
     * Removes an asteroid from the asteroids list.
     *
     * @param asteroid need to remove from the asteroids list.
     * @return with the reference of the removed asteroid.
     */
    public Asteroid removeAsteroid(Asteroid asteroid) {
        OutputFormatter.OutputCall("remove - " + asteroid.toString());
        asteroids.remove(asteroid);
        OutputFormatter.OutputReturn("return - "+ asteroid.toString());
        return asteroid;
    }


    /**
     * Spreads sunflare all across the asteroids in the list.
     */
    public void sunflare() {
        OutputFormatter.OutputCall("sunflare() - " + this.toString());
        for (Asteroid a : asteroids){
            a.hitBySunflare();
        }
        OutputFormatter.OutputReturn("return");
    }


    /**
     * Calls changePerihelionState function in all asteroids at the end of the round.
     */
    public void changePerihelion() {
        for (Asteroid a : asteroids){
            a.changePerihelionState();
            OutputFormatter.OutputCall("changePerihelionState() - " + a.toString());
        }
        OutputFormatter.OutputReturn("return");
    }


}
