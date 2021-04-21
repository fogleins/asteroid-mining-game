package map.asteroid;

/**
 * Class map.asteroid.Iron
 * A type of normal resource (without any special behaviour)
 */
public class Iron extends Resource {

    public Iron() {
    }

    @Override
    public Resource clone() {
        return new Iron();
    }

}
