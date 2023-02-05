package org.example.leetcode.problems._9_contest.week331;

import java.util.Comparator;
import java.util.PriorityQueue;

//
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    public long pickGifts(int[] gifts, int k) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
      for (int gift : gifts) {
        pq.offer(gift);
      }
      
      for (int i = 0; i < k; i++) {
        Integer poll = pq.poll();
        pq.offer((int) Math.sqrt(poll));
      }
      
      long ans = 0;
      while (!pq.isEmpty()) {
        ans += pq.poll();
      }
      return ans;
    }
  }
}
