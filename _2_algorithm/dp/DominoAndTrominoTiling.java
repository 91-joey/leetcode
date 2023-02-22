//<p>有两种形状的瓷砖：一种是&nbsp;<code>2 x 1</code> 的多米诺形，另一种是形如&nbsp;"L" 的托米诺形。两种形状都可以旋转。</p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/07/15/lc-domino.jpg" style="height: 195px; width: 362px;" /></p>
//
//<p>给定整数 n ，返回可以平铺&nbsp;<code>2 x n</code> 的面板的方法的数量。<strong>返回对</strong>&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取模&nbsp;</strong>的值。</p>
//
//<p>平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<p><img src="https://assets.leetcode.com/uploads/2021/07/15/lc-domino1.jpg" style="height: 226px; width: 500px;" /></p>
//
//<pre>
//<strong>输入:</strong> n = 3
//<strong>输出:</strong> 5
//<strong>解释:</strong> 五种不同的方法如上所示。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> n = 1
//<strong>输出:</strong> 1
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
//<div><li>👍 149</li><li>👎 0</li></div>
package _2_algorithm.dp;

// 790.多米诺和托米诺平铺
// 开题时间：2022-11-12 08:58:01
public class DominoAndTrominoTiling {
  public static void main(String[] args) {
    Solution solution = new DominoAndTrominoTiling().new Solution();
    System.out.println(solution.numTilings(2));
    System.out.println(solution.numTilings(3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // DP
    public int numTilings8(int n) {
      int mod = 10_0000_0000 + 7;
      int d = 1;
      for (int i = 1, a = 1, b = 0, c = 0; i < n; i++) {
        int tmpA = a;
        int tmpB = b;
        int tmpC = c;
        a = d;
        b = (tmpA + c) % mod;
        c = (tmpA + tmpB) % mod;
        d = ((c + tmpC) % mod + d) % mod;
      }
      return d;
    }
    
    public static final long MOD = (long) (1e9 + 7);
    
    // DP递推  n   n
    // f(i)=2*f(i-1)+f(i-3) i>=4
    public int numTilings7(int n) {
      long[] f = new long[n + 2];
      f[0] = 1;
      f[1] = 2;
      f[2] = 5;
      for (int i = 3; i < n; i++)
        f[i] = ((f[i - 1] << 1) + f[i - 3]) % MOD;
      return (int) f[n - 1];
    }
    
    
    //☆☆☆☆☆ DP递推  n   1
    public int numTilings(int n) {
      if (n == 1) return 1;
      if (n == 2) return 2;
      long a = 1, b = 2, c = 5;
      for (int i = 3; i < n; i++) {
        long tmp = ((c << 1) + a) % MOD;
        a = b;
        b = c;
        c = tmp;
      }
      return (int) c;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}