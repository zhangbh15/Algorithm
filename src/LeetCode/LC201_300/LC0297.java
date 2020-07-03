package LeetCode.LC201_300;
import LeetCode.util.TreeNode;
import java.util.*;

/**
 * Serialize and Deserialize Binary Tree
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that
 * it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
public class LC0297 {
    public static String serializeBetterBFS(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        boolean lastLevel = false;

        while (!que.isEmpty()) {
            if (!lastLevel) {
                int size = que.size();
                lastLevel = true;

                for (int i = 0; i < size; i++) {
                    TreeNode cur = que.poll();

                    if (cur != null) {
                        que.offer(cur.left);
                        que.offer(cur.right);
                        sb.append(cur.val);
                        sb.append('/');

                        if (lastLevel && (cur.left != null || cur.right != null)) {
                            lastLevel = false;
                        }
                    } else {
                        sb.append("null/");
                    }
                }
            } else {
                break;
            }
        }

        return sb.toString();
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) { // BFS
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);

        while (!que.isEmpty()) {
            TreeNode cur = que.poll();

            if (cur != null) {
                que.offer(cur.left);
                que.offer(cur.right);
                sb.append(cur.val);
                sb.append('/');
            } else {
                sb.append("null/");
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) { // BFS
        if (data == null) {
            return null;
        }

        String[] vals = data.split("/");
        Queue<TreeNode> que = new LinkedList<>();

        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        que.offer(root);

        for (int i = 1; i < vals.length; i++) {
            TreeNode cur = vals[i].equals("null") ? null
                    : new TreeNode(Integer.parseInt(vals[i]));

            if (!que.isEmpty() && i % 2 == 1) {
                que.peek().left = cur;
            } else if (!que.isEmpty()){
                que.poll().right = cur;
            }

            if (cur != null) {
                que.offer(cur);
            }
        }

        return root;
    }

    // Encodes a tree to a single string.
    public String serializeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        serialHelper(root, sb);
        return sb.toString();
    }

    private void serialHelper(TreeNode cur, StringBuilder sb) {
        if (cur == null) {
            sb.append("null/");
            return;
        }

        sb.append(cur.val);
        sb.append("/");

        serialHelper(cur.left, sb);
        serialHelper(cur.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeDFS(String data) {
        if (data == null) {
            return null;
        }

        String[] vals = data.split("/");
        List<String> valsList = new LinkedList<>();
        Collections.addAll(valsList, vals);

        return deserialHelper(valsList);
    }

    private TreeNode deserialHelper(List<String> vals) {
        if (vals.isEmpty()) {
            return null;
        }

        String val = vals.remove(0);
        if (val.equals("null")) {
            return null;
        } else {
            TreeNode cur = new TreeNode(Integer.parseInt(val));
            cur.left = deserialHelper(vals);
            cur.right = deserialHelper(vals);

            return cur;
        }
    }

    public String serializeStack(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();

        push(stack, root, sb);

        while (!stack.empty()) {
            pop(stack, sb);
        }

        return sb.toString();
    }

    private void push(Stack<TreeNode> stack, TreeNode root, StringBuilder sb) {
        TreeNode cur = root;
        while (true) {
            sb.append(cur == null ? "null/" : cur.val + "/");
            stack.push(cur);

            if (cur != null) {
                cur = cur.left;
            } else {
                 break;
            }
        }
    }

    private void pop(Stack<TreeNode> stack, StringBuilder sb) {
        TreeNode popNode = stack.pop();
        if (popNode != null) {
            push(stack, popNode.right, sb);
        }
    }

    public TreeNode deserializeStack(String data) {
        if (data == null) {
            return null;
        }

        String[] vals = data.split("/");
        TreeNode cur = new TreeNode(Integer.parseInt(vals[0]));
        TreeNode root = cur;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cur);

        int i = 1;
        while (i < vals.length) {
            while (i < vals.length && !vals[i].equals("null")) {
                cur = new TreeNode(Integer.parseInt(vals[i++]));
                stack.peek().left = cur;
                stack.push(cur);
            }

            while (i < vals.length && vals[i].equals("null")){
                i++;
                if (stack.empty()) {
                    break;
                } else {
                    cur = stack.pop();
                }
            }

            if (i < vals.length) {
                cur.right = new TreeNode(Integer.parseInt(vals[i++]));
                cur = cur.right;
                stack.push(cur);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        LC0297 so = new LC0297();
        Integer[] array = new Integer[] {1, 2, 3, 4, 5, null, null};
        TreeNode root = new TreeNode(array);
        String dataBFS = so.serialize(root);
        String dataBetterBFS = serializeBetterBFS(root);
        String dataDFS = so.serializeDFS(root);
        String dataStack = so.serializeStack(root);

        System.out.println(dataBFS);
        System.out.println(dataBetterBFS);
        System.out.println(dataDFS);
        System.out.println(dataStack);

        System.out.println(deserialize(dataBFS).toArrayList());
        System.out.println(deserialize(dataBetterBFS).toArrayList());
        System.out.println(so.deserializeDFS(dataDFS).toArrayList());
        System.out.println(so.deserializeStack(dataStack).toArrayList());
    }
}
