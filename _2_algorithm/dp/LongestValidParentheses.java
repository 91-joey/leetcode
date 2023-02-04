//<p>给你一个只包含 <code>'('</code>&nbsp;和 <code>')'</code>&nbsp;的字符串，找出最长有效（格式正确且连续）括号子串的长度。</p>
//
//<p>&nbsp;</p>
//
//<div class="original__bRMd"> 
// <div> 
//  <p><strong>示例 1：</strong></p> 
// </div>
//</div>
//
//<pre>
//<strong>输入：</strong>s = "(()"
//<strong>输出：</strong>2
//<strong>解释：</strong>最长有效括号子串是 "()"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = ")()())"
//<strong>输出：</strong>4
//<strong>解释：</strong>最长有效括号子串是 "()()"
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = ""
//<strong>输出：</strong>0
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>s[i]</code> 为 <code>'('</code> 或 <code>')'</code></li> 
//</ul>
//
//<div><li>👍 2094</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import java.util.Deque;
import java.util.LinkedList;

// 32.最长有效括号
// 开题时间：2022-12-01 17:53:44
public class LongestValidParentheses {
  public static void main(String[] args) {
    Solution solution = new LongestValidParentheses().new Solution();
    System.out.println(solution.longestValidParentheses("(()(())))))))"));
    //        System.out.println(solution.longestValidParentheses("(()())"));
    //        System.out.println(solution.longestValidParentheses("()(())"));
    //        System.out.println(solution.longestValidParentheses("())"));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public static final char L = '(';
    public static final char R = ')';
    
    public int longestValidParentheses9(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      if (n <= 1)
        return 0;
      
      int[] f = new int[n];
      int[] g = new int[n];
      g[0] = -1;
      if (cs[0] == L && cs[1] == R)
        f[1] = 2;
      g[1] = -1;
      int max = f[1];
      
      for (int i = 2; i < n; i++) {
        if (cs[i] == L)
          g[i] = -1;
        else {
          //()
          if (cs[i - 1] == L) {
            f[i] = f[i - 2] + 2;
            if (f[i - 2] == 0)
              g[i] = i - 2;
            else
              g[i] = g[i - 2];
            //))
          } else {
            if (f[i - 1] != 0 && g[i - 1] != -1 && cs[g[i - 1]] == L) {
              f[i] = f[i - 1] + 2;
              g[i] = g[i - 1] - 1;
              if (g[i] != -1 && f[g[i]] != 0) {
                f[i] += f[g[i]];
                g[i] = g[g[i]];
              }
            } else {
              g[i] = -1;
            }
          }
        }
        max = Math.max(max, f[i]);
      }
      
      return max;
    }
    
    
    /*
     * dp[i]:以索引 i 处结尾的最大有效子串长度。
     *      dp[i]=dp[i-2]+2                 ,s[i-1]=( && s[i]=)
     *           =dp[i-1]+dp[i-dp[i-1]-2]+2 ,s[i-1]=) && s[i]=) && s[i-dp[i-1]-1]=(
     */
    public int longestValidParentheses8(String s) {
      char[] cs = s.toCharArray();
      int n = cs.length;
      int[] dp = new int[n];
      
      int max = 0;
      for (int i = 1; i < n; i++) {
        if (cs[i] == R)
          if (cs[i - 1] == L) {
            dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
          } else {
            int idx = i - dp[i - 1] - 1;
            if (idx >= 0 && cs[idx] == L)
              dp[i] = dp[i - 1] + (idx - 1 >= 0 ? dp[idx - 1] : 0) + 2;
          }
        max = Math.max(max, dp[i]);
      }
      
      return max;
    }
    
    /*
     * 栈：
     *   保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
     *   栈里其他元素维护左括号的下标
     *   栈底元素初始为 -1
     */
    public int longestValidParentheses7(String s) {
      Deque<Integer> stack = new LinkedList<>();
      stack.push(-1);
      
      int max = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == L) {
          stack.push(i);
        } else {
          stack.pop();
          if (stack.isEmpty())
            stack.push(i);
          else
            max = Math.max(max, i - stack.peek());
        }
      }
      
      return max;
    }
    
    //☆☆☆☆☆ 正向逆向结合(贪心)
    public int longestValidParentheses(String s) {
      int max = 0;
      
      for (int i = 0, l = 0, r = 0; i < s.length(); i++) {
        if (s.charAt(i) == L)
          l++;
        else
          r++;
        
        if (l == r)
          max = Math.max(max, l + r);
        else if (l < r)
          l = r = 0;
      }
      
      for (int i = s.length() - 1, l = 0, r = 0; i >= 0; i--) {
        if (s.charAt(i) == L)
          l++;
        else
          r++;
        
        if (l == r)
          max = Math.max(max, l + r);
        else if (l > r)
          l = r = 0;
      }
      
      return max;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}