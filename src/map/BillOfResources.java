package map;

import map.asteroid.Resource;
import utility.OutputFormatter;
import java.util.ArrayList;

public class BillOfResources {

    private ArrayList<Resource> resourcesNeeded = new ArrayList<>();

    public BillOfResources() { }
    /*public BillOfResources(ArrayList<Resource> resourcesNeeded) {
        OutputFormatter.OutputCall("create - " + this.toString());
        this.resourcesNeeded = resourcesNeeded;
        OutputFormatter.OutputReturn("return");
    }*/

    /**
     * Add a Resources object to the resources list
     */
    public void addResources(Resource resource) {
        this.resourcesNeeded.add(resource);
    }

    /**
     * Remove a Resources object from the resources list
     */
    public void removeResources(Resource resource) {
        this.resourcesNeeded.remove(resource);
    }

    /**
     * @param ownedResources The resources that the caller owns. Changed if used.
     * @return boolean Whether the usage was successful or not. If yes, the appropriate resources were removed.
     */
    public boolean use(ArrayList<Resource> ownedResources) {
        OutputFormatter.OutputCall("use("+ ownedResources.toString() +")");
        if (!check(ownedResources)) {
            OutputFormatter.OutputReturn("return - false");
            return false;
        }
        for (Resource rn : resourcesNeeded) {
            for (int i = 0; i < ownedResources.size(); ++i)
                if (rn.isCompatibleWith(ownedResources.get(i))) {
                    ownedResources.remove(i);
                    break;
                }
        }
        OutputFormatter.OutputReturn("return - true");
        return true;
    }

    /**
     * @param ownedResources The resources that the caller owns.
     * @return boolean Is there enough resources for the build?
     */
    public boolean check(ArrayList<Resource> ownedResources) {
        OutputFormatter.OutputCall("check("+ ownedResources.toString() +")");
        int okCnt = 0;
        for (Resource rn : resourcesNeeded) {
            boolean found = false;
            for (Resource ro : ownedResources)
                if (rn.isCompatibleWith(ro))
                    found = true;
            if (!found) break;
            else ++okCnt;
        }
        boolean resultOk = okCnt == resourcesNeeded.size();
        OutputFormatter.OutputReturn("return - "+resultOk);
        return resultOk;
    }
}
