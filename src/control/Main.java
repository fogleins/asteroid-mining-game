package control;

import map.Map;
import map.asteroid.*;
import map.entity.Entity;
import map.entity.Robot;
import map.entity.Settler;
import utility.OutputFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            System.out.println("Choose a use-case:");
            System.out.println("\t0. Exit");
            System.out.println("\t1. Settler mines (enough space)" +
                    "\n\t2. Settler mines (not enough space)"); //TODO: minden use-case hozzáadása
            System.out.println("\n> ");
            int selection = input.nextInt();
            switch (selection) {
                case 0:
                    running = false;
                    break;
                case 1:
                    Test_Settler_Mines_Enough_Space();
                    break;
                case 2:
                    Test_Settler_Mines_Not_Enough_Space();
                    break;
                default:
                    System.out.println("Invalid selection!");
                    continue;
            }
            if (running) {
                System.out.println("\nPress enter to continue.");
                try { System.in.read(); }
                catch (Exception e) {/*this is fine (:*/}
            }
        }
        input.close();
        System.out.println("\nBye!");
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

    public static void Test_Settler_Mines_Enough_Space() {
        System.out.println("Settler Mines With Enough Space In Storage:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(null); // Létrehozzuk  a teszthez szükséges objektumokat.
        s.setName("Settler");

        Asteroid a = new Asteroid();
        a.setName("Asteroid");
        Uranium uranium = new Uranium();

        a.addResource(uranium);
        a.setSurfaceThickness(0);

        s.move(a);

        OutputFormatter.setState(true);
        s.mine();
    }

    public static void Test_Settler_Mines_Not_Enough_Space() {
        System.out.println("Settler Mines Without Enough Space In Storage:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Settler s = new Settler(null); // Létrehozzuk  a teszthez szükséges objektumokat.
        s.setName("Settler");

        Asteroid [] a = new Asteroid[11];
        Uranium uranium = new Uranium();


        for (int i = 0; i < 10; i++) {
            a[i] = new Asteroid();
            a[i].addResource(uranium);
            a[i].setSurfaceThickness(0);
        }

        //A telepes raktere be kell hogy telljen
        for (int i = 0; i < 10; i++) {
            s.move(a[i]);
            s.mine();
        }

        s.move(a[10]);

        OutputFormatter.setState(true);
        s.mine();
    }

    public static void Test_Map_Initialization(){
        System.out.println("Test_Map_Initialization:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        BaseAsteroid ba = new BaseAsteroid();
        ba.setName("ba");
        Map map = new Map(ba);
    }

    public static void Test_Move(){
        System.out.println("Test_Move:\n\n");
        OutputFormatter.setState(false); // Kikapcsoljuk az OutputFormattert, hogy ne írjon ki lényegtelen információkat.
        Asteroid a1 = new Asteroid();
        a1.setName("a1");
        Asteroid a2 = new Asteroid();
        a2.setName("a2");
        a1.addNeighbour(a2);
        a2.addNeighbour(a1);
        Settler s = new Settler(null);
        s.setName("telepes");
        s.setAsteroid(a1);
        a1.acceptEntity(s);
        s.move(a2);
    }








}
