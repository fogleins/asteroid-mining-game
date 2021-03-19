package map.entity;

import map.asteroid.Asteroid;

/**
 * Class map.entity.TeleportGate
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
//    private void removeCurrentAsteroid(map.asteroid.Asteroid new_object) {
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
     * @return map.asteroid.Asteroid
     */
    public Asteroid getOtherSide() {
        return otherGate.getCurrentAsteroid(); // TODO: implementálni
    }


}
