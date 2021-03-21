package map;

import map.asteroid.Asteroid;
import map.asteroid.BaseAsteroid;
import utility.OutputFormatter;

import java.util.ArrayList;


/**
 * Class map.Map
 */
public class Map {

    //
    // Fields
    //


    private final ArrayList<Asteroid> asteroids = new ArrayList<>();
    private BaseAsteroid baseAsteroid;

    //
    // Constructors
    //
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
     * Get the List of map.asteroid.BaseAsteroid objects held by baseasteroidVector
     *
     * @return List of map.asteroid.BaseAsteroid objects held by baseasteroidVector
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
     * @return map.asteroid.Asteroid
     */
    public Asteroid removeAsteroid(Asteroid asteroid) {
        OutputFormatter.OutputCall("remove - " + asteroid.toString());
        asteroids.remove(asteroid);
        OutputFormatter.OutputReturn("return - "+ asteroid.toString());
        return asteroid;
    }


    /**
     *
     */
    public void sunflare() {
        OutputFormatter.OutputCall("sunflare() - " + this.toString());
        for (Asteroid a : asteroids){
            a.hitBySunflare();
        }
        OutputFormatter.OutputReturn("return");
    }


    /**
     *
     */
    public void changePerihelion() {
        for (Asteroid a : asteroids){
            a.changePerihelionState();
            OutputFormatter.OutputCall("changePerihelionState() - " + a.toString());
        }
        OutputFormatter.OutputReturn("return");
    }


}
