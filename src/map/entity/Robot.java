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
        this.move(asteroid); // TODO: ez eredetileg a create() végén volt, de ha itt úgyis megkapja paraméterként, talán jobb így
        printState(); // TODO remove later
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

    // TODO: itt a randomizálással mi legyen?
    /**
     * Every time a round ends (all the Settlers have stepped), every Robot steps. This method is a basic implementation
     * of a Robot object deciding what to do.
     */
    public void step() {
        Random rnd = new Random();
        int choice = rnd.nextInt(2); // generated number will be 0 or 1
        if (choice == 0) {
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
            super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        } else {
            super.drill();
        }
        printState(); // TODO: remove later
    }

    // TODO: proto output, marked for removal
    public void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Robot");
        System.out.println("name: " + name);
        System.out.println("asteroid: " + asteroid.getName());
    }

    @Override
    void printDeath() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Robot");
        System.out.println("name: " + name + " ->X ");
    }
}
