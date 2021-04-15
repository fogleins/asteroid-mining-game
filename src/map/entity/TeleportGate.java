package map.entity;

import map.BillOfResources;
import map.asteroid.*;
import utility.OutputFormatter;

import java.util.ArrayList;

/**
 * Class TeleportGate
 */
public class TeleportGate implements Steppable {

    /**
     * The bill to build a teleportgate(pair).
     */
    private static BillOfResources billToBuild;
    /**
     * The asteroid, where the teleportgate placed on.
     */
    private Asteroid currentAsteroid;
    /**
     * The pair of this teleportgate.
     */
    private TeleportGate otherGate;

    /**
     * Constructor of the TeleportGate, which initialize the resources to build a gate(pair).
     */
    public TeleportGate() {
        OutputFormatter.OutputCall("create - " + this.toString());
        initBillToBuild();
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Creates a teleportgate(pair) if there is enough resources to build.
     *
     * @param ownedResources The resources of settler, whereof the gate can be built.
     * @return with the gate-pair.
     */
    public static ArrayList<TeleportGate> create(ArrayList<Resource> ownedResources) {
        OutputFormatter.OutputCall("create(" + ownedResources.toString() + ") - static in TeleportGate");
        boolean hasResourcesToBuildTeleport = billToBuild.use(ownedResources);
        if (hasResourcesToBuildTeleport) {
            ArrayList<TeleportGate> gates = new ArrayList<>();
            TeleportGate t1 = new TeleportGate();
            TeleportGate t2 = new TeleportGate();
            t1.otherGate = t2;
            t2.otherGate = t1;
            gates.add(t1);
            gates.add(t2);
            OutputFormatter.OutputReturn("return - " + gates.toString());
            return gates;
        }
        OutputFormatter.OutputReturn("return - null");
        return null;
    }


    /**
     * Getter of the current asteroid, not used in the test.
     *
     * @return currentAsteroid.
     */
    private Asteroid getCurrentAsteroid() {
        return currentAsteroid;
    }

    /**
     * Sets the current asteroid, not used in the test.
     */
    private void setCurrentAsteroid(Asteroid new_object) {
        currentAsteroid = new_object;
    }

    /**
     * Initialize the resources to the bill, which is required to build a gate(pair).
     */
    private void initBillToBuild() {
        billToBuild = new BillOfResources();
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Ice());
        billToBuild.addResources(new Uranium());
    }


    /**
     * Returns an asteroid, where the other gate is placed on.
     *
     * @return The asteroid, where the other gate is placed on.
     */
    public Asteroid getOtherSide() {
        OutputFormatter.OutputCall("getOtherSide() - " + this.toString());
        OutputFormatter.OutputReturn("return - " + otherGate.getCurrentAsteroid().getName());
        return otherGate.getCurrentAsteroid();
    }

    @Override
    public void step() {
        // TODO
    }
}
