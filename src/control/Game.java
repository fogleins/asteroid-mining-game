package control;

import map.Map;
import map.asteroid.BaseAsteroid;
import map.asteroid.Resource;
import map.entity.Entity;
import map.entity.Settler;
import map.entity.Steppable;
import utility.OutputFormatter;

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
        OutputFormatter.OutputCall("Game() - " + this.toString());
        currentRound = 0;
        nextSunflare = generateNextSunflare();
        BaseAsteroid baseAsteroid = new BaseAsteroid(this);
        map = new Map(baseAsteroid);
        settlers = new ArrayList<>();
        steppables = new ArrayList<>();
        current = null/*settlers.get(0)*/;
        OutputFormatter.OutputReturn("return");
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
        OutputFormatter.OutputCall("addSettler() - " + this.toString());
        OutputFormatter.OutputReturn("return - map");
        return map;
    }

    public ArrayList<Settler> getSettlers() {
        return settlers;
    }

    public ArrayList<Steppable> getSteppables() {
        return steppables;
    }

    /**
     * Add a Settler object to the settlers list
     */
    public void addSettler(Settler settler) {
        OutputFormatter.OutputCall("addSettler() - " + this.toString());
        settlers.add(settler);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Remove a Settler object from settlers
     */
    public void removeSettler(Settler settler) {
        OutputFormatter.OutputCall("removeSettler() - " + this.toString());
        settlers.remove(settler);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Add a Steppable object to the steppables list
     */
    public void addSteppable(Steppable steppable) {
        OutputFormatter.OutputCall("addSteppable() - " + this.toString());
        steppables.add(steppable);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Remove a Steppable object from steppables
     */
    public void removeSteppable(Steppable steppable) {
        OutputFormatter.OutputCall("removeSteppable() - " + this.toString());
        steppables.remove(steppable);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Returns a {@code Resource} object from the Settler's resource list which should be exchanged.
     *
     * @param resources A list of {@code Resource}s the Settler has.
     * @return The {@code Resource} which should be exchanged.
     */
    public Resource exchangeResource(ArrayList<Resource> resources) {
        OutputFormatter.OutputCall("exchangeResource() - " + this.toString());
        var ret = resources.get(resources.size() - 1);
        OutputFormatter.OutputReturn("return - resource");
        return ret; // a lista utolsó tagját cseréljük ki
    }

    /**
     * This method is called when the settlers have collected all the needed resources on a single asteroid.
     */
    public void gameWon() {
        OutputFormatter.OutputCall("gameWon() - " + this.toString());
        System.out.println("Settlers won!");
        OutputFormatter.OutputReturn("return");
    }

    /**
     * This method is called if there's no way for the players to win.
     */
    private void gameLost() {
        OutputFormatter.OutputCall("gameLost() - " + this.toString());
        System.out.println("Settlers lost!");
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Randomly generates a number when a sunflare should occur.
     *
     * @return int The number of round in which the next sunflare occurs.
     */
    private int generateNextSunflare() {
        OutputFormatter.OutputCall("generateNextSunflare() - " + this.toString());
        Random rnd = new Random();
        OutputFormatter.OutputReturn("return - int");
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
        OutputFormatter.OutputCall("roundFinished() - " + this.toString());
        for (Steppable steppable : steppables) {
            steppable.step();
        }
        if (currentRound == nextSunflare) {
            map.sunflare();
            generateNextSunflare();
        }
        map.changePerihelion();
        currentRound++;
        OutputFormatter.OutputReturn("return");
    }
}
