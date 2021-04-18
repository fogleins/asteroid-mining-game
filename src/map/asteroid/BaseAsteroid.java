package map.asteroid;

import control.Game;
import map.BillOfResources;
import map.entity.Entity;

import java.util.ArrayList;

/**
 * Class map.asteroid.BaseAsteroid
 */
public class BaseAsteroid extends Asteroid {

    //
    // Fields
    //

    public BillOfResources winConditionResources;

    public BaseAsteroid() {
//        name = "BASE"; // TODO: uncomment after testing
        winConditionResources = new BillOfResources();

        //To win the game, three of every resource needed
        for (int i = 0; i < 3; i++) {
            winConditionResources.addResources(new Iron());
            winConditionResources.addResources(new Uranium());
            winConditionResources.addResources(new Coal());
            winConditionResources.addResources(new Ice());
        }
//        printState();
    }

    public BaseAsteroid (Asteroid a){
        name=a.name;
        resource=a.resource;
        surfaceThickness=a.surfaceThickness;
        inPerihelion=a.inPerihelion;

        winConditionResources = new BillOfResources();

        //To win the game, three of every resource needed
        for (int i = 0; i < 3; i++) {
            winConditionResources.addResources(new Iron());
            winConditionResources.addResources(new Uranium());
            winConditionResources.addResources(new Coal());
            winConditionResources.addResources(new Ice());
        }
    }


    /**
     * Get the value of m_billOfResources
     *
     * @return the value of m_billOfResources
     */
    public BillOfResources getBillOfResources() {
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

    /**
     * Accepts an entity and checks if the game is won.
     *
     * @param entity it will be added to the list of entities.
     */
    public void acceptEntity(Entity entity) {
        this.entities.add(entity);

        ArrayList<Resource> resourcesOnAsteroid = new ArrayList<>();

        //We need to know the quantity of the resources that are on the baseAsteroid.
        for (int i = 0; i < entities.size(); i++) {
            if(entities.get(i).getResources()!=null)
                resourcesOnAsteroid.addAll(entities.get(i).getResources());
        }

        if (winConditionResources.check(resourcesOnAsteroid))
            Game.getInstance().gameWon();
        printState();
    }
}
