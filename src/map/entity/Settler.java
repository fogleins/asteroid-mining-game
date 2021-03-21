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

    //
    // Fields
    //

    private final ArrayList<Resource> resources = new ArrayList<>();
    private final ArrayList<TeleportGate> teleports = new ArrayList<>();
    private Game m_game;

    //
    // Constructors
    //
    public Settler(Game g) {
        OutputFormatter.OutputCall("create - " + this.toString());
        m_game = g;
        OutputFormatter.OutputReturn("return");
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //


    /**
     * Get the List of Resources objects held by resourcesVector
     *
     * @return List of Resources objects held by resourcesVector
     */
    public ArrayList<Resource> getResources() {
        OutputFormatter.OutputCall("getResources() - " + name);
        OutputFormatter.OutputReturn("return - resources");
        return resources;
    }

    /*// only used in testing initialization
    public void setResources(ArrayList<Resource> res) {
        resources.clear();
        resources.addAll(res);
    }*/

    // only used in testing initialization
    public void addTeleport(TeleportGate teleportGate) {
        teleports.add(teleportGate);
    }

    //
    // Other methods
    //

    /**
     * Get the List of Teleport objects held by teleportVector
     *
     * @return List of Teleport objects held by teleportVector
     */
    public ArrayList<TeleportGate> getTeleportList() {
        OutputFormatter.OutputCall("getTeleportList() - " + name);
        OutputFormatter.OutputReturn("return - teleports");
        return teleports;
    }

    /**
     * Settler mines.
     */
    public void mine() {
        OutputFormatter.OutputCall("mine() - " + name);
        if (resources.size() < 10){
            Resource r = asteroid.mined();
            if(r != null){
                resources.add(r);
                OutputFormatter.OutputReturn("return - resource added " + r.getClass().toString());
            } else {
                if(resources.size()>0){
                    Resource res = m_game.exchangeResource(resources);
                    asteroid.placeResource(res);
                    resources.remove(res);
                }
                OutputFormatter.OutputReturn("return - null");
            }
        } else {
            Resource resourceToExchange = m_game.exchangeResource(resources);
            Resource r = asteroid.mined();
            if(r != null){
                resources.add(r);
            }
            boolean success = asteroid.placeResource(resourceToExchange);
            if (success){
                OutputFormatter.OutputReturn("return - resource exchanged " + r.getClass().toString());
                resources.remove(resourceToExchange);
            } else {
                OutputFormatter.OutputReturn("return - exchange not possible");
            }
        }

    }

    /**
     * Settler builds robot.
     */

    public void buildRobot() {
        OutputFormatter.OutputCall("buildRobot() - " + name);
        Robot.create(asteroid, resources);
        OutputFormatter.OutputReturn("return");
    }



    /**
     * Settler builds teleport.
     */
    public void buildTeleport() {
        OutputFormatter.OutputCall("buildTeleport() - " + name);
        if(teleports.size() == 0) {
            ArrayList<TeleportGate> teleportGates = TeleportGate.create(resources);
            if (teleportGates != null) {
                for (TeleportGate tp : teleportGates){
                    teleports.add(tp);
                }
                OutputFormatter.OutputReturn("return - teleportgates created ");
            } else {
                OutputFormatter.OutputReturn("return - null");
            }
        } else{
            OutputFormatter.OutputReturn("return - already has teleports");
        }
    }

    /**
     * Settler places teleport.
     */
    public void placeTeleport() {
        OutputFormatter.OutputCall("placeTeleport() - " + name);
        if(teleports.size() != 0){
            //todo: check if the asteroid has a teleport gate (could be in Asteroid)
            asteroid.setTeleportGate(teleports.get(0));
            teleports.remove(0);
            OutputFormatter.OutputReturn("return - teleportgate placed");
        } else {
            OutputFormatter.OutputReturn("return - there is no teleportgate to place");
        }
    }
}
