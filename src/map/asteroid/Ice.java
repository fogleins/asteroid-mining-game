package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Ice
 */
public class Ice extends SublimableResource {

    public Ice() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
