/**
 * Class BaseAsteroid
 */
public class BaseAsteroid extends Asteroid {

    //
    // Fields
    //


    public BillOfResources m_billOfResources;

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
        return m_billOfResources;
    }

    /**
     * Set the value of m_billOfResources
     *
     * @param newVar the new value of m_billOfResources
     */
    public void setBillOfResources(BillOfResources newVar) {
        m_billOfResources = newVar;
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
