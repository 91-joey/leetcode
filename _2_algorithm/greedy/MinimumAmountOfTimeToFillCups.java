package org.example.leetcode.problems._2_algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2335.装满杯子需要的最短总时长 <br>
 * 开题时间：2023-02-11 09:51:45
 */
public class MinimumAmountOfTimeToFillCups {
  public static void main(String[] args) {
    Solution solution = new MinimumAmountOfTimeToFillCups().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 优先队列
    public int fillCups9(int[] amount) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(3, Comparator.reverseOrder());
      for (int x : amount) {
        pq.offer(x);
      }
      
      int ans = 0;
      while (!pq.isEmpty()) {
        int a = pq.poll();
        if (pq.peek() == 0) {
          ans += a;
          break;
        } else {
          ans++;
          pq.offer(pq.poll() - 1);
          pq.offer(a - 1);
        }
      }
      return ans;
    }
    
    // ☆☆☆☆☆ 排序 + 贪心
    public int fillCups(int[] amount) {
      Arrays.sort(amount);
      int t = amount[0] + amount[1] - amount[2];
      return amount[2] + (t > 0 ? (t + 1) / 2 : 0);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}