package org.example.leetcode.problems._2_algorithm.dfs;

import java.util.HashSet;

/**
 * 1593.拆分字符串使唯一子字符串的数目最大 <br>
 * 开题时间：2023-02-11 15:10:06
 */
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {
  public static void main(String[] args) {
    Solution solution = new SplitAStringIntoTheMaxNumberOfUniqueSubstrings().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int ans = 1;
    HashSet<String> set = new HashSet<>();
    
    // 回溯 +　哈希表
    public int maxUniqueSplit(String s) {
      backtrack(s, 0);
      return ans;
    }
    
    private void backtrack(String s, int begin) {
      // 剪枝
      if (set.size() + s.length() - begin <= ans) {
        return;
      }
      if (begin == s.length()) {
        ans = Math.max(ans, set.size());
        return;
      }
      for (int i = begin; i < s.length(); i++) {
        String sub = s.substring(begin, i + 1);
        if (set.add(sub)) {
          backtrack(s, i + 1);
          set.remove(sub);
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}