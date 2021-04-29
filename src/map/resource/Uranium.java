package map.resource;

/**
 * Class map.asteroid.Uranium
 * A type of radioactive resource (explodes if drilled in perihelion)
 */
public class Uranium extends RadioactiveResource {

    int exposedCount = 0;
    final static int exposedThreshold = 3;

    public Uranium() {}

    @Override
    public void drilledInPerihelion() {
        this.exposedCount++;
        if (exposedCount >= exposedThreshold)
            asteroid.explode();
    }

    /**
     * Increments the exposition counter
     * If it reaches a predetermined threshold, the asteroid explodes
     */
    @Override
    public void exposed() {
        exposedCount++;
        if (exposedCount >= exposedThreshold)
            asteroid.explode();
    }

    @Override
    public Resource clone() {
        return new Uranium();
    }

}
