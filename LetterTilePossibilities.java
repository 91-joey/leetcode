package org.example.leetcode.problems;

import java.util.Arrays;

/**
 * 1079.活字印刷 <br>
 * 开题时间：2023-02-11 15:39:35
 */
public class LetterTilePossibilities {
  public static void main(String[] args) {
    Solution solution = new LetterTilePossibilities().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    int ans = 0;
    boolean[] vis;
    
    // 排序 +　回溯
    public int numTilePossibilities(String tiles) {
      vis = new boolean[tiles.length()];
      char[] cs = tiles.toCharArray();
      Arrays.sort(cs);
      
      backtrack(cs);
      
      return ans;
    }
    
    private void backtrack(char[] cs) {
      for (int i = 0; i < cs.length; i++) {
        if (!vis[i]) {
          ans++;
          vis[i] = true;
          backtrack(cs);
          vis[i] = false;
          // 剪枝
          while (i + 1 < cs.length && cs[i] == cs[i + 1]) {
            i++;
          }
        }
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}