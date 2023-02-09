package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 17.电话号码的字母组合 <br>
 * 开题时间：2023-02-09 17:57:55
 */
public class LetterCombinationsOfAPhoneNumber {
  public static void main(String[] args) {
    Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final char[][] cs = {
        {'a', 'b', 'c'},
        {'d', 'e', 'f'},
        {'g', 'h', 'i'},
        {'j', 'k', 'l'},
        {'m', 'n', 'o'},
        {'p', 'q', 'r', 's'},
        {'t', 'u', 'v'},
        {'w', 'x', 'y', 'z'},
    };
    List<String> ans = new ArrayList<>();
    
    // 回溯 + 哈希（注意输入字符串长度为 0 时，需要特判）
    public List<String> letterCombinations(String digits) {
      if (digits.length() > 0) {
        backtrack(digits, "", 0);
      }
      
      return ans;
    }
    
    private void backtrack(String digits, String s, int i) {
      if (i == digits.length()) {
        ans.add(s);
        return;
      }
      for (char c : cs[digits.charAt(i) - '2']) {
        backtrack(digits, s + c, i + 1);
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}