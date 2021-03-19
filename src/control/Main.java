package control;

import map.asteroid.Asteroid;
import map.asteroid.Ice;
import map.asteroid.Iron;
import map.asteroid.Uranium;
import map.entity.Robot;
import map.entity.Settler;
import utility.OutputFormatter;

public class Main {
    public static void main(String[] args) {
        Test_Drill_Radioactive_Asteroid_Perihelion_Settler();
    }

    public static void Test_Drill_Normal_Asteroid_Drilled(){
        System.out.println("Drill Normal Asteroid Perihelion:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(null); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setInPerihelion(false);
        a.setSurfaceThickness(0);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Normal_Asteroid(){
        System.out.println("Drill Normal Asteroid Perihelion:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(null); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setInPerihelion(false);
        a.setSurfaceThickness(2);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Normal_Asteroid_Perihelion(){
        System.out.println("Drill Normal Asteroid Perihelion:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(null); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setInPerihelion(true);
        Iron i = new Iron();
        a.addResource(i);
        a.setSurfaceThickness(1);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Radioactive_Asteroid_Perihelion_Settler(){
        System.out.println("Drill Radioactive Asteroid Perihelion:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Game g = new Game();
        Settler s = new Settler(g); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setInPerihelion(true);
        Uranium u = new Uranium();
        a.addResource(u);
        a.setSurfaceThickness(1);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Radioactive_Asteroid_Perihelion_Robot(){
        System.out.println("Drill Radioactive Asteroid Perihelion:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Robot r = new Robot(); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        r.setName("Robot");
        Asteroid a = new Asteroid();
        a.setInPerihelion(true);
        Uranium u = new Uranium();
        a.addResource(u);
        a.setSurfaceThickness(1);

        r.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        r.drill();
    }

    public static void Test_Drill_Sublimable_Asteroid_Perihelion(){
        System.out.println("Drill Sublimable Asteroid Perihelion:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(null); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setInPerihelion(true);
        Ice i = new Ice();
        a.addResource(i);
        a.setSurfaceThickness(1);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }
}
