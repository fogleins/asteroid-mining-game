package map.asteroid;

import utility.OutputFormatter;

public abstract class Resource {

    protected Asteroid asteroid;

    /**
     * Set the asteroid object
     */
    public void setAsteroid(Asteroid asteroid) {
        this.asteroid = asteroid;
    }

    public void drilledInPerihelion() {
        OutputFormatter.OutputCall("drilledInPerihelion() - " + this.toString());
        OutputFormatter.OutputReturn("return");
    }

    public boolean isCompatibleWith(Resource res) {
        OutputFormatter.OutputCall("isCompatibleWith("+res.toString()+")");
        boolean result = res.getClass().equals(this.getClass());
        OutputFormatter.OutputReturn("return - " + result);
        return result;
    }
}
