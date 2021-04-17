package control;

import map.Map;
import map.asteroid.BaseAsteroid;
import map.asteroid.Resource;
import map.entity.Entity;
import map.entity.Settler;
import map.entity.Steppable;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class control.Game
 */
public class Game {
    /**
     * The instance of the singleton Game class.
     */
    private static final Game instance = new Game();

    /**
     * Counts the number of rounds played.
     */
    private int currentRound;

    /**
     * The number of the round in which the next sunflare occurs.
     */
    private int nextSunflare;

    /**
     * Reference to the Map object, which contains game's map.
     */
    private Map map;

    /**
     * The settler, who should steps next.
     */
    private Settler current;

    /**
     * List of Settlers, who are playing the game.
     */
    private ArrayList<Settler> settlers;

    /**
     * List of Steppables who are playing.
     */
    private ArrayList<Steppable> steppables;


    /**
     * Constructor. Initializes the Game object.
     */
    private Game() {
        currentRound = 0;
        nextSunflare = generateNextSunflare();
        BaseAsteroid baseAsteroid = new BaseAsteroid(this);
        map = new Map(baseAsteroid);
        settlers = new ArrayList<>();
        steppables = new ArrayList<>();
        current = null/*settlers.get(0)*/;
    }

    /**
     * Other objects can use this method to access the Game object.
     *
     * @return The instance of the Game class.
     */
    public static Game getInstance() {
        return instance;
    }

    /**
     * Returns the map the game is played on.
     *
     * @return The game's Map object.
     */
    public Map getMap() {
        return map;
    }

    public ArrayList<Settler> getSettlers() {
        return settlers;
    }

    public ArrayList<Steppable> getSteppables() {
        return steppables;
    }

    // TODO: only for testing, remove later
    public void setNextSunflare(int nextSunflare) {
        this.nextSunflare = nextSunflare;
    }

    /**
     * Add a Settler object to the settlers list
     */
    public void addSettler(Settler settler) {
        settlers.add(settler);
    }

    /**
     * Remove a Settler object from settlers
     */
    public void removeSettler(Settler settler) {
        settlers.remove(settler);
    }

    /**
     * Add a Steppable object to the steppables list
     */
    public void addSteppable(Steppable steppable) {
        steppables.add(steppable);
    }

    /**
     * Remove a Steppable object from steppables
     */
    public void removeSteppable(Steppable steppable) {
        steppables.remove(steppable);
    }

    /**
     * Returns a {@code Resource} object from the Settler's resource list which should be exchanged.
     *
     * @param resources A list of {@code Resource}s the Settler has.
     * @return The {@code Resource} which should be exchanged.
     */
    public Resource exchangeResource(ArrayList<Resource> resources) {
        return resources.get(resources.size() - 1);  // a lista utolsó tagját cseréljük ki
    }

    /**
     * This method is called when the settlers have collected all the needed resources on a single asteroid.
     */
    public void gameWon() {
        System.out.println("Settlers won!");
    }

    /**
     * This method is called if there's no way for the players to win.
     */
    private void gameLost() {
        System.out.println("Settlers lost!");
    }

    /**
     * Randomly generates a number when a sunflare should occur.
     *
     * @return int The number of round in which the next sunflare occurs.
     */
    private int generateNextSunflare() {
        Random rnd = new Random();
        if (currentRound < 20) {
            return rnd.nextInt(2) + 20;
        }
        return rnd.nextInt(19) + 10 + currentRound;
    }

    /**
     * This method is called every time all the settlers have moved in a given round. It steps with all the steppables,
     * checks if there should be a sunflare, and changes the asteroid's 'inPerihelion' state.
     */
    private void roundFinished() {
        for (Steppable steppable : steppables) {
            steppable.step();
        }
        if (currentRound == nextSunflare) {
            map.sunflare();
            generateNextSunflare();
        }
        map.changePerihelion();
        currentRound++;
    }

    /**
     * TODO: used only for testing, should be removed later.
     */
    public void roundFinishedWrapper() {
        this.roundFinished();
    }
}
