/*
 * j) Write a JAVA Program to find the lowest common ancestor of a binary tree
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
between two nodes p and q as the lowest node in T that has both p and q as descendants (where
we allow a node to be a descendant of itself).”
Given the following binary tree: root = [3,5,1,6,2,0,8,null,null,7,4]
Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according
to the LCA definition.
Note:
All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
 */

import java.util.*;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
}

class TreeNode {
    public int data;
    public TreeNode left, right;
    public TreeNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class LCA {
    static TreeNode root;

    void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.remove();
            if (temp.left == null) {
                temp.left = new TreeNode(key);
                break;
            } else {
                q.add(temp.left);
            }
            if (temp.right == null) {
                temp.right = new TreeNode(key);
                break;
            } else {
                q.add(temp.right);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        LCA lca = new LCA();
        for (String s : str) {
            lca.insert(Integer.parseInt(s));
        }
        Solution sol = new Solution();
        TreeNode res = sol.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(3));
        System.out.println(res.data);
    }
}
