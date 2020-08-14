package Amazon;

import java.util.*;

/**
 * Description
 * https://leetcode.com/discuss/interview-question/782606/
 */
public class LargestItemAssociation {
    public List<String> largestItemAssociation(List<PairString> itemAssociation) {
        // write your code here
        // cc
       UnionFind uf = new UnionFind(itemAssociation);
       for (PairString pair : itemAssociation) {
           if (!uf.find(pair.first, pair.second)) {
               uf.union(pair.first, pair.second);
           }
       }

       List<String> res = null;
       for (List<String> group : uf.groups.values()) {
           if (res == null || group.size() > res.size()) {
               res = group;
           } else if (group.size() == res.size()) {
               Collections.sort(res);
               Collections.sort(group);
               if (group.get(0).compareTo(res.get(0)) < 0) {
                   res = group;
               }
           }
       }

       Collections.sort(res);
       return res;
    }

    private static class UnionFind {
        final Map<String, String> parents;
        final Map<String, List<String>> groups;

        public UnionFind(List<PairString> pairs) {
            parents = new HashMap<>();
            groups = new HashMap<>();
            for (PairString pair : pairs) {
                String first = pair.first;
                String second = pair.second;
                if (!parents.containsKey(first)) {
                    parents.put(first, first);
                    groups.put(first, new ArrayList<>());
                    groups.get(first).add(first);
                }
                if (!parents.containsKey(second)) {
                    parents.put(second, second);
                    groups.put(second, new ArrayList<>());
                    groups.get(second).add(second);
                }
            }
        }

        private String getRoot(String str) {
            if (!parents.containsKey(str)) {
                return null;
            }

            String cur = str;
            while (!parents.get(cur).equals(cur)) {
                parents.put(str, parents.get(parents.get(str)));
                cur = parents.get(cur);
            }

            return cur;
        }

        public boolean find(String str1, String str2) {
            String root1 = getRoot(str1);
            String root2 = getRoot(str2);
            return Objects.equals(root1, root2);
        }

        public void union(String str1, String str2) {
            String root1 = getRoot(str1);
            String root2 = getRoot(str2);
            int size1 = groups.get(root1).size();
            int size2 = groups.get(root2).size();

            if (size1 < size2) {
                parents.put(root1, root2);
                groups.get(root2).addAll(groups.remove(root1));
            } else {
                parents.put(root2, root1);
                groups.get(root1).addAll(groups.remove(root2));
            }
        }
    }

    private static class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        LargestItemAssociation so = new LargestItemAssociation();
        final PairString p1 = new PairString("Item1", "Item2");
        final PairString p2 = new PairString("Item3", "Item4");
        final PairString p3 = new PairString("Item4", "Item5");
        final List<PairString> list = Arrays.asList(p1, p2, p3);

        final PairString p21 = new PairString("item3","item4");
        final PairString p22 = new PairString("item1","item2");
        final PairString p23 = new PairString("item5","item6");
        final PairString p24 = new PairString("item4","item5");
        final PairString p25 = new PairString("item2","item7");
        final PairString p26 = new PairString("item7","item8");
        final PairString p27 = new PairString("item2","item3");
        final PairString p28 = new PairString("item6","item7");
        final PairString p29 = new PairString("item0","item2");
        final List<PairString> list2 = Arrays.asList(p21, p22, p23, p24, p25, p26, p27, p28, p29);

        System.out.println(so.largestItemAssociation(list));
        System.out.println(so.largestItemAssociation(list2));
    }
}
