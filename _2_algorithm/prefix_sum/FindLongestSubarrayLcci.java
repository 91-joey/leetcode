package _2_algorithm.prefix_sum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 面试题 17.05. 字母与数字 <br>
 * 开题时间：2023-03-11 09:30:47
 */
public class FindLongestSubarrayLcci {
  public static void main(String[] args) {
    Solution solution = new FindLongestSubarrayLcci().new Solution();
    String[] arr = {};
    // String[] arr = {"A", "1"};
    System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, 0)));
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 题意转换 + 前缀和 + 哈希表
    public String[] findLongestSubarray(String[] array) {
      int max = -1; // 子数组最大长度
      int end = -1; // 最长子数组的右端点下标
      HashMap<Integer, Integer> firstIdxOfSum = new HashMap<>(); // 存储第一次出现某个前缀和值的下标
      firstIdxOfSum.put(0, -1);
      
      for (int i = 0, sum = 0; i < array.length; i++) {
        sum += Character.isLetter(array[i].charAt(0)) ? 1 : -1; // 字母加一，数字减一
        int len = i - firstIdxOfSum.getOrDefault(sum, i);
        if (len > max) {
          max = len;
          end = i;
        }
        firstIdxOfSum.putIfAbsent(sum, i);
      }
      
      return Arrays.copyOfRange(array, end - max + 1, end + 1);
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}