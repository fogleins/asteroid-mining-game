package map.asteroid;

import map.BillOfResources;
import map.entity.Entity;
import utility.OutputFormatter;

/**
 * Class map.asteroid.BaseAsteroid
 */
public class BaseAsteroid extends Asteroid {

    //
    // Fields
    //


    public BillOfResources winConditionResources;

    //
    // Constructors
    //
    public BaseAsteroid() {
        OutputFormatter.OutputCall("create - " + this.toString());
        winConditionResources = new BillOfResources();
        OutputFormatter.OutputReturn("return");
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the value of m_billOfResources
     *
     * @return the value of m_billOfResources
     */
    public BillOfResources getBillOfResources() {
        OutputFormatter.OutputCall("getBillOfResources() - " + name);
        OutputFormatter.OutputReturn("return - winConditionResources");
        return winConditionResources;
    }

    /**
     * Set the value of m_billOfResources
     *
     * @param billOfResources the new value of m_billOfResources
     */
    public void setBillOfResources(BillOfResources billOfResources) {

        this.winConditionResources = billOfResources;
    }

    //
    // Other methods
    //

    /**
     * @param entity
     */
    public void acceptEntity(Entity entity) {
        OutputFormatter.OutputCall("acceptEntity() - " + name);
        this.entities.add(entity);
        OutputFormatter.OutputReturn("return");
    }


}
