package control;

import map.Map;
import map.asteroid.*;
import map.entity.Robot;
import map.entity.Settler;
import map.entity.TeleportGate;
import utility.OutputFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Scanner input = new Scanner(System.in);
        while (running) {
            OutputFormatter.reset();
            System.out.println("Choose a use-case:");
            System.out.println("\t0. Exit");
            System.out.println("\t1. Settler mines (enough space)" +
                    "\n\t2. Settler mines (not enough space)" +
                    "\n\t3. Build teleport (enough resource)" +
                    "\n\t4. Build teleport (not enough resource)" +
                    "\n\t5. Place teleport (ok)" +
                    "\n\t6. Place teleport (no teleport gate in storage)" +
                    "\n\t7. Place teleport (teleport already placed on asteroid)" +
                    "\n\t8. Test Drill Normal Asteroid Drilled" +
                    "\n\t9. Test Drill Normal Asteroid" +
                    "\n\t10. Test Drill Normal Asteroid Perihelion" +
                    "\n\t11. Test Drill Radioactive Asteroid Perihelion Settler" +
                    "\n\t12. Test Drill Radioactive Asteroid Perihelion Robot" +
                    "\n\t13. Test Drill Sublimable Asteroid Perihelion" +
                    "\n\t14. Test Resource Placeback" +
                    "\n\t15. Test Move" +
                    "\n\t16. Test Generate Sunflare" +
                    "\n\t17. Test Build Robot (enough resources)" +
                    "\n\t18. Test Build Robot (not enough resources)");
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
                case 3:
                    Test_Build_Teleport_Has_Resources();
                    break;
                case 4:
                    Test_Build_Teleport_No_Resources();
                    break;
                case 5:
                    Test_Place_Teleport_Ok();
                    break;
                case 6:
                    Test_Place_Teleport_No_Teleport();
                    break;
                case 7:
                    Test_Place_Teleport_Already_Exists();
                    break;
                case 8:
                    Test_Drill_Normal_Asteroid_Drilled();
                    break;
                case 9:
                    Test_Drill_Normal_Asteroid();
                    break;
                case 10:
                    Test_Drill_Normal_Asteroid_Perihelion();
                    break;
                case 11:
                    Test_Drill_Radioactive_Asteroid_Perihelion_Settler();
                    break;
                case 12:
                    Test_Drill_Radioactive_Asteroid_Perihelion_Robot();
                    break;
                case 13:
                    Test_Drill_Sublimable_Asteroid_Perihelion();
                    break;
                case 14:
                    Test_Resource_Placeback();
                    break;
                case 15:
                    Test_Move();
                    break;
                case 16:
                    Test_Generate_Sunflare();
                    break;
                case 17:
                    Test_Build_Robot_Has_Resources();
                    break;
                case 18:
                    Test_Build_Robot_No_Resources();
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
        Settler s = new Settler(new Game()); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setName("NGC-1304");
        a.setInPerihelion(false);
        a.setSurfaceThickness(0);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Normal_Asteroid(){
        System.out.println("Drill Normal Asteroid Perihelion:\n\n");
        Settler s = new Settler(new Game()); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setName("NGC-1304");
        a.setInPerihelion(false);
        a.setSurfaceThickness(2);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Normal_Asteroid_Perihelion(){
        System.out.println("Drill Normal Asteroid Perihelion:\n\n");
        Settler s = new Settler(null); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setName("NGC-1304");
        a.setInPerihelion(true);
        Iron i = new Iron();
        a.addResource(i);
        a.setSurfaceThickness(1);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Radioactive_Asteroid_Perihelion_Settler(){
        System.out.println("Drill Radioactive Asteroid Perihelion Settler:\n\n");
        Game g = new Game();
        Settler s = new Settler(g); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setName("NGC-1304");
        a.setInPerihelion(true);
        Uranium u = new Uranium();
        a.addResource(u);
        a.setSurfaceThickness(1);

        s.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        s.drill();
    }

    public static void Test_Drill_Radioactive_Asteroid_Perihelion_Robot(){
        System.out.println("Drill Radioactive Asteroid Perihelion Robot:\n\n");
        Robot r = new Robot(); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        r.setName("Robot");
        Asteroid a = new Asteroid();
        a.setName("NGC-1304");
        Asteroid b = new Asteroid();
        b.setName("NGC-1305");
        a.setInPerihelion(true);
        Uranium u = new Uranium();
        a.addResource(u);
        a.setSurfaceThickness(1);
        a.addNeighbour(b);

        r.move(a);
        OutputFormatter.setState(true); // Innen már számít a kimenet, így bekapcsoljuk az OutputFormattert.
        r.drill();
    }

    public static void Test_Drill_Sublimable_Asteroid_Perihelion(){
        System.out.println("Drill Sublimable Asteroid Perihelion:\n\n");
        Settler s = new Settler(new Game()); // Létrehozzuk és összekötögetjük a teszthez szükséges objektumokat.
        s.setName("Settler");
        Asteroid a = new Asteroid();
        a.setName("NGC-1304");
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
        Settler s = new Settler(new Game()); // Létrehozzuk  a teszthez szükséges objektumokat.
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
        Settler s = new Settler(new Game()); // Létrehozzuk  a teszthez szükséges objektumokat.
        s.setName("Settler");

        Asteroid [] a = new Asteroid[11];
        Uranium uranium = new Uranium();


        for (int i = 0; i < 11; i++) {
            a[i] = new Asteroid();
            a[i].setName("NGC-13" + i);
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

    public static void Test_Move(){
        System.out.println("Test_Move:\n\n");
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

        OutputFormatter.setState(true);
        s.move(a2);
    }

    public static void Test_Build_Teleport_Has_Resources() {
        System.out.println("Test_Build_Teleport_Has_Resources:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);

        a.setSurfaceThickness(0);
        a.setResource(new Iron());
        s.mine();
        a.setResource(new Iron());
        s.mine();
        a.setResource(new Ice());
        s.mine();
        a.setResource(new Uranium());
        s.mine();

        TeleportGate tg0 = new TeleportGate(); // to generate BillOfRes data

        OutputFormatter.setState(true);
        s.buildTeleport();
    }

    public static void Test_Build_Teleport_No_Resources() {
        System.out.println("Test_Build_Teleport_No_Resources:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);

        a.setSurfaceThickness(0);
        a.setResource(new Iron());
        s.mine();

        TeleportGate tg0 = new TeleportGate(); // to generate BillOfRes data

        OutputFormatter.setState(true);
        s.buildTeleport();
    }

    public static void Test_Place_Teleport_Ok() {
        System.out.println("Test_Place_Teleport_Ok:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);
        TeleportGate tg = new TeleportGate();
        s.addTeleport(tg);

        OutputFormatter.setState(true);
        s.placeTeleport();
    }

    public static void Test_Place_Teleport_No_Teleport() {
        System.out.println("Test_Place_Teleport_No_Teleport:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);

        OutputFormatter.setState(true);
        s.placeTeleport();
    }

    public static void Test_Place_Teleport_Already_Exists() {
        System.out.println("Test_Place_Teleport_Already_Exists:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);
        TeleportGate tg = new TeleportGate();
        a.setTeleportGate(tg);
        s.addTeleport(tg);

        OutputFormatter.setState(true);
        s.placeTeleport();
    }

    public static void Test_Resource_Placeback(){
        System.out.println("Test_Resource_Placeback:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("settler");
        Asteroid a1 = new Asteroid();
        a1.addResource(new Iron());
        Asteroid a2 = new Asteroid();
        s.setAsteroid(a1);
        a1.acceptEntity(s);
        s.mine();
        s.move(a2);
        a2.setSurfaceThickness(0);
        a2.setName("a2");
        OutputFormatter.setState(true);
        s.mine();
    }

    public static void Test_Generate_Sunflare() {
        System.out.println("Test_Generate_Sunflare:\n");
        Game g = new Game();
        Map m = g.getMap();

        OutputFormatter.setState(true);
        m.sunflare();
    }

    public static void Test_Build_Robot_Has_Resources() {
        System.out.println("Test_Build_Robot_Has_Resources:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);

        a.setSurfaceThickness(0);
        a.setResource(new Iron());
        s.mine();
        a.setResource(new Coal());
        s.mine();
        a.setResource(new Uranium());
        s.mine();

        Robot r = new Robot(); // to generate BillOfRes data

        OutputFormatter.setState(true);
        s.buildRobot();
    }

    public static void Test_Build_Robot_No_Resources() {
        System.out.println("Test_Build_Robot_No_Resources:\n");
        Game g = new Game();
        Settler s = new Settler(g);
        s.setName("s");
        Asteroid a = new Asteroid();
        a.setName("a1");
        s.setAsteroid(a);

        a.setSurfaceThickness(0);
        a.setResource(new Iron());
        s.mine();

        Robot r = new Robot(); // to generate BillOfRes data

        OutputFormatter.setState(true);
        s.buildRobot();
    }
}
