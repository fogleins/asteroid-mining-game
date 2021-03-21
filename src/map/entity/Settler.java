package map.entity;

import control.Game;
import map.BillOfResources;
import map.asteroid.Asteroid;
import map.asteroid.Resource;
import utility.OutputFormatter;

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
     * Game reference.
     */
    private Game m_game;

    /**
     * Constructor of Settler.
     *
     * @param g Reference to game.
     */
    public Settler(Game g) {
        OutputFormatter.OutputCall("create - " + this.toString());
        m_game = g;
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Returns a list of resources.
     *
     * @return list of resources.
     */
    public ArrayList<Resource> getResources() {
        OutputFormatter.OutputCall("getResources() - " + name);
        OutputFormatter.OutputReturn("return - resources");
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
        OutputFormatter.OutputCall("mine() - " + name);
        if (resources.size() < 10) {
            Resource r = asteroid.mined();
            if (r != null) {
                resources.add(r);
                OutputFormatter.OutputReturn("return - resource added " + r.getClass().toString());
            }
        } else {
            Resource resourceToExchange = m_game.exchangeResource(resources);
            Resource r = asteroid.mined();
            if (r != null) {
                resources.add(r);
            }
            boolean success = asteroid.placeResource(resourceToExchange);
            if (success) {
                OutputFormatter.OutputReturn("return - resource exchanged " + r.getClass().toString());
                resources.remove(resourceToExchange);
            } else {
                OutputFormatter.OutputReturn("return - exchange not possible");
            }
        }

    }

    /**
     * Settler tries to build a robot.
     */

    public void buildRobot() {
        OutputFormatter.OutputCall("buildRobot() - " + name);
        Robot.create(asteroid, resources);
        OutputFormatter.OutputReturn("return");
    }


    /**
     * Settler tries to build a teleportgate if there aren't any teleportgates in the cargo hold.
     */
    public void buildTeleport() {
        OutputFormatter.OutputCall("buildTeleport() - " + name);
        if (teleports.size() == 0) {
            ArrayList<TeleportGate> teleportGates = TeleportGate.create(resources);
            if (teleportGates != null) {
                for (TeleportGate tp : teleportGates) {
                    teleports.add(tp);
                }
                OutputFormatter.OutputReturn("return - teleportgates created ");
            } else {
                OutputFormatter.OutputReturn("return - null");
            }
        } else {
            OutputFormatter.OutputReturn("return - already has teleports");
        }
    }

    /**
     * Settler tries to place a teleport which is only successful when the asteroid has none and
     * the Settler has at least one in its inventory.
     */
    public void placeTeleport() {
        OutputFormatter.OutputCall("placeTeleport() - " + name);
        if (teleports.size() != 0) {
            boolean success = asteroid.setTeleportGate(teleports.get(0));
            if (success) {
                teleports.remove(0);
                OutputFormatter.OutputReturn("return - teleportgate placed");
            } else {
                OutputFormatter.OutputReturn("return - unable to place teleportgate");
            }
        } else {
            OutputFormatter.OutputReturn("return - there is no teleportgate to place");
        }
    }
}
