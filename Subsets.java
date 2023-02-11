package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 78.子集 <br>
 * 开题时间：2023-02-11 11:21:35
 */
public class Subsets {
  public static void main(String[] args) {
    Solution solution = new Subsets().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> stack = new LinkedList<>();
    
    // 回溯
    public List<List<Integer>> subsets9(int[] nums) {
      backtrack(nums, 0);
      
      return ans;
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
    
    // 二进制枚举
    public List<List<Integer>> subsets(int[] nums) {
      int n = nums.length;
      for (int i = (1 << n) - 1; i >= 0; i--) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int j = 0; j < n; j++) {
          if ((i & (1 << j)) != 0) {
            list.add(nums[j]);
          }
        }
        ans.add(list);
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}