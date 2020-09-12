package LeetCode;

import java.util.*;

/**
 * Given two strings representing the original and target states of a parking lot,
 * find the least number of moves from the original state to the target state.
 *
 * Each character in the string represents a car parked at the spot.
 * An empty spot is represented by '#'.
 * Any other character represents a car.
 * There is only 1 empty spot in the parking lot.
 * The original and target states are of the same length.
 *
 * If it's impossible to get to the target state, return -1.
 *
 *
 * Example:
 * input:
 *      originalState = "AB#C"
 *      targetState = "C#AB"
 * output:
 *      3
 * explanation:
 *      "AB#C" --> "#BAC" --> "CBA#" --> "C#AB"
 */
public class MoveCars {
    public int leastMove(String originalState, String targetState) {
        if (originalState == null || targetState == null || originalState.length() != targetState.length()) {
            throw new IllegalArgumentException("Invalid input.");
        }

        Queue<String> que = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        que.offer(originalState);

        int move = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String cur = que.poll();
                if (cur.equals(targetState)) {
                    return move;
                }

                for (String next : convert(cur)) {
                    if (visited.add(next)) {
                        que.offer(next);
                    }
                }
            }

            move++;
        }

        return -1;
    }

    private List<String> convert(String cur) {
        char[] chars = cur.toCharArray();

        int emptyIdx = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '#') {
                emptyIdx = i;
                break;
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (i != emptyIdx) {
                swap(chars, i, emptyIdx);
                res.add(String.valueOf(chars));
                swap(chars, i, emptyIdx);
            }
        }

        return res;
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void main(String[] args) {
        MoveCars so = new MoveCars();

        List<String[]> testCases = new ArrayList<>();
        // corner cases
        testCases.add(new String[] {"", ""});   // expected -1
        testCases.add(new String[] {"", "AB#C"});// expected Exception
        testCases.add(new String[] {"#", ""});  // expected Exception
        // regular cases
        testCases.add(new String[] {"AB#C", "C#AB"});// expected 3
        testCases.add(new String[] {"A#", "#A"});   // expected 1
        testCases.add(new String[] {"#", "#"}); // expected 0
        testCases.add(new String[] {"#", "A"}); // expected -1

        for (String[] testCase : testCases) {
            try {
                int res = so.leastMove(testCase[0], testCase[1]);
                System.out.println(res);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
