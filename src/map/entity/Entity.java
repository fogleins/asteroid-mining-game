package map.entity;

import control.Game;
import map.asteroid.Asteroid;
import map.asteroid.Resource;


import java.util.ArrayList;

/**
 * Class map.entity.Entity
 */
abstract public class Entity {
    /**
     * Entity's name.
     */
    protected String name;
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
    public void drill() {
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
     *
     * @return null.
     */
    public ArrayList<Resource> getResources() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }
}
