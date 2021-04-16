package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Uranium
 * A type of radioactive resource (explodes if drilled in perihelion)
 */
public class Uranium extends RadioactiveResource {

    public Uranium() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
