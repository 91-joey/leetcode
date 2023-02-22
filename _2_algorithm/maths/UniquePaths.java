//<p>一个机器人位于一个 <code>m x n</code><em>&nbsp;</em>网格的左上角 （起始点在下图中标记为 “Start” ）。</p>
//
//<p>机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。</p>
//
//<p>问总共有多少条不同的路径？</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p> 
//<img src="https://assets.leetcode.com/uploads/2018/10/22/robot_maze.png" /> 
//<pre>
//<strong>输入：</strong>m = 3, n = 7
//<strong>输出：</strong>28</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>m = 3, n = 2
//<strong>输出：</strong>3
//<strong>解释：</strong>
// 从左上角开始，总共有 3 条路径可以到达右下角。
// 1. 向右 -&gt; 向下 -&gt; 向下
// 2. 向下 -&gt; 向下 -&gt; 向右
// 3. 向下 -&gt; 向右 -&gt; 向下
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>m = 7, n = 3
//<strong>输出：</strong>28
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>m = 3, n = 3
//<strong>输出：</strong>6</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= m, n &lt;= 100</code></li> 
// <li>题目数据保证答案小于等于 <code>2 * 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 1648</li><li>👎 0</li></div>
package _2_algorithm.maths;

import java.util.Arrays;

// 62.不同路径
// 开题时间：2023-01-18 10:14:52
public class UniquePaths {
  public static void main(String[] args) {
    Solution solution = new UniquePaths().new Solution();
    System.out.println(solution.uniquePaths(23, 12));
    //        System.out.println(Stream.iterate(1, i -> i + 1).limit(7).mapToInt(Integer::intValue).sum());
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 1 m-1 2*(m-2)+1 4*(m-3)+2+1
    public int uniquePathsX(int m, int n) {
      int ans = 2;
      //            for (int i = 1; i < m; i++)
      //                ans += ((m - i) * (1 << (i - 1))) + sum(i - 1);
      for (int i = 1; i <= n; i++)
        ans += ((n - i) * (1 << (i - 1))) + sum(i - 1);
      return ans;
    }
    
    // 二维dp
    public int uniquePaths9(int m, int n) {
      int[][] f = new int[m + 1][n + 1];
      f[0][1] = 1;
      for (int i = 1; i < m + 1; i++)
        for (int j = 1; j < n + 1; j++)
          f[i][j] = f[i][j - 1] + f[i - 1][j];
      return f[m][n];
    }
    
    
    //☆☆☆ 二维dp（空间优化）
    public int uniquePaths8(int m, int n) {
      int[] f = new int[n];
      Arrays.fill(f, 1);
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          f[j] += f[j - 1];
      return f[n - 1];
    }
    
    public int sum(int n) {
      return n * (n + 1) / 2;
    }
    
    /*
     * ☆☆☆☆☆ 组合数
     * 机器人的路径固定为：向下移动 m-1 步，向右移动 n-1 步，一共 m+n-2 步
     * 求路径数，相当于求 m+n-2 步中 m-1 步向下移动的组合数（ n-1 步向右移动也是一样的）
     */
    public int uniquePaths(int m, int n) {
      return (int) comb(m + n - 2, m - 1);
    }
    
    /**
     * 求组合数：C_n^m，即 n 个数里取 m 个的组合数
     */
    public long comb(int n, int m) {
      m = Math.min(m, n - m);
      long comb = 1;
      for (int i = 1, j = n - m + 1; i <= m; i++, j++)
        comb = comb * j / i;
      return comb;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}