package LeetCode;

import java.util.*;


/**
 * There are two buckets with volume1 and volume2.
 * There is a water tap that can provide infinite water supply.
 * For each move, there are 3 kinds of actions:
 *      1. Fill up one bucket.
 *      2. Pour water from one bucket to the other, until the source bucket
 *          is empty or the target bucket is full.
 *      3. Pour out all the water in a bucket.
 * Given two empty buckets and a target volume of water, find the least move
 * to get the target volume in either bucket.
 * If it's impossible to get the target volume, return -1;
 */
public class TwoWaterBuckets {
    public int leastMove(int volume1, int volume2, int target) {
        if (volume1 <= 0 || volume2 <= 0 ||
                target < 0 || target >= Math.max(volume1, volume2)) {
            return -1;
        }

        // Queue<[waterInBucket1, waterInBucket2]>
        Queue<List<Integer>> que = new LinkedList<>();
        que.offer(Arrays.asList(0, 0));
        Set<List<Integer>> visited = new HashSet<>();

        int move = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                List<Integer> cur = que.poll();
                if (cur.get(0) == target || cur.get(1) == target) {
                    return move;
                }

                for (List<Integer> next : transform(cur, volume1, volume2)) {
                    if (visited.add(next)) {
                        que.offer(next);
                    }
                }
            }

            move++;
        }

        return -1;
    }

    /**
     * There are 6 possible actions:
     * tap to bucket1, bucket2 to bucket1,
     * tap to bucket2, bucket1 to bucket2,
     * empty bucket1, empty bucket2
     */
    private List<List<Integer>> transform(List<Integer> cur, int volume1, int volume2) {
        List<List<Integer>> res = new ArrayList<>();
        int cur1 = cur.get(0);
        int cur2 = cur.get(1);

        // tap to bucket1, bucket2 to bucket1
        if (cur1 < volume1) {
            res.add(Arrays.asList(volume1, cur2));
            if (cur2 > 0) {
                int diff = Math.min(volume1 - cur1, cur2);
                res.add(Arrays.asList(cur1 + diff, cur2 - diff));
            }
        }

        // tap to bucket2, bucket1 to bucket2
        if (cur2 < volume2) {
            res.add(Arrays.asList(cur1, volume2));
            if (cur1 > 0) {
                int diff = Math.max(volume2 - cur2, cur1);
                res.add(Arrays.asList(cur1 - diff, cur2 + diff));
            }
        }

        // empty bucket1, empty bucket2
        if (cur1 > 0) {
            res.add(Arrays.asList(0, cur2));
        }
        if (cur2 > 0) {
            res.add(Arrays.asList(cur1, 0));
        }

        return res;
    }



    /**
     * If the volume of each bucket is smaller than 2^16 - 1;
     * I can use bit operation to reduce the space.
     */
    public int leastMoveBit(int volume1, int volume2, int target) {
        if (volume1 <= 0 || volume2 <= 0 ||
                target < 0 || target >= Math.max(volume1, volume2)) {
            return -1;
        }

        // Queue<[waterInBucket1, waterInBucket2]>
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        Set<Integer> visited = new HashSet<>();
        int mask = 0xffff;

        int move = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; i++) {
                int cur = que.poll();
                int cur1 = (cur >> 16) & mask;
                int cur2 = cur & mask;

                if (cur1 == target || cur2 == target) {
                    return move;
                }

                for (int next : transformBit(cur1, cur2, volume1, volume2)) {
                    if (visited.add(next)) {
                        que.offer(next);
                    }
                }
            }

            move++;
        }

        return -1;
    }

    private List<Integer> transformBit(int cur1, int cur2, int volume1, int volume2) {
        List<Integer> res = new ArrayList<>();

        // tap to bucket1, bucket2 to bucket1
        if (cur1 < volume1) {
            res.add((volume1 << 16) | cur2);
            if (cur2 > 0) {
                int diff = Math.min(volume1 - cur1, cur2);
                res.add(((cur1 + diff) << 16) | (cur2 - diff));
            }
        }

        // tap to bucket2, bucket1 to bucket2
        if (cur2 < volume2) {
            res.add((cur1 << 16) | volume2);
            if (cur1 > 0) {
                int diff = Math.max(volume2 - cur2, cur1);
                res.add(((cur1 - diff) << 16 )| (cur2 + diff));
            }
        }

        // empty bucket1, empty bucket2
        if (cur1 > 0) {
            res.add(cur2);
        }
        if (cur2 > 0) {
            res.add(cur1 << 16);
        }

        return res;
    }



    public static void main(String[] args) {
        TwoWaterBuckets so = new TwoWaterBuckets();

        List<int[]> testCases = new ArrayList<>();
        // corner cases
        testCases.add(new int[] {0, 3, 2}); // expected -1
        testCases.add(new int[] {3, 0, 2}); // expected -1
        testCases.add(new int[] {1, 3, 5}); // expected -1
        // regular cases
        testCases.add(new int[] {1, 3, 0}); // expected 0
        testCases.add(new int[] {1, 3, 1}); // expected 1
        testCases.add(new int[] {1, 3, 2}); // expected 2
        testCases.add(new int[] {3, 5, 4}); // expected 6
        testCases.add(new int[] {3, 5, 2}); // expected 2

        for (int[] testCase : testCases) {
            int res = so.leastMoveBit(testCase[0], testCase[1], testCase[2]);
            System.out.println(res);
        }
    }
}
