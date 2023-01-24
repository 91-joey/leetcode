//<p>给定字符串 <strong>s</strong> 和 <strong>t</strong> ，判断 <strong>s</strong> 是否为 <strong>t</strong> 的子序列。</p>
//
//<p>字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，<code>"ace"</code>是<code>"abcde"</code>的一个子序列，而<code>"aec"</code>不是）。</p>
//
//<p><strong>进阶：</strong></p>
//
//<p>如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k &gt;= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？</p>
//
//<p><strong>致谢：</strong></p>
//
//<p>特别感谢<strong> </strong><a href="https://leetcode.com/pbrother/">@pbrother&nbsp;</a>添加此问题并且创建所有测试用例。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abc", t = "ahbgdc"
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "axc", t = "ahbgdc"
//<strong>输出：</strong>false
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= s.length &lt;= 100</code></li> 
// <li><code>0 &lt;= t.length &lt;= 10^4</code></li> 
// <li>两个字符串都只由小写字符组成。</li> 
//</ul>
//
//<div><li>👍 752</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.Arrays;

//392.判断子序列
//开题时间：2022-11-17 13:51:12
public class IsSubsequence {
    public static void main(String[] args) {
        Solution solution = new IsSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isSubsequence99(String s, String t) {
            if (s.length() > t.length())
                return false;

            for (int i = 0, j = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                while (j < t.length() && c != t.charAt(j))
                    j++;
                if (j == t.length())
                    return false;
                j++;
            }
            return true;
        }

        public boolean isSubsequence88(String s, String t) {
            for (int i = 0, j = -1; i < s.length(); i++)
                if ((j = t.indexOf(s.charAt(i), j + 1)) == -1)
                    return false;
            return true;
        }

        //☆☆☆☆☆ 贪心 + 双指针
        public boolean isSubsequence9(String s, String t) {
            int i = 0, j = 0;
            while (i < s.length() && j < t.length())
                if (s.charAt(i) == t.charAt(j++))
                    i++;
            return i == s.length();
        }

        //☆☆☆☆☆ 后续挑战： dp 预处理每个索引开始每个字符第一次出现的位置
        public boolean isSubsequence(String s, String t) {
            int m = s.length(), n = t.length();
            int[][] f = new int[n + 1][26];
            Arrays.fill(f[n], n);

            for (int i = n - 1; i >= 0; i--)
                for (int j = 0; j < 26; j++)
                    f[i][j] = t.charAt(i) == (char) ('a' + j) ?
                            i :
                            f[i + 1][j];

            int i = 0, j = -1;
            while (i < m)
                if ((j = f[j + 1][s.charAt(i) - 'a']) == n)
                    break;
                else
                    i++;
            return i == m;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}