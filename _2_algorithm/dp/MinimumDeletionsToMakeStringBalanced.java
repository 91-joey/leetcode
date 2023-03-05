package _2_algorithm.dp;

/**
 * 1653.使字符串平衡的最少删除次数 <br>
 * 开题时间：2023-03-06 02:44:26
 */
public class MinimumDeletionsToMakeStringBalanced {
  public static void main(String[] args) {
    Solution solution = new MinimumDeletionsToMakeStringBalanced().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 前后缀分解（两次遍历）：枚举分割点，要删除的 b 都在前缀中，要删除的 a 都在后缀中
    public int minimumDeletions9(String s) {
      int a = 0;
      for (int i = 0; i < s.length(); i++) {
        a += s.charAt(i) & 1;
      }
      
      int min = a;
      for (int i = 0, b = 0; i < s.length(); i++) {
        b += s.charAt(i) & 1 ^ 1;
        a -= s.charAt(i) & 1;
        min = Math.min(min, a + b);
      }
      
      return min;
    }
  
    // ☆☆☆☆☆ 前后缀分解（两次遍历）优化
    public int minimumDeletions(String s) {
      char[] cs = s.toCharArray();
      int del = 0;
      for (char c : cs) {
        del += c & 1;
      }
      
      int min = del;
      for (char c : cs) {
        del += 2 * (c - 'b') + 1;
        min = Math.min(min, del);
      }
      
      return min;
    }
    
    // todo DP
  }
  // leetcode submit region end(Prohibit modification and deletion)
}