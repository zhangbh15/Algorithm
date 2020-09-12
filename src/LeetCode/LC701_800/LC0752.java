package LeetCode.LC701_800;

import java.util.*;


/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
 * '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and
 * wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * he wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 *
 *
 *
 * Example 1:
 *
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".
 *
 *
 * Example 2:
 *
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".
 *
 *
 * Example 3:
 *
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.
 *
 *
 * Example 4:
 *
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target will not be in the list deadends.
 * target and deadends[i] consist of digits only.
 */
public class LC0752 {
    public int openLock(String[] deadends, String target) {
        if (deadends == null || target == null || target.length() != 4) {
            throw new IllegalArgumentException();
        }

        String start = "0000";
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        if (deads.contains(start)) {
            return -1;
        }

        Queue<String> que = new LinkedList<>();
        que.offer(start);
        Set<String> visited = new HashSet<>();

        int turns = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                String cur = que.poll();

                if (cur.equals(target)) {
                    return turns;
                }

                for (String next : convert(cur)) {
                    if (!deads.contains(next) && visited.add(next)) {
                        que.offer(next);
                    }
                }
            }

            turns++;
        }

        return -1;
    }

    private List<String> convert(String cur) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char temp = chars[i];

            chars[i] = (char) (temp + 1);
            if (chars[i] > '9') {
                chars[i] = '0';
            }
            res.add(String.valueOf(chars));

            chars[i] = (char) (temp - 1);
            if (chars[i] < '0') {
                chars[i] = '9';
            }
            res.add(String.valueOf(chars));

            chars[i] = temp;
        }

        return res;
    }

    public static void main(String[] args) {
        LC0752 so = new LC0752();
        String[] deadends = {"8888"};
        String target = "0009";

        int res = so.openLock(deadends, target);
        System.out.println(res);
    }
}
