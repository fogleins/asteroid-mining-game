package map.entity;

import control.Game;
import map.asteroid.Asteroid;
import map.asteroid.Resource;

import java.util.ArrayList;
import java.util.Random;

public class Ufo extends Entity implements Steppable {
    private ArrayList<Resource> resources = new ArrayList<>();

    /**
     * Constructor of Entity.
     *
     * @param name
     */
    public Ufo(String name) {
        super(name);
    }


    /**
     * A UFO collects the resource of the asteroid if it can, otherwise it moves on
     */
    @Override
    public void step() {
        Resource resource;
        resource = asteroid.mined();

        //If the UFO couldn't mine, it moves on
        if (resource == null) {
            Random rnd = new Random();
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();

            if(neighbours.size()!=0){
                move(neighbours.get(rnd.nextInt(neighbours.size())));
                printState();
            }

        }
    }

    @Override
    public void asteroidExploded() {
        die();
        printDeath();
    }

    private void printDeath() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("UFO");
        System.out.println("name: " + name + " ->X ");

    }

    private void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("UFO");
        System.out.println("name: " + name);
        System.out.println("asteroid: " + asteroid.getName());
    }


}
