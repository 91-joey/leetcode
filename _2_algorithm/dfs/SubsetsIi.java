package _2_algorithm.dfs;

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
    System.out.println(solution.subsetsWithDup(new int[] {1, 2, 2}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    Set<List<Integer>> ans = new HashSet<>();
    Deque<Integer> stack = new LinkedList<>();
    
    // 回溯 + 哈希表去重
    public List<List<Integer>> subsetsWithDup9(int[] nums) {
      Arrays.sort(nums);
      
      backtrack9(nums, 0);
      
      return new ArrayList<>(ans);
    }
    
    private void backtrack9(int[] nums, int i) {
      if (i == nums.length) {
        ans.add(new ArrayList<>(stack));
        return;
      }
      backtrack9(nums, i + 1);
      stack.addLast(nums[i]);
      backtrack9(nums, i + 1);
      stack.removeLast();
    }
    
    List<List<Integer>> res = new ArrayList<>();
  
    // 回溯 + 虚拟树同层去重
    public List<List<Integer>> subsetsWithDup(int[] nums) {
      Arrays.sort(nums);
      
      backtrack(nums, 0);
      
      return res;
    }
    
    private void backtrack(int[] nums, int begin) {
      // 每个节点都是答案
      res.add(new ArrayList<>(stack));
      for (int i = begin; i < nums.length; i++) {
        stack.addLast(nums[i]);
        backtrack(nums, i + 1);
        stack.removeLast();
        // 关键代码
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
          i++;
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}