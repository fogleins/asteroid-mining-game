package control;

import map.Map;
import map.asteroid.BaseAsteroid;
import map.asteroid.Resource;
import map.entity.Entity;
import map.entity.Robot;
import map.entity.Settler;
import utility.OutputFormatter;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class control.Game
 */
public class Game {

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
     * List of Robots who are playing.
     */
    private ArrayList<Robot> robots;


    /**
     * Constructor. Initializes the Game object.
     */
    public Game() {
        OutputFormatter.OutputCall("Game() - " + this.toString());
        Entity.setGame(this);
        currentRound = 0;
        nextSunflare = generateNextSunflare();
        BaseAsteroid baseAsteroid = new BaseAsteroid(this);
        map = new Map(baseAsteroid);
        settlers = new ArrayList<>();
        robots = new ArrayList<>();
        current = null/*settlers.get(0)*/;
        OutputFormatter.OutputReturn("return");
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
     * Add a Robot object to the robots list
     */
    public void addRobot(Robot robot) {
        OutputFormatter.OutputCall("addRobot() - " + this.toString());
        robots.add(robot);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Remove a Robot object from robots
     */
    public void removeRobot(Robot robot) {
        OutputFormatter.OutputCall("removeRobot() - " + this.toString());
        robots.remove(robot);
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
     * This method is called every time all the settlers have moved in a given round. It steps with all the robots,
     * checks if there should be a sunflare, and changes the asteroid's 'inPerihelion' state.
     */
    private void roundFinished() {
        OutputFormatter.OutputCall("roundFinished() - " + this.toString());
        for (Robot robot : robots) {
            robot.step();
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
