package _2_algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 1326.灌溉花园的最少水龙头数目 <br>
 * 开题时间：2023-02-21 10:41:20
 */
public class MinimumNumberOfTapsToOpenToWaterAGarden {
  public static void main(String[] args) {
    Solution solution = new MinimumNumberOfTapsToOpenToWaterAGarden().new Solution();
    System.out.println(solution.minTaps(5, new int[]{3, 4, 1, 1, 0, 0}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 哈希表 + 优先队列
    public int minTaps9(int n, int[] ranges) {
      int ans = 0;
      HashSet<int[]> set = new HashSet<>();
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(o -> o[1]).reversed());
      for (int i = 0; i < ranges.length; i++) {
        if (ranges[i] != 0) {
          int[] range = {i - ranges[i], i + ranges[i]};
          if (i - ranges[i] <= 0) {
            pq.offer(range);
          } else {
            set.add(range);
          }
        }
      }
      
      int begin = 0;
      while (!pq.isEmpty()) {
        int max = pq.poll()[1];
        if (max <= begin) {
          return -1;
        }
        
        begin = max;
        ans++;
        if (begin >= n) {
          return ans;
        }
        
        Iterator<int[]> it = set.iterator();
        while (it.hasNext()) {
          int[] range = it.next();
          if (range[0] <= begin && begin < range[1]) {
            it.remove();
            pq.offer(range);
          }
        }
      }
      
      return -1;
    }
    
    // 排序 + 贪心
    public int minTaps8(int n, int[] ranges) {
      int ans = 0;
      int[][] arr = new int[n + 1][];
      for (int i = 0; i < n + 1; i++) {
        arr[i] = new int[]{i - ranges[i], i + ranges[i]};
      }
      Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
      
      int l = 0;
      int target = 0;
      while (true) {
        int max = 0;
        while (l < n + 1 && arr[l][0] <= target) {
          max = Math.max(max, arr[l++][1]);
        }
        
        ans++;
        if (max >= n) {
          return ans;
        } else if (max <= target) {
          return -1;
        }
        
        target = max;
      }
    }
    
    // 贪心
    public int minTaps(int n, int[] ranges) {
      // 某个区间左端点能够到达的最远区间右端点
      int[] rightMost = new int[n + 1];
      for (int i = 0; i < n + 1; i++) {
        int r = ranges[i];
        if (i > r) {
          rightMost[i - r] = i + r;
        } else {
          rightMost[0] = Math.max(rightMost[0], i + r);
        }
      }
      
      int ans = 0;
      int cur = 0; // 已建造的桥的右端点
      int next = 0; // 下一座桥的右端点的最大值
      for (int i = 0; i < n; i++) {
        next = Math.max(next, rightMost[i]);
        if (i == cur) { // 到达已建造的桥的右端点
          if (i == next) {
            // 无论怎么造桥，都无法从 i 到 i+1
            return -1;
          }
          cur = next; // 造一座桥
          ans++;
        }
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}