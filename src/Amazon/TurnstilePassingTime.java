package Amazon;

import java.util.*;

/**
 * https://docs.google.com/document/d/1qLzyFxP9112Z_YfcdulyeEPreanEJNlSyEQI78EaK4o/edit#
 */
public class TurnstilePassingTime {
    public List<Integer> getTimes(int numCustomers, List<Integer> arrTime,
                                  List<Integer> direction) {
        List<Integer> res = new ArrayList<>();
        if (arrTime == null || arrTime.size() == 0 ||
                direction == null || direction.size() == 0 ||
                arrTime.size() != numCustomers || direction.size() != numCustomers) {
            return res;
        }

        Queue<Integer> enter = new LinkedList<>();
        Queue<Integer> exit = new LinkedList<>();
        for (int i = 0; i < numCustomers; i++) {
            res.add(-1); // Fill the res list with invalid times for further updates.

            if (direction.get(i) == 0) { // enter
                enter.offer(i);
            } else if (direction.get(i) == 1) {
                exit.offer(i);
            } else {
                throw new IllegalArgumentException("Invalid direction.");
            }
        }

        int lastEnter = -2;
        int lastExit = -2;

        while (!enter.isEmpty() && !exit.isEmpty()) {
            if (arrTime.get(enter.peek()) <= lastEnter + 1) {
                res.set(enter.poll(), ++lastEnter);
            } else if (arrTime.get(exit.peek()) <= lastExit + 1) {
                res.set(exit.poll(), ++lastExit);
            } else {
                int lastPass = Math.max(lastEnter, lastExit);

                if (arrTime.get(exit.peek()) <= arrTime.get(enter.peek())) {
                    int idx = exit.poll();
                    res.set(idx, Math.max(lastPass + 1, arrTime.get(idx)));
                    lastExit = res.get(idx);
                } else {
                    int idx = enter.poll();
                    res.set(idx, Math.max(lastPass + 1, arrTime.get(idx)));
                    lastEnter = res.get(idx);
                }
            }
        }

        while (!enter.isEmpty()) {
            int idx = enter.poll();
            lastEnter = Math.max(lastEnter, lastExit);
            res.set(idx, Math.max(lastEnter + 1, arrTime.get(idx)));
            lastEnter = res.get(idx);
        }

        while (!exit.isEmpty()) {
            int idx = exit.poll();
            lastExit = Math.max(lastEnter, lastExit);
            res.set(idx, Math.max(lastExit + 1, arrTime.get(idx)));
            lastExit = res.get(idx);
        }

        return res;
    }

    public static void main (String[] args){
        TurnstilePassingTime solution = new TurnstilePassingTime();
        List<Integer> res;
        //[2, 0, 1, 5]
        res = solution.getTimes(4, Arrays.asList(0, 0, 1, 5), Arrays.asList(0, 1, 1, 0));
        System.out.println(res);

        //[4, 0, 5, 1, 6, 2, 7, 3, 10, 22, 20, 21]
        res = solution.getTimes(12, Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 10, 20, 20, 21), Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1));
        System.out.println(res);

        //[0, 2, 1, 4, 3]
        res = solution.getTimes(5, Arrays.asList(0, 1, 1, 3, 3), Arrays.asList(0, 1, 0, 0, 1));
        System.out.println(res);

        //[4, 5, 0, 1, 2, 6, 3]
        res = solution.getTimes(7, Arrays.asList(0, 0, 0, 0, 1, 1, 3), Arrays.asList(0, 0, 1, 1, 1, 0, 1));
        System.out.println(res);

        //[3, 4, 0, 1, 2, 5, 6]
        res = solution.getTimes(7, Arrays.asList(0, 0, 0, 0, 1, 1, 4), Arrays.asList(0, 0, 1, 1, 1, 0, 1));
        System.out.println(res);
    }
}
