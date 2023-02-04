package org.example.leetcode.problems._9_contest.history.week321;

// 6246. Append Characters to String to Make Subsequence
public class T2 {
  public static void main(String[] args) {
    System.out.println(appendCharacters("coaching", "coding"));
  }
  
  public int appendCharacters9(String s, String t) {
    char[] sChars = s.toCharArray();
    char[] tChars = t.toCharArray();
    int m = sChars.length;
    int n = tChars.length;
    
    int i = 0, j = 0;
    while (i < m && j < n) {
      if (sChars[i] == tChars[j])
        j++;
      i++;
    }
    
    return n - j;
  }
  
  // 双指针
  public static int appendCharacters(String s, String t) {
    int j = 0, n = t.length();
    for (int i = 0, m = s.length(); i < m && j < n; i++)
      if (s.charAt(i) == t.charAt(j))
        j++;
    return n - j;
  }
}
