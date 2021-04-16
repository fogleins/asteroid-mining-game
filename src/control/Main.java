package control;

import map.asteroid.*;
import map.entity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final Game game = Game.getInstance();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        boolean mapSet = false;
        boolean entitiesSet = false;
        boolean randomize = true; // TODO ?

        /*
         * TODO: "Ha a felhasználó nem kapcsolja ki a véletlenszerűséget, akkor nem használhatja
         *  a setsunflare és changeperihelion parancsokat."
         *
         * TODO: "A tesztelés során a setmap és setentities parancsokat kell legelőször kiadni, a további parancsok
         *  csak ezek megadását követően működnek megfelelően."
         *
         * TODO: setteleport - "Ez a parancs csak a setmap és setentities előtt jöhet!"
         */

        try {
            // TODO: "A bemeneti fájl szintakszisa: Az egyes parancsokat (paramétereikkel együtt)
            //  egy üres sor választja el egymástól!

            System.out.print("Random? (y/n) [y]: "); // TODO
            if (reader.readLine().contains("n"))
                randomize = false;

            System.out.print("Type a command and hit enter: ");
            while (((line = reader.readLine()) != null) && !(line.equals("")) && !line.equals("exit")) {
                String[] details = line.split(" ");
                switch (details[0]) {
                    case "setmap":
                        String[] asteroids = reader.readLine().split(" ");
                        String[] thisAsteroid;
                        String[] neighbors;
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
                                neighbors = thisAsteroid[4].split("-");
                                // We are checking if this asteroid was initialized previously. If yes, we modify it's
                                // variables, otherwise we create a new asteroid.
                                Asteroid asteroid = getAsteroidByName(thisAsteroid[0]);
                                if (asteroid == null)
                                    asteroid = new Asteroid();
                                asteroid.setName(thisAsteroid[0]);
                                asteroid.setResource(getResourceObject(thisAsteroid[1]));
                                asteroid.setSurfaceThickness(Integer.parseInt(thisAsteroid[2]));
                                asteroid.setInPerihelion(thisAsteroid[3].equals("ip"));
                                String[] neighbours = thisAsteroid[4].split("-");
                                if (!neighbours[0].equals("x")) {
                                    for (int j = 0; j < neighbours.length; j++) {
                                        Asteroid neighbor = getAsteroidByName(neighbours[j]);
                                        if (neighbor == null) {
                                            neighbor = new Asteroid();
                                            game.getMap().addAsteroid(neighbor);
                                        }
                                        asteroid.addNeighbour(neighbor);
                                    }
                                }
                                if (!thisAsteroid[5].equals("x")) {
                                    TeleportGate teleportGate = (TeleportGate) getSteppableByName(thisAsteroid[5]);
                                    if (teleportGate == null)
                                        teleportGate = new TeleportGate();
                                    // TODO: ezt a két lépést össze kéne vonni, pl ha az aszteroida elfogadja a teleportot,
                                    //  mert van szabad helye, akkor az aszteroida setTeleportGate-jében meghívhatná a
                                    //  teleport függvényét, önmagát paraméterül adva
                                    teleportGate.setCurrentAsteroid(asteroid);
                                    asteroid.setTeleportGate(teleportGate);
                                }

                                // TODO: remove - csak a teszteléshez kellett
                                for (int j = 0; j < thisAsteroid.length; j++) {
                                    if (j == 4) {
                                        for (int k = 0; k < neighbors.length; k++) {
                                            System.out.println(neighbors[k]);
                                        }
                                    } else {
                                        System.out.println(thisAsteroid[j]);
                                    }
                                }
                                // TODO: --- remove vége ---
                            } else // if the syntax doesn't match the specified syntax, an exception is thrown
                                throw new InvalidSyntaxException("Invalid syntax.");
                        }
                        mapSet = true;
                        break;
                    case "setentities":
                        if (line.matches("setentities \\d+")) {
                            int entityCount = Integer.parseInt(line.split(" ")[1]);
                            String[] thisEntity;
                            for (int i = 0; i < entityCount; i++) {
                                line = reader.readLine();
                                // if the entity is a settler TODO (#typecheck :))
                                /*
                                 * TODO Regexplecke part 2:
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
                                        "(\\s[a-zA-z0-9]*)?$")) { // TODO: max 3 teleport nincs ellenőrizve, [ucx] nem *-gal?
                                    Settler settler = new Settler(thisEntity[1]);
                                    settler.setAsteroid(getAsteroidByName(thisEntity[2]));
                                    // TODO: 8.2.17-ben aszteroidánál x esetén null lesz a resource/teleport, itt hogy legyen?
                                    String[] resources = thisEntity[3].split("-");
                                    for (int j = 0; j < resources.length; j++) {
                                        settler.getResources().add(getResourceObject(resources[j]));
                                    }
                                    String[] teleports = thisEntity[4].split("-");
                                    for (int j = 0; j < teleports.length; j++) {
                                        // TODO: teleport már létezik vagy itt hozzuk létre? Mindkettő kezelése?
                                        // settler.addTeleport(getSteppableByName(teleports[j]));
                                        TeleportGate teleportGate = new TeleportGate();
                                        teleportGate.setName(teleports[j]);
//                                        game.addSteppable(teleportGate); // TODO: itt elvileg még nincs lerakva, felesleges hozzáadni a game-hez
                                    }
                                    game.addSettler(settler);
                                    // if the entity is a ufo or robot
                                } else if (line.matches("^[ur] [a-zA-Z0-9]+ [a-zA-Z0-9]+$")) {
                                    // ufo
                                    if (line.startsWith("u")) {
                                        Ufo ufo = new Ufo(thisEntity[1]);
                                        ufo.setAsteroid(getAsteroidByName(thisEntity[2]));
                                        game.addSteppable(ufo);
                                        // robot
                                    } else if (line.startsWith("r")) {
                                        Robot robot = new Robot(thisEntity[1]);
                                        robot.setAsteroid(getAsteroidByName(thisEntity[2]));
                                        game.addSteppable(robot);
                                    }
                                } else {
                                    throw new InvalidSyntaxException("Invalid syntax.");
                                }
                            }
                        } else
                            throw new InvalidSyntaxException("Invalid syntax.");
                        entitiesSet = true;
                        break;
                    case "move":
                        break;
                    case "drill":
                        break;
                    case "mine":
                        break;
                    case "buildrobot":
                        break;
                    case "buildteleport":
                        break;
                    case "placeteleport":
                        break;
                    case "setsunflare":
                        break;
                    case "changeperihelion":
                        break;
                    case "setteleport":
                        if (!mapSet && !entitiesSet)
                            // TODO
                            break;
                        break;
                    case "finishround":
                        break;
                    case "exchresource":
                        break;
                    case "exit":
                        break;
                    default:
                        System.out.println("Unknown command.");
                        break;
                }
                System.out.print("Type a command and hit enter: ");
            }
        } catch (InvalidSyntaxException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Asteroid getAsteroidByName(String name) {
        for (Asteroid asteroid : game.getMap().getAsteroids()) {
            if (asteroid.getName() != null && asteroid.getName().equals(name))
                return asteroid;
        }
        return null;
    }

    private static Settler getSettlerByName(String name) {
        for (Settler settler : game.getSettlers()) {
            if (settler.getName() != null && settler.getName().equals(name))
                return settler;
        }
        return null;
    }

    private static Steppable getSteppableByName(String name) {
        for (Steppable steppable : game.getSteppables()) {
            if (steppable.getName() != null && steppable.getName().equals(name))
                return steppable;
        }
        return null;
    }

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
}
