/*2) Develop a java program to find the Lexicographically smallest equivalent
string.
Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For
example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:
Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and
"aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.
Return the lexicographically smallest equivalent string of S by using the equivalency information
from A and B.
Example 1:
Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in A and B, we can group their characters as
[m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in
lexicographical order. So the answer is "makkek".
Example 2:
Input: A = "hello", B = "world", S = "hold"
Output: "hdld"
Explanation: Based on the equivalency information in A and B, we can group their characters
as [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
Example 3:
Input: A = "leetcode", B = "programs", S = "sourcecode"
Output: "aauaaaaada"
Explanation: We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and
[d,m], thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
35
Note:
String A, B and S consist of only lowercase English letters from 'a' - 'z'.
String A and B are of the same length.
 */

 import java.util.*;

public class 2 {
    public String smallestEquivalentString(String A, String B, String S) {
        // create a mapping of equivalent characters
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < A.length(); i++) {
            int a = A.charAt(i) - 'a';
            int b = B.charAt(i) - 'a';
            union(parent, a, b);
        }
        // replace each character in S with its equivalent character
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            sb.append((char)('a' + find(parent, c - 'a')));
        }
        return sb.toString();
    }
    
    // find the parent of a character
    private int find(int[] parent, int c) {
        if (parent[c] != c) {
            parent[c] = find(parent, parent[c]);
        }
        return parent[c];
    }
    
    // merge two characters into the same group
    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        String B = sc.nextLine();
        String S = sc.nextLine();
        SmallestEquivalentString obj = new SmallestEquivalentString();
        String result = obj.smallestEquivalentString(A, B, S);
        System.out.println(result);
    }
}
