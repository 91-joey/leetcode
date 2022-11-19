//<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个字符规律&nbsp;<code>p</code>，请你来实现一个支持 <code>'.'</code>&nbsp;和&nbsp;<code>'*'</code>&nbsp;的正则表达式匹配。</p>
//
//<ul> 
// <li><code>'.'</code> 匹配任意单个字符</li> 
// <li><code>'*'</code> 匹配零个或多个前面的那一个元素</li> 
//</ul>
//
//<p>所谓匹配，是要涵盖&nbsp;<strong>整个&nbsp;</strong>字符串&nbsp;<code>s</code>的，而不是部分字符串。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aa", p = "a"
//<strong>输出：</strong>false
//<strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aa", p = "a*"
//<strong>输出：</strong>true
//<strong>解释：</strong>因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//</pre>
//
//<p><strong>示例&nbsp;3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ab", p = ".*"
//<strong>输出：</strong>true
//<strong>解释：</strong>".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li> 
// <li><code>1 &lt;= p.length&nbsp;&lt;= 30</code></li> 
// <li><code>s</code>&nbsp;只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li> 
// <li><code>p</code>&nbsp;只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母，以及字符&nbsp;<code>.</code>&nbsp;和&nbsp;<code>*</code>。</li> 
// <li>保证每次出现字符&nbsp;<code>*</code> 时，前面都匹配到有效的字符</li> 
//</ul>
//
//<div><li>👍 3325</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//10.正则表达式匹配
//开题时间：2022-11-19 10:49:27
public class RegularExpressionMatching {
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isMatch9(String s, String p) {
            return s.matches(p);
        }

        //todo DP
        public boolean isMatch(String s, String p) {
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}