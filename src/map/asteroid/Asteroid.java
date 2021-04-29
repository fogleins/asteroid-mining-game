package map.asteroid;

import control.Game;
import map.resource.Resource;
import map.entity.Entity;
import map.entity.TeleportGate;

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

    public Asteroid() {}

    public String getName() {
        return name;
    }

    /**
     * Get the value of surfaceThickness
     * @return the value of surfaceThickness
     */
    public int getSurfaceThickness() {
        return surfaceThickness;
    }

    /**
     * Get the value of inPerihelion
     * @return the value of inPerihelion
     */
    public boolean getInPerihelion() {
        return inPerihelion;
    }

    /**
     * Set the value of inPerihelion
     * @param inPerihelion the new value of inPerihelion
     */
    public void setInPerihelion(boolean inPerihelion) {
        this.inPerihelion = inPerihelion;
    }

    /**
     * Get the List of Entities objects held by entitiesVector
     * @return List of Entities objects held by entitiesVector
     */
    private ArrayList<Entity> getEntitiesList() {
        return entities;
    }

    /**
     * Get the asteroid's resource
     * @return The resource which the asteroid contains
     */
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        if (this.resource != null)
            this.resource.setAsteroid(this);
    }

    /**
     * Get the TeleportGate of this asteroid
     * @return The TeleportGate object, null if there is none
     */
    private TeleportGate getTeleportGate() {
        return teleportGate;
    }

    /**
     * Asteroid explodes.
     */
    public void explode() {
        //All entities that were on the asteroid are warned that the asteroid has been exploded
        for (Entity entity : entities) {
            entity.asteroidExploded();
        }
        Game.getInstance().getMap().removeAsteroid(this);
        if (teleportGate != null)
            teleportGate.die();
        // todo: connect neighbours
    }

    /**
     * The asteroid is drilled.
     */
    public void drilled() {
        //You can't drill if the surface thickness is 0
        if (surfaceThickness != 0) {
            this.surfaceThickness--;
            //If the asteroid is in the perihelion zone, some resources behave different, that's why drilledInPerihelion() is called
            if (resource != null && surfaceThickness == 0 && inPerihelion) {
                this.resource.drilledInPerihelion();
            }
        } else {
            System.out.println("Error: Cannot drill");  // todo: error handling
        }
    }

    /**
     * @return the mined asteroid (map.asteroid.Resource)
     */
    public Resource mined() {
        Resource minedResource = null;
        //If the asteroid is not empty and the thickness is zero, the resource is mined
        if (this.resource != null && surfaceThickness == 0) {
            minedResource = resource;
            resource = null;
            minedResource.setAsteroid(null);
        }
        return minedResource;
    }

    /**
     * @return A map.asteroid.Neighbours object that contains all the neighbours of the asteroid
     */
    public Neighbours getNeighbours() {

        //A Neighbours osztály konstruktora ArrayList-et vár a teleport által összekötött aszteroidák listájával
        ArrayList<Asteroid> teleportGateOtherSide = new ArrayList<>();
        if (teleportGate != null) {
            teleportGateOtherSide.add(teleportGate.getOtherSide());
        }

        return new Neighbours(neighbours, teleportGateOtherSide);
    }

    /**
     * @return An asteroid list whose don't have teleportgate.
     */
    public ArrayList<Asteroid> getNeighboursWithoutTeleportGate() {
        ArrayList<Asteroid> neighbourswithouttele = new ArrayList<>();
        for (Asteroid a : neighbours) {
            if (a.getTeleportGate() == null) {
                neighbourswithouttele.add(a);
            }
        }
        return neighbourswithouttele;
    }

    /**
     * @param entity that will be added to the list
     */
    public void acceptEntity(Entity entity) {
        this.entities.add(entity);
    }

    /**
     * @param entity that will be removed from the list
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * @param asteroid new neighbour of the asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
        this.neighbours.add(asteroid);
    }

    /**
     * @param asteroid remove this asteroid from it's neighbours
     */
    public void removeAsteroid(Asteroid asteroid) {
        this.neighbours.remove(asteroid);
    }

    /**
     * @param teleportGate the teleport gate that will be placed on the asteroid
     * @return success or not
     */
    public boolean setTeleportGate(TeleportGate teleportGate) {
        if (teleportGate == null) {
            this.teleportGate = null;
            return true;
        } else if (this.teleportGate == null) {
            Game.getInstance().addSteppable(teleportGate);
            this.teleportGate = teleportGate;
            this.teleportGate.setCurrentAsteroid(this);
            return true;
        } else {
            return false;
        }
    }

    /**
     * remove the teleport gate from the asteroid
     */
    public void removeTeleportGate() {
        this.teleportGate = null;
    }

    /**
     * @param resource this will be placed in the asteroid
     */
    public boolean placeResource(Resource resource) {
        //if the asteroid is empty and the surface thickness is zero, we can place the resource inside the asteroid
        if (this.resource == null && surfaceThickness == 0) {
            this.resource = resource;
            this.resource.setAsteroid(this);
            return true;
        }
        return false;
    }

    /**
     * a sunflare hits the asteroid
     */
    public void hitBySunflare() {
        ///If the asteroid is not empty, all the entities die on its surface
        if (resource != null || surfaceThickness != 0) {
            ArrayList<Entity> entities2 = new ArrayList<>(entities);
            for (Entity entity : entities2)
                entity.die();
        }
        if (teleportGate != null)
            teleportGate.hitBySunflare();
    }

    /**
     * change the perihelion state
     */
    public void changePerihelionState() {
        this.inPerihelion = !inPerihelion;
    }

}
