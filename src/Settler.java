import java.util.ArrayList;


/**
 * Class Settler
 */
public class Settler extends Entity {

    //
    // Fields
    //

    private final ArrayList<Resource> resources = new ArrayList<>();
    private final ArrayList<TeleportGate> teleports = new ArrayList<>();
    private BillOfResources robotBill;
    private BillOfResources teleportBill;
    private Game m_game;

    //
    // Constructors
    //
    public Settler() {
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the value of robotBill
     *
     * @return the value of robotBill
     */
    public BillOfResources getRobotBill() {
        return robotBill;
    }

    /**
     * Set the value of robotBill
     *
     * @param billOfResources the new value of robotBill
     */
    public void setRobotBill(BillOfResources billOfResources) {
        robotBill = billOfResources;
    }

    /**
     * Get the value of teleportBill
     *
     * @return the value of teleportBill
     */
    public BillOfResources getTeleportBill() {
        return teleportBill;
    }

    /**
     * Set the value of teleportBill
     *
     * @param billOfResources the new value of teleportBill
     */
    public void setTeleportBill(BillOfResources billOfResources) {
        teleportBill = billOfResources;
    }

    /**
     * Get the value of m_game
     *
     * @return the value of m_game
     */
    private Game getGame() {
        return m_game;
    }

    /**
     * Set the value of m_game
     *
     * @param game the new value of m_game
     */
    private void setGame(Game game) {
        m_game = game;
    }

    /**
     * Add a Resources object to the resourcesVector List
     */
    private void addResources(Resource resource) {
        resources.add(resource);
    }

    /**
     * Remove a Resources object from resourcesVector List
     */
    private void removeResources(Resource resource) {
        resources.remove(resource);
    }

    /**
     * Get the List of Resources objects held by resourcesVector
     *
     * @return List of Resources objects held by resourcesVector
     */
    private ArrayList<Resource> getResources() {
        return resources;
    }

    /**
     * Add a Teleport object to the teleportVector List
     */
    private void addTeleport(TeleportGate teleportGate) {
        teleports.add(teleportGate);
    }

    /**
     * Remove a Teleport object from teleportVector List
     */
    private void removeTeleport(TeleportGate teleportGate) {
        teleports.remove(teleportGate);
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
        return teleports;
    }

    /**
     *
     */
    public void mine() {
    }

    /**
     *
     */
    public void buildRobot() {
    }

    /**
     *
     */
    public void buildTeleport() {
    }

    /**
     *
     */
    public void asteroidExploded() {
    }

    /**
     *
     */
    public void placeTeleport() {
    }


}
