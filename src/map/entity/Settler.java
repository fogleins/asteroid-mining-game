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
        /**
         * if there's space in cargo, the settler can mine.
         */
        if (resources.size() < 10) {
            Resource r = asteroid.mined();
            /**
             * if there's any resource, the settler adds the resource to the cargo
             */
            if (r != null) {
                resources.add(r);
                printState();
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
                printState();
            }
        }
        Game.getInstance().nextPlayer();
    }

    @Override
    public void drill() {
        asteroid.drilled();
        Game.getInstance().nextPlayer();
    }

    /**
     * Settler tries to build a robot.
     */

    public void buildRobot() {
        Robot.create(asteroid, resources);
        Game.getInstance().nextPlayer();
    }


    /**
     * Settler tries to build a teleportgate if there aren't any teleportgates in the cargo hold.
     */
    public void buildTeleport() {
        /**
         * can only build new teleportgates if there's less than 2 in cargo.
         */
        if (teleports.size() < 2) {
            ArrayList<TeleportGate> teleportGates = TeleportGate.create(resources);
            if (teleportGates != null) {
                for (TeleportGate tp : teleportGates) {
                    teleports.add(tp);
                }
                Game.getInstance().nextPlayer();
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
                printState();
                Game.getInstance().nextPlayer();
            }
        }
    }

    // TODO: remove after tests
    public ArrayList<TeleportGate> getTeleports() {
        return teleports;
    }

    @Override
    public void printDeath() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Settler");
        System.out.println(name + " -> X\n");
    }

    @Override
    public void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Settler");
        System.out.println("name: " + name);
        System.out.println("asteroid: " + asteroid.getName());
        /**
         * gets the resources a settler has
         */
        String ress = "";
        for (int i = 0; i < resources.size(); i++) {
            if (i == resources.size() - 1) {
                ress += resources.get(i).getTypeName();
            } else {
                ress += resources.get(i).getTypeName() + "-";
            }
        }
        System.out.println("resources: " + (ress.equals("") ? "x" : ress));
        String tps = "";
        for (int i = 0; i < teleports.size(); i++) {
            if (i == teleports.size() - 1) {
                tps += teleports.get(i).getName();
            } else {
                tps += teleports.get(i).getName() + "-";
            }
        }
        System.out.println("teleportgates: " + (tps.equals("") ? "x" : tps) + "\n");
    }
}
