package map.entity;

import control.Game;
import map.resource.Resource;

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
     * @return list of resources.
     */
    public ArrayList<Resource> getResources() {
        return resources;
    }

    /**
     * Settler mines. Asks for item exchange if cargo hold is full and places the resource
     * from the asteroid to the cargo otherwise.
     */
    public void mine() {
        // todo: fix resource place back (new method?)
        // if there's space in cargo, the settler can mine.
        if (resources.size() < 10) {
            Resource r = asteroid.mined();
            // if there's any resource, the settler adds the resource to the cargo
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
        // can only build new teleportgates if there's room for it in cargo (max capacity is 3).
        if (teleports.size() + 2 < 3) {
            ArrayList<TeleportGate> teleportGates = TeleportGate.create(resources);
            if (teleportGates != null) {
                teleports.addAll(teleportGates);
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
                Game.getInstance().nextPlayer();
            }
        }
    }

    @Override
    public void die() {
        super.die();
        for (TeleportGate tg : teleports)
            tg.die();   // all teleport gate in storage dies
        Game.getInstance().removeSettler(this);
    }

}
