package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.Coal
 */
public class Coal extends Resource {

    public Coal() {
        OutputFormatter.OutputCall("create - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

}
