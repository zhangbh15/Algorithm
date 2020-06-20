package LeetCode.LC401_500;
import LeetCode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Serialize and Deserialize BST
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
public class LC0449 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        serialHelper(root, sb);
        return sb.toString();
    }

    private void serialHelper(TreeNode cur, StringBuilder sb) {
        if (cur == null) {
            return;
        }

        sb.append(cur.val);
        sb.append("/");
        serialHelper(cur.left, sb);
        serialHelper(cur.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }

        String[] vals = data.split("/");
        List<Integer> valsList = new LinkedList<>();
        for (String str : vals) {
            valsList.add(Integer.parseInt(str));
        }
        return deserialHelper(Integer.MIN_VALUE, Integer.MAX_VALUE,valsList);
    }

    private TreeNode deserialHelper(int floor, int ceiling, List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }

        int val = data.get(0);
        if (val < floor || val > ceiling) {
            return null;
        }

        TreeNode root = new TreeNode(val);
        data.remove(0);
        root.left = deserialHelper(floor, val, data);
        root.right = deserialHelper(val, ceiling, data);
        return root;
    }

    public static void main(String[] args) {
        LC0449 so = new LC0449();
        String str = "3/1/5/null/2/4/";
        TreeNode root = TreeNode.deserialize(str);
        System.out.println(root.toArrayList());
        String data = so.serialize(root);
        System.out.println(data);
        System.out.println(so.deserialize(data).toArrayList());
    }
}
