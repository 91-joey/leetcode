//<p>给你两个整数 <code>left</code> 和 <code>right</code> ，表示区间 <code>[left, right]</code> ，返回此区间内所有数字 <strong>按位与</strong> 的结果（包含 <code>left</code> 、<code>right</code> 端点）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>left = 5, right = 7
//<strong>输出：</strong>4
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>left = 0, right = 0
//<strong>输出：</strong>0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>left = 1, right = 2147483647
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= left &lt;= right &lt;= 2<sup>31</sup> - 1</code></li> 
//</ul>
//
//<div><li>👍 426</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

// 201.数字范围按位与
// 开题时间：2023-01-12 18:28:10
public class BitwiseAndOfNumbersRange {
  public static void main(String[] args) {
    Solution solution = new BitwiseAndOfNumbersRange().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 公共前缀（递归）
    public int rangeBitwiseAnd9(int left, int right) {
      if (left == right)
        return left;
      return rangeBitwiseAnd(left >> 1, right >> 1) << 1;
    }
    
    //☆☆☆ 公共前缀（迭代）
    public int rangeBitwiseAnd8(int left, int right) {
      int shift = 0;
      while (left < right) {
        left >>= 1;
        right >>= 1;
        shift++;
      }
      return left << shift;
    }
    
    //☆☆☆☆☆ Brian Kernighan 算法 （每次运算，清除二进制最右侧的 1 ）
    public int rangeBitwiseAnd(int left, int right) {
      while (left < right)
        right &= right - 1;
      return right;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}