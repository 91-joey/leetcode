//<p>输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums =&nbsp;[1,2,3,4]
//<strong>输出：</strong>[1,3,2,4] 
//<strong>注：</strong>[3,1,2,4] 也是正确的答案之一。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ol> 
// <li><code>0 &lt;= nums.length &lt;= 50000</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10000</code></li> 
//</ol>
//
//<div><li>👍 271</li><li>👎 0</li></div>
package _2_algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

// 剑指 Offer 21.调整数组顺序使奇数位于偶数前面
// 开题时间：2022-12-24 09:12:23
public class DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMianLcof {
  public static void main(String[] args) {
    Solution solution = new DiaoZhengShuZuShunXuShiQiShuWeiYuOuShuQianMianLcof().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int[] exchange9(int[] nums) {
      return Arrays.stream(nums)
          .boxed()
          .sorted(Comparator.<Integer>comparingInt(x -> x & 1).reversed())
          .mapToInt(Integer::intValue)
          .toArray();
    }
    
    //☆☆☆☆☆ 首尾双指针
    public int[] exchange(int[] nums) {
      int l = 0, r = nums.length - 1;
      while (l < r) {
        while (l < r && (nums[l] & 1) == 1) l++;
        while (l < r && (nums[r] & 1) == 0) r--;
        if (l < r) {
          int tmp = nums[l];
          nums[l++] = nums[r];
          nums[r--] = tmp;
        }
      }
      return nums;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}