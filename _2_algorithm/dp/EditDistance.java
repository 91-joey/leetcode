//<p>给你两个单词&nbsp;<code>word1</code> 和&nbsp;<code>word2</code>， <em>请返回将&nbsp;<code>word1</code>&nbsp;转换成&nbsp;<code>word2</code> 所使用的最少操作数</em> &nbsp;。</p>
//
//<p>你可以对一个单词进行如下三种操作：</p>
//
//<ul> 
// <li>插入一个字符</li> 
// <li>删除一个字符</li> 
// <li>替换一个字符</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "horse", word2 = "ros"
//<strong>输出：</strong>3
//<strong>解释：</strong>
// horse -&gt; rorse (将 'h' 替换为 'r')
// rorse -&gt; rose (删除 'r')
// rose -&gt; ros (删除 'e')
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "intention", word2 = "execution"
//<strong>输出：</strong>5
//<strong>解释：</strong>
// intention -&gt; inention (删除 't')
// inention -&gt; enention (将 'i' 替换为 'e')
// enention -&gt; exention (将 'n' 替换为 'x')
// exention -&gt; exection (将 'n' 替换为 'c')
// exection -&gt; execution (插入 'u')
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= word1.length, word2.length &lt;= 500</code></li> 
// <li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 2706</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

// 72.编辑距离
// 开题时间：2022-12-13 11:02:45
public class EditDistance {
  public static void main(String[] args) {
    Solution solution = new EditDistance().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 关键点：我们以最后一步需要执行的操作（增删改）进行划分，因为操作的先后顺序不影响结果
     * dp[i][j]表示字符串1的前i个字符 转换成 字符串2的前j个字符 所需的最小操作数
     * cA[i - 1] == cB[j - 1]: 不执行操作
     *      f[i][j] = f[i - 1][j - 1]
     * cA[i - 1] != cB[j - 1]:
     *      最后一步为 增：f[i][j] = f[i][j - 1]     + 1
     *      最后一步为 删：f[i][j] = f[i - 1][j]     + 1
     *      最后一步为 改：f[i][j] = f[i - 1][j - 1] + 1
     */
    public int minDistance(String word1, String word2) {
      char[] cA = word1.toCharArray();
      char[] cB = word2.toCharArray();
      int m = cA.length + 1;
      int n = cB.length + 1;
      int[][] f = new int[m][n];
      for (int i = 1; i < m; i++)
        f[i][0] = i;
      for (int j = 1; j < n; j++)
        f[0][j] = j;
      
      for (int i = 1; i < m; i++)
        for (int j = 1; j < n; j++)
          //                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]) + 1, f[i - 1][j - 1] + (cA[i - 1] == cB[j - 1] ? 0 : 1));
          
          //                    f[i][j] = 1 + min(
          //                            f[i - 1][j],
          //                            f[i][j - 1],
          //                            f[i - 1][j - 1] + (cA[i - 1] == cB[j - 1] ? -1 : 0)
          //                    );
          
          f[i][j] = cA[i - 1] == cB[j - 1] ?
              f[i - 1][j - 1] :
              1 + min(
                  f[i - 1][j],
                  f[i][j - 1],
                  f[i - 1][j - 1]
              );
      
      return f[m - 1][n - 1];
    }
    
    public static int min(int... arr) {
      int min = Integer.MAX_VALUE;
      for (int x : arr) if (min > x) min = x;
      return min;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}