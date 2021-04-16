package map.asteroid;

import map.entity.Entity;
import map.entity.TeleportGate;
import utility.OutputFormatter;

import java.util.ArrayList;


/**
 * Class map.asteroid.Asteroid
 */
public class Asteroid {

    protected final ArrayList<Entity> entities = new ArrayList<>();
    protected final ArrayList<Asteroid> neighbours = new ArrayList<>();
    protected int surfaceThickness;
    protected boolean inPerihelion;
    protected String name;
    protected Resource resource;
    protected TeleportGate teleportGate;


    public Asteroid() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }


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

    public void setResource(Resource resource) {
        this.resource = resource;
        this.resource.setAsteroid(this);
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

    /**
     * Asteroid explodes.
     */
    public void explode() {
        OutputFormatter.OutputCall("explode() - " + name);

        //All entities that were on the asteroid are warned that the asteroid has been exploded
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).asteroidExploded();
        }
        OutputFormatter.OutputReturn("return");
    }

    /**
     * The asteroid is drilled.
     */
    public void drilled() {
        OutputFormatter.OutputCall("drilled() - " + name);

        //You can't drill if the surface thickness is 0
        if (surfaceThickness != 0) {
            this.surfaceThickness--;

            //If the asteroid is in the perihelion zone, some resources behave different, that's why drilledInPerihelion() is called
            if (surfaceThickness == 0 && inPerihelion) {
                this.resource.drilledInPerihelion();
            }
        }

        OutputFormatter.OutputReturn("return");
    }

    /**
     * @return the mined asteroid (map.asteroid.Resource)
     */
    public Resource mined() {
        OutputFormatter.OutputCall("mined() - " + name);


        Resource minedResource = null;
        //If the asteroid is not empty and the thickness is zero, the resource is mined
        if (this.resource != null && surfaceThickness == 0) {
            minedResource = resource;
            resource = null;
            minedResource.setAsteroid(null);
        }
        OutputFormatter.OutputReturn("return - minedResource");
        return minedResource;
    }

    /**
     * @return A map.asteroid.Neighbours object that contains all the neighbours of the asteroid
     */
    public Neighbours getNeighbours() {
        OutputFormatter.OutputCall("getNeighbours() - " + name);

        //A Neighbours osztály konstruktora ArrayList-et vár a teleport által összekötött aszteroidák listájával
        ArrayList<Asteroid> teleportGateOtherSide = new ArrayList<>();
        if (teleportGate != null) {
            teleportGateOtherSide.add(teleportGate.getOtherSide());
        }

        OutputFormatter.OutputReturn("return - new Neighbours(neighbours, teleportGateOtherSide)");

        return new Neighbours(neighbours, teleportGateOtherSide);
    }

    /**
     * @param entity that will be added to the list
     */
    public void acceptEntity(Entity entity) {
        OutputFormatter.OutputCall("acceptEntity() - " + name);
        this.entities.add(entity);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * @param entity that will be removed from the list
     */
    public void removeEntity(Entity entity) {
        OutputFormatter.OutputCall("removeEntity() - " + name);
        entities.remove(entity);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * @param asteroid new neighbour of the asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
        OutputFormatter.OutputCall("addNeighbour() - " + name + " -> " + asteroid.name);
        this.neighbours.add(asteroid);
        OutputFormatter.OutputReturn("return");
    }

    /**
     * @param asteroid remove this asteroid from  it's neighbours
     */
    public void removeAsteroid(Asteroid asteroid) {
        OutputFormatter.OutputCall("removeAsteroid() - " + name);

        this.neighbours.remove(asteroid);

        OutputFormatter.OutputReturn("return");
    }

    /**
     * @param teleportGate the teleport gate that will be placed on the asteroid
     * @return success or not
     */
    public boolean setTeleportGate(TeleportGate teleportGate) {
        OutputFormatter.OutputCall("setTeleportGate() - " + name);
        if (this.teleportGate == null) {
            this.teleportGate = teleportGate;
            OutputFormatter.OutputReturn("return - true");
            return true;
        } else {
            OutputFormatter.OutputReturn("return - false");
            return false;
        }
    }

    /**
     * remove the teleport gate from the asteroid
     */
    public void removeTeleportGate() {
        OutputFormatter.OutputCall("removeTeleportGate() - " + name);

        this.teleportGate = null;

        OutputFormatter.OutputReturn("return");
    }


    /**
     * @param resource this will be placed in the asteroid
     */
    public boolean placeResource(Resource resource) {
        OutputFormatter.OutputCall("placeResource() - " + name);

        //if the asteroid is empty and the surface thickness is zero, we can place the resource inside the asteroid
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
     * a sunflare hits the asteroid
     */
    public void hitBySunflare() {
        OutputFormatter.OutputCall("hitBySunflare() - " + name);

        ///If the asteroid is not empty, all the entities die on its surface
        if (resource != null) {
            for (Entity e : entities) {
                if (resource != null || surfaceThickness != 0) {
                    e.die();
                }
            }
        }

        OutputFormatter.OutputReturn("return");
    }


    /**
     * change the perihelion state
     */
    public void changePerihelionState() {
        OutputFormatter.OutputCall("changePerihelionState() - " + name);
        this.inPerihelion = !inPerihelion;
        OutputFormatter.OutputReturn("return");
    }
}
