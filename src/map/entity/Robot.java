package map.entity;

import control.Game;
import map.BillOfResources;
import map.asteroid.*;
import utility.OutputFormatter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class map.entity.Robot
 */
public class Robot extends Entity implements Steppable {

    /**
     * The resources needed to build a Robot
     */
    private static BillOfResources billToBuild;

    /**
     * ID to make names unique for robots.
     */
    private static int nameID = 0;

    /**
     * Constructor. Creates a Robot object.
     */
    public Robot(String name) {
        super(name);
        OutputFormatter.OutputCall("create - " + name);
        initBillToBuild();
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Creates a Robot object and places it on the Asteroid passed as a parameter. If the caller hasn't got enough
     * resources, nothing happens.
     *
     * @param currentAsteroid The Asteroid object on which the caller Entity is on.
     * @param ownedResources  A list of {@code Resource}s the caller has.
     */
    public static void create(Asteroid currentAsteroid, ArrayList<Resource> ownedResources) {
        OutputFormatter.OutputCall("create() - static in Robot");
        boolean hasResourcesToBuildRobot = billToBuild.use(ownedResources);
        if (hasResourcesToBuildRobot) {
            Robot robot = new Robot("Robot_" + nameID);
            Game.getInstance().addSteppable(robot);
            robot.move(currentAsteroid);
            OutputFormatter.OutputReturn("return - success");
        } else {
            OutputFormatter.OutputReturn("return - could not create");
        }
    }

    /**
     * Initializes the list of Resources needed to build a Robot.
     */
    private static void initBillToBuild() {
        billToBuild = new BillOfResources();
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Coal());
        billToBuild.addResources(new Uranium());
    }

    /**
     * This method is called when the asteroid on which the robot is on explodes. If this happens, the robot will
     * randomly select a neighbouring asteroid and move onto that.
     */
    public void asteroidExploded() {
        OutputFormatter.OutputCall("asteroidExploded() - " + name);
        ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
        Random rnd = new Random();
        super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Every time a round ends (all the Settlers have stepped), every Robot steps. This method is a basic implementation
     * of a Robot object deciding what to do.
     */
    public void step() {
        OutputFormatter.OutputCall("step() - " + name);
        Random rnd = new Random();
        int choice = rnd.nextInt(2); // generated number will be 0 or 1
        if (choice == 0) {
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
            super.move(neighbours.get(rnd.nextInt(neighbours.size())));
            OutputFormatter.OutputReturn("return - move");
        } else {
            super.drill();
            OutputFormatter.OutputReturn("return - drill");
        }
    }
}
