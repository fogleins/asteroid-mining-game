package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Iron
 * A type of normal resource (without any special behaviour)
 */
public class Iron extends Resource {

    public Iron() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
