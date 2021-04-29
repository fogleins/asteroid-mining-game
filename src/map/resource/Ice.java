package map.resource;

/**
 * Class map.asteroid.Ice
 * A type of sublimable resource (disappears if drilled in perihelion)
 */
public class Ice extends SublimableResource {

    public Ice() {
    }

    @Override
    public Resource clone() {
        return new Ice();
    }

}
