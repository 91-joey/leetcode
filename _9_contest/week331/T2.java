package org.example.leetcode.problems._9_contest.week331;

//
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
      int n = words.length;
      int[] prefix = new int[n + 1];
      for (int i = 1; i < n + 1; i++) {
        prefix[i] = prefix[i - 1];
        if (withVowel(words[i - 1])) {
          prefix[i]++;
        }
      }
      
      int[] ans = new int[queries.length];
      for (int i = 0; i < queries.length; i++) {
        ans[i] = prefix[queries[i][1] + 1] - prefix[queries[i][0]];
      }
      return ans;
    }
    
    private boolean withVowel(String word) {
      return isVowelLowerOnly(word.charAt(0))
          && isVowelLowerOnly(word.charAt(word.length() - 1));
    }
    
    public static boolean isVowelLowerOnly(char c) {
      return switch (c) {
        case 'a', 'e', 'i', 'o', 'u' -> true;
        default -> false;
      };
    }
  }
}
