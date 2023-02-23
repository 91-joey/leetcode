package _2_algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 22.括号生成 <br>
 * 开题时间：2023-02-09 15:48:26
 */
public class GenerateParentheses {
  public static void main(String[] args) {
    Solution solution = new GenerateParentheses().new Solution();
    System.out.println(solution.generateParenthesis(3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    List<String> ans ;
    private int n;
  
    public List<String> generateParenthesis(int n) {
      ans = new ArrayList<>();
      this.n = n;
      
      dfs("", 0, 0);
      // backtrack(ans, new char[2 * n], n, 0, 0);
      // backtrack(ans, new StringBuilder(), n, 0, n, n);
      
      return ans;
    }
    
    /**
     * 回溯（StringBuilder）
     *
     * @param cnt 剩余可以匹配的左括号数，也可以用 r - l 来计算
     * @param l   剩余的左括号数
     * @param r   剩余的右括号数
     */
    private void backtrack(List<String> ans, StringBuilder sb, int n, int cnt, int l, int r) {
      // 左右括号都用完时（左括号可以不用判断），递归结束
      // if (l == 0 && r == 0) {
      if (r == 0) {
        ans.add(sb.toString());
        return;
      }
      if (l > 0) {
        sb.append('(');
        backtrack(ans, sb, n, cnt + 1, l - 1, r);
        sb.deleteCharAt(sb.length() - 1);
      }
      if (cnt > 0) {
        sb.append(')');
        backtrack(ans, sb, n, cnt - 1, l, r - 1);
        sb.deleteCharAt(sb.length() - 1);
      }
    }
    
    
    /**
     * 回溯（字符数组）
     *
     * @param l 已用的左括号数
     * @param r 已用的右括号数
     */
    private void backtrack(List<String> ans, char[] cs, int n, int l, int r) {
      if (r == n) {
        ans.add(new String(cs));
        return;
      }
      if (l < n) {
        cs[l + r] = '(';
        backtrack(ans, cs, n, l + 1, r);
      }
      if (l > r) {
        cs[l + r] = ')';
        backtrack(ans, cs, n, l, r + 1);
      }
    }
    
    
    /**
     * 回溯（字符串）
     *
     * @param l 已用的左括号数
     * @param r 已用的右括号数
     */
    private void dfs(String s, int l, int r) {
      if (r == n) {
        ans.add(s);
        return;
      }
      
      if (l < n) {
        dfs(s + "(", l + 1, r);
      }
      if (l > r) {
        dfs(s + ")", l, r + 1);
      }
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}