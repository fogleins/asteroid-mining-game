package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Uranium
 */
public class Uranium extends RadioactiveResource {

    public Uranium() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
