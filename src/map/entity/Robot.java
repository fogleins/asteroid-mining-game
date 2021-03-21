package map.entity;

import map.asteroid.Asteroid;
import utility.OutputFormatter;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class map.entity.Robot
 */
public class Robot extends Entity {

    /**
     *
     */
    public void asteroidExploded() {
        OutputFormatter.OutputCall("asteroidExploded() - " + this.toString());
        ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
        Random rnd = new Random();
        super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        OutputFormatter.OutputReturn("return");
    }


    /**
     *
     */
    public void step() {
        OutputFormatter.OutputCall("step() - " + this.toString());
        Random rnd = new Random();
        int choice = rnd.nextInt(2); // 0 vagy 1 lesz a generált szám
        if (choice == 0) {
            ArrayList<Asteroid> neighbours = this.asteroid.getNeighbours().getAsteroidNeighbours();
            super.move(neighbours.get(rnd.nextInt(neighbours.size())));
        } else {
            super.drill();
        }
        OutputFormatter.OutputReturn("return");
    }


}
