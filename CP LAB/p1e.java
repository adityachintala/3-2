
/*

e) Write a JAVA Program to implement TREAP with its operations
Given an integer array nums, return the number of reverse pairs in the array.
A reverse pair is a pair (i,j) where 0<=i<j<nums.length and nums[i]>2*nums[j].

Example 1:
Input: nums=[1,3,2,3,1]
Output: 2

Example 2:
Input: nums=[2,4,3,5,1]
Output:3

Constraints:
1<=nums.length<=5*104
-2^31<=nums[i]<=2^31–1

*/

import java.util.Random;

class Solution {
    private static class Item {
        Double key;
        Double priority;
        long cnt;
        Item left, right;
    }

    private long cnt(Item item) {
        return (item != null) ? item.cnt : 0;
    }

    private void updateCnt(Item item) {
        if (item != null)
            item.cnt = 1 + cnt(item.left) + cnt(item.right);
    }

    private Item[] split(Item item, double key) {
        if (item == null) return new Item[] {null, null};
        Item[] ret;
        if (item.key < key) {
            ret = split(item.right, key);
            item.right = ret[0];
            ret[0] = item;
        } else {
            ret = split(item.left, key);
            item.left = ret[1];
            ret[1] = item;
        }
        updateCnt(item);
        return ret;
    }

    private Item merge(Item l, Item r) {
        if (l == null || r == null) return (l != null) ? l : r;
        Item ret;
        if (l.priority > r.priority) {
            l.right = merge(l.right, r);
            ret = l;
        } else {
            r.left = merge(l, r.left);
            ret = r;
        }
        updateCnt(ret);
        return ret;
    }

    private Item insert(Item root, Item item) {
        if (root == null) return item;
        Item ret;
        if (root.priority < item.priority) {
            Item[] res = split(root, item.key);
            item.left = res[0];
            item.right = res[1];
            ret = item;
        } else {
            if (root.key > item.key) {
                ret = insert(root.left, item);
                root.left = ret;
            } else {
                ret = insert(root.right, item);
                root.right = ret;
            }
        }
        updateCnt(ret);
        return ret;
    }

    public int reversePairs(int[] nums) {
        int n = nums.length, ans = 0;
        Random rand = new Random();
        Item root = new Item();
        root.priority = rand.nextDouble();
        root.key = nums[n - 1] + 0.0;
        for (int i = n - 2; i >= 0; i--) {
            Item[] res = split(root, (nums[i] + 0.0) / 2);
            ans += cnt(res[0]);
            root = merge(res[0], merge(new Item() {{priority=rand.nextDouble();key=nums[i]+0.0;}}, res[1]));
        }
        return ans;
    }
}

public class p1e {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        Solution sol = new Solution();
        System.out.println(sol.reversePairs(arr));
        sc.close();
    }
}
