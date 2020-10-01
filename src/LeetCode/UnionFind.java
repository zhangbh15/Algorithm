package LeetCode;

class V {
    int id;
    int size;
    V parent;
}

public class UnionFind {
    public UnionFind() {

    }

    public void union(V p, V q) {
        V rootP = getRoot(p);
        V rootQ = getRoot(q);
        if (rootP.size < rootQ.size) {
            rootP.parent = rootQ;
            rootQ.size += rootP.size;
        } else {
            rootQ.parent = rootP;
            rootP.size += rootQ.size;
        }
    }

    public boolean find(V p, V q) {
        return getRoot(p) == getRoot(q);
    }

    private V getRoot(V p) {
        V cur = p;
        while (cur.parent != cur) {
            cur.parent = cur.parent.parent;
            cur = cur.parent;
        }
        p.parent = cur;
        return cur;
    }
}
