package control;

import map.asteroid.Asteroid;
import map.asteroid.Uranium;
import map.entity.Settler;
import utility.OutputFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Example Test:\nAsteroid drilled in Perihelion\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Alexa");
        Asteroid a = new Asteroid();
        a.setInPerihelion(true);
        Uranium u = new Uranium();
        a.addResource(u);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }
}
