//<p>给定一个整数，写一个函数来判断它是否是 3&nbsp;的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>整数 <code>n</code> 是 3 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 3<sup>x</sup></code></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 27
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 0
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 9
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 45
//<strong>输出：</strong>false
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
//<div><li>👍 283</li><li>👎 0</li></div>
package _2_algorithm.bit;

// 326.3 的幂
// 开题时间：2023-01-14 20:59:56
public class PowerOfThree {
  public static void main(String[] args) {
    Solution solution = new PowerOfThree().new Solution();
    
    for (int i = 1; i > 0; i *= 3)
      System.out.println(Integer.toBinaryString(i));
    
    System.out.println("_________________");
    for (int i = 1; i > 0; i *= 5)
      System.out.println(i);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 枚举所有 3 的幂
    public boolean isPowerOfThree9(int n) {
      if (n <= 0)
        return false;
      for (int i = 1; i > 0; i *= 3)
        if (i == n)
          return true;
      return false;
    }
    
    //☆☆☆☆☆ 判断是否为最大 3 的幂的约数
    public boolean isPowerOfThree8(int n) {
      return n > 0 && 1162261467 % n == 0;
    }
    
    // 试除法
    public boolean isPowerOfThree(int n) {
      while (n > 0 && n % 3 == 0)
        n /= 3;
      return n == 1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}