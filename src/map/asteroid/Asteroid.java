package map.asteroid;

import control.ActionFailedException;
import control.Game;
import map.entity.Entity;
import map.entity.TeleportGate;
import map.resource.Resource;
import view.AsteroidView;
import view.GameWindow;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class map.asteroid.Asteroid
 */
public class Asteroid implements Serializable {

    private static final long serialVersionUID = -8591675552003339149L;
    /**
     * List of entities that are on the surface of the asteroid.
     */
    protected final ArrayList<Entity> entities = new ArrayList<>();
    /**
     * List of the neighbours.
     */
    protected final ArrayList<Asteroid> neighbours = new ArrayList<>();
    /**
     * The view that displays the asteroid.
     */
    protected final AsteroidView asteroidView;
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

    public Asteroid() { // BaseAsteroid's ctor calls this
        this.asteroidView = new AsteroidView(this);
        this.asteroidView.updateView();
    }

    /**
     * Constructor for the map initialization.
     *
     * @param name             Name of the asteroid.
     * @param inPerihelion     Gives whether the asteroid located inside the perihelion zone.
     * @param surfaceThickness Surface thickness.
     * @param res              Resource to be set as core of the Asteroid (null if empty).
     */
    public Asteroid(String name, boolean inPerihelion, int surfaceThickness, Resource res) {
        this.name = name;
        this.inPerihelion = inPerihelion;
        this.surfaceThickness = surfaceThickness;
        teleportGate = null;
        resource = res;
        if (this.resource != null) {
            this.resource.setAsteroid(this);
        }
        this.asteroidView = new AsteroidView(this);
        this.asteroidView.updateView();
    }

    /**
     * Getter of the asteroidview. Used in MapView generation.
     *
     * @return The AsteroidView object.
     */
    public AsteroidView getAsteroidView() {
        return asteroidView;
    }

    /**
     * Getter for the asteroid's name.
     *
     * @return The asteroid's name.
     */
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
     * Asteroid explodes. The asteroid is removed from the map and the neighbours of the asteroid
     * will be each other's neighbours.
     */
    public void explode() {
        asteroidView.explosionNotification();
        // All entities that were on the asteroid are warned that the asteroid has been exploded
        int size = entities.size();
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).asteroidExploded();
            if (size > entities.size()) {
                i = i - (size - entities.size());
                size = entities.size();
            }
        }

        if (teleportGate != null)
            teleportGate.die();

        // Modifying neighbours to preserve consistency
        for (int i = 0; i < neighbours.size(); i++) {
            neighbours.get(i).removeAsteroid(this);
            for (int j = 0; j < neighbours.size(); j++) // Connecting the neighbours to each other
                if (i != j) neighbours.get(i).addNeighbour(neighbours.get(j));
        }
        Game.getInstance().getMap().removeAsteroid(this);
        GameWindow.getMapView().updateView(this);
    }

    /**
     * The asteroid is drilled.
     */
    public void drilled() throws ActionFailedException {
        // You can't drill if the surface thickness is 0
        if (surfaceThickness != 0) {
            this.surfaceThickness--;
            // If the asteroid is in the perihelion zone, some resources behave different, that's why drilledInPerihelion() is called
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
        // If the asteroid is not empty and the thickness is zero, the resource is mined
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
        // The Neighbours constructor expects an ArrayList containing the asteroids connected by a teleport gate
        ArrayList<Asteroid> teleportGateOtherSide = new ArrayList<>();
        if (teleportGate != null && teleportGate.getOtherSide() != null) {
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
     * Sets the asteroid's teleport gate.
     *
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
     * Removes the teleport gate from the asteroid.
     */
    public void removeTeleportGate() {
        this.teleportGate = null;
    }

    /**
     * Places a resource inside the asteroid.
     *
     * @param resource this will be placed in the asteroid
     */
    public boolean placeResource(Resource resource) {
        // if the asteroid is empty and the surface thickness is zero, we can place the resource inside the asteroid
        if (this.resource == null && surfaceThickness == 0) {
            this.resource = resource;
            this.resource.setAsteroid(this);
            return true;
        }
        return false;
    }

    /**
     * A sunflare hits the asteroid.
     */
    public void hitBySunflare() {
        // If the asteroid is not empty, all the entities die on its surface
        if (resource != null || surfaceThickness != 0) {
            ArrayList<Entity> entities2 = new ArrayList<>(entities);
            for (Entity entity : entities2)
                entity.die();
        }
        if (teleportGate != null)
            teleportGate.hitBySunflare();
    }

    /**
     * Changes the asteroid's perihelion state.
     */
    public void changePerihelionState() {
        this.inPerihelion = !inPerihelion;
    }

    /**
     * Exposes the resource in the asteroid's core if the surface is fully drilled and the asteroid is in perihelion.
     */
    public void expose() {
        if (surfaceThickness == 0 && inPerihelion && resource != null)
            resource.exposed();
    }
}
