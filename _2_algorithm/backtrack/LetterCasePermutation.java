package org.example.leetcode.problems._2_algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 784.字母大小写全排列 <br>
 * 开题时间：2023-01-31 11:10:56
 */
public class LetterCasePermutation {
  public static void main(String[] args) {
    Solution solution = new LetterCasePermutation().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 状态压缩 n * 2^n
    public List<String> letterCasePermutation9(String s) {
      char[] cs = s.toLowerCase().toCharArray();
      int n = cs.length;
      HashSet<String> set = new HashSet<>();
      for (int state = 0; state < 1 << n; state++) {
        char[] copy = Arrays.copyOf(cs, n);
        for (int i = state, idx = 0; i > 0; i >>= 1, idx++) {
          if ((i & 1) == 1) {
            copy[idx] = Character.toUpperCase(copy[idx]);
          }
        }
        set.add(String.valueOf(copy));
      }
      return new ArrayList<>(set);
    }
    
    // 回溯
    public List<String> letterCasePermutation8(String s) {
      ArrayList<String> ans = new ArrayList<>();
      
      backtrack(s.toLowerCase().toCharArray(), ans, 0);
      
      return ans;
    }
    
    private void backtrack(char[] cs, ArrayList<String> ans, int i) {
      if (i == cs.length) {
        ans.add(String.valueOf(cs));
        return;
      }
      backtrack(cs, ans, i + 1);
      if (Character.isLetter(cs[i])) {
        cs[i] = Character.toUpperCase(cs[i]);
        backtrack(cs, ans, i + 1);
        cs[i] = Character.toLowerCase(cs[i]);
      }
    }
  
    // ☆☆☆☆☆ 回溯（优化）
    public List<String> letterCasePermutation(String s) {
      ArrayList<String> ans = new ArrayList<>();
      
      dfs(s.toLowerCase().toCharArray(), ans, 0);
      
      return ans;
    }
    
    private void dfs(char[] cs, ArrayList<String> ans, int i) {
      if (i == cs.length) {
        ans.add(String.valueOf(cs));
        return;
      }
      dfs(cs, ans, i + 1);
      if (Character.isLetter(cs[i])) {
        // 变换大小写，省去回溯
        cs[i] ^= 32;
        dfs(cs, ans, i + 1);
      }
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}