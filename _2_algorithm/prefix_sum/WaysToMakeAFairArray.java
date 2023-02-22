package _2_algorithm.prefix_sum;

import java.util.Arrays;

/**
 * 1664.生成平衡数组的方案数 <br>
 * 开题时间：2023-01-28 10:28:12
 */
public class WaysToMakeAFairArray {
  public static void main(String[] args) {
    Solution solution = new WaysToMakeAFairArray().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    /*
     * 去除索引为 i 的元素后，i 之前元素的奇偶性不变，i 之后元素的奇偶性改变
     *
     * 预处理奇数索引前缀和、偶数索引后缀和
     * 去除索引为 i 的元素后：
     *  新的奇数索引元素和 = i 之前奇数索引元素和 + i 之后偶数索引元素和
     *  新的偶数索引元素和 = 元素和 - 新的奇数索引元素和
     */
    public int waysToMakeFair9(int[] nums) {
      int n = nums.length;
      int[] oddPrefix = new int[n];
      int[] evenSuffix = new int[n];
      for (int i = 1; i < n; i++) {
        oddPrefix[i] = oddPrefix[i - 1] + (i % 2 == 0 ? 0 : nums[i]);
      }
      for (int i = n - 1; i >= 0; i--) {
        evenSuffix[i] = (i + 1 < n ? evenSuffix[i + 1] : 0) + (i % 2 == 0 ? nums[i] : 0);
      }
      int sum = Arrays.stream(nums).sum();
      
      int ans = 0;
      for (int i = 0; i < n; i++) {
        int sumOdd = (i - 1 > 0 ? oddPrefix[i - 1] : 0) + (i + 1 < n ? evenSuffix[i + 1] : 0);
        if (sumOdd == sum - sumOdd - nums[i]) {
          ans++;
        }
      }
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 正负交替前缀和
     * 定义：f[i] 表示第 i 个元素之前（包括 i）的前缀和（i 为奇时加元素值，i 为偶时减元素值），即: sumEven[i] - sumOdd[i]
     *
     * 去除索引为 i 的元素后，奇数索引元素和 = 偶数索引元素和
     *  即：sumOdd[i] + sumEven[n] - sumEven[i+1] = sumEven[i] + sumOdd[n] - sumOdd[i+1]
     *  即：(sumEven[n] - sumOdd[n]) + (sumEven[i+1] - sumOdd[i+1]) = sumEven[i] - sumOdd[i]
     *  即：f[n] - f[i+1] = f[i]
     */
    public int waysToMakeFair(int[] nums) {
      int n = nums.length;
      int[] f = new int[n + 1]; // 正负交替前缀和
      for (int i = 1, sign = 1; i < n + 1; i++) {
        f[i] = f[i - 1] + sign * nums[i - 1];
        sign = -sign;
      }
      
      int ans = 0;
      for (int i = 0; i < n; i++) {
        if (f[i] == f[n] - f[i + 1]) {
          ans++;
        }
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}