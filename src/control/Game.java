package control;

import map.Map;
import map.asteroid.BaseAsteroid;
import map.asteroid.Resource;
import map.entity.Robot;
import map.entity.Settler;
import utility.OutputFormatter;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class control.Game
 */
public class Game {

    private int currentRound;
    private int nextSunflare;
    private Map map;
    private Settler current;
    private ArrayList<Settler> settlers;
    private ArrayList<Robot> robots;


    public Game() {
        OutputFormatter.OutputCall("Game() - " + this.toString());
        currentRound = 0;
        nextSunflare = generateNextSunflare();
        BaseAsteroid baseAsteroid = new BaseAsteroid();
        map = new Map(baseAsteroid);
        settlers = new ArrayList<>();
        robots = new ArrayList<>();
        current = null/*settlers.get(0)*/;
        OutputFormatter.OutputReturn("return");
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
     * @param resources
     * @return map.asteroid.Resource
     */
    public Resource exchangeResource(ArrayList<Resource> resources) {
        OutputFormatter.OutputCall("exchangeResource() - " + this.toString());
        OutputFormatter.OutputReturn("return");
        return resources.get(resources.size() - 1); // a lista utolsó tagját cseréljük ki
    }

    /**
     *
     */
    public void gameWon() {
        OutputFormatter.OutputCall("gameWon() - " + this.toString());
        System.out.println("Settlers won!");
        OutputFormatter.OutputReturn("return");
    }

    /**
     *
     */
    private void gameLost() {
        OutputFormatter.OutputCall("gameLost() - " + this.toString());
        System.out.println("Settlers lost!");
        OutputFormatter.OutputReturn("return");
    }

    /**
     * @return int
     */
    private int generateNextSunflare() {
        OutputFormatter.OutputCall("generateNextSunflare() - " + this.toString());
        Random rnd = new Random();
        OutputFormatter.OutputReturn("return");
        if (currentRound < 20) {
            return rnd.nextInt(2) + 20;
        }
        return rnd.nextInt(19) + 10 + currentRound;
    }

    /**
     *
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
