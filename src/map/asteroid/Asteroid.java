package map.asteroid;

import Exceptions.ActionFailedException;
import control.Game;
import map.resource.*;
import map.entity.Entity;
import map.entity.TeleportGate;
import view.AsteroidView;
import view.GameWindow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class map.asteroid.Asteroid
 */
public class Asteroid implements Serializable {

    /**
     * List of entities that are on the surface of the asteroid.
     */
    protected final ArrayList<Entity> entities = new ArrayList<>();

    /**
     * List of the neighbours.
     */
    protected final ArrayList<Asteroid> neighbours = new ArrayList<>();

    /**
     * Surface thickness.
     */
    protected int surfaceThickness;

    /**
     * Gives whether the asteroid located inside the perihelion zone.
     */
    protected boolean inPerihelion;

    /**
     * Name of the asteroid.
     */
    protected String name;

    /**
     * The resource that is located in the core.
     */
    protected Resource resource;

    /**
     * The teleport gate that is attached to the asteroid.
     */
    protected TeleportGate teleportGate;

    /**
     * The view that displays the asteroid.
     */
    protected final AsteroidView asteroidView;

    /**
     * Getter of the asteroidview. Used in MapView generation.
     * @return The AsteroidView object.
     */
    public AsteroidView getAsteroidView() {
        return asteroidView;
    }

    /**
     * Indicates whether or not the asteroid has exploded. The value is used in AsteroidView to display the asteroid
     * correctly.
     */
    protected boolean exploded;

    public Asteroid() { // BaseAsteroid's ctor calls this
        this.asteroidView = new AsteroidView(this);
        this.exploded = false;
        // TODO: MapView init
        this.asteroidView.updateView();
    }

    /**
     * Constructor for the map initialization.
     * @param name Name of the asteroid.
     * @param inPerihelion Gives whether the asteroid located inside the perihelion zone.
     * @param surfaceThickness Surface thickness.
     * @param res Resource to be set as core of the Asteroid (null if empty).
     */
    public Asteroid(String name, boolean inPerihelion, int surfaceThickness, Resource res) {
        this.name = name;
        this.inPerihelion = inPerihelion;
        this.surfaceThickness = surfaceThickness;
        teleportGate=null;
        resource = res;
        if (this.resource != null) {
            this.resource.setAsteroid(this);
        }
        this.asteroidView = new AsteroidView(this);
        this.exploded = false;
        // TODO: MapView init
        this.asteroidView.updateView();
    }

    public String getName() {
        return name;
    }

    /**
     * Get the value of surfaceThickness
     *
     * @return the value of surfaceThickness
     */
    public int getSurfaceThickness() {
        return surfaceThickness;
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
     * @param inPerihelion the new value of inPerihelion
     */
    public void setInPerihelion(boolean inPerihelion) {
        this.inPerihelion = inPerihelion;
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
     * Get the asteroid's resource
     *
     * @return The resource which the asteroid contains
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Get the TeleportGate of this asteroid
     *
     * @return The TeleportGate object, null if there is none
     */
    public TeleportGate getTeleportGate() {
        return teleportGate;
    }

    /**
     * Returns whether the asteroid has exploded or not.
     * @return True, if the asteroid has exploded, false otherwise.
     */
    public boolean isExploded() {
        return exploded;
    }

    /**
     * Asteroid explodes. The asteroid is removed from the map and the neighbours of the asteroid
     * will be each other's neighbours.
     */
    public void explode() {
        //All entities that were on the asteroid are warned that the asteroid has been exploded
        for(int i =0;i<entities.size();i++){
            entities.get(i).asteroidExploded();
        }

        if (teleportGate != null)
            teleportGate.die();

        // Modifying neighbours to preserve consistency
        for (int i = 0; i < neighbours.size(); i++) {
            neighbours.get(i).removeAsteroid(this);
            for (int j = 0; j < neighbours.size(); j++)     // Connecting the neighbours to each other
                if (i != j) neighbours.get(i).addNeighbour(neighbours.get(j));
        }
        Game.getInstance().getMap().removeAsteroid(this);
        GameWindow.getMapView().updateView(this);
        this.exploded = true; // this way the AsteroidView knows not to paint this asteroid
    }

    /**
     * The asteroid is drilled.
     */
    public void drilled() throws ActionFailedException {
        //You can't drill if the surface thickness is 0
        if (surfaceThickness != 0) {
            this.surfaceThickness--;
            //If the asteroid is in the perihelion zone, some resources behave different, that's why drilledInPerihelion() is called
            if (resource != null && surfaceThickness == 0 && inPerihelion) {
                this.resource.drilledInPerihelion();
            }
        } else {
            throw new ActionFailedException("The surface thickness is 0, cannot drill");
        }
    }

    /**
     * The asteroid is mined.
     *
     * @return the mined asteroid (map.asteroid.Resource)
     */
    public Resource mined() throws ActionFailedException {
        Resource minedResource;
        //If the asteroid is not empty and the thickness is zero, the resource is mined
        if (resource != null) {
            if (surfaceThickness == 0) {
                minedResource = resource;
                resource = null;
                minedResource.setAsteroid(null);
            } else {
                throw new ActionFailedException("Surface thickness is not 0, cannot mine");
            }
        } else {
            throw new ActionFailedException("There is no resource inside the core");
        }

        return minedResource;
    }

    /**
     * Gets all neighbours
     *
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
     * Gets all the entities who are on this asteroid.
     *
     * @return The entities who are staniding on this asteroid.
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @return An asteroid list whose don't have teleportgate.
     */
    public ArrayList<Asteroid> getNeighboursWithoutTeleportGate() {
        return neighbours;
    }

    /**
     * Places an entity onto the surface of the asteroid
     *
     * @param entity that will be added to the list
     */
    public void acceptEntity(Entity entity) {
        this.entities.add(entity);
    }

    /**
     * Removes an entity from the asteroid
     *
     * @param entity that will be removed from the list
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * Adds a new neighbour to the asteroid
     *
     * @param asteroid new neighbour of the asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
        if (!neighbours.contains(asteroid) && asteroid != this)
            this.neighbours.add(asteroid);
    }

    /**
     * Removes a neighbour
     *
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
     * removes the teleport gate from the asteroid
     */
    public void removeTeleportGate() {
        this.teleportGate = null;
    }

    /**
     * Places a resource inside the asteroid
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
            for (Entity entity :entities2)
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

    /**
     * Exposes the resource in the asteroid's core if the surface is fully drilled and the asteroid is in perihelion
     */
    public void expose() {
        if (surfaceThickness == 0 && inPerihelion && resource != null)
            resource.exposed();
    }

}
