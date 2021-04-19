package map.entity;

import control.Game;
import map.BillOfResources;
import map.asteroid.*;

import java.util.ArrayList;

/**
 * Class TeleportGate
 */
public class TeleportGate implements Steppable {

    /**
     * The bill to build a teleportgate(pair).
     */
    private final static BillOfResources billToBuild;
    static {    // initialize the billToBuild variable
        billToBuild = new BillOfResources();
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Iron());
        billToBuild.addResources(new Ice());
        billToBuild.addResources(new Uranium());
    }
    /**
     * The asteroid, where the teleportgate placed on.
     */
    private Asteroid currentAsteroid;
    /**
     * The pair of this teleportgate.
     */
    private TeleportGate otherGate;

    /**
     * Determines that the teleportgate has been hitted by sunflare.
     */
    private boolean crazy;
    private boolean steppedThisRound = false; // TODO remove later

    /**
     * Constructor of the TeleportGate, which initialize the resources to build a gate(pair).
     */
    public TeleportGate() {
        crazy=false;
    }

    /**
     * Creates a teleportgate(pair) if there is enough resources to build.
     *
     * @param ownedResources The resources of settler, whereof the gate can be built.
     * @return with the gate-pair.
     */
    public static ArrayList<TeleportGate> create(ArrayList<Resource> ownedResources) {
        boolean hasResourcesToBuildTeleport = billToBuild.use(ownedResources);
        if (hasResourcesToBuildTeleport) {
            ArrayList<TeleportGate> gates = new ArrayList<>();
            TeleportGate t1 = new TeleportGate();
            TeleportGate t2 = new TeleportGate();
            t1.otherGate = t2;
            t2.otherGate = t1;
            gates.add(t1);
            gates.add(t2);
            return gates;
        }
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
    public void setCurrentAsteroid(Asteroid asteroid) {
        if (currentAsteroid != null)
            currentAsteroid.setTeleportGate(null);
        if (asteroid.setTeleportGate(this))
            currentAsteroid = asteroid;
        printState();
    }


    /**
     * Returns an asteroid, where the other gate is placed on.
     *
     * @return The asteroid, where the other gate is placed on.
     */
    public Asteroid getOtherSide() {
        return otherGate.getCurrentAsteroid();
    }

    public void hitBySunflare(){
        crazy=true;
    }

    /**
     * At the finish of the round the teleportgate move to anoteher asteroid (which hasn't got teleportgate),
     * if the telport gate has been hitted by sunflare before.
     */
    private void move(){
        ArrayList<Asteroid> neighbours = currentAsteroid.getNeighboursWithoutTeleportGate();
        currentAsteroid.removeTeleportGate();
        neighbours.get(neighbours.size()-1).setTeleportGate(this);
        this.currentAsteroid = neighbours.get(neighbours.size() - 1);
    }

    /**
     * Overrides the Steppable interface's step method.
     */
    @Override
    public void step() {
        if(crazy){
            move();
        }
    }




    /*
     * TODO: MUST BE REMOVED after tests.
     */
    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean getSteppedThisRound() {
        return steppedThisRound;
    }

    @Override
    public void setSteppedThisRound(boolean stepped) {
        this.steppedThisRound = stepped;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Teleport");
        System.out.println(name);
        System.out.println("asteroid: " + currentAsteroid.getName() + "\n");
    }
}
