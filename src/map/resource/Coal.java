package map.resource;

/**
 * Class map.asteroid.Coal
 * A type of normal resource (without any special behaviour)
 */
public class Coal extends Resource {

    public Coal() {
    }

    @Override
    public Resource clone() {
        return new Coal();
    }

    // needed for displaying the resource's name in AsteroidStatusView
    @Override
    public String toString() {
        return "Coal";
    }
}
