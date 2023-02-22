//<p>给你一个正整数 <code>num</code> ，请你统计并返回 <strong>小于或等于</strong> <code>num</code> 且各位数字之和为 <strong>偶数</strong> 的正整数的数目。</p>
//
//<p>正整数的 <strong>各位数字之和</strong> 是其所有位上的对应数字相加的结果。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>num = 4
//<strong>输出：</strong>2
//<strong>解释：</strong>
// 只有 2 和 4 满足小于等于 4 且各位数字之和为偶数。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>num = 30
//<strong>输出：</strong>14
//<strong>解释：</strong>
// 只有 14 个整数满足小于等于 30 且各位数字之和为偶数，分别是：
// 2、4、6、8、11、13、15、17、19、20、22、24、26 和 28 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= num &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 28</li><li>👎 0</li></div>
package _2_algorithm.maths;

import java.util.stream.Stream;

// 2180.统计各位数字之和为偶数的整数个数
// 开题时间：2023-01-06 10:11:31
public class CountIntegersWithEvenDigitSum {
  public static void main(String[] args) {
    Solution solution = new CountIntegersWithEvenDigitSum().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力
    public int countEven9(int num) {
      int cnt = 0;
      for (int i = 2; i <= num; i++) {
        int sum = 0;
        for (int j = i; j != 0; j /= 10)
          sum += j % 10;
        if (sum % 2 == 0)
          cnt++;
      }
      return cnt;
    }
    
    //☆☆☆☆☆ 数学推导
    public int countEven8(int num) {
      int sum = 0;
      for (int i = num / 10; i != 0; i /= 10)
        sum += i % 10;
      return (num - (sum & 1)) / 2;
    }
    
    public int countEven(int num) {
      return (num - (Stream.iterate(num / 10, x -> x > 0, x -> x / 10).mapToInt(x -> x % 10).sum() & 1)) / 2;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}