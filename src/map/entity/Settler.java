package map.entity;

import control.Game;
import map.asteroid.Resource;


import java.util.ArrayList;


/**
 * Class map.entity.Settler
 */
public class Settler extends Entity {

    /**
     * List of resources in the Settler's inventory.
     */
    private final ArrayList<Resource> resources = new ArrayList<>();

    /**
     * List of teleportgates in the Settler's inventory.
     */
    private final ArrayList<TeleportGate> teleports = new ArrayList<>();


    /**
     * Constructor of Settler.
     */
    public Settler(String name) {
        super(name);


    }

    /**
     * Returns a list of resources.
     *
     * @return list of resources.
     */
    public ArrayList<Resource> getResources() {


        return resources;
    }

    // only used in testing initialization

    /**
     * Adds a teleportgate to an asteroid.
     *
     * @param teleportGate Teleport gate.
     */
    public void addTeleport(TeleportGate teleportGate) {
        teleports.add(teleportGate);
    }


    /**
     * Settler mines. Asks for item exchange if cargo hold is full and places the resource
     * from the asteroid to the cargo otherwise.
     */
    public void mine() {
        if (resources.size() < 10) {
            Resource r = asteroid.mined();
            if (r != null) {
                resources.add(r);
            }
        } else {
            Resource r = asteroid.mined();
            if (r != null) {
                Resource resourceToExchange = Game.getInstance().exchangeResource(resources);
                resources.add(r);
                boolean success = asteroid.placeResource(resourceToExchange);
                if (success) {
                    resources.remove(resourceToExchange);
                }
            }
        }
    }

    /**
     * Settler tries to build a robot.
     */

    public void buildRobot() {
        Robot.create(asteroid, resources);
    }


    /**
     * Settler tries to build a teleportgate if there aren't any teleportgates in the cargo hold.
     */
    public void buildTeleport() {
        if (teleports.size() < 2) {
            ArrayList<TeleportGate> teleportGates = TeleportGate.create(resources);
            if (teleportGates != null) {
                for (TeleportGate tp : teleportGates) {
                    teleports.add(tp);
                }
            }
        }
    }

    /**
     * Settler tries to place a teleport which is only successful when the asteroid has none and
     * the Settler has at least one in its inventory.
     */
    public void placeTeleport() {
        if (teleports.size() != 0) {
            boolean success = asteroid.setTeleportGate(teleports.get(0));
            if (success) {
                teleports.remove(0);
            }
        }
    }

    // TODO: remove after tests
    public ArrayList<TeleportGate> getTeleports() {
        return teleports;
    }

    public void printDeath() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Settler");
        System.out.println("name: " + name + " ->X ");

    }


    public void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Settler");
        System.out.println("name: " + name);
        System.out.println("asteroid: " + asteroid + "\n");
        String ress = "";
        for (int i = 0; i < resources.size(); i++) {
            if (i == resources.size() - 1) {
                ress += resources.get(i).getTypeName();
            } else {
                ress += resources.get(i).getTypeName() + "-";
            }
        }
        System.out.println("resources: " + (ress == "" ? "x" : ress) + "\n");
        String tps = "";
        for (int i = 0; i < resources.size(); i++) {
            if (i == resources.size() - 1) {
                tps += resources.get(i).getTypeName();
            } else {
                tps += resources.get(i).getTypeName() + "-";
            }
        }
        System.out.println("teleportgates: " + (tps == "" ? "x" : tps) + "\n");
    }
}
