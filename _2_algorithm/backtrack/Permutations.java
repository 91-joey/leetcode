package org.example.leetcode.problems._2_algorithm.backtrack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 46.全排列 <br>
 * 开题时间：2023-01-30 11:38:06
 */
public class Permutations {
  public static void main(String[] args) {
    Solution solution = new Permutations().new Solution();
    System.out.println(solution);
    int a = 0x57;
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 回溯
    public List<List<Integer>> permute9(int[] nums) {
      ArrayList<List<Integer>> ans = new ArrayList<>();
      
      int n = nums.length;
      // 待优化：这里的循环可以并入 dfs 中
      for (int i = 0; i < n; i++) {
        dfs(nums, ans, new ArrayList<Integer>(), new boolean[n], i);
      }
      
      return ans;
    }
    
    // 待优化：这里的回溯维护状态的数据结构是后进先出的，因此用栈更准确
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
    
    /*
     * ☆☆☆☆☆ 回溯（优化）
     * 1. 状态变量改为栈
     * 2. 维护路径深度
     */
    public List<List<Integer>> permute(int[] nums) {
      ArrayList<List<Integer>> ans = new ArrayList<>();
      
      backtrack(nums, ans, new LinkedList<Integer>(), new boolean[nums.length], 0);
      
      return ans;
    }
    
    private void backtrack(int[] nums, ArrayList<List<Integer>> ans, Deque<Integer> path, boolean[] vis, int depth) {
      if (depth == nums.length) {
        ans.add(new LinkedList<>(path));
        return;
      }
      
      for (int i = 0; i < nums.length; i++) {
        if (!vis[i]) {
          path.addLast(nums[i]);
          vis[i] = true;
          backtrack(nums, ans, path, vis, depth + 1);
          // 对称的状态重置操作
          path.removeLast();
          vis[i] = false;
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}