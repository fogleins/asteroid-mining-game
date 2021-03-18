import java.util.ArrayList;


/**
 * Class Asteroid
 */
public class Asteroid {

    //
    // Fields
    //

    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<Asteroid> neighbours = new ArrayList<>();
    private int surfaceThickness;
    private boolean inPerihelion;
    private String name;
    private Resource resource;
    private TeleportGate teleportGate;
//    private final ArrayList<Resource> resourceVector = new ArrayList<>();
//    private final Vector teleportgateVector = new Vector();
//    private Asteroid m_asteroid;
//    private final ArrayList<Asteroid> asteroid = new ArrayList<>();

    //
    // Constructors
    //
    public Asteroid() {
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the value of surfaceThickness
     *
     * @return the value of surfaceThickness
     */
    public int getSurfaceThickness() {
        return surfaceThickness;
    }

    /**
     * Set the value of surfaceThickness
     *
     * @param newVar the new value of surfaceThickness
     */
    public void setSurfaceThickness(int newVar) {
        surfaceThickness = newVar;
    }

    /**
     * Get the value of inPerihelion
     *
     * @return the value of inPerihelion
     */
    public boolean getInPerihelion() {
        return inPerihelion;
    }

    /**
     * Set the value of inPerihelion
     *
     * @param newVar the new value of inPerihelion
     */
    public void setInPerihelion(boolean newVar) {
        inPerihelion = newVar;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param newVar the new value of name
     */
    public void setName(String newVar) {
        name = newVar;
    }

    /**
     * Add a Entities object to the entitiesVector List
     */
    private void addEntities(Entity new_object) {
        entities.add(new_object);
    }

    /**
     * Remove a Entities object from entitiesVector List
     */
    private void removeEntities(Entity new_object) {
        entities.remove(new_object);
    }

    /**
     * Get the List of Entities objects held by entitiesVector
     *
     * @return List of Entities objects held by entitiesVector
     */
    private ArrayList<Entity> getEntitiesList() {
        return entities;
    }


    /**
     * Add a Resource object to the Asteroid
     */
    private void addResource(Resource new_object) {
        resource = new_object;
    }

    /**
     * Remove a Resource object from resourceVector List
     */
    private void removeResource(/*Resource new_object*/) {
//        resource.remove(new_object);
        resource = null;
    }

    /**
     * Get the List of Resource objects held by resourceVector
     *
     * @return List of Resource objects held by resourceVector
     */
    private Resource getResource() {
        return resource;
    }

    /**
     * Get the List of TeleportGate objects held by teleportgateVector
     *
     * @return List of TeleportGate objects held by teleportgateVector
     */
    private TeleportGate getTeleportGate() {
        return teleportGate;
    }


    //
    // Other methods
    //

    /**
     *
     */
    public void explode() {
    }


    /**
     *
     */
    public void drilled() {
    }


    /**
     * @return Resource
     */
    public Resource mined() {
        return null; // TODO: implementálni
    }


    /**
     * @return Neighbours
     */
    public Neighbours getNeighbours() {
        return null; // TODO: implementálni
    }


    /**
     * @param entity
     */
    public void acceptEntity(Entity entity) {
    }


    /**
     * @param entity
     */
    public void removeEntity(Entity entity) {
    }


    /**
     * @param asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
    }


    /**
     * @param asteroid
     * @return Asteroid
     */
    public Asteroid removeAsteroid(Asteroid asteroid) {
        return null; // TODO: implementálni
    }


    /**
     * @param teleportGate
     */
    public void addTeleportGate(TeleportGate teleportGate) {
    }


    /**
     *
     */
    public void removeTeleportGate() {
    }


    /**
     * @param resource
     */
    public void placeResource(Resource resource) {
    }


    /**
     *
     */
    public void hitBySunflare() {
    }


    /**
     *
     */
    public void changePerihelionState() {
    }


}
