import java.util.ArrayList;


/**
 * Class Game
 */
public class Game {

    //
    // Fields
    //

    private int currentRound;
    private int nextSunflare;

    private Map m_map;

    private Game m_game;

    private ArrayList<Settler> settlers = new ArrayList<>();

    private Settler m_current;

    private ArrayList<Robot> robots = new ArrayList<>();

    //
    // Constructors
    //
    public Game() {
    }

    ;

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
     * Set the value of currentRound
     *
     * @param newVar the new value of currentRound
     */
    public void setCurrentRound(int newVar) {
        currentRound = newVar;
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
     * Set the value of nextSunflare
     *
     * @param newVar the new value of nextSunflare
     */
    public void setNextSunflare(int newVar) {
        nextSunflare = newVar;
    }

    /**
     * Get the value of m_map
     *
     * @return the value of m_map
     */
    private Map getMap() {
        return m_map;
    }

    /**
     * Set the value of m_map
     *
     * @param newVar the new value of m_map
     */
    private void setMap(Map newVar) {
        m_map = newVar;
    }

    /**
     * Add a Settlers object to the settlersVector List
     */
    private void addSettlers(Settler new_object) {
        settlers.add(new_object);
    }

    /**
     * Remove a Settlers object from settlersVector List
     */
    private void removeSettlers(Settler new_object) {
        settlers.remove(new_object);
    }

    /**
     * Get the List of Settlers objects held by settlersVector
     *
     * @return List of Settlers objects held by settlersVector
     */
    private ArrayList<Settler> getSettlersList() {
        return settlers;
    }

    /**
     * Get the value of m_current
     *
     * @return the value of m_current
     */
    private Settler getCurrent() {
        return m_current;
    }

    /**
     * Set the value of m_current
     *
     * @param newVar the new value of m_current
     */
    private void setCurrent(Settler newVar) {
        m_current = newVar;
    }

    /**
     * Add a Robots object to the robotsVector List
     */
    private void addRobots(Robot new_object) {
        robots.add(new_object);
    }

    /**
     * Remove a Robots object from robotsVector List
     */
    private void removeRobots(Robot new_object) {
        robots.remove(new_object);
    }

    /**
     * Get the List of Robots objects held by robotsVector
     *
     * @return List of Robots objects held by robotsVector
     */
    private ArrayList<Robot> getRobotsList() {
        return robots;
    }


    //
    // Other methods
    //

    /**
     *
     */
    public void gameWon() {
    }


    /**
     * @param settler
     */
    public void addSettler(Settler settler) {
    }


    /**
     * @param settler
     */
    public void removeSettler(Settler settler) {
    }


    /**
     * @param robot
     */
    public void addRobot(Robot robot) {
    }


    /**
     * @param robot
     */
    public void removeRobot(Robot robot) {
    }


    /**
     * @param resources
     * @return Resource
     */
    public Resource exchangeResource(ArrayList<Resource> resources) {
        return null; // TODO: implementálni
    }


    /**
     *
     */
    private void gameLost() {
    }


    /**
     * @return int
     */
    private int generateNextSunflare() {
        return -1; // TODO: implementálni
    }


    /**
     *
     */
    private void roundFinished() {
    }


}
