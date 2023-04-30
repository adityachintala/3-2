/*

b) Write a JAVA Program to find shortest sub array with sum at least K 
Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
If there is no non-empty subarray with sum at least K,return-1. 

Example 1:
Input:A=[1],K=1
Output:1

Example 2:
Input:A=[1,2],K=4 
Output:-1 

Example 3:
Input:A=[2,-1,2],K=3 
Output:3 

Note:
1<=A.length<=50000
-10^5<=A[i]<=10^5 
1<=K<=10^9 

*/

import java.util.*;

class p1b {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(shortestSubarray(arr, k));
        sc.close();
    }

    static int shortestSubarray(int[] arr, int K) {
        int n = arr.length, ans = n + 1;
        Deque<Integer> deque = new LinkedList<>();
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; ++i)
            prefixSum[i + 1] = prefixSum[i] + arr[i];
        for (int i = 0; i < prefixSum.length; ++i) {
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.getFirst()] >= K)
                ans = Math.min(ans, i - deque.removeFirst());
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.getLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        return ans <= n ? ans : -1;
    }
}
