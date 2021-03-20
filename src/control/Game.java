package control;

import map.Map;
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

    //
    // Fields
    //

    private int currentRound;
    private int nextSunflare;
    private Map map;
    //    private control.Game m_game;
    private Settler current;
    private ArrayList<Settler> settlers;
    private ArrayList<Robot> robots;

    //
    // Constructors
    //
    public Game() {
        /* TODO: listák inicializálás? */
        currentRound = 0;
        nextSunflare = generateNextSunflare(); // TODO: az elején is lehet, vagy csak későbbi körökben?
//        map = ? // TODO: map és settler init
        settlers = new ArrayList<>();
        robots = new ArrayList<>();
        current = null/*settlers.get(0)*/;
    }


    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the value of currentRound
     *
     * @return the value of currentRound
     */
    public int getCurrentRound() {
        return currentRound;
    }

    /**
     * Get the value of nextSunflare
     *
     * @return the value of nextSunflare
     */
    public int getNextSunflare() {
        return nextSunflare;
    }

    /**
     * Get the value of m_map
     *
     * @return the value of m_map
     */
    public Map getMap() {
        return map;
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
     * Add a Robot object to the robots list
     */
    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    /**
     * Remove a Robot object from robots
     */
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    //
    // Other methods
    //

    /**
     * @param resources
     * @return map.asteroid.Resource
     */
    public Resource exchangeResource(ArrayList<Resource> resources) {
        /* TODO: a leírásban az szerepel, hogy a lista bármelyik elemét kicserélheti, ehhez nem kellene a kicserélendő
                elem indexe?
         */
        Resource tmp = resources.get(resources.size() - 1); // a lista utolsó tagját cseréljük ki
        Resource mined = current.getAsteroid().mined(); // a magból kibányászott nyersanyag
        current.getAsteroid().setResource(tmp);
        return mined;
    }

    /**
     *
     */
    public void gameWon() {
        OutputFormatter.OutputCall("gameWon()");
        // TODO: ennyi elég?
        System.out.println("Settlers won!");
        OutputFormatter.OutputReturn("return");
    }

    /**
     *
     */
    private void gameLost() {
        OutputFormatter.OutputCall("gameLost()");
        // TODO: ennyi elég?
        System.out.println("Settlers lost!");
        OutputFormatter.OutputReturn("return");
    }

    /**
     * @return int
     */
    private int generateNextSunflare() {
        OutputFormatter.OutputCall("generateNextSunflare()");
        Random rnd = new Random();
        OutputFormatter.OutputReturn("return");
        // a random szám 0 <= rnd.nextInt() <= 2, de mivel nem lehet az éppen játszott körben napvihar (mert egy körrel
        // előtte értesítjük a játékosokat), hozzáadunk egyet, így 1 <= return <= 4 lesz.
        return rnd.nextInt(3) + 1;
    }

    /**
     *
     */
    private void roundFinished() {
        currentRound++;
    }

    // TODO: ezekből valami kell?
//    /**
//     * Get the List of Settlers objects held by settlersVector
//     *
//     * @return List of Settlers objects held by settlersVector
//     */
//    private ArrayList<Settler> getSettlers() {
//        return settlers;
//    }

//    /**
//     * Get the value of current
//     *
//     * @return the value of current
//     */
//    private Settler getCurrent() {
//        return current;
//    }

//    /**
//     * Set the value of current
//     *
//     * @param settler the new value of current
//     */
//    private void setCurrent(Settler settler) {
//        current = settler;
//    }

//    /**
//     * Set the value of m_map
//     *
//     * @param map the new value of m_map
//     */
//    private void setMap(Map map) {
//        this.map = map;
//    }

//    /**
//     * Set the value of nextSunflare
//     *
//     * @param newVar the new value of nextSunflare
//     */
//    public void setNextSunflare(int newVar) {
//        nextSunflare = newVar;
//    }

//    /**
//     * Set the value of currentRound
//     *
//     * @param newVar the new value of currentRound
//     */
//    public void setCurrentRound(int newVar) {
//        currentRound = newVar;
//    }

//    /**
//     * Get the List of Robots objects held by robotsVector
//     *
//     * @return List of Robots objects held by robotsVector
//     */
//    private ArrayList<Robot> getRobots() {
//        return robots;
//    }


}
