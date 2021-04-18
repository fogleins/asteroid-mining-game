package control;

public class Test {
    private static boolean automaticPerihelion = true;

    public static void setAutomaticPerihelionChange(boolean value){
        automaticPerihelion = value;
    }

    public static boolean getAutomaticPerihelion(){
        return automaticPerihelion;
    }
}
