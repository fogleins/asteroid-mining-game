package map.entity;

import map.asteroid.Resource;

import java.util.ArrayList;

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

    @Override
    public void step() {
        // TODO
    }

    private void mine() {
        // TODO
    }
}
