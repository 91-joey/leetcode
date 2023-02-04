//<p>一个序列的 <strong>宽度</strong> 定义为该序列中最大元素和最小元素的差值。</p>
//
//<p>给你一个整数数组 <code>nums</code> ，返回 <code>nums</code> 的所有非空 <strong>子序列</strong> 的 <strong>宽度之和</strong> 。由于答案可能非常大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 后的结果。</p>
//
//<p><strong>子序列</strong> 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，<code>[3,6,2,7]</code> 就是数组 <code>[0,3,1,6,2,2,7]</code> 的一个子序列。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,1,3]
//<strong>输出：</strong>6
//<strong>解释：</strong>子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
// 相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
// 宽度之和是 6 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2]
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 86</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.maths;

import java.util.Arrays;

// 891.子序列宽度之和
// 开题时间：2022-11-18 09:07:55
public class SumOfSubsequenceWidths {
  public static void main(String[] args) {
    Solution solution = new SumOfSubsequenceWidths().new Solution();
    System.out.println(solution.sumSubseqWidths(new int[]{2, 1, 3}));
    System.out.println(solution.sumSubseqWidths(new int[]{2}));
    System.out.println(solution.sumSubseqWidths(new int[]{1, 2, 3}));// 6
    System.out.println(solution.sumSubseqWidths(new int[]{1, 2, 3, 4}));// 10
    System.out.println(solution.sumSubseqWidths(new int[]{1, 2, 3, 4, 5}));// 15
    System.out.println(solution.sumSubseqWidths(new int[]{1, 2, 3, 4, 5, 6}));// 21
    System.out.println(solution.sumSubseqWidths(new int[]{1, 2, 3, 4, 5, 6, 7}));// 28
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final int MOD = 10_0000_0007;
    
    // WA
    public int sumSubseqWidths9(int[] nums) {
      Arrays.sort(nums);
      int sum = 0;
      for (int i = 1; i < 1 << nums.length; i++) {
        int l = 0;
        for (int j = i; j != 0; j >>= 1) {
          if ((j & 1) == 1)
            break;
          l++;
        }
        int r = 30;
        for (int j = i, mask = 1 << 30; mask != 0; mask >>= 1) {
          if ((j & mask) == mask)
            break;
          r--;
        }
        sum += nums[r] - nums[l];
      }
      return sum;
    }
    
    public int sumSubseqWidths8(int[] nums) {
      Arrays.sort(nums);
      
      long sum = 0;
      long x = 2, y = nums[0];
      for (int i = 1; i < nums.length; i++) {
        sum = (sum + nums[i] * (x - 1) - y) % MOD;
        x = (x << 1) % MOD;
        y = ((y << 1) + nums[i]) % MOD;
      }
      
      return (int) sum;
    }
    
    //☆☆☆☆☆ 贡献法：计算元素值的贡献（contribution(nums[i])=作为最大值的贡献+作为最小值的贡献=nums[i]*(2^i - 2^(n-1-i)）
    public int sumSubseqWidths7(int[] nums) {
      Arrays.sort(nums);
      
      int n = nums.length;
      int[] pow2 = new int[n];
      pow2[0] = 1;
      for (int i = 1; i < n; i++)
        pow2[i] = (pow2[i - 1] << 1) % MOD;
      
      long sum = 0L;
      for (int i = 0; i < n; i++)
        sum = (sum + (long) nums[i] * (pow2[i] - pow2[n - 1 - i])) % MOD;
      return (int) sum;
    }
    
    //☆☆☆☆☆ 贡献法：计算2^i的贡献
    public int sumSubseqWidths(int[] nums) {
      Arrays.sort(nums);
      int n = nums.length;
      
      long sum = 0L;
      for (int i = 0, pow = 1; i < n; i++) {
        sum = (sum + (long) pow * (nums[i] - nums[n - 1 - i])) % MOD;
        pow = pow * 2 % MOD;
      }
      return (int) sum;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}