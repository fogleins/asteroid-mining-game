package map.entity;

import Exceptions.ActionFailedException;
import map.asteroid.Asteroid;
import map.resource.Resource;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class map.entity.Entity
 */
abstract public class Entity implements Serializable {
    /**
     * Entity's name.
     */
    protected String name;

    public Asteroid getAsteroid() {
        return asteroid;
    }

    /**
     * Asteroid.
     */
    protected Asteroid asteroid;

    /**
     * Constructor of Entity.
     */
    public Entity(String name) {
        this.name = name;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    /**
     * Moves the entity from current asteroid to the asteroid given as parameter.
     *
     * @param whereTo Given asteroid.
     */
    public void move(Asteroid whereTo) {
        if (asteroid != null) {
            asteroid.removeEntity(this);
        }
        whereTo.acceptEntity(this);
        asteroid = whereTo;
    }

    /**
     * Entity tries to drill.
     */
    public void drill() throws ActionFailedException {
        asteroid.drilled();
    }

    /**
     * Entity dies.
     */
    public void die() {
        asteroid.removeEntity(this);
    }

    /**
     * Handles the event of asteroid explosion on the exposed entity.
     */
    public void asteroidExploded() {
        die();
    }

    /**
     * Returns a list of resources in carry capable entities. Returns null by default.
     * @return null.
     */
    public ArrayList<Resource> getResources() {
        return null;
    }

    /**
     * Sets the name of an entity.
     * @param name String type, name of entity.
     */
    public void setName(String name) {
        this.name = name;
    }

}
