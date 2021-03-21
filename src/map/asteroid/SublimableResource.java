package map.asteroid;

import utility.OutputFormatter;

public class SublimableResource extends Resource {

    public SublimableResource() { }

    public void drilledInPerihelion() {
        OutputFormatter.OutputCall("drilledInPerihelion() - " + this.toString());
        asteroid.mined();
        OutputFormatter.OutputReturn("return");
    }
}
