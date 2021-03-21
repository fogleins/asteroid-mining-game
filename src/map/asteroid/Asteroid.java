package map.asteroid;

import map.entity.Entity;
import map.entity.TeleportGate;
import utility.OutputFormatter;

import java.util.ArrayList;


/**
 * Class map.asteroid.Asteroid
 */
public class Asteroid {

    //
    // Fields
    //

    protected final ArrayList<Entity> entities = new ArrayList<>();
    protected final ArrayList<Asteroid> neighbours = new ArrayList<>();
    protected int surfaceThickness;
    protected boolean inPerihelion;

    public String getName() {
        OutputFormatter.OutputCall("getName() - " + name);
        OutputFormatter.OutputReturn("return - name");
        return name;
    }

    public void setName(String name) {
        OutputFormatter.OutputCall("setName() - " + name);

        this.name = name;

        OutputFormatter.OutputReturn("return");
    }

    protected String name;
    protected Resource resource;
    protected TeleportGate teleportGate;

    //
    // Constructors
    //
    public Asteroid() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
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
        OutputFormatter.OutputCall("getSurfaceThickness() - " + name);
        OutputFormatter.OutputReturn("return - surfaceThickness");
        return surfaceThickness;
    }

    /**
     * Set the value of surfaceThickness
     *
     * @param thickness the new value of surfaceThickness
     */
    public void setSurfaceThickness(int thickness) {
        OutputFormatter.OutputCall("setSurfaceThickness() - " + name);
        surfaceThickness = thickness;
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Get the value of inPerihelion
     *
     * @return the value of inPerihelion
     */
    public boolean getInPerihelion() {
        OutputFormatter.OutputCall("getInPerihelion() - " + name);
        OutputFormatter.OutputReturn("return - inPerihelion");
        return inPerihelion;
    }

    /**
     * Set the value of inPerihelion
     *
     * @param inPerihelion the new value of inPerihelion
     */
    public void setInPerihelion(boolean inPerihelion) {
        OutputFormatter.OutputCall("setInPerihelion() - " + name);
        this.inPerihelion = inPerihelion;
        OutputFormatter.OutputReturn("return");
    }

    /*

     * Get the value of name
     *
     * @return the value of name

    public String getName() {
        System.out.println("getName()");
        return name;
    }*/

    /**
     * Get the List of Entities objects held by entitiesVector
     *
     * @return List of Entities objects held by entitiesVector
     */
    private ArrayList<Entity> getEntitiesList() {
        OutputFormatter.OutputCall("getEntitiesList() - " + name);
        OutputFormatter.OutputReturn("return - entities");
        return entities;
    }


    /**
     * Add a map.asteroid.Resource object to the map.asteroid.Asteroid
     */
    public void addResource(Resource resource) {
        OutputFormatter.OutputCall("addResource() - " + name);
        this.resource = resource;
        this.resource.setAsteroid(this);
        OutputFormatter.OutputReturn("return");
    }


    /**
     * Get the List of map.asteroid.Resource objects held by resourceVector
     *
     * @return List of map.asteroid.Resource objects held by resourceVector
     */
    private Resource getResource() {
        OutputFormatter.OutputCall("getResource() - " + name);
        OutputFormatter.OutputReturn("return - resource");
        return resource;
    }

    /**
     * Get the List of map.entity.TeleportGate objects held by teleportgateVector
     *
     * @return List of map.entity.TeleportGate objects held by teleportgateVector
     */
    private TeleportGate getTeleportGate() {
        OutputFormatter.OutputCall("getTeleportGate() - " + name);
        OutputFormatter.OutputReturn("return - teleportGate");
        return teleportGate;
    }


    //
    // Other methods
    //

    /**
     *
     */
    public void explode() {
        OutputFormatter.OutputCall("explode() - " + name);
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).asteroidExploded();
        }
        OutputFormatter.OutputReturn("return");
    }


    /**
     *
     */
    public void drilled() {
        OutputFormatter.OutputCall("drilled() - " + name);

        if (surfaceThickness != 0) {
            this.surfaceThickness--;

            if (surfaceThickness == 0 && inPerihelion) {
                this.resource.drilledInPerihelion();
            }
        }

        OutputFormatter.OutputReturn("return");
    }


    /**
     * @return map.asteroid.Resource
     */
    public Resource mined() {
        OutputFormatter.OutputCall("mined() - " + name);


        Resource minedResource = null;
        // TODO: itt a thickness-t nem kéne ellenőrizni?
        //Ha nem üreges az aszteroida, akkor kivesszük belőle a nyersanyagot
        if (this.resource != null) {
            minedResource = resource;
            resource = null;
        }
        OutputFormatter.OutputReturn("return - minedResource");
        return minedResource;
    }


    /**
     * @return map.asteroid.Neighbours
     */
    public Neighbours getNeighbours() {
        OutputFormatter.OutputCall("getNeighbours() - " + name);

        //A Neighbours osztály konstruktora ArrayList-et vár a teleport által összekötött aszteroidák listájával
        ArrayList<Asteroid> teleportGateOtherSide = new ArrayList<>();
        if(teleportGate != null) {
            teleportGateOtherSide.add(teleportGate.getOtherSide());
        }

        OutputFormatter.OutputReturn("return - new Neighbours(neighbours, teleportGateOtherSide)");

        return new Neighbours(neighbours, teleportGateOtherSide);
    }


    /**
     * @param entity
     */
    public void acceptEntity(Entity entity) {
        OutputFormatter.OutputCall("acceptEntity() - " + name);
        this.entities.add(entity);
        OutputFormatter.OutputReturn("return");
    }


    /**
     * @param entity
     */
    public void removeEntity(Entity entity) {
        OutputFormatter.OutputCall("removeEntity() - " + name);
        entities.remove(entity);
        OutputFormatter.OutputReturn("return");
    }


    /**
     * @param asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
        OutputFormatter.OutputCall("addNeighbour() - " + name + " -> " + asteroid.name);
        this.neighbours.add(asteroid);
        OutputFormatter.OutputReturn("return");
    }


    /**
     * @param asteroid
     */
    public void removeAsteroid(Asteroid asteroid) {
        OutputFormatter.OutputCall("removeAsteroid() - " + name);

        this.neighbours.remove(asteroid);

        OutputFormatter.OutputReturn("return");
    }


    /**
     * @param teleportGate
     */
    public void setTeleportGate(TeleportGate teleportGate) {
        OutputFormatter.OutputCall("setTeleportGate() - " + name);

        this.teleportGate = teleportGate;

        OutputFormatter.OutputReturn("return");
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }


    /**
     *
     */
    public void removeTeleportGate() {
        OutputFormatter.OutputCall("removeTeleportGate() - " + name);

        this.teleportGate = null;

        OutputFormatter.OutputReturn("return");
    }


    /**
     * @param resource
     */
    public boolean placeResource(Resource resource) {
        OutputFormatter.OutputCall("placeResource() - " + name);

        if (this.resource == null && surfaceThickness == 0) {
            this.resource = resource;
            this.resource.setAsteroid(this);
            OutputFormatter.OutputReturn("return - true");
            return true;
        }

        OutputFormatter.OutputReturn("return - false");
        return false;
    }


    /**
     *
     */
    public void hitBySunflare() {
        OutputFormatter.OutputCall("hitBySunflare() - " + name);

        //Ha nem üreges a mag, az összes rajta lévő entitás elhalálozik
        if (resource != null) {
            for (Entity e : entities) {
                e.die();
            }
        }

        OutputFormatter.OutputReturn("return");
    }


    /**
     *
     */
    public void changePerihelionState() {
        OutputFormatter.OutputCall("changePerihelionState() - " + name);
        this.inPerihelion = !inPerihelion;
        OutputFormatter.OutputReturn("return");
    }


}
