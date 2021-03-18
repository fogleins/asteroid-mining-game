/**
 * Class Entity
 */
abstract public class Entity {

    //
    // Fields
    //

    private String name;

    private Asteroid m_asteroid;

    //
    // Constructors
    //
    public Entity() {
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param newVar the new value of name
     */
    public void setName(String newVar) {
        name = newVar;
    }

    /**
     * Get the value of m_asteroid
     *
     * @return the value of m_asteroid
     */
    private Asteroid getAsteroid() {
        return m_asteroid;
    }

    /**
     * Set the value of m_asteroid
     *
     * @param newVar the new value of m_asteroid
     */
    private void setAsteroid(Asteroid newVar) {
        m_asteroid = newVar;
    }

    //
    // Other methods
    //

    /**
     * @param whereTo
     */
    public void move(Asteroid whereTo) {
    }


    /**
     *
     */
    public void drill() {
    }


    /**
     *
     */
    public void die() {
    }


    /**
     *
     */
    public void asteroidExploded() {
    }


}
