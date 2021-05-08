package control;

import map.Map;
import map.entity.Settler;
import map.entity.Steppable;
import map.resource.Resource;
import view.GameStatusView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class control.Game
 */
public final class Game {

    /**
     * The instance of the singleton Game class.
     */
    private static final Game instance = new Game();

    /**
     * Reference to the Map object, which contains the game's map.
     */
    private final Map map;

    /**
     * List of Settlers, who are playing the game.
     */
    private final ArrayList<Settler> settlers;

    /**
     * List of Steppables who are playing.
     */
    private final ArrayList<Steppable> steppables;

    /**
     * Counts the number of rounds played.
     */
    private int currentRound;

    /**
     * The number of the round in which the next sunflare occurs.
     */
    private int nextSunflare;

    /**
     * The settler, who should step next.
     */
    private Settler current;

    private static GameStatusView statusView;

    /**
     * Constructor. Initializes the Game object.
     */
    private Game() {
        currentRound = 1;
        nextSunflare = generateNextSunflare();
        map = new Map();
        settlers = new ArrayList<>();
        steppables = new ArrayList<>();
    }

    /**
     * Adds settlers with the specified names to the settlers list and sets the first settler as current.
     *
     * @param playerNames A String list containing the players' names.
     */
    public static void start(ArrayList<String> playerNames) {
        instance.settlers.clear(); // just to be sure
        for (String playerName : playerNames) {
            Settler settler = new Settler(playerName);
            instance.settlers.add(settler);
            // settlers are placed on the base asteroid at the beginning of the game
            settler.move(instance.map.getBaseAsteroid());
        }
        instance.current = instance.settlers.get(0);
        instance.current.yourTurn(); // TODO: implement yourTurn in Settler
    }

    public static void setStatusView(GameStatusView view) {
        statusView = view;
        statusView.updateView(instance.currentRound, false);
    }

    /**
     * Other objects can use this method to access the Game object.
     *
     * @return The instance of the Game class.
     */
    public static Game getInstance() {
        return instance;
    }

    /**
     * Returns the map the game is played on.
     *
     * @return The game's Map object.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Returns the player who should step next.
     *
     * @return The current settler.
     */
    public Settler getCurrentSettler() {
        return current;
    }

    /**
     * Remove a Settler object from settlers
     */
    public void removeSettler(Settler settler) {
        settlers.remove(settler);
    }

    /**
     * Add a Steppable object to the steppables list (if the object is not already in it)
     *
     * @param newSteppable The steppable object that should be added to the list
     */
    public void addSteppable(Steppable newSteppable) {
        if (!steppables.contains(newSteppable))
            steppables.add(newSteppable);
    }

    /**
     * Remove a Steppable object from steppables
     */
    public void removeSteppable(Steppable steppable) {
        steppables.remove(steppable);
    }

    /**
     * Returns a {@code Resource} object from the Settler's resource list which should be exchanged.
     *
     * @param resources A list of {@code Resource}s the Settler has.
     * @return The {@code Resource} which should be exchanged.
     */
    public Resource exchangeResource(ArrayList<Resource> resources) {
        return resources.get(resources.size() - 1);  // a lista utolsó tagját cseréljük ki
    }

    /**
     * This method is called when the settlers have collected all the needed resources on a single asteroid.
     */
    public void gameWon() {
        System.out.println("Settlers won!");
    }

    /**
     * This method is called if there's no way for the players to win.
     */
    private void gameLost() {
        System.out.println("Settlers lost!");
    }

    /**
     * Randomly generates a number when a sunflare should occur.
     *
     * @return int The number of round in which the next sunflare occurs.
     */
    private int generateNextSunflare() {
        Random rnd = new Random();
        if (currentRound < 20) {
            return rnd.nextInt(2) + 20;
        }
        return rnd.nextInt(20) + 10;
    }

    /**
     * This method is called every time all the settlers have moved in a given round. It steps with all the steppables,
     * checks if there should be a sunflare, and changes the asteroid's 'inPerihelion' state.
     */
    private void roundFinished() {
        Random random = new Random();
        if (currentRound == nextSunflare) {
            map.sunflare();
            nextSunflare = generateNextSunflare() + currentRound;
        }
        for (Steppable steppable : steppables) {
            steppable.step();
        }
        if (random.nextInt(4) == 0) // generates a random number, where 0 <= n < 4
            map.changePerihelion();

        if (settlers.size() == 0)
            gameLost();

        currentRound++;
        current = settlers.get(0);

        statusView.updateView(currentRound, nextSunflare + 1 == currentRound);
        map.roundPassed();  // resource exposition
    }

    /**
     * A Settler calls this method after he stepped in a given round.
     */
    public void nextPlayer() {
        Settler previousSettler = current;
        int idx = settlers.indexOf(current);
        if (idx == settlers.size() - 1) {
            roundFinished();
        } else {
            current = settlers.get(idx + 1);
        }
        // the previous settler's asteroid should not be painted light pink, so we repaint it
        if (previousSettler != null) {
            previousSettler.getAsteroid().getAsteroidView().updateView();
        }
        // update the current settler's asteroid's view so that its painted in light pink
        current.getAsteroid().getAsteroidView().updateView();
        current.yourTurn();
    }
}
