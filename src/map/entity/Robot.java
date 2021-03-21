package map.entity;

import map.BillOfResources;
import map.asteroid.*;
import utility.OutputFormatter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class map.entity.Robot
 */
public class Robot extends Entity {

    private static BillOfResources billToBuild;

    public Robot() {
        OutputFormatter.OutputCall("create - " + this.toString());
        initBillToBuild();
        OutputFormatter.OutputReturn("return");
    }

    public static Robot create(Asteroid currentAsteroid, ArrayList<Resource> ownedResources) {
        OutputFormatter.OutputCall("create() - static in Robot");
        boolean hasResourcesToBuildTeleport = billToBuild.use(ownedResources);
        if (hasResourcesToBuildTeleport) {
            Robot robot = new Robot();
            robot.move(currentAsteroid);
            OutputFormatter.OutputReturn("return - " + robot.toString());
            return robot;
        }
        OutputFormatter.OutputReturn("return - null");
        return null;
    }

    /**
     *
     */
    public void asteroidExploded() {
        OutputFormatter.OutputCall("asteroidExploded() - " + this.toString());
        ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
        Random rnd = new Random();
        super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        OutputFormatter.OutputReturn("return");
    }

    /**
     *
     */
    public void step() {
        OutputFormatter.OutputCall("step() - " + this.toString());
        Random rnd = new Random();
        int choice = rnd.nextInt(2); // 0 vagy 1 lesz a generált szám
        if (choice == 0) {
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
            super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        } else {
            super.drill();
        }
        OutputFormatter.OutputReturn("return");
    }

    private void initBillToBuild() {
        billToBuild = new BillOfResources();
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Coal());
        billToBuild.addResources(new Uranium());
    }
}
