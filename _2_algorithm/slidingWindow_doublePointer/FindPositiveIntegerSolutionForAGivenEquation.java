package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import org.example.leetcode.problems._3_common.interactive.CustomFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1237.找出给定方程的正整数解 <br>
 * 开题时间：2023-02-18 09:25:01
 */
public class FindPositiveIntegerSolutionForAGivenEquation {
  public static void main(String[] args) {
    Solution solution = new FindPositiveIntegerSolutionForAGivenEquation().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力
    public List<List<Integer>> findSolution9(CustomFunction customfunction, int z) {
      List<List<Integer>> ans = new ArrayList<>();
      for (int x = 1; x <= 1000; x++) {
        for (int y = 1; y <= 1000; y++) {
          if (customfunction.f(x, y) == z) {
            ans.add(Arrays.asList(x, y));
          }
        }
      }
      return ans;
    }
    
    // ☆☆☆ 二分（只利用函数在 y 上的单调递增特性）
    public List<List<Integer>> findSolution8(CustomFunction customfunction, int z) {
      List<List<Integer>> ans = new ArrayList<>();
      for (int x = 1, y = 1001; x <= 1000; x++) {
        int idx = binarySearch(customfunction, x, 1, y, z);
        if (idx < y && customfunction.f(x, idx) == z) {
          ans.add(Arrays.asList(x, idx));
        }
        y = idx;
      }
      return ans;
    }
    
    public static int binarySearch(CustomFunction customfunction, int x, int l, int r, int target) {
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (target <= customfunction.f(x, mid))
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
  
    // ☆☆☆☆☆ 相向双指针（利用函数在 x、y 上都单调递增的特性）
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
      List<List<Integer>> ans = new ArrayList<>();
      for (int x = 1, y = 1000; x <= 1000 && 1 <= y; ) {
        int res = customfunction.f(x, y);
        if (res == z) {
          ans.add(Arrays.asList(x++, y--));
        } else if (res > z) {
          y--;
        } else {
          x++;
        }
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}