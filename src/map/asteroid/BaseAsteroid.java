package map.asteroid;

import control.Game;
import map.BillOfResources;
import map.entity.Entity;
import utility.OutputFormatter;

import java.util.ArrayList;

/**
 * Class map.asteroid.BaseAsteroid
 */
public class BaseAsteroid extends Asteroid {

    //
    // Fields
    //

    public BillOfResources winConditionResources;
    private Game game;

    public BaseAsteroid(Game g) {
        OutputFormatter.OutputCall("create - " + this.toString());
        winConditionResources = new BillOfResources();
        game = g;

        //To win the game, three of every resource needed
        for (int i = 0; i < 3; i++) {
            winConditionResources.addResources(new Iron());
            winConditionResources.addResources(new Uranium());
            winConditionResources.addResources(new Coal());
            winConditionResources.addResources(new Ice());
        }

        OutputFormatter.OutputReturn("return");
    }


    /**
     * Get the value of m_billOfResources
     *
     * @return the value of m_billOfResources
     */
    public BillOfResources getBillOfResources() {
        OutputFormatter.OutputCall("getBillOfResources() - " + name);
        OutputFormatter.OutputReturn("return - winConditionResources");
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
        OutputFormatter.OutputCall("acceptEntity() - " + name);
        this.entities.add(entity);

        ArrayList<Resource> resourcesOnAsteroid = new ArrayList<>();

        //We need to know the quantity of the resources that are on the baseAsteroid.
        for (int i = 0; i < entities.size(); i++) {
            for (Resource r : entities.get(i).getResources()) {
                resourcesOnAsteroid.add(r);
            }
        }

        if (winConditionResources.check(resourcesOnAsteroid))
            game.gameWon();


        OutputFormatter.OutputReturn("return");
    }
}
