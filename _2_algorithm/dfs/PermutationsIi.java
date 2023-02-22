package _2_algorithm.dfs;

import _3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 47.全排列 II <br>
 * 开题时间：2023-02-10 09:20:33
 */
public class PermutationsIi {
  public static void main(String[] args) {
    Solution solution = new PermutationsIi().new Solution();
    System.out.println(solution.permuteUnique(new int[]{1, 1, 2}));
    System.out.println(Tools.factorial(8));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    HashSet<List<Integer>> ans = new HashSet<>();
    Deque<Integer> path = new LinkedList<>();
    boolean[] vis;
    
    // 回溯 + 哈希表
    public List<List<Integer>> permuteUnique9(int[] nums) {
      vis = new boolean[nums.length];
      
      backtrack9(nums, 0);
      
      return ans.stream().toList();
    }
    
    private void backtrack9(int[] nums, int depth) {
      if (depth == nums.length) {
        ans.add(new LinkedList<>(path));
        return;
      }
      
      for (int i = 0; i < nums.length; i++) {
        if (!vis[i]) {
          path.addLast(nums[i]);
          vis[i] = true;
          backtrack9(nums, depth + 1);
          path.removeLast();
          vis[i] = false;
        }
      }
    }
    
    List<List<Integer>> res = new ArrayList<>();
    
    // ☆☆☆☆☆　排序 + 回溯 +　剪枝
    public List<List<Integer>> permuteUnique(int[] nums) {
      Arrays.sort(nums);
      vis = new boolean[nums.length];
      
      backtrack(nums);
      
      return res;
    }
    
    private void backtrack(int[] nums) {
      if (path.size() == nums.length) {
        res.add(new LinkedList<>(path));
        return;
      }
      
      // int pre = Integer.MIN_VALUE;
      for (int i = 0; i < nums.length; i++) {
        if (!vis[i] && !(0 < i && vis[i - 1] && nums[i - 1] == nums[i])) {
          // if (!vis[i] && nums[i] != pre) {
          // pre = nums[i];
          path.addLast(nums[i]);
          vis[i] = true;
          backtrack(nums);
          path.removeLast();
          vis[i] = false;
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}