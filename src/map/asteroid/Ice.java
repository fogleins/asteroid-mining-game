package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Ice
 * A type of sublimable resource (disappears if drilled in perihelion)
 */
public class Ice extends SublimableResource {

    public Ice() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
