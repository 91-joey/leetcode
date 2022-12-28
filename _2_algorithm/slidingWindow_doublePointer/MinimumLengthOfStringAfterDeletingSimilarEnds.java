//<p>给你一个只包含字符 <code>'a'</code>，<code>'b'</code>&nbsp;和 <code>'c'</code>&nbsp;的字符串&nbsp;<code>s</code>&nbsp;，你可以执行下面这个操作（5 个步骤）任意次：</p>
//
//<ol> 
// <li>选择字符串 <code>s</code>&nbsp;一个 <strong>非空</strong> 的前缀，这个前缀的所有字符都相同。</li> 
// <li>选择字符串 <code>s</code>&nbsp;一个 <strong>非空</strong> 的后缀，这个后缀的所有字符都相同。</li> 
// <li>前缀和后缀在字符串中任意位置都不能有交集。</li> 
// <li>前缀和后缀包含的所有字符都要相同。</li> 
// <li>同时删除前缀和后缀。</li> 
//</ol>
//
//<p>请你返回对字符串 <code>s</code>&nbsp;执行上面操作任意次以后（可能 0 次），能得到的 <strong>最短长度</strong>&nbsp;。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>s = "ca"
//<b>输出：</b>2
//<strong>解释：</strong>你没法删除任何一个字符，所以字符串长度仍然保持不变。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>s = "cabaabac"
//<b>输出：</b>0
//<b>解释：</b>最优操作序列为：
//- 选择前缀 "c" 和后缀 "c" 并删除它们，得到 s = "abaaba" 。
//- 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "baab" 。
//- 选择前缀 "b" 和后缀 "b" 并删除它们，得到 s = "aa" 。
//- 选择前缀 "a" 和后缀 "a" 并删除它们，得到 s = "" 。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>s = "aabccabba"
//<b>输出：</b>3
//<b>解释：</b>最优操作序列为：
//- 选择前缀 "aa" 和后缀 "a" 并删除它们，得到 s = "bccabb" 。
//- 选择前缀 "b" 和后缀 "bb" 并删除它们，得到 s = "cca" 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code>&nbsp;只包含字符&nbsp;<code>'a'</code>，<code>'b'</code>&nbsp;和&nbsp;<code>'c'</code>&nbsp;。</li> 
//</ul>
//
//<div><li>👍 27</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

//1750.删除字符串两端相同字符后的最短长度
//开题时间：2022-12-28 09:20:48
public class MinimumLengthOfStringAfterDeletingSimilarEnds {
    public static void main(String[] args) {
        Solution solution = new MinimumLengthOfStringAfterDeletingSimilarEnds().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumLength9(String s) {
            int l = 0, r = s.length() - 1;
            while (l < r && s.charAt(l) == s.charAt(r)) {
                char c = s.charAt(l);
                while (l < r && s.charAt(l) == c) l++;
                if (l == r) return 0;
                while (l < r && s.charAt(r) == c) r--;
            }
            return r - l + 1;
        }

        //☆☆☆☆☆ 双指针（内循环为闭区间）
        public int minimumLength(String s) {
            int l = 0, r = s.length() - 1;
            while (l < r && s.charAt(l) == s.charAt(r)) {
                char c = s.charAt(l);
                while (l <= r && s.charAt(l) == c) l++;
                while (l <= r && s.charAt(r) == c) r--;
            }
            return r - l + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}