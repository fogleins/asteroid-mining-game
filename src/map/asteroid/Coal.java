package map.asteroid;

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

}
