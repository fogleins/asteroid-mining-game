package map.asteroid;

import control.Game;
import map.entity.Entity;
import map.resource.*;

import java.util.ArrayList;

/**
 * Class map.asteroid.BaseAsteroid
 */
public class BaseAsteroid extends Asteroid {

    /**
     * Stores the Resources that are needed to win the game.
     */
    public static final BillOfResources winConditionResources;
    private static final long serialVersionUID = -1707774486046615795L;

    static {
        //To win the game, three of every type of resource are needed
        winConditionResources = new BillOfResources();
        for (int i = 0; i < 3; i++) {
            winConditionResources.addResources(new Iron());
            winConditionResources.addResources(new Uranium());
            winConditionResources.addResources(new Coal());
            winConditionResources.addResources(new Ice());
        }
    }

    public BaseAsteroid() {
        name = "BASE";
        surfaceThickness = 0;
        inPerihelion = false;
    }

    /**
     * Accepts an entity and checks if the game is won.
     *
     * @param entity it will be added to the list of entities.
     */
    public void acceptEntity(Entity entity) {
        this.entities.add(entity);

        ArrayList<Resource> resourcesOnAsteroid = new ArrayList<>();

        // We need to know the quantity of the resources that are on the baseAsteroid.
        for (Entity value : entities) {
            if (value.getResources() != null)
                resourcesOnAsteroid.addAll(value.getResources());
        }

        if (winConditionResources.check(resourcesOnAsteroid))
            Game.getInstance().gameWon();
    }

    /**
     * Overridden method, because the Base Asteroid should not be in perihelion.
     */
    @Override
    public void changePerihelionState() {
    }
}
