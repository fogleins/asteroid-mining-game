package map.asteroid;

import control.Game;
import control.Test;
import map.entity.Entity;
import map.entity.TeleportGate;

import java.util.ArrayList;


/**
 * Class map.asteroid.Asteroid
 */
public class Asteroid {

    protected final ArrayList<Entity> entities = new ArrayList<>();
    protected final ArrayList<Asteroid> neighbours = new ArrayList<>();
    protected int surfaceThickness;
    protected boolean inPerihelion;
    protected String name;
    protected Resource resource;
    protected TeleportGate teleportGate;


    public Asteroid() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of surfaceThickness
     *
     * @return the value of surfaceThickness
     */
    public int getSurfaceThickness() {
        return surfaceThickness;
    }

    /**
     * Set the value of surfaceThickness
     *
     * @param thickness the new value of surfaceThickness
     */
    public void setSurfaceThickness(int thickness) {
        surfaceThickness = thickness;
    }

    /**
     * Get the value of inPerihelion
     *
     * @return the value of inPerihelion
     */
    public boolean getInPerihelion() {
        return inPerihelion;
    }

    /**
     * Set the value of inPerihelion
     *
     * @param inPerihelion the new value of inPerihelion
     */
    public void setInPerihelion(boolean inPerihelion) {
        this.inPerihelion = inPerihelion;
    }


    /**
     * Get the List of Entities objects held by entitiesVector
     *
     * @return List of Entities objects held by entitiesVector
     */
    private ArrayList<Entity> getEntitiesList() {
        return entities;
    }


    /**
     * Add a map.asteroid.Resource object to the map.asteroid.Asteroid
     */
    public void addResource(Resource resource) {
        this.resource = resource;
        this.resource.setAsteroid(this);
    }


    /**
     * Get the asteroid's resource
     *
     * @return The resource which the asteroid contains
     */
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        if (this.resource != null)
            this.resource.setAsteroid(this);
    }

    /**
     * Get the List of map.entity.TeleportGate objects held by teleportgateVector
     *
     * @return List of map.entity.TeleportGate objects held by teleportgateVector
     */
    private TeleportGate getTeleportGate() {
        return teleportGate;
    }

    /**
     * Asteroid explodes.
     */
    public void explode() {

        //All entities that were on the asteroid are warned that the asteroid has been exploded
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).asteroidExploded();
        }
        Game.getInstance().getMap().removeAsteroid(this);
        printDeath();
    }


    /**
     * The asteroid is drilled.
     */
    public void drilled() {
        //You can't drill if the surface thickness is 0
        if (surfaceThickness != 0) {
            this.surfaceThickness--;
            // todo: just for testing, marked for removal
            printState();
            //If the asteroid is in the perihelion zone, some resources behave different, that's why drilledInPerihelion() is called
            if (surfaceThickness == 0 && inPerihelion) {
                this.resource.drilledInPerihelion();
            }
        } else {
            System.out.println("Error: Cannot drill");
        }


    }

    /**
     * @return the mined asteroid (map.asteroid.Resource)
     */
    public Resource mined() {


        Resource minedResource = null;
        //If the asteroid is not empty and the thickness is zero, the resource is mined
        if (this.resource != null && surfaceThickness == 0) {
            minedResource = resource;
            resource = null;
            minedResource.setAsteroid(null);
            printState();
        }
        return minedResource;
    }

    /**
     * @return A map.asteroid.Neighbours object that contains all the neighbours of the asteroid
     */
    public Neighbours getNeighbours() {

        //A Neighbours osztály konstruktora ArrayList-et vár a teleport által összekötött aszteroidák listájával
        ArrayList<Asteroid> teleportGateOtherSide = new ArrayList<>();
        if (teleportGate != null) {
            teleportGateOtherSide.add(teleportGate.getOtherSide());
        }


        return new Neighbours(neighbours, teleportGateOtherSide);
    }

    /**
     * @return An asteroid list whose don't have teleportgate.
     */
    public ArrayList<Asteroid> getNeighboursWithoutTeleportGate() {
        ArrayList<Asteroid> neighbourswithouttele = new ArrayList<>();
        for (Asteroid a : neighbours) {
            if (a.getTeleportGate() == null) {
                neighbourswithouttele.add(a);
            }
        }
        return neighbourswithouttele;
    }


    /**
     * @param entity that will be added to the list
     */
    public void acceptEntity(Entity entity) {
        this.entities.add(entity);
    }

    /**
     * @param entity that will be removed from the list
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * @param asteroid new neighbour of the asteroid
     */
    public void addNeighbour(Asteroid asteroid) {
        this.neighbours.add(asteroid);

    }

    /**
     * @param asteroid remove this asteroid from  it's neighbours
     */
    public void removeAsteroid(Asteroid asteroid) {

        this.neighbours.remove(asteroid);
        printState();
    }

    /**
     * @param teleportGate the teleport gate that will be placed on the asteroid
     * @return success or not
     */
    public boolean setTeleportGate(TeleportGate teleportGate) {
        if (teleportGate == null) {
            this.teleportGate = null;
            printState();
            return true;
        } else if (this.teleportGate == null) {
            if (!Game.getInstance().getSteppables().contains(teleportGate))
                Game.getInstance().getSteppables().add(teleportGate);
            this.teleportGate = teleportGate;
            this.teleportGate.setCurrentAsteroid(this);
            printState();
            return true;
        } else {
            return false;
        }

    }

    /**
     * remove the teleport gate from the asteroid
     */
    public void removeTeleportGate() {

        this.teleportGate = null;
        printState();
    }


    /**
     * @param resource this will be placed in the asteroid
     */
    public boolean placeResource(Resource resource) {

        //if the asteroid is empty and the surface thickness is zero, we can place the resource inside the asteroid
        if (this.resource == null && surfaceThickness == 0) {
            this.resource = resource;
            this.resource.setAsteroid(this);
            return true;
        }
        printState();
        return false;
    }


    /**
     * a sunflare hits the asteroid
     */
    public void hitBySunflare() {

        ArrayList<Entity> entities2 = new ArrayList<>();
        ///If the asteroid is not empty, all the entities die on its surface
        if (resource != null || surfaceThickness != 0) {
            entities2.addAll(entities);
            for (Entity entity : entities2)
                entity.die();
        }
        if (teleportGate != null)
            teleportGate.hitBySunflare();
    }


    /**
     * change the perihelion state
     */
    public void changePerihelionState() {
        this.inPerihelion = !inPerihelion;
    }

    // todo: proto output, marked for removal
    protected void printState() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Asteroid");
        System.out.println("name: " + name);
        System.out.println("resource: " + (resource != null ? resource.getTypeName() : "null"));
        System.out.println("surfacethickness: " + surfaceThickness);
        System.out.println("inperihelion: " + inPerihelion);
        System.out.print("neighbours: ");
        for (int i = 0; i < neighbours.size(); i++) {
            if (i != 0) System.out.print("-");
            System.out.print(neighbours.get(i).name);
        }
        System.out.println("\nteleportgate: " + (teleportGate != null ? teleportGate.getName() : "null") + "\n");
    }

    // todo: proto output, marked for removal
    private void printDeath() {
        System.out.println("Round number: " + Game.getInstance().getCurrentRound());
        System.out.println("Asteroid");
        System.out.println(name + " -> X\n");

    }
}
