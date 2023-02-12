package org.example.leetcode.problems._9_contest.week332;

//
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution.minimumScore("abacaba", "bzaa"));
    System.out.println(solution.minimumScore("dabbbeddeabbaccecaee", "bcbbaabdbebecbebded"));
    System.out.println(solution.minimumScore("cde", "xyz"));
  }
  
  class Solution {
    
    public int minimumScore(String s, String t) {
      int lS = 0;
      int lT = 0;
      int last = -1;
      while (lS < s.length() && lT < t.length()) {
        if (s.charAt(lS) == t.charAt(lT)) {
          lT++;
          last = lS;
        }
        lS++;
      }
      
      int rS = s.length();
      int rT = t.length();
      while (rS >= last + 2 && rT >= lT + 1) {
        if (s.charAt(rS - 1) == t.charAt(rT - 1)) {
          rT--;
        }
        rS--;
      }
      
      return rT - lT;
    }
  }
}
