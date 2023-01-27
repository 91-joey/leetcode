package org.example.leetcode.problems;

import java.util.Arrays;

/**
 * 280.摆动排序 <br>
 * 开题时间：2023-01-26 18:26:15
 */
public class WiggleSort {
  public static void main(String[] args) {
    Solution solution = new WiggleSort().new Solution();
    solution.wiggleSort(new int[]{3, 5, 2, 1, 6, 4});
  }
  
  //leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    //排序 + 贪心 + 偶数索引取小数、奇数索引取大数
    public void wiggleSort9(int[] nums) {
      Arrays.sort(nums);
      
      int[] ans = new int[nums.length];
      for (int i = 0; i * 2 < nums.length; i++) {
        ans[i * 2] = nums[i];
      }
      for (int i = nums.length - 1 - (nums.length & 1), j = nums.length - 1; i >= 0; i -= 2, j--) {
        ans[i] = nums[j];
      }
      
      System.arraycopy(ans, 0, nums, 0, nums.length);
    }
    
    //（简化）排序 + 贪心 + 偶数索引取小数、奇数索引取大数
    public void wiggleSort8(int[] nums) {
      Arrays.sort(nums);
      
      int n = nums.length;
      int[] ans = new int[n];
      for (int i = 0, l = 0, r = n - 1; i < n; i += 2, l++, r--) {
        ans[i] = nums[l];
        if (i + 1 < n)
          ans[i + 1] = nums[r];
      }
      
      System.arraycopy(ans, 0, nums, 0, nums.length);
    }
    
    //（优化）排序 + 从索引 2 开始逐对交换元素
    public void wiggleSort7(int[] nums) {
      Arrays.sort(nums);
      
      for (int i = 2; i < nums.length; i += 2) {
        swap(nums, i - 1, i);
      }
    }
    
    /*
     * ☆☆☆☆☆ 一遍交换
     * 用数学归纳法证明：
     * 假设 [0,1, ..., k] 均已满足摆动排序，而 k+1 不满足，记此时 nums[k-1]，nums[k]，nums[k+1] 分别为 a，b，c
     *  若不满足的是降序，则可知：c>b，同时 b>=a，此时三个数状态是 a <= b < c，交换 b 和 c 后变成：a < c > b，满足小大小
     *  若不满足的是升序，则可知：c<b，同时 b<=a，此时三个数状态是 a >= b > c，交换 b 和 c 后变成：a > c < b，满足大小大
     */
    public void wiggleSort(int[] nums) {
      for (int i = 1; i < nums.length; i++) {
        if ((i % 2 == 0) == nums[i - 1] < nums[i]) {
          swap(nums, i - 1, i);
        }
      }
    }
    
    public static void swap(int[] arr, int i, int j) {
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }
  }
//leetcode submit region end(Prohibit modification and deletion)
}