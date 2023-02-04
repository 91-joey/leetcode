//<p>写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例:</strong></p>
//
//<pre><strong>输入:</strong> a = 1, b = 1
//<strong>输出:</strong> 2</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>a</code>,&nbsp;<code>b</code>&nbsp;均可能是负数或 0</li> 
// <li>结果不会溢出 32 位整数</li> 
//</ul>
//
//<div><li>👍 358</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.divideAndConquer.problems;

// 剑指 Offer 65.不用加减乘除做加法
// 开题时间：2022-11-10 14:37:46
public class BuYongJiaJianChengChuZuoJiaFaLcof {
  public static void main(String[] args) {
    Solution solution = new BuYongJiaJianChengChuZuoJiaFaLcof().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 递归（尾递归）
     *  两数之和 = 无进位和 + 进位和
     *      进位和为 0 时 ，此时的无进位和即为答案。
     *
     *      a(i)  b(i)  无进位和 n(i)    进位和 c(i+1)
     *      0       0       0               0
     *      0       1       1               0
     *      1       0       1               0
     *      1       1       0               1
     *  观察上表，可以发现：两数相同位(i)之和 = 此位 + 进位 << 1
     *      而不难看出：
     *          - 此位 = a(i)^b(i)
     *          - 进位 = a(i)&b(i)<<1
     *  对于所有位则有：a + b = (a ^ b) + ((a & b) << 1)，是一个递归过程，递归结束的条件是 b 为 0
     */
    public int add9(int a, int b) {
      if (b == 0)
        return a;
      return add(a ^ b, (a & b) << 1);
    }
    
    // 迭代
    public int add(int a, int b) {
      while (b != 0) {
        int carry = (a & b) << 1;
        a ^= b;
        b = carry;
      }
      return a;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}