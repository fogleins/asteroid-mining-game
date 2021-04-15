package map.entity;

import control.Game;
import map.asteroid.Asteroid;
import map.asteroid.Resource;
import utility.OutputFormatter;

import java.util.ArrayList;

/**
 * Class map.entity.Entity
 */
abstract public class Entity {

    /**
     * Game reference.
     */
    protected static Game game;
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
     * Set the value of game
     *
     * @param g The new value of game
     */
    public static void setGame(Game g) {
        game = g;
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
     * Moves the entity from current asteroid to the asteroid given as parameter.
     *
     * @param whereTo Given asteroid.
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
     * Entity tries to drill.
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
     * Handles the event of asteroid explosion on the exposed entity.
     */
    public void asteroidExploded() {
        OutputFormatter.OutputCall("asteroidExploded() - " + this.name);
        die();
        OutputFormatter.OutputReturn("return");
    }

    /**
     * Returns a list of resources in carry capable entities. Returns null by default.
     *
     * @return null.
     */
    public ArrayList<Resource> getResources() {
        OutputFormatter.OutputCall("getResources() - " + name);
        OutputFormatter.OutputReturn("return - null");
        return null;
    }
}
