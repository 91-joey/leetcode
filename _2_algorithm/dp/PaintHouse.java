//<p>假如有一排房子，共 <code>n</code> 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。</p>
//
//<p>当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个&nbsp;<code>n x 3</code><em>&nbsp;</em>的正整数矩阵 <code>costs</code> 来表示的。</p>
//
//<p>例如，<code>costs[0][0]</code> 表示第 0 号房子粉刷成红色的成本花费；<code>costs[1][2]</code>&nbsp;表示第 1 号房子粉刷成绿色的花费，以此类推。</p>
//
//<p>请计算出粉刷完所有房子最少的花费成本。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入: </strong>costs = [[17,2,17],[16,16,5],[14,3,19]]
//<strong>输出: </strong>10
//<strong>解释: </strong>将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色<strong>。</strong>
//&nbsp;    最少花费: 2 + 5 + 3 = 10。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入: </strong>costs = [[7,6,2]]
//<strong>输出: 2</strong>
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>costs.length == n</code></li> 
// <li><code>costs[i].length == 3</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>1 &lt;= costs[i][j] &lt;= 20</code></li> 
//</ul>
//
//<div><li>👍 202</li><li>👎 0</li></div>
package _2_algorithm.dp;

import java.util.Arrays;

// 256.粉刷房子
// 开题时间：2022-12-09 17:08:44
public class PaintHouse {
  public static void main(String[] args) {
    Solution solution = new PaintHouse().new Solution();
    System.out.println(solution.minCost(new int[][]{
        {17, 2, 17},
        {16, 16, 5},
        {14, 3, 19}
    }));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minCost9(int[][] costs) {
      int n = costs.length + 1;
      int[][] f = new int[n][3];
      
      for (int i = 1; i < n; i++) {
        for (int j = 0; j < 3; j++) {
          int min = Integer.MAX_VALUE;
          for (int k = 0; k < 3; k++)
            if (k != j)
              min = Math.min(min, f[i - 1][k]);
          f[i][j] = min + costs[i - 1][j];
        }
      }
      
      return Arrays.stream(f[n - 1]).min().getAsInt();
    }
    
    public int minCost8(int[][] costs) {
      int n = costs.length + 1;
      int[] f = new int[3];
      
      for (int i = 1; i < n; i++) {
        int[] g = new int[3];
        for (int j = 0; j < 3; j++) {
          int min = Integer.MAX_VALUE;
          for (int k = 0; k < 3; k++)
            if (k != j)
              min = Math.min(min, f[k]);
          g[j] = min + costs[i - 1][j];
        }
        f = g;
      }
      
      return Arrays.stream(f).min().getAsInt();
    }
    
    // dp（带维度单串 dp[i][k]，i 为位置，k 为附加的维度，这里 k 表示颜色）
    public int minCost(int[][] costs) {
      int n = costs.length + 1;
      int[] f = new int[3];
      
      for (int i = 1; i < n; i++) {
        int[] tmp = new int[3];
        for (int j = 0; j < 3; j++)
          tmp[j] = Math.min(f[(j + 1) % 3], f[(j + 2) % 3]) + costs[i - 1][j];
        f = tmp;
      }
      
      return Arrays.stream(f).min().getAsInt();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}