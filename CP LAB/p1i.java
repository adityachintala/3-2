/*i) Write a JAVA Program to return all index pairs [i,j] given a text string and
words (a list), so that the substring text[i]…text[j] is in the list of words
Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring
text[i]...text[j] is in the list of words.
Note:
 All strings contains only lowercase English letters.
 It's guaranteed that all strings in words are different.
 Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties
sort them by their second coordinate).
Example 1:
Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:
Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: Notice that matches can overlap, see "aba" is found in [0,2] and [2,4]. */

import java.util.*;

class Solution {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> indexPairsList = new ArrayList<>();
        for (String word : words) {
            int curIndex = -1;
            while ((curIndex = text.indexOf(word, curIndex + 1)) >= 0) {
                indexPairsList.add(new int[] { curIndex, curIndex + word.length() - 1 });
            }
        }
        indexPairsList.sort(Comparator.comparingInt(a -> a[0]));
        return indexPairsList.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String[] words = sc.nextLine().split(" ");
        int[][] indexPairs = new Solution().indexPairs(text, words);
        for (int[] pair : indexPairs) {
            System.out.println(pair[0] + " " + pair[1]);
        }
    }
}
