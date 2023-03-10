package _2_algorithm.prefix_sum;

import java.util.HashMap;

/**
 * 1590.使数组和能被 P 整除 <br>
 * 开题时间：2023-03-10 09:16:53
 */
public class MakeSumDivisibleByP {
  public static void main(String[] args) {
    Solution solution = new MakeSumDivisibleByP().new Solution();
    System.out.println(solution.minSubarray(new int[]{4, 3, 1, 2}, 6));
    // System.out.println(solution.minSubarray(new int[]{3, 1, 4, 2}, 6));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 前缀和 + 哈希表 + 同余
    public int minSubarray9(int[] nums, int p) {
      long sum = 0;
      for (int num : nums) {
        sum += num;
      }
      
      int mod = (int) (sum % p);
      if (mod == 0) {
        return 0;
      }
      
      HashMap<Integer, Integer> lastIdxOfModP = new HashMap<>();
      lastIdxOfModP.put(0, -1);
      int n = nums.length;
      long curSum = 0;
      int ans = n;
      for (int i = 0; i < n; i++) {
        curSum += nums[i];
        int mod1 = (int) (curSum % p);
        int key = (mod1 - mod + p) % p;
        if (lastIdxOfModP.containsKey(key)) {
          ans = Math.min(ans, i - lastIdxOfModP.get(key));
        }
        lastIdxOfModP.put(mod1, i);
      }
      return ans == n ? -1 : ans;
    }
    
    public int minSubarray(int[] nums, int p) {
      int sumMod = 0;
      for (int x : nums) {
        sumMod = (sumMod + x) % p;
      }
      
      if (sumMod == 0) {
        return 0;
      }
      
      HashMap<Integer, Integer> lastIdxOfModP = new HashMap<>();
      lastIdxOfModP.put(0, -1);
      
      int n = nums.length;
      int ans = n;
      for (int i = 0, curMod = 0; i < n; i++) {
        curMod = (curMod + nums[i]) % p;
        int key = (curMod - sumMod + p) % p;
        if (lastIdxOfModP.containsKey(key)) {
          ans = Math.min(ans, i - lastIdxOfModP.get(key));
        }
        lastIdxOfModP.put(curMod, i);
      }
      return ans == n ? -1 : ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}