package map.resource;

import java.util.ArrayList;

/**
 * Class map.BillOfResources
 * Used as blueprints for builds
 */
public class BillOfResources {

    private final ArrayList<Resource> resourcesNeeded = new ArrayList<>();

    /**
     * Constructor of class
     */
    public BillOfResources() { }

    /**
     * Add a Resource object to the resources list
     * @param resource The resource to add
     */
    public void addResources(Resource resource) {
        this.resourcesNeeded.add(resource);
    }

    /**
     * Remove a Resource object from the resources list
     * @param resource The resource to remove
     */
    public void removeResources(Resource resource) {
        this.resourcesNeeded.remove(resource);
    }

    /**
     * Uses the needed resources for the build (removes them from the owned ones)
     * @param ownedResources The resources that the caller owns. Changed if used.
     * @return boolean Whether the usage was successful or not. If yes, the appropriate resources were removed.
     */
    public boolean use(ArrayList<Resource> ownedResources) {
        if (!check(ownedResources)) { // check if there are enough resources, to prevent unnecessary removal
            return false;
        }
        for (Resource rn : resourcesNeeded) {
            for (int i = 0; i < ownedResources.size(); ++i)
                if (rn.isCompatibleWith(ownedResources.get(i))) { // check if resource types match
                    ownedResources.remove(i); // removed the found resource
                    break; // we are done with this one, don't need to remove others of the same type
                }
        }
        return true;
    }

    /**
     * Checks whether there is enough owned resources to build the damn thing
     * @param ownedResources The resources that the caller owns.
     * @return boolean Is there enough resources for the build?
     */
    public boolean check(ArrayList<Resource> ownedResources) {
        // create a copy of the input array, because we need to modify it, but just locally (elements are not duplicated)
        ArrayList<Resource> ownedRes = new ArrayList<>(ownedResources);

        // okCnt: number of found resources
        int okCnt = checker(ownedRes);
        return okCnt == resourcesNeeded.size(); // if it found all the necessary resources, then its okay
    }

     /**
      * Calculates how many resources does the caller have. resourceNeeded must contain only one resource
      * @param ownedResources The resources that the caller owns.
      * @return number of found resources
     * */
    public int count(ArrayList<Resource> ownedResources){
        return checker(ownedResources);
    }

    //Calculates how many necessary resources are there
    private int checker(ArrayList<Resource> ownedRes){
        int okCnt = 0;
        for (Resource rn : resourcesNeeded) {
            boolean found = false;
            for (int i = 0; i < ownedRes.size(); ++i)
                if (rn.isCompatibleWith(ownedRes.get(i))) { // check compatibility
                    found = true; // found it :)
                    ownedRes.remove(i); // remove found resource to prevent a problem with duplicate resources
                    break;
                }
            if (!found) break;
            else ++okCnt; // increment the number of found resources
        }

        return okCnt;
    }
}
