package map.resource;

/**
 * Class SublimableResource
 * Special resource, sublimates if drilled in perihelion
 */
public abstract class SublimableResource extends Resource {

    public SublimableResource() { }

    public void drilledInPerihelion() {
        asteroid.mined(); // sublimate (removes the resource from the asteroid
    }
}
