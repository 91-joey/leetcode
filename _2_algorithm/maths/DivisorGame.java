//<p>爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。</p>
//
//<p>最初，黑板上有一个数字&nbsp;<code>n</code>&nbsp;。在每个玩家的回合，玩家需要执行以下操作：</p>
//
//<ul> 
// <li>选出任一&nbsp;<code>x</code>，满足&nbsp;<code>0 &lt; x &lt; n</code>&nbsp;且&nbsp;<code>n % x == 0</code>&nbsp;。</li> 
// <li>用 <code>n - x</code>&nbsp;替换黑板上的数字&nbsp;<code>n</code> 。</li> 
//</ul>
//
//<p>如果玩家无法执行这些操作，就会输掉游戏。</p>
//
//<p><em>只有在爱丽丝在游戏中取得胜利时才返回&nbsp;<code>true</code>&nbsp;。假设两个玩家都以最佳状态参与游戏。</em></p>
//
//<p>&nbsp;</p>
//
//<ol> 
//</ol>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>true
//<strong>解释：</strong>爱丽丝选择 1，鲍勃无法进行操作。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3
//<strong>输出：</strong>false
//<strong>解释：</strong>爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 388</li><li>👎 0</li></div>
package _2_algorithm.maths;

// 1025.除数博弈
// 开题时间：2023-01-18 16:43:29
public class DivisorGame {
  public static void main(String[] args) {
    Solution solution = new DivisorGame().new Solution();
    System.out.println(solution.divisorGame(3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int[] memo;
    
    // 记忆化递归
    public boolean divisorGame9(int n) {
      memo = new int[n];
      return helper(n) == 1;
    }
    
    private int helper(int n) {
      for (int i = 1; i < n; i++)
        if (n % i == 0) {
          if (memo[n - i] == 0)
            memo[n - i] = helper(n - i);
          if (memo[n - i] == -1)
            return 1;
        }
      return -1;
    }
    
    // dp
    public boolean divisorGame8(int n) {
      boolean[] f = new boolean[n + 1];
      for (int i = 2; i < n + 1; i++)
        for (int j = 1; j < i; j++)
          if (i % j == 0 && !f[i - j]) {
            f[i] = true;
            break;
          }
      return f[n];
    }
    
    /*
     * ☆☆☆☆☆ 数学
     * 题意为当玩家轮到数字为 1 时，无法执行操作，输掉游戏
     * 操作的含义为：选一个 [1,n) 范围的约数（因数） x 作为减数，替换数字为 n-x
     *
     * 前置知识：奇数的约数必为奇数，偶数的约数可奇可偶
     * 按 n 的奇偶性分类讨论：
     *  1. n 为偶数时
     *      轮到 Alice 时，每次都选 1
     *      轮到 Bob 时，每次都是奇数，无论选哪个约数都是奇数
     *      轮到 Alice 时，每次都是偶数，重复以上步骤
     *      由于 Alice 每次都选 1，数字 1（奇数）最终轮到了 Bob，因此 Alice 必胜
     *  2. n 为奇数时，同理， Alice 必输
     */
    public boolean divisorGame(int n) {
      return (n & 1) == 0;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}