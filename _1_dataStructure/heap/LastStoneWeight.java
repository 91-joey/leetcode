package _1_dataStructure.heap;

import java.util.PriorityQueue;

/**
 * 1046.最后一块石头的重量 <br>
 * 开题时间：2023-03-07 10:13:07
 */
public class LastStoneWeight {
  public static void main(String[] args) {
    Solution solution = new LastStoneWeight().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 优先队列（最大堆）
    public int lastStoneWeight(int[] stones) {
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(stones.length, (a, b) -> b - a);
      for (int stone : stones) {
        pq.offer(stone);
      }
      
      while (pq.size() > 1) {
        int y = pq.poll();
        int x = pq.poll();
        if (x < y) {
          pq.offer(y - x);
        }
      }
      
      return pq.isEmpty() ? 0 : pq.poll();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}