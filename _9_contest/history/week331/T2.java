package org.example.leetcode.problems._9_contest.history.week331;

// 6347. Count Vowel Strings in Ranges
public class T2 {
  public static void main(String[] args) {
    Solution solution = new T2().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // 前缀和 + 字符串首尾字符元音判断
    public int[] vowelStrings9(String[] words, int[][] queries) {
      int n = words.length;
      int[] prefix = new int[n + 1];
      for (int i = 1; i < n + 1; i++) {
        prefix[i] = prefix[i - 1];
        if (startsAndEndsWithVowel(words[i - 1])) {
          prefix[i]++;
        }
      }
      
      int[] ans = new int[queries.length];
      for (int i = 0; i < queries.length; i++) {
        ans[i] = prefix[queries[i][1] + 1] - prefix[queries[i][0]];
      }
      return ans;
    }
    
    private boolean startsAndEndsWithVowel(String word) {
      return isLowerCaseVowel(word.charAt(0))
          && isLowerCaseVowel(word.charAt(word.length() - 1));
    }
    
    public static boolean isLowerCaseVowel(char c) {
      return switch (c) {
        case 'a', 'e', 'i', 'o', 'u' -> true;
        default -> false;
      };
    }
  }
  
  // ☆☆☆☆☆ 前缀和 + 正则表达式
  public int[] vowelStrings(String[] words, int[][] queries) {
    int n = words.length;
    int[] prefix = new int[n + 1];
    for (int i = 1; i < n + 1; i++) {
      prefix[i] = prefix[i - 1];
      if (words[i - 1].matches("[aeiou](.*[aeiou])?")) {
        prefix[i]++;
      }
    }
    
    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      ans[i] = prefix[queries[i][1] + 1] - prefix[queries[i][0]];
    }
    return ans;
  }
  
}
