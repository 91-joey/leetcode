//<p>给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>整数 <code>n</code> 是 4 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 4<sup>x</sup></code></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 16
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>true
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你能不使用循环或者递归来完成本题吗？</p>
//
//<div><li>👍 328</li><li>👎 0</li></div>
package _2_algorithm.bit;

// 342.4的幂
// 开题时间：2023-01-14 21:24:15
public class PowerOfFour {
  public static void main(String[] args) {
    Solution solution = new PowerOfFour().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public boolean isPowerOfFour9(int n) {
      if (n <= 0)
        return false;
      double sqrt = Math.sqrt(n);
      int intSqrt = (int) sqrt;
      return intSqrt == sqrt && (intSqrt & (intSqrt - 1)) == 0;
    }
    
    //☆☆☆☆☆ 二进制表示中 1 的位置（1.为 2 的幂 2.位 1 的位置为从右往左数的奇数个位置）
    public boolean isPowerOfFour8(int n) {
      return n > 0 && (n & (n - 1)) == 0 && (n & 0xAAAAAAAA) == 0;
    }
    
    // 取模性质：4*x=3*x+x ,(3*x+x)%3 = x%3 ,x0=1
    public boolean isPowerOfFour(int n) {
      return (n & (n - 1)) == 0 && n % 3 == 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}