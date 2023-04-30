/*

d) Write a JAVA Program to implement a segment tree with its operations
In Hyderabad after a long pandemic gap, the Telangana Youth festival Is Organized at HITEX.
In HITEX, there are a lot of programs planned. During the festival in order to maintain the rules 
of Pandemic, they put a constraint that one person can only attend any one of the programs in 
one day according to planned days. Now it"s your aim to implement the "Solution" class in such 
a way that you need to return the maximum number of programs you can attend according to 
given constraints.

Explanation:
You have a list of programs "p" and days "d", where you can attend only one program on one day.
Programs [p] = [first day, last day], p is the program's first day and the last day.

Input Format:
Line-1: An integer N, number of programs.
Line-2: N comma separated pairs, each pair(f_day, l_day) is separated by space.

Output Format:
An integer, the maximum number of programs you can attend.

Sample Input-1:
4
1 2,2 4,2 3,2 2
Sample Output-1:
4

Sample Input-2:
6
1 5,2 3,2 4,2 2,3 4,3 5
Sample Output-2:
5

*/

import java.util.*;
import java.io.*;

class SegmentTreeNode {
    int start, end, val;
    SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        this.val = start;
    }
}

class Solution {
    SegmentTreeNode root;

    public int maxEvents(int[][] events) {
        if (events == null || events.length == 0) return 0;
        Arrays.sort(events, (a, b) -> a[1] - b[1] != 0 ? a[1] - b[1] : a[0] - b[0]);
        int lastDay = events[events.length - 1][1];
        int firstDay = Arrays.stream(events).mapToInt(e -> e[0]).min().orElse(Integer.MAX_VALUE);
        root = buildSegmentTree(firstDay, lastDay);
        int count = 0;
        for (int[] event : events) {
            int earliestDay = query(root, event[0], event[1]);
            if (earliestDay != Integer.MAX_VALUE) {
                count++;
                update(root, earliestDay);
            }
        }
        return count;
    }

    private SegmentTreeNode buildSegmentTree(int start, int end) {
        if (start > end) return null;
        SegmentTreeNode node = new SegmentTreeNode(start, end);
        if (start != end) {
            int mid = start + (end - start) / 2;
            node.left = buildSegmentTree(start, mid);
            node.right = buildSegmentTree(mid + 1, end);
        }
        return node;
    }

    private void update(SegmentTreeNode curr, int lastDay) {
        if (curr.start == curr.end) curr.val = Integer.MAX_VALUE;
        else {
            int mid = curr.start + (curr.end - curr.start) / 2;
            if (mid >= lastDay) update(curr.left, lastDay);
            else update(curr.right, lastDay);
            curr.val = Math.min(curr.left.val, curr.right.val);
        }
    }

    private int query(SegmentTreeNode curr, int left, int right) {
        if (curr.start == left && curr.end == right) return curr.val;
        int mid = curr.start + (curr.end - curr.start) / 2;
        return mid >= right ? query(curr.left, left, right) :
            mid < left ? query(curr.right, left, right) :
            Math.min(query(curr.left, left, mid), query(curr.right, mid + 1, right));
    }

    public char[] indexPairs(String text, String[] words) {
        return null;
    }
}

public class p1d {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[][] events = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] val = sc.nextLine().split(" ");
            events[i][0] = Integer.parseInt(val[0]);
            events[i][1] = Integer.parseInt(val[1]);
        }
        Solution fna = new Solution();
        int result = fna.maxEvents(events);
        System.out.println(result);
        sc.close();
    }
}
