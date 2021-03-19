package map.asteroid;

import map.BillOfResources;
import map.entity.Entity;

/**
 * Class map.asteroid.BaseAsteroid
 */
public class BaseAsteroid extends Asteroid {

    //
    // Fields
    //


    public BillOfResources billOfResources;

    //
    // Constructors
    //
    public BaseAsteroid() {
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
        return billOfResources;
    }

    /**
     * Set the value of m_billOfResources
     *
     * @param billOfResources the new value of m_billOfResources
     */
    public void setBillOfResources(BillOfResources billOfResources) {
        this.billOfResources = billOfResources;
    }

    //
    // Other methods
    //

    /**
     * @param entity
     */
    public void acceptEntity(Entity entity) {
    }


}
