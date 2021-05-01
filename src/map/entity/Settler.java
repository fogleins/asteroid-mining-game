package map.entity;

import control.Game;
import map.resource.Resource;

import java.util.ArrayList;
import java.util.Random;

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
     * Settler mines the asteroid for resource.
     * Asks for item exchange if cargo inventory is full and places the resource from the asteroid to the cargo otherwise.
     */
    public void mine() {
        /* new */
        if (asteroid.getResource() != null) {        // if there's any resource to mine
            if (resources.size() < 10) {        // if there's space in cargo
                resources.add(asteroid.mined());   // add resource to inventory
                Game.getInstance().nextPlayer();    // the settler used its only step
            } else {    // if there is no space in cargo, another resource must be placed back to get the new one
                Resource resourceToExchange = Game.getInstance().exchangeResource(resources);   // todo: move exchange from game to view
                if (resources.contains(resourceToExchange)) {   // if the resource is owned by the settler
                    resources.add(asteroid.mined());
                    asteroid.placeResource(resourceToExchange);     // place back the resource
                    resources.remove(resourceToExchange);   // remove the placed back resource from inventory
                    Game.getInstance().nextPlayer();    // the settler used its only step
                }
            }
        }
    }

    /**
     * Places a selected resource from the settlers inventory to the asteroid's core
     * @param resource The resource to be placed
     */
    public void placeBack(Resource resource) {
        if (!resources.contains(resource)) return;   // if the resource is not in the settler's inventory, the action is not valid
        if (asteroid.placeResource(resource))   // try to place back the resource
            Game.getInstance().nextPlayer();    // if it was successful, notify the game
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
        if (teleports.size() + 2 <= 3) {
            ArrayList<TeleportGate> teleportGates = TeleportGate.create(resources);
            if (teleportGates != null) {
                for(int i = 0; i < teleportGates.size(); i++){
                    teleports.add(teleportGates.get(i));
                    teleportGates.get(i).setSettler(this);
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
                teleports.get(0).setSettler(null);
                teleports.remove(0);
                Game.getInstance().nextPlayer();
            }
        }
    }

    /**
     * Removes dead teleportgate from cargo.
     * @param t Dead teleportgate to be removed.
     */
    public void removeTeleportGate(TeleportGate t){
        teleports.remove(t);
    }

    @Override
    public void die() {
        super.die();
        for (TeleportGate tg : teleports)
            tg.die();   // all teleport gate in storage dies
        Game.getInstance().removeSettler(this);
    }

}
