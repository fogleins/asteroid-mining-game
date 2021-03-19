package map.entity;

import control.Game;
import map.BillOfResources;
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
    private ArrayList<Resource> getResources() {
        OutputFormatter.OutputCall("getResources() - " + name);
        OutputFormatter.OutputReturn("return - resources");
        return resources;
    }

    //
    // Other methods
    //

    /**
     * Get the List of Teleport objects held by teleportVector
     *
     * @return List of Teleport objects held by teleportVector
     */
    private ArrayList<TeleportGate> getTeleportList() {
        OutputFormatter.OutputCall("getTeleportList() - " + name);
        OutputFormatter.OutputReturn("return - teleports");
        return teleports;
    }

    /**
     *
     */
    public void mine() {
        OutputFormatter.OutputCall("mine() - " + name);
        Resource r = asteroid.mined();
        if(r != null){
            resources.add(r);
            OutputFormatter.OutputReturn("return - resource added " + r.getClass().toString());
        } else {
            OutputFormatter.OutputReturn("return - null");
        }
    }

    /**
     *
     */
    public void buildRobot() {
        OutputFormatter.OutputCall("buildRobot() - " + name);
        Robot r = Robot.create(resources);
        if(r != null){
            r.move(asteroid);
            OutputFormatter.OutputReturn("return - robot created " + r.toString());
        } else {
            OutputFormatter.OutputReturn("return - null");
        }
    }

    /**
     *
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
     *
     */
    public void placeTeleport() {
        OutputFormatter.OutputCall("buildTeleport() - " + name);
        if(teleports.size() != 0){
            asteroid.setTeleportGate(teleports.get(0));
            teleports.remove(0);
            OutputFormatter.OutputReturn("return - teleportgate placed");
        } else {
            OutputFormatter.OutputReturn("return - there is no teleportgate to place");
        }
    }


}