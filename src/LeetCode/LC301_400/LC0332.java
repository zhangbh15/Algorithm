package LeetCode.LC301_400;
import java.util.*;

/**
 * Reconstruct Itinerary
 *
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
 * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries,
 * you should return the itinerary that has the smallest lexical order when read as a single string.
 * For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class LC0332 {
    // Euler Circuit
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        if (tickets == null || tickets.size() == 0) {
            return res;
        }

        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            String departure = ticket.get(0);
            String arrival = ticket.get(1);
            if (!graph.containsKey(departure)) {
                graph.put(departure, new PriorityQueue<>());
            }
            graph.get(departure).offer(arrival);
        }

        search("JFK", graph, res);

        return res;
    }

    private void search(String cur, Map<String, PriorityQueue<String>> graph, List<String> res) {
        if (!graph.containsKey(cur)) {
            res.add(0, cur);
            return;
        }

        PriorityQueue<String> heap = graph.get(cur);
        while (!heap.isEmpty()) {
            String next = heap.poll();
            search(next, graph, res);
        }

        graph.remove(cur);
        res.add(0, cur);
    }



    public static void main(String[] args) {
        LC0332 so = new LC0332();
        List<List<String>> input = new ArrayList<>();
        input.add(Arrays.asList("JFK","SFO"));
        input.add(Arrays.asList("JFK","ATL"));
        input.add(Arrays.asList("SFO","ATL"));
        input.add(Arrays.asList("ATL","JFK"));
        input.add(Arrays.asList("ATL","SFO"));

        List<String> res = so.findItinerary(input);
        System.out.println(res);
    }
}
