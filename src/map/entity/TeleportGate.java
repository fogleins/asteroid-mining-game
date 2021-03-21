package map.entity;

import map.BillOfResources;
import map.asteroid.*;
import utility.OutputFormatter;

import java.util.ArrayList;

/**
 * Class map.entity.TeleportGate
 */
public class TeleportGate {


    //
    // Fields
    //

    private static BillOfResources billToBuild;
    private Asteroid currentAsteroid;
    private TeleportGate otherGate;

    //
    // Constructors
    //
    public TeleportGate() {
        OutputFormatter.OutputCall("create - " + this.toString());
        initBillToBuild();
        OutputFormatter.OutputReturn("return");
    }

    //
    // Methods
    //
    public static ArrayList<TeleportGate> create(ArrayList<Resource> ownedResources){
        OutputFormatter.OutputCall("create("+ownedResources.toString()+") - static in TeleportGate");
        boolean hasResourcesToBuildTeleport = billToBuild.use(ownedResources);
        if(hasResourcesToBuildTeleport){
            ArrayList<TeleportGate> gates = new ArrayList<>();
            TeleportGate t1 = new TeleportGate();
            TeleportGate t2 = new TeleportGate();
            t1.otherGate=t2;
            t2.otherGate=t1;
            gates.add(t1);
            gates.add(t2);
            OutputFormatter.OutputReturn("return - " + gates.toString());
            return gates;
        }
        OutputFormatter.OutputReturn("return - null");
        return null;
    }

    //
    // Accessor methods
    //

    /**
     * Get the List of CurrentAsteroid objects held by currentasteroidVector
     *
     * @return List of CurrentAsteroid objects held by currentasteroidVector
     */
    private Asteroid getCurrentAsteroid() {
        return currentAsteroid;
    }

    /**
     * Add a CurrentAsteroid object to the currentasteroidVector List
     */
    private void setCurrentAsteroid(Asteroid new_object) {
        currentAsteroid = new_object;
    }


    //
    // Other methods
    //

    private void initBillToBuild(){
        billToBuild = new BillOfResources();
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Ice());
        billToBuild.addResources(new Uranium());
    }


    /**
     * @return map.asteroid.Asteroid
     */
    public Asteroid getOtherSide() {
        OutputFormatter.OutputCall("getOtherSide() - " + this.toString());
        OutputFormatter.OutputReturn("return - "+ otherGate.getCurrentAsteroid().getName());
        return otherGate.getCurrentAsteroid();
    }


}
