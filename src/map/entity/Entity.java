package map.entity;

import map.asteroid.Asteroid;
import map.asteroid.Resource;
import utility.OutputFormatter;

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
    public Entity() {
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
     * @param name the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    //
    // Other methods
    //

    /**
     * @param whereTo
     */
    public void move(Asteroid whereTo) {
        OutputFormatter.OutputCall("move(" + whereTo.toString() + ") - " + name);
        if (asteroid != null) {
            asteroid.removeEntity(this);
        }
        whereTo.acceptEntity(this);
        asteroid = whereTo;
        OutputFormatter.OutputReturn("return");
    }


    /**
     * Entity drills.
     */
    public void drill() {
        OutputFormatter.OutputCall("drill() - " + this.name);
        asteroid.drilled();
        OutputFormatter.OutputReturn("return");
    }


    /**
     * Entity dies.
     */
    public void die() {
        OutputFormatter.OutputCall("die() - " + this.name);
        asteroid.removeEntity(this);
        OutputFormatter.OutputReturn("return");
    }


    /**
     * Asteroid explodes.
     */
    public void asteroidExploded() {
        OutputFormatter.OutputCall("asteroidExploded() - " + this.name);
        die();
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Returns a list of resources in carry capable entities. Returns null by default.
     * @return null.
     */
    public ArrayList<Resource> getResources() {
        OutputFormatter.OutputCall("getResources() - " + name);
        OutputFormatter.OutputReturn("return - null");
        return null;
    }


}
