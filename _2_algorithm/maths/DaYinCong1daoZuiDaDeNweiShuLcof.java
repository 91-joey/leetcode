//<p>输入数字 <code>n</code>，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre><strong>输入:</strong> n = 1
//<strong>输出:</strong> [1,2,3,4,5,6,7,8,9]
//</pre>
//
//<p>&nbsp;</p>
//
//<p>说明：</p>
//
//<ul> 
// <li>用返回一个整数列表来代替打印</li> 
// <li>n 为正整数</li> 
//</ul>
//
//<div><li>👍 267</li><li>👎 0</li></div>
package _2_algorithm.maths;

// 剑指 Offer 17.打印从1到最大的n位数
// 开题时间：2022-12-28 09:05:43
public class DaYinCong1daoZuiDaDeNweiShuLcof {
  public static void main(String[] args) {
    Solution solution = new DaYinCong1daoZuiDaDeNweiShuLcof().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] printNumbers(int n) {
      int bound = (int) Math.pow(10, n);
      int[] ans = new int[bound - 1];
      for (int i = 1; i < bound; i++)
        ans[i - 1] = i;
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}