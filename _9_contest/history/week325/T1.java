package org.example.leetcode.problems._9_contest.history.week325;

// 6269. Shortest Distance to Target String in a Circular Array
public class T1 {
  public static void main(String[] args) {
  
  }
  
  // 中心扩散
  public int closetTarget(String[] words, String target, int startIndex) {
    int n = words.length;
    for (int i = 0; i <= n / 2; i++)
      if (target.equals(words[(startIndex - i + n) % n]) ||
          target.equals(words[(startIndex + i) % n]))
        return i;
    return -1;
  }
  
}
