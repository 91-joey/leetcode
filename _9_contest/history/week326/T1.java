package org.example.leetcode.problems._9_contest.history.week326;

// 6278. Count the Digits That Divide a Number
public class T1 {
  public static void main(String[] args) {
  
  }
  
  // 枚举数位
  public int countDigits(int num) {
    int cnt = 0;
    for (int i = num; i != 0; i /= 10)
      if (num % (i % 10) == 0)
        cnt++;
    return cnt;
  }
}
