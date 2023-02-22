//<p>汽车从起点出发驶向目的地，该目的地位于出发位置东面 <code>target</code>&nbsp;英里处。</p>
//
//<p>沿途有加油站，每个&nbsp;<code>station[i]</code>&nbsp;代表一个加油站，它位于出发位置东面&nbsp;<code>station[i][0]</code>&nbsp;英里处，并且有&nbsp;<code>station[i][1]</code>&nbsp;升汽油。</p>
//
//<p>假设汽车油箱的容量是无限的，其中最初有&nbsp;<code>startFuel</code>&nbsp;升燃料。它每行驶 1 英里就会用掉 1 升汽油。</p>
//
//<p>当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。</p>
//
//<p>为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 <code>-1</code> 。</p>
//
//<p>注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>target = 1, startFuel = 1, stations = []
//<strong>输出：</strong>0
//<strong>解释：</strong>我们可以在不加油的情况下到达目的地。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>target = 100, startFuel = 1, stations = [[10,100]]
//<strong>输出：</strong>-1
//<strong>解释：</strong>我们无法抵达目的地，甚至无法到达第一个加油站。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
//<strong>输出：</strong>2
//<strong>解释：</strong>
// 我们出发时有 10 升燃料。
// 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
// 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
// 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
// 我们沿途在1两个加油站停靠，所以返回 2 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ol> 
// <li><code>1 &lt;= target, startFuel, stations[i][1] &lt;= 10^9</code></li> 
// <li><code>0 &lt;= stations.length &lt;= 500</code></li> 
// <li><code>0 &lt; stations[0][0] &lt; stations[1][0] &lt; ... &lt; stations[stations.length-1][0] &lt; target</code></li> 
//</ol>
//
//<div><li>👍 383</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 871.最低加油次数
// 开题时间：2022-12-07 15:12:56
public class MinimumNumberOfRefuelingStops {
  public static void main(String[] args) {
    Solution solution = new MinimumNumberOfRefuelingStops().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    //☆☆☆☆☆ 贪心+优先队列
    public int minRefuelStops9(int target, int startFuel, int[][] stations) {
      int ans = 0;
      
      PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
      
      int i = 0, n = stations.length;
      while (startFuel < target)
        if (i < n && startFuel >= stations[i][0])
          pq.offer(stations[i++][1]);
        else if (pq.isEmpty())
          return -1;
        else {
          startFuel += pq.poll();
          ans++;
        }
      
      return ans;
    }
    
    // dp[i][j]：经过 i 个加油站，停靠 j 次，能达到的最远距离
    public int minRefuelStops8(int target, int startFuel, int[][] stations) {
      if (startFuel >= target)
        return 0;
      
      int n = stations.length + 1;
      int[][] f = new int[n][n];
      
      for (int i = 0; i < n; i++) f[i][0] = startFuel;
      for (int i = 1; i < n; i++)
        for (int j = 1; j <= i; j++) {
          int[] station = stations[i - 1];
          f[i][j] = Math.max(
              f[i - 1][j - 1] >= station[0] ? f[i - 1][j - 1] + station[1] : 0,
              f[i - 1][j] >= station[0] ? f[i - 1][j] : 0
          );
        }
      
      for (int j = 0; j < f[n - 1].length; j++)
        if (f[n - 1][j] >= target)
          return j;
      
      return -1;
    }
    
    // dp 空间优化（降维）
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
      if (startFuel >= target)
        return 0;
      
      int n = stations.length + 1;
      int[] f = new int[n];
      
      Arrays.fill(f, startFuel);
      for (int i = 1; i < n; i++) {
        int[] station = stations[i - 1];
        for (int j = i; j >= 1; j--)
          f[j] = Math.max(
              f[j - 1] >= station[0] ? f[j - 1] + station[1] : 0,
              f[j] >= station[0] ? f[j] : 0
          );
      }
      
      for (int j = 0; j < n; j++)
        if (f[j] >= target)
          return j;
      
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}