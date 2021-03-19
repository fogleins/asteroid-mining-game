package utility;

public class OutputFormatter {
    static int level = 0;

    static boolean state = true;

    public static void setState(boolean state) {
        OutputFormatter.state = state;
    }

    public static void OutputCall(String s) {
        String res = "";
        for (int i = 0; i < level; i++) {
            res += "\t";
        }
        res += s;
        System.out.println(res);
        level++;
    }

    public static void OutputReturn(String s) {
        level--;
        String res = "";
        for (int i = 0; i < level; i++) {
            res += "\t";
        }
        res += s;
        System.out.println(res);
    }
}
