package map.entity;

import control.ActionFailedException;
import control.Game;
import map.asteroid.Asteroid;
import map.resource.Resource;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity implements Steppable {
    private static final long serialVersionUID = -3364598517128775794L;
    /**
     * List of resources the ufo has collected.
     */
    private final ArrayList<Resource> resources = new ArrayList<>();

    /**
     * Constructor of Entity.
     * @param name Name of the UFO
     */
    public Ufo(String name) {
        super(name);
    }

    /**
     * A UFO collects the resource of the asteroid if it can, otherwise it moves on
     */
    @Override
    public void step() {
        try {
            resources.add(asteroid.mined());
        } catch (ActionFailedException e) {
            // If the UFO couldn't mine, it moves on
            Random rnd = new Random();
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();

            if (neighbours.size() != 0) {
                move(neighbours.get(rnd.nextInt(neighbours.size())));
            }
        }
    }

    /**
     * Ufo dies.
     */
    @Override
    public void die() {
        super.die();
        Game.getInstance().removeSteppable(this);
    }
}
