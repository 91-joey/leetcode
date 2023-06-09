//<p>给你一个整数 <code>n</code>，请你判断该整数是否是 2 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>如果存在一个整数 <code>x</code> 使得&nbsp;<code>n == 2<sup>x</sup></code> ，则认为 <code>n</code> 是 2 的幂次方。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>true
//<strong>解释：</strong>2<sup>0</sup> = 1
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 16
//<strong>输出：</strong>true
//<strong>解释：</strong>2<sup>4</sup> = 16
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 3
//<strong>输出：</strong>false
//</pre>
//
//<p><strong>示例 4：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 4
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 5：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5
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
//<p><strong>进阶：</strong>你能够不使用循环/递归解决此问题吗？</p>
//
//<div><li>👍 564</li><li>👎 0</li></div>
package _2_algorithm.bit;

// 231.2 的幂
// 开题时间：2023-01-13 22:51:19
public class PowerOfTwo {
  public static void main(String[] args) {
    Solution solution = new PowerOfTwo().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 删去最低位的 1 后变为 0
    public boolean isPowerOfTwo9(int n) {
      return n > 0 && (n & (n - 1)) == 0;
    }
    
    // LSB（最低有效位）为自身
    public boolean isPowerOfTwo(int n) {
      return n > 0 && (n & -n) == n;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}