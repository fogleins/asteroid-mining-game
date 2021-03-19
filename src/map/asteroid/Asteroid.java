package map.asteroid;

import map.entity.Entity;
import map.entity.TeleportGate;

import java.util.ArrayList;


/**
 * Class map.asteroid.Asteroid
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
//    private final ArrayList<map.asteroid.Resource> resourceVector = new ArrayList<>();
//    private final Vector teleportgateVector = new Vector();
//    private map.asteroid.Asteroid m_asteroid;
//    private final ArrayList<map.asteroid.Asteroid> asteroid = new ArrayList<>();

    //
    // Constructors
    //
    public Asteroid() {
        System.out.println("Asteroid constructor called");
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
        System.out.println("getSurfaceThickness()");
        return surfaceThickness;
    }

    /**
     * Set the value of surfaceThickness
     *
     * @param thickness the new value of surfaceThickness
     */
    public void setSurfaceThickness(int thickness) {
        System.out.println("setSurfaceThickness()");
        surfaceThickness = thickness;
    }

    /**
     * Get the value of inPerihelion
     *
     * @return the value of inPerihelion
     */
    public boolean getInPerihelion() {
        System.out.println("getInPerihelion()");
        return inPerihelion;
    }

    /**
     * Set the value of inPerihelion
     *
     * @param inPerihelion the new value of inPerihelion
     */
    public void setInPerihelion(boolean inPerihelion) {
        System.out.println("setInPerihelion()");
        this.inPerihelion = inPerihelion;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        System.out.println("getName()");
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name the new value of name
     */
    public void setName(String name) {
        System.out.println("setName()");
        this.name = name;
    }

    /**
     * Add a Entities object to the entities List
     */
    private void addEntities(Entity entity) {
        System.out.println("Entity added onto the asteroid. Name: " + entity.getName());
        this.entities.add(entity);
    }

    /**
     * Remove a Entities object from entitiesVector List
     */
    private void removeEntities(Entity entity) {
        this.entities.remove(entity);
    }

    /**
     * Get the List of Entities objects held by entitiesVector
     *
     * @return List of Entities objects held by entitiesVector
     */
    private ArrayList<Entity> getEntitiesList() {
        System.out.println("getEntitiesList()");
        return entities;
    }


    /**
     * Add a map.asteroid.Resource object to the map.asteroid.Asteroid
     */
    private void addResource(Resource resource) {
        System.out.println("addResource()");
        this.resource = resource;
    }

    /**
     * Remove a map.asteroid.Resource object from resourceVector List
     */
    private void removeResource(/*map.asteroid.Resource new_object*/) {
//        resource.remove(new_object);
        resource = null;
    }

    /**
     * Get the List of map.asteroid.Resource objects held by resourceVector
     *
     * @return List of map.asteroid.Resource objects held by resourceVector
     */
    private Resource getResource() {
        System.out.println("getResource()");
        return resource;
    }

    /**
     * Get the List of map.entity.TeleportGate objects held by teleportgateVector
     *
     * @return List of map.entity.TeleportGate objects held by teleportgateVector
     */
    private TeleportGate getTeleportGate() {
        System.out.println("getTeleportGate()");
        return teleportGate;
    }


    //
    // Other methods
    //

    /**
     *
     */
    public void explode() {
        System.out.println("explode()");
    }


    /**
     *
     */
    public void drilled() {
        System.out.println("drilled()");
    }


    /**
     * @return map.asteroid.Resource
     */
    public Resource mined() {
        System.out.println("mined()");
        return null; // TODO: implementálni
    }


    /**
     * @return map.asteroid.Neighbours
     */
    public Neighbours getNeighbours() {
        System.out.println("getNeighbours()");
        return null; // TODO: implementálni
    }


    /**
     * @param entity
     */
    public void acceptEntity(Entity entity) {
        System.out.println("acceptEntity()");
    }


    /**
     * @param entity
     */
    public void removeEntity(Entity entity) {
        System.out.println("removeEntity()");
    }


    /**
     * @param asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
        System.out.println("addNeighbour");
        this.neighbours.add(asteroid);
    }


    /**
     * @param asteroid
     * @return map.asteroid.Asteroid
     */
    public Asteroid removeAsteroid(Asteroid asteroid) {
        System.out.println("removeAsteroid()");
        return null; // TODO: implementálni
    }


    /**
     * @param teleportGate
     */
    public void setTeleportGate(TeleportGate teleportGate) {
        System.out.println("setTeleportGate()");
        this.teleportGate = teleportGate;
    }


    /**
     *
     */
    public void removeTeleportGate() {
        System.out.println("removeTeleportGate()");
        this.teleportGate = null;
    }


    /**
     * @param resource
     */
    public void placeResource(Resource resource) {
        System.out.println("placeResource()");
    }


    /**
     *
     */
    public void hitBySunflare() {
        System.out.println("hitBySunflare()");
    }


    /**
     *
     */
    public void changePerihelionState() {
        System.out.println("changePerihelionState()");
    }


}
