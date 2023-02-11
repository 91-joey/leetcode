package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 40.组合总和 II <br>
 * 开题时间：2023-02-11 11:01:07
 */
public class CombinationSumIi {
  public static void main(String[] args) {
    Solution solution = new CombinationSumIi().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> comb = new LinkedList<>();
    
    // ☆☆☆☆☆ 排序 + 回溯 + 剪枝
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      Arrays.sort(candidates);
      
      backtrack(candidates, target, 0);
      
      return ans;
    }
    
    private void backtrack(int[] candidates, int target, int begin) {
      if (target == 0) {
        ans.add(new ArrayList<>(comb));
        return;
      }
      // 剪枝①：candidates[i] <= target，若当前元素大于目标值、往后的元素只会更大
      for (int i = begin; i < candidates.length && candidates[i] <= target; i++) {
        comb.addLast(candidates[i]);
        backtrack(candidates, target - candidates[i], i + 1);
        comb.removeLast();
        // 剪枝②：因为求的是组合，虚拟树的同层不能有重复
        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
          i++;
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}