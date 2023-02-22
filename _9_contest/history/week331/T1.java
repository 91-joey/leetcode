package _9_contest.history.week331;

import java.util.Comparator;
import java.util.PriorityQueue;

//6348. Take Gifts From the Richest Pile
public class T1 {
  public static void main(String[] args) {
    Solution solution = new T1().new Solution();
    System.out.println(solution);
  }
  
  class Solution {
    // 大顶堆
    public long pickGifts(int[] gifts, int k) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(gifts.length, Comparator.reverseOrder());
      for (int gift : gifts) {
        pq.offer(gift);
      }
      
      for (int i = 0; i < k; i++) {
        pq.offer((int) Math.sqrt(pq.poll()));
      }
      
      return pq.stream().mapToLong(Integer::intValue).sum();
    }
  }
}
