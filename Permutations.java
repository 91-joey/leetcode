package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 46.全排列 <br>
 * 开题时间：2023-01-30 11:38:06
 */
public class Permutations {
  public static void main(String[] args) {
    Solution solution = new Permutations().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<List<Integer>> permute(int[] nums) {
      ArrayList<List<Integer>> ans = new ArrayList<>();
  
      int n = nums.length;
      for (int i = 0; i < n; i++) {
        dfs(nums, ans, new ArrayList<Integer>(), new boolean[n], i);
      }
      
      return ans;
    }
    
    private void dfs(int[] nums, ArrayList<List<Integer>> ans, List<Integer> list, boolean[] vis, int i) {
      list.add(nums[i]);
      vis[i] = true;
      if (list.size() == nums.length) {
        ans.add(new ArrayList<>(list));
        return;
      }
      for (int j = 0; j < nums.length; j++) {
        if (!vis[j]) {
          dfs(nums, ans, list, vis, j);
          list.remove(list.size() - 1);
          vis[j] = false;
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}