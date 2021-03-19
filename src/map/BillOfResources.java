package map;

import map.asteroid.Resource;

import java.util.ArrayList;


/**
 * Class map.BillOfResources
 */
public class BillOfResources {

    //
    // Fields
    //


    private ArrayList<Resource> resources = new ArrayList<>();

    //
    // Constructors
    //
    public BillOfResources() {
    }

    ;

    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Add a Resources object to the resourcesVector List
     */
    private void addResources(Resource resource) {
        this.resources.add(resource);
    }

    /**
     * Remove a Resources object from resourcesVector List
     */
    private void removeResources(Resource resource) {
        this.resources.remove(resource);
    }

    /**
     * Get the List of Resources objects held by resourcesVector
     *
     * @return List of Resources objects held by resourcesVector
     */
    private ArrayList<Resource> getResourcesList() {
        return resources;
    }


    //
    // Other methods
    //

    /**
     * @param ownedResources
     * @return boolean
     */
    public boolean use(Resource ownedResources) {
        return false; // TODO: implementálni
    }


    /**
     * @param ownedResources
     * @return boolean
     */
    public boolean check(Resource ownedResources) {
        return false; // TODO: implementálni
    }


}
