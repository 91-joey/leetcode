package org.example.leetcode.problems._2_algorithm.backtrack;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 77.组合 <br>
 * 开题时间：2023-02-01 12:17:57
 */
public class Combinations {
  public static void main(String[] args) {
    Solution solution = new Combinations().new Solution();
    System.out.println(solution);
    System.out.println(Tools.comb(20, 10));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 回溯（根据搜索起点)
    public List<List<Integer>> combine(int n, int k) {
      ArrayList<List<Integer>> ans = new ArrayList<>();
      
      backtrack(n, k, ans, new LinkedList<Integer>(), 1);
      
      return ans;
    }
    
    private void backtrack(int n, int k, List<List<Integer>> ans, Deque<Integer> stack, int i) {
      if (k == stack.size()) {
        ans.add(new LinkedList<>(stack));
        return; // 不要忘记 return 了
      }
      
      // 剪枝
      // for (int j = i; j <= n; j++) {
      for (int j = i; j <= n - k + stack.size() + 1; j++) {
        stack.addLast(j);
        backtrack(n, k, ans, stack, j + 1);
        stack.removeLast();
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}