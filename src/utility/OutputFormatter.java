package utility;

public class OutputFormatter {
    static int level = 0;

    static boolean state = true;

    public static void setState(boolean state) {
        OutputFormatter.state = state;
    }

    public static void reset() {
        level = 0;
        state = false;
    }

    public static void OutputCall(String s) {
        if (state) {
            String res = "";
            for (int i = 0; i < level; i++) {
                res += "|   ";
            }
            res += s;
            System.out.println(res);
            level++;
        }
    }

    public static void OutputReturn(String s) {
        if (state) {
            level--;
            String res = "";
            for (int i = 0; i < level; i++) {
                res += "|   ";
            }
            res += s;
            System.out.println(res);
        }
    }
}
