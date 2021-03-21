package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Iron
 */
public class Iron extends Resource {

    public Iron() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
