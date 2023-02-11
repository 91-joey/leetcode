package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 90.子集 II <br>
 * 开题时间：2023-02-11 11:44:38
 */
public class SubsetsIi {
  public static void main(String[] args) {
    Solution solution = new SubsetsIi().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    Set<List<Integer>> ans = new HashSet<>();
    Deque<Integer> stack = new LinkedList<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
      Arrays.sort(nums);
      
      backtrack(nums, 0);
      
      return new ArrayList<>(ans);
    }
    
    private void backtrack(int[] nums, int i) {
      if (i == nums.length) {
        ans.add(new ArrayList<>(stack));
        return;
      }
      backtrack(nums, i + 1);
      stack.addLast(nums[i]);
      backtrack(nums, i + 1);
      stack.removeLast();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}