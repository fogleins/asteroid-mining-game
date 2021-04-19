package control;

import map.Map;
import map.asteroid.*;
import map.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    private static final Game game = Game.getInstance();
    private static final ArrayList<TeleportGate> teleportGates = new ArrayList<>();


    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean mapSet = false;
        boolean entitiesSet = false;
        Test.setInitiazePhase(true);
        Test.setAutomaticPerihelionChange(true);


        try {
            while (((line = reader.readLine()) != null) && !(line.equals("")) && !line.equals("exit")) {
                String[] details = line.split(" ");
                switch (details[0]) {
                    case "setmap": {
                        Test.setInitiazePhase(true);
                        String[] asteroids = reader.readLine().split(" ");
                        String[] thisAsteroid;
                        for (int i = 0; i < asteroids.length; i++) {
                            /*
                             * Regexps arent easy:
                             * ^ is the start of the string
                             * ([ucx]|(ir)|(ic)) makes sure that only the specified resources can be set for an Asteroid
                             * \d+ matches an integer
                             * ((ip)|(op)) accepts either "ip" or "op"
                             * [a-zA-z0-9\-]+ matches the asteroid's neighbours: their name may contain upper- or lowercase letters,
                             *      numbers, and a dash (-)
                             * (\s[a-zA-z0-9]*)? matches an asteroid's teleport gate; may be left empty
                             * $ is the end of the string
                             */
                            if ((line = reader.readLine()).matches("^" + asteroids[i] + " ([ucx]|(ir)|(ic)) " +
                                    "\\d+ ((ip)|(op)) [a-zA-z0-9\\-]+(\\s[a-zA-z0-9]*)?$")) {
                                thisAsteroid = line.split(" ");
                                // We are checking if this asteroid was initialized previously. If yes, we modify its
                                // variables, otherwise we create a new asteroid.
                                Asteroid asteroid = getAsteroidByName(thisAsteroid[0]);
                                if (asteroid == null)
                                    asteroid = new Asteroid();
                                asteroid.setName(thisAsteroid[0]);
                                asteroid.setResource(getResourceObject(thisAsteroid[1]));
                                asteroid.setSurfaceThickness(Integer.parseInt(thisAsteroid[2]));
                                asteroid.setInPerihelion(thisAsteroid[3].equals("ip"));
                                String[] neighbours = thisAsteroid[4].split("-");
                                if (game.getMap().getAsteroids().size() == 0) {
                                    BaseAsteroid baseAsteroid = new BaseAsteroid(asteroid);
                                    game.getMap().addAsteroid(baseAsteroid);
                                    game.getMap().setBaseAsteroid(baseAsteroid);
                                    asteroid = baseAsteroid;
                                }
                                if (!neighbours[0].equals("x")) {
                                    for (int j = 0; j < neighbours.length; j++) {
                                        Asteroid neighbor = getAsteroidByName(neighbours[j]);
                                        if (neighbor == null) {
                                            neighbor = new Asteroid();
                                            neighbor.setName(neighbours[j]);
                                            game.getMap().addAsteroid(neighbor);
                                        }
                                        asteroid.addNeighbour(neighbor);
                                    }
                                }
                                // the 6th parameter is optional, but even if its given, it may be x, which has
                                // the same result as if it was not given
                                if (thisAsteroid.length == 6 && !thisAsteroid[5].equals("x")) {
                                    TeleportGate teleportGate = (TeleportGate) getSteppableByName(thisAsteroid[5]);
                                    if (teleportGate == null)
                                        teleportGate = new TeleportGate();
                                    asteroid.setTeleportGate(teleportGate);
                                }
                                if (game.getMap().getAsteroids().size() != 0)
                                    game.getMap().addAsteroid(asteroid);
                            } else // if the syntax doesn't match the specified syntax, an exception is thrown
                                throw new InvalidSyntaxException("Invalid syntax.");
                        }
                        mapSet = true;
                        Test.setInitiazePhase(false);
                        break;
                    }
                    case "setentities":
                        Test.setInitiazePhase(true);
                        if (line.matches("setentities \\d+")) {
                            int entityCount = Integer.parseInt(line.split(" ")[1]);
                            String[] thisEntity;
                            for (int i = 0; i < entityCount; i++) {
                                line = reader.readLine();
                                // if the entity is a settler
                                /*
                                 * ^s Makes sure the input starts with a lowercase 's'
                                 * [a-zA-Z0-9]+ accepts any number, upper- or lowercase letter; used for checking if the
                                 *      settler's and the asteroid's names only contain simple characters
                                 * ([ucx\-]|(ir)|(ic)){1,19} matches 'u', 'c', 'x', '-', 'ic' or 'ir', the settler's
                                 *      resources {1,19}, because the settler can have up to 10 resources, but the
                                 *      separators (dashes, '-') are counted separately. At least one should be specified.
                                 * (\s[a-zA-z0-9]*)? matches a whitespace followed by letters and/or numbers (the name
                                 *      of the teleports the settler has
                                 * $: end of string
                                 */
                                thisEntity = line.split(" ");
                                if (line.matches("^s [a-zA-Z0-9]+ [a-zA-Z0-9]+ ([ucx\\-]|(ir)|(ic)){1,19}" +
                                        "(\\s[a-zA-z0-9]*)?$")) {
                                    Settler settler = new Settler(thisEntity[1]);
                                    Asteroid asteroid = getAsteroidByName(thisEntity[2]);
                                    settler.setAsteroid(asteroid);
                                    String[] resources = thisEntity[3].split("-");
                                    for (int j = 0; j < resources.length; j++) {
                                        Resource resource = getResourceObject(resources[j]);
                                        if (resource != null)
                                            settler.getResources().add(resource);
                                    }
                                    // teleport gate is an optional parameter
                                    if (thisEntity.length == 5 && !thisEntity[4].equals("x")) {
                                        String[] teleports = thisEntity[4].split("-");
                                        for (int j = 0; j < teleports.length; j++) {
                                            TeleportGate teleportGate = (TeleportGate) getSteppableByName(teleports[j]);
                                            if (teleportGate == null)
                                                throw new BadArgumentException("TeleportGate " + teleports[j] + " doesn't exist.");
                                            settler.addTeleport(teleportGate);
                                        }
                                    }
                                    asteroid.acceptEntity(settler);
                                    game.addSettler(settler);
                                    game.nextPlayer();
                                    // if the entity is a ufo or robot
                                } else if (line.matches("^[ur] [a-zA-Z0-9]+ [a-zA-Z0-9]+(\\s[a-zA-z0-9]*)?$")) {
                                    // ufo
                                    if (line.startsWith("u")) {
                                        Ufo ufo = new Ufo(thisEntity[1]);
                                        Asteroid asteroid = getAsteroidByName(thisEntity[2]);
                                        ufo.setAsteroid(asteroid);
                                        if (thisEntity.length == 4 && thisEntity[3].equals("dm"))
                                            ufo.setMine(false);
                                        asteroid.acceptEntity(ufo);
                                        game.addSteppable(ufo);
                                        // robot
                                    } else if (line.startsWith("r")) {
                                        Robot robot = new Robot(thisEntity[1], getAsteroidByName(thisEntity[2]));
                                        Asteroid asteroid = getAsteroidByName(thisEntity[2]);
                                        robot.setAsteroid(asteroid);
                                        if (thisEntity.length == 4 && thisEntity[3].equals("dm"))
                                            Test.setRobotDontMove(true);
                                        game.addSteppable(robot);
                                    }
                                } else {
                                    throw new InvalidSyntaxException("Invalid syntax.");
                                }
                            }
                        } else
                            throw new InvalidSyntaxException("Invalid syntax.");
                        entitiesSet = true;
                        Test.setInitiazePhase(false);
                        break;
                    case "move":
                        if (line.matches("^move [sru] [a-zA-Z0-9]+ [a-zA-Z0-9]+$")) {
                            String[] parameters = line.split(" ");
                            Asteroid whereTo = getAsteroidByName(parameters[3]);
                            if (whereTo == null)
                                throw new BadArgumentException("Asteroid " + parameters[3] + " can't be found.");
                            if (parameters[1].equals("s")) {
                                Settler settler = getSettlerByName(parameters[2]);
                                if (settler == null)
                                    throw new BadArgumentException("Settler " + parameters[2] + " can't be found.");
                                settler.move(whereTo);
                            } else {
                                Steppable steppable = getSteppableByName(parameters[2]);
                                if (steppable == null)
                                    throw new BadArgumentException("Steppable " + parameters[2] + " can't be found.");
                                if (parameters[1].equals("r"))
                                    ((Robot) steppable).move(whereTo);
                                else if (parameters[1].equals("u"))
                                    ((Ufo) steppable).move(whereTo);
                                steppable.setSteppedThisRound(true);
                            }
                        } else
                            throw new InvalidSyntaxException("Invalid syntax: move <entity_type> <entity_name> <asteroid>");
                        break;
                    case "drill":
                        if (line.matches("^drill [sr] [a-zA-Z0-9]+$")) {
                            String[] parameters = line.split(" ");
                            if (parameters[1].equals("s")) {
                                Settler settler = getSettlerByName(parameters[2]);
                                if (settler == null)
                                    throw new BadArgumentException("Settler " + parameters[2] + " can't be found.");
                                settler.drill();
                            } else {
                                Robot robot = (Robot) getSteppableByName(parameters[2]);
                                if (robot == null)
                                    throw new BadArgumentException("Robot " + parameters[2] + " can't be found.");
                                robot.drill();
                                robot.setSteppedThisRound(true);
                            }
                        } else throw new InvalidSyntaxException("Invalid syntax in drill.");
                        break;
                    case "mine":
                        if (line.matches("^mine [su] [a-zA-Z0-9]+$")) {
                            String[] parameters = line.split(" ");
                            if (parameters[1].equals("s")) {
                                Settler settler = getSettlerByName(parameters[2]);
                                if (settler == null)
                                    throw new BadArgumentException("Settler " + parameters[2] + " can't be found.");
                                settler.mine();
                            }
                        } else throw new InvalidSyntaxException("Invalid syntax in drill.");
                        break;
                    case "buildrobot":
                        if (line.matches("^buildrobot [a-zA-Z0-9]+ [a-zA-Z0-9]+$")) {
                            String[] parameters = line.split(" ");
                            Settler settler = getSettlerByName(parameters[1]);
                            if (settler == null)
                                throw new BadArgumentException("Settler " + parameters[1] + " can't be found.");
                            // we make a copy of the current steppables, before the robot is built
                            ArrayList<Steppable> before = new ArrayList<>();
                            for (Steppable steppable : game.getSteppables())
                                before.add(steppable);
                            settler.buildRobot();
                            // we are looking for the robot we've just created
                            for (Steppable steppable : game.getSteppables()) {
                                if (!before.contains(steppable)) {
                                    ((Robot) steppable).setName(parameters[2]);
                                    break;
                                }
                            }
                        } else throw new InvalidSyntaxException("Invalid syntax in buildrobot.");
                        break;
                    case "buildteleport":
                        if (line.matches("^buildteleport [a-zA-Z0-9]+ [a-zA-Z0-9]+ [a-zA-Z0-9]+$")) {
                            String[] parameters = line.split(" ");
                            Settler settler = getSettlerByName(parameters[1]);
                            if (settler == null)
                                throw new BadArgumentException("Settler " + parameters[1] + " can't be found.");
                            // we make a copy of the current steppables, before the robot is built
                            ArrayList<TeleportGate> before = new ArrayList<>();
                            for (TeleportGate teleportGate : settler.getTeleports())
                                before.add(teleportGate);
                            Test.setAutomaticPerihelionChange(false);
                            settler.buildTeleport();
                            boolean firstRenamed = false;
                            // we are looking for the robot we've just created
                            for (TeleportGate teleportGate : settler.getTeleports()) {
                                if (!before.contains(teleportGate)) {
                                    teleportGate.setName(firstRenamed ? parameters[2] : parameters[3]);
                                    firstRenamed = true;
                                }
                            }
                        } else throw new InvalidSyntaxException("Invalid syntax in buildrobot.");
                        break;
                    case "placeteleport":
                        if (line.matches("^placeteleport [a-zA-Z0-9]+$")) {
                            String[] parameters = line.split(" ");
                            Settler settler = getSettlerByName(parameters[1]);
                            if (settler == null)
                                throw new BadArgumentException("Settler " + parameters[1] + " can't be found.");
                            settler.placeTeleport();
                        } else throw new InvalidSyntaxException("Invalid syntax in placeteleport.");
                        break;
                    case "setsunflare":
                        if (line.matches("^setsunflare \\d+ [a-zA-z0-9\\-]+$")) {
                            String[] parameters = line.split(" ");
                            game.setNextSunflare(Integer.parseInt(parameters[1]));
                            String[] asteroids = parameters[2].split("-");
                            Asteroid asteroid;
                            for (String a : asteroids) {
                                if ((asteroid = getAsteroidByName(a)) != null)
                                    Map.sunflareAsteroids.add(asteroid);
                            }
                        } else throw new InvalidSyntaxException("Invalid syntax in setsunflare.");
                        break;
                    case "changeperihelion": {
                        if (line.matches("^changeperihelion [a-zA-z0-9\\-]+$")) {
                            String[] asteroids = line.split(" ")[1].split("-");
                            for (String asteroidStr : asteroids) {
                                Asteroid asteroid = getAsteroidByName(asteroidStr);
                                if (asteroid == null)
                                    throw new BadArgumentException("Asteroid " + asteroidStr + " can't be found.");
                                asteroid.setInPerihelion(!asteroid.getInPerihelion());
                            }
                        } else throw new InvalidSyntaxException("Invalid syntax in changeperihelion.");
                        break;
                    }
                    case "setteleport":
                        Test.setInitiazePhase(true);
                        if (!mapSet && !entitiesSet && line.matches("^setteleport [a-zA-z0-9\\-]+$")) {
                            String[] gatesNames = line.split(" ")[1].split("-");
                            ArrayList<Resource> resources = new ArrayList<>();
                            for (String substr : "ir-ir-ic-u".split("-"))
                                resources.add(getResourceObject(substr));
                            ArrayList<TeleportGate> gates = TeleportGate.create(resources);
                            gates.get(0).setName(gatesNames[0]);
                            gates.get(1).setName(gatesNames[1]);
                            teleportGates.add(gates.get(0));
                            teleportGates.add(gates.get(1));
                        } else throw new InvalidSyntaxException("Syntax error. Maybe setteleport was " +
                                "called after map or entities have been set?");
                        Test.setInitiazePhase(false);
                        break;
                    case "finishround":
                        game.roundFinishedWrapper();
                        break;
                    case "exit":
                        break;
                    default:
                        System.out.println("Unknown command.");
                        break;
                }
            }
        } catch (InvalidSyntaxException | BadArgumentException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Test.setInitiazePhase(false);
    }

    /**
     * Gets an {@code Asteroid} from the Map's asteroids list based on the asteroid's name.
     *
     * @param name The name of the asteroid.
     * @return The Asteroid with the given name, null if not found.
     */
    private static Asteroid getAsteroidByName(String name) {
        for (Asteroid asteroid : game.getMap().getAsteroids()) {
            if (asteroid.getName() != null && asteroid.getName().equals(name))
                return asteroid;
        }
        return null;
    }

    /**
     * Gets a {@code Settler} from the Game's settlers list based on the settler's name.
     *
     * @param name The name of the settler.
     * @return The Settler with the given name, null if not found.
     */
    private static Settler getSettlerByName(String name) {
        for (Settler settler : game.getSettlers()) {
            if (settler.getName() != null && settler.getName().equals(name))
                return settler;
        }
        return null;
    }

    /**
     * Gets a steppable object from the Game's steppables list based on the name of the {@code Steppable}.
     *
     * @param name The name of the steppable.
     * @return The steppable with the given name, null if not found.
     */
    private static Steppable getSteppableByName(String name) {
        for (Steppable steppable : game.getSteppables()) {
            if (steppable.getName() != null && steppable.getName().equals(name))
                return steppable;
        }
        for (TeleportGate teleportGate : teleportGates)
            if (teleportGate.getName() != null && teleportGate.getName().equals(name))
                return teleportGate;
        return null;
    }

    /**
     * Returns a resource object based on their abbreviations.
     *
     * @param type The resource's abbreviation as specified in the docs.
     * @return A resource object of the specified type.
     */
    private static Resource getResourceObject(String type) {
        switch (type) {
            case "u":
                return new Uranium();
            case "ic":
                return new Ice();
            case "ir":
                return new Iron();
            case "c":
                return new Coal();
            // argument may be 'x'
            default:
                return null;
        }
    }

    /**
     * Thrown when the input does not match the specified syntax.
     */
    private final static class InvalidSyntaxException extends Exception {
        InvalidSyntaxException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when the name given as a parameter cannot be found in the current game.
     */
    private final static class BadArgumentException extends Exception {
        BadArgumentException(String message) {
            super(message);
        }
    }
}
