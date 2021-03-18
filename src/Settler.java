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
     * @param newVar the new value of robotBill
     */
    public void setRobotBill(BillOfResources newVar) {
        robotBill = newVar;
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
     * @param newVar the new value of teleportBill
     */
    public void setTeleportBill(BillOfResources newVar) {
        teleportBill = newVar;
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
     * @param newVar the new value of m_game
     */
    private void setGame(Game newVar) {
        m_game = newVar;
    }

    /**
     * Add a Resources object to the resourcesVector List
     */
    private void addResources(Resource new_object) {
        resources.add(new_object);
    }

    /**
     * Remove a Resources object from resourcesVector List
     */
    private void removeResources(Resource new_object) {
        resources.remove(new_object);
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
    private void addTeleport(TeleportGate new_object) {
        teleports.add(new_object);
    }

    /**
     * Remove a Teleport object from teleportVector List
     */
    private void removeTeleport(TeleportGate new_object) {
        teleports.remove(new_object);
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
