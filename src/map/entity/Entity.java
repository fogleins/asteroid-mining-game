package map.entity;

import map.asteroid.Asteroid;
import utility.OutputFormatter;

/**
 * Class map.entity.Entity
 */
abstract public class Entity {

    //
    // Fields
    //

    protected String name;

    protected Asteroid asteroid;

    //
    // Constructors
    //
    public Entity() {
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

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

    /**
     * Get the value of m_asteroid
     *
     * @return the value of m_asteroid
     */
    private Asteroid getAsteroid() {
        return asteroid;
    }

    /**
     * Set the value of m_asteroid
     *
     * @param asteroid the new value of m_asteroid
     */
    private void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
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
     *
     */
    public void drill() {
        OutputFormatter.OutputCall("drill() - " + this.name);
        asteroid.drilled();
        OutputFormatter.OutputReturn("return");
    }


    /**
     *
     */
    public void die() {
        OutputFormatter.OutputCall("die() - " + this.name);
        asteroid.removeEntity(this);
        OutputFormatter.OutputReturn("return");
    }


    /**
     *
     */
    public void asteroidExploded() {
        OutputFormatter.OutputCall("asteroidExploded() - " + this.name);
        die();
        OutputFormatter.OutputReturn("return");
    }


}
