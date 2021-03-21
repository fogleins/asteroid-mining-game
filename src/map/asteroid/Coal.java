package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Coal
 * A type of normal resource (without any special behaviour)
 */
public class Coal extends Resource {

    public Coal() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
