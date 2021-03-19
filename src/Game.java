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

    private Map map;

//    private Game m_game;

    private ArrayList<Settler> settlers = new ArrayList<>();

    private Settler current;

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

//    /**
//     * Set the value of currentRound
//     *
//     * @param newVar the new value of currentRound
//     */
//    public void setCurrentRound(int newVar) {
//        currentRound = newVar;
//    }

    /**
     * Get the value of nextSunflare
     *
     * @return the value of nextSunflare
     */
    public int getNextSunflare() {
        return nextSunflare;
    }

//    /**
//     * Set the value of nextSunflare
//     *
//     * @param newVar the new value of nextSunflare
//     */
//    public void setNextSunflare(int newVar) {
//        nextSunflare = newVar;
//    }

    /**
     * Get the value of m_map
     *
     * @return the value of m_map
     */
    private Map getMap() {
        return map;
    }

    /**
     * Set the value of m_map
     *
     * @param map the new value of m_map
     */
    private void setMap(Map map) {
        this.map = map;
    }

    /**
     * Add a Settlers object to the settlersVector List
     */
    private void addSettler(Settler settler) {
        settlers.add(settler);
    }

    /**
     * Remove a Settlers object from settlersVector List
     */
    private void removeSettler(Settler settler) {
        settlers.remove(settler);
    }

    /**
     * Get the List of Settlers objects held by settlersVector
     *
     * @return List of Settlers objects held by settlersVector
     */
    private ArrayList<Settler> getSettlers() {
        return settlers;
    }

    /**
     * Get the value of current
     *
     * @return the value of current
     */
    private Settler getCurrent() {
        return current;
    }

    /**
     * Set the value of current
     *
     * @param settler the new value of current
     */
    private void setCurrent(Settler settler) {
        current = settler;
    }

    /**
     * Add a Robots object to the robotsVector List
     */
    private void addRobot(Robot robot) {
        robots.add(robot);
    }

    /**
     * Remove a Robots object from robotsVector List
     */
    private void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    /**
     * Get the List of Robots objects held by robotsVector
     *
     * @return List of Robots objects held by robotsVector
     */
    private ArrayList<Robot> getRobots() {
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

//  Ezek már fent megvannak
//
//    /**
//     * @param settler
//     */
//    public void addSettler(Settler settler) {
//    }
//
//
//    /**
//     * @param settler
//     */
//    public void removeSettler(Settler settler) {
//    }
//
//
//
//    /**
//     * @param robot
//     */
//    public void addRobot(Robot robot) {
//    }
//
//
//    /**
//     * @param robot
//     */
//    public void removeRobot(Robot robot) {
//    }


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
