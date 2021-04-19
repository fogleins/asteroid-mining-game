package map.entity;

import control.Game;
import map.BillOfResources;
import map.asteroid.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class map.entity.Robot
 */
public class Robot extends Entity implements Steppable {

    /**
     * The resources needed to build a Robot
     */
    private static final BillOfResources billToBuild;

    /**
     * ID to make names unique for robots.
     */
    private static int nameID = 0;
    private boolean steppedThisRound = false; // TODO remove later

    // Initializes the list of Resources needed to build a Robot.
    static {
        billToBuild = new BillOfResources();
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Coal());
        billToBuild.addResources(new Uranium());
    }

    /**
     * Constructor. Creates a Robot object.
     */
    public Robot(String name, Asteroid asteroid) {
        super(name);
        this.asteroid = asteroid;
        this.move(asteroid);
    }

    /**
     * Creates a Robot object and places it on the Asteroid passed as a parameter. If the caller hasn't got enough
     * resources, nothing happens.
     *
     * @param currentAsteroid The Asteroid object on which the caller Entity is on.
     * @param ownedResources  A list of {@code Resource}s the caller has.
     */
    public static void create(Asteroid currentAsteroid, ArrayList<Resource> ownedResources) {
        boolean hasResourcesToBuildRobot = billToBuild.use(ownedResources);
        if (hasResourcesToBuildRobot) {
            Robot robot = new Robot("Robot_" + nameID, currentAsteroid);
            nameID++;
            Game.getInstance().addSteppable(robot);
        } else {
            System.out.println("Error: Not enough resources, robot cannot be built.");
        }
    }

    /**
     * This method is called when the asteroid on which the robot is on explodes. If this happens, the robot will
     * randomly select a neighbouring asteroid and move onto that.
     */
    public void asteroidExploded() {
        ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
        Random rnd = new Random();
        super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        printState(); // TODO remove later
    }

    /**
     * Every time a round ends (all the Settlers have stepped), every Robot steps. This method is a basic implementation
     * of a Robot object deciding what to do.
     */
    public void step() {
        Random rnd = new Random();
        int choice = rnd.nextInt(2); // generated number will be 0 or 1
        ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
        if (choice == 0 && !(neighbours.size() < 1)) {
            super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        } else {
            super.drill();
            printState(); // TODO: remove later
        }
    }



    // TODO: the following methods are only used for testing


    @Override
    public boolean getSteppedThisRound() {
        return steppedThisRound;
    }

    @Override
    public void setSteppedThisRound(boolean stepped) {
        this.steppedThisRound = stepped;
    }

    public void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Robot");
        System.out.println("name: " + name);
        System.out.println("asteroid: " + asteroid.getName() + "\n");
    }

    @Override
    public void printDeath() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Robot");
        System.out.println(name + " ->X\n");
    }
}
