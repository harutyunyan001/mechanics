package mechanics_project;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;


public class SpringArray {

    private static final String OPEN_BRACKET = "[";
    private static final String CLOSE_BRACKET = "]";
    private static final String OPEN_BRACE = "{";
    private static final String CLOSE_BRACE = "}";
    private static final List<String> OPENERS = List.of(OPEN_BRACE, OPEN_BRACKET);
    private static final List<String> CLOSERS = List.of(CLOSE_BRACE, CLOSE_BRACKET);

    private static int currentIndex = 0;
    private static List<Spring> springsList;

    public SpringArray() {}

    public static String getOpenBracket() {
        return OPEN_BRACKET;
    }

    public static String getCloseBracket() {
        return CLOSE_BRACKET;
    }

    public static String getOpenBrace() {
        return OPEN_BRACE;
    }

    public static String getCloseBrace() {
        return CLOSE_BRACE;
    }

    public static List<String> getOPENERS() {
        return OPENERS;
    }

    public static List<String> getCLOSERS() {
        return CLOSERS;
    }

    public static int getCurrentIndex() {
        return currentIndex;
    }

    public static List<Spring> getSpringsList() {
        return springsList;
    }

    public static Spring equivalentSpring(String springExpr) {
        springsList = new ArrayList<>();
        ArrayList<List<Integer>> indexPairs = pairedIndex(springExpr);
        return null;
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        springsList = Arrays.stream(springs).collect(Collectors.toList());
        ArrayList<List<Integer>> indexPairs = pairedIndex(springExpr);
        return null;
    }

    protected static ArrayList<List<Integer>> pairedIndex(String springExpr) {
        ArrayList<List<Integer>> indexes = new ArrayList<>();

        for (int i = 0; i < springExpr.length(); i++) {
            if (OPENERS.contains(Character.toString(springExpr.charAt(i)))) {
                indexes.add(List.of(i, closerIndex(springExpr, i)));
            }
        }
        return indexes;
    }

    private static int closerIndex(String springExpr, int index) {

        String current = Character.toString(springExpr.charAt(index));
        String close = current.equals(OPEN_BRACE) ? CLOSE_BRACE : CLOSE_BRACKET;
        String open = current.equals(OPEN_BRACE) ? OPEN_BRACE : OPEN_BRACKET;


        Queue<String> queue = new ArrayDeque<>();
        queue.add(current);

        for (int i = index + 1; i < springExpr.length(); i++) {
            String currentChar = Character.toString(springExpr.charAt(i));

            if (currentChar.equals(close)) {
                queue.poll();
            } else if (currentChar.equals(open)) {
                queue.add(currentChar);
            }
            if (queue.size() == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid Spring Expression");
    }




    @Override
    public String toString() {
        return "SpringArray{}";
    }

}