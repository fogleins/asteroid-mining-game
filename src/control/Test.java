package control;

public class Test {
    private static boolean automaticPerihelion = true;

    public static boolean isInitiazePhase() {
        return initiazePhase;
    }

    public static void setInitiazePhase(boolean initiazePhase) {
        Test.initiazePhase = initiazePhase;
    }

    private static boolean initiazePhase = true;

    public static void setAutomaticPerihelionChange(boolean value){
        automaticPerihelion = value;
    }

    public static boolean getAutomaticPerihelion(){
        return automaticPerihelion;
    }
}
