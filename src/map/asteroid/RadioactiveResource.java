package map.asteroid;

import utility.OutputFormatter;

/**
 * Class map.asteroid.RadioactiveResource
 */
public class RadioactiveResource extends Resource {

    //
    // Fields
    //


    //
    // Constructors
    //
    public RadioactiveResource() {
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    //
    // Other methods
    //

    /**
     *
     */
    public void drilledInPerihelion() {
        OutputFormatter.OutputCall("drilledInPerihelion() - "+this.toString());
        asteroid.explode();
        OutputFormatter.OutputReturn("return");
    }


}
