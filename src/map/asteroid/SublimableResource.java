package map.asteroid;

import utility.OutputFormatter;

/**
 * Class SublimableResource
 * Special resource, sublimates if drilled in perihelion
 */
public abstract class SublimableResource extends Resource {

    public SublimableResource() { }

    public void drilledInPerihelion() {
        OutputFormatter.OutputCall("drilledInPerihelion() - " + this.toString());
        asteroid.mined(); // sublimate (removes the resource from the asteroid
        OutputFormatter.OutputReturn("return");
    }
}
