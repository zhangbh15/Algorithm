package LeetCode;
import java.util.*;

/**
 * Router Longest Prefix Matching
 *
 * A network router takes incoming packet from network, and forwards it to the corresponding outgoing port.
 * It uses a routing table to make the forward decision.
 * Each routing table entries contains a prefix (you can think it as a bit array) and a port number,
 * and then it takes the entry that has the longest prefix match with the packet (also a bit array) ro get the target port from.
 *
 * Example:
 * 000      3
 * 10       4
 * 1        5
 * no-match 8
 *
 * incoming Packet-Port
 * 00010    3
 * 1001     4
 * 111      5
 * 001010  8
 *
 * You are given a list of entries:
 * class Entry {
 *     boolean[] prefix;
 *     int port;
 * }
 *
 * implement the two methods:
 * void build(List<Entry> entries);
 * int route(boolean[] packet;
 */
class Entry {
    boolean[] prefix;
    int port;
}

class TrieNode {
    boolean val;
    int port;
    TrieNode[] children;

    public TrieNode(boolean b) {
        val = b;
        port = -1;
        children = new TrieNode[2];
    }
}

public class RouterPrefixMatching {
    private final TrieNode root;
    private static final int DEFAULT_PORT = 8;

    public RouterPrefixMatching() {
        root = new TrieNode(false);
    }

    public void build(List<Entry> entries) {
        if (entries == null || entries.size() == 0) {
            return;
        }

        for (Entry e : entries) {
            TrieNode cur = root;
            for (boolean b : e.prefix) {
                int idx = b ? 1 : 0;
                if (cur.children[idx] == null) {
                    cur.children[idx] = new TrieNode(b);
                }
                cur = cur.children[idx];
            }
            cur.port = e.port;
        }
    }
    public int route(boolean[] packet) {
        if (packet == null || packet.length == 0) {
            return DEFAULT_PORT;
        }

        TrieNode cur = root;
        int port = DEFAULT_PORT;

        for (boolean b : packet) {
            int idx = b ? 1 : 0;
            if (cur.children[idx] == null) {
                break;
            }

            cur = cur.children[idx];
            if (cur.port != -1) {
                port = cur.port;
            }
        }
        return port;
    }
}
