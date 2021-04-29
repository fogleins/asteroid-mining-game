package map.entity;

import control.Game;
import map.asteroid.Asteroid;
import map.resource.Resource;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity implements Steppable {
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
        Resource resource = asteroid.mined();

        //If the UFO couldn't mine, it moves on
        if (resource == null) {
            Random rnd = new Random();
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();

            if(neighbours.size()!=0){
                move(neighbours.get(rnd.nextInt(neighbours.size())));
            }
        }
    }

    @Override
    public void die() {
        super.die();
        Game.getInstance().removeSteppable(this);
    }

}
