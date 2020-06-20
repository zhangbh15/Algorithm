package LeetCode.LC301_400;

import java.util.*;

/**
 * Evaluate Division
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers.
 * If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries ,
 * where equations.size() == values.size(), and the values are positive.
 * This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 *
 *
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class LC0399 {
    // TODO: use doubly linked list and hashMap. O(1) based on bucket.
    class V {
        public V parent;
        public double val;
        public int size;

        public V() {
            parent = this;
            val = 1.0;
            size = 1;
        }
    }

    class UnionFind {
        private final Map<String, V> map;

        public UnionFind() {
            this.map = new HashMap<>();
        }

        private V getRoot(V p) {
            V cur = p;
            double d = 1.0;
            while (cur.parent != cur) {
                d *= cur.val;
                cur.parent = cur.parent.parent;
                cur.val *= cur.parent.val;
                cur = cur.parent;
            }

            p.parent = cur;
            p.val = d;
            return cur;
        }

        public boolean find(V p, V q) {
            return getRoot(p) == getRoot(q);
        }

        public void union(V p, V q, double pOverQ) {
            V rootP = getRoot(p);
            V rootQ = getRoot(q);
            if (rootP.size < rootQ.size) {
                rootP.parent = rootQ;
                rootP.val = q.val / p.val / pOverQ;
                rootQ.size += rootP.size;
            } else {
                rootQ.parent = rootP;
                rootQ.val = p.val / q.val * pOverQ;
                rootP.size += rootQ.size;
            }
        }

        public V addV(String str) {
            if (!map.containsKey(str)) {
                V newV = new V();
                map.put(str, newV);
            }
            return map.get(str);
        }

        public V getV(String str) {
            return map.get(str);
        }

        public double calc(V p, V q) {
            return q.val / p.val;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // cc
        UnionFind uf = new UnionFind();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];

            V p = uf.addV(a);
            V q = uf.addV(b);

            if (!uf.find(p, q)) {
                uf.union(p, q, val);
            }
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);

            V p = uf.getV(a);
            V q = uf.getV(b);
            if (p == null || q == null || !uf.find(p, q)) {
                res[i] = -1.0;
                continue;
            }

            res[i] = uf.calc(p, q);
        }
        return res;
    }
}
