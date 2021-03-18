/**
 * Class TeleportGate
 */
public class TeleportGate {

    //
    // Fields
    //


    private Asteroid currentAsteroid;

    private TeleportGate otherGate;

    //
    // Constructors
    //
    public TeleportGate() {
    }

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Get the List of CurrentAsteroid objects held by currentasteroidVector
     *
     * @return List of CurrentAsteroid objects held by currentasteroidVector
     */
    private Asteroid getCurrentAsteroid() {
        return currentAsteroid;
    }

//    /**
//     * Remove a CurrentAsteroid object from currentasteroidVector List
//     */
//    private void removeCurrentAsteroid(Asteroid new_object) {
//        currentAsteroid.remove(new_object);
//    }

    /**
     * Add a CurrentAsteroid object to the currentasteroidVector List
     */
    private void setCurrentAsteroid(Asteroid new_object) {
        currentAsteroid = new_object;
    }


    //
    // Other methods
    //

    /**
     * @return Asteroid
     */
    public Asteroid getOtherSide() {
        return null; // TODO: implementálni
    }


}
