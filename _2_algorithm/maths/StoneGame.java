//<p>Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，<strong>排成一行</strong>；每堆都有 <strong>正</strong> 整数颗石子，数目为 <code>piles[i]</code>&nbsp;。</p>
//
//<p>游戏以谁手中的石子最多来决出胜负。石子的 <strong>总数</strong> 是 <strong>奇数</strong> ，所以没有平局。</p>
//
//<p>Alice 和 Bob 轮流进行，<strong>Alice 先开始</strong> 。 每回合，玩家从行的 <strong>开始</strong> 或 <strong>结束</strong> 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 <strong>石子最多</strong> 的玩家 <strong>获胜</strong> 。</p>
//
//<p>假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回&nbsp;<code>true</code>&nbsp;，当 Bob 赢得比赛时返回&nbsp;<code>false</code>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>piles = [5,3,4,5]
//<strong>输出：</strong>true
//<strong>解释：</strong>
// Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
// 假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
// 如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
// 如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
// 这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>piles = [3,7,2,3]
//<strong>输出：</strong>true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= piles.length &lt;= 500</code></li> 
// <li><code>piles.length</code> 是 <strong>偶数</strong></li> 
// <li><code>1 &lt;= piles[i] &lt;= 500</code></li> 
// <li><code>sum(piles[i])</code>&nbsp;是 <strong>奇数</strong></li> 
//</ul>
//
//<div><li>👍 471</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

// 877.石子游戏
// 开题时间：2023-01-18 18:07:32
public class StoneGame {
  public static void main(String[] args) {
    Solution solution = new StoneGame().new Solution();
    System.out.println(solution.stoneGame(new int[]{5, 3, 4, 5}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean stoneGame9(int[] piles) {
      int n = piles.length;
      int[][] f = new int[n + 1][n + 1];
      for (int i = n - 1; i >= 0; i--)
        for (int j = i + 1; j < n + 1; j++)
          f[i][j] = Math.max(piles[i] - f[i + 1][j], piles[j - 1] - f[i][j - 1]);
      return f[0][n] > 0;
    }
    
    /*
     * ☆☆☆☆☆ 数学（ Alice 必胜）
     *
     * 假设有 n 堆石子，n 是偶数，则每堆石子的下标从 0 到 n−1。根据下标将 n 堆石子分成两组，每组有 n/2 堆石子，
     * 下标为偶数的石子堆属于第一组，下标为奇数的石子堆属于第二组。初始时，行的开始处的石子堆位于下标 000，属于第
     * 一组，行的结束处的石子堆位于下标 n−1，属于第二组，因此作为先手的 Alice 可以自由选择取走第一组的一堆石子
     * 或者第二组的一堆石子。如果 Alice 取走第一组的一堆石子，则剩下的部分在行的开始处和结束处的石子堆都属于第二
     * 组，因此 Bob 只能取走第二组的一堆石子。如果 Alice 取走第二组的一堆石子，则剩下的部分在行的开始处和结束处
     * 的石子堆都属于第一组，因此 Bob 只能取走第一组的一堆石子。无论 Bob 取走的是开始处还是结束处的一堆石子，剩
     * 下的部分在行的开始处和结束处的石子堆一定是属于不同组的，因此轮到 Alice 取走石子时，Alice 又可以在两组石
     * 子之间进行自由选择。
     *
     * 根据上述分析可知，作为先手的 Alice 可以在第一次取走石子时就决定取走哪一组的石子，并全程取走同一组的石子。
     * 既然如此，Alice 是否有必胜策略？
     *
     * 答案是肯定的。将石子分成两组之后，可以计算出每一组的石子数量，同时知道哪一组的石子数量更多。Alice 只要选
     * 择取走数量更多的一组石子即可。因此，Alice 总是可以赢得比赛。
     */
    public boolean stoneGame(int[] piles) {
      return true;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}