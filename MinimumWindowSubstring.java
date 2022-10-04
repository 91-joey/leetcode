//<p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>注意：</strong></p>
//
//<ul> 
// <li>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</li> 
// <li>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
//<strong>输出：</strong>"BANC"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "a", t = "a"
//<strong>输出：</strong>"a"
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> s = "a", t = "aa"
//<strong>输出:</strong> ""
//<strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code> 和 <code>t</code> 由英文字母组成</li> 
//</ul>
//
//<p>&nbsp;</p> 
//<strong>进阶：</strong>你能设计一个在 
//<code>o(n)</code> 时间内解决此问题的算法吗？
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 2179</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//76.最小覆盖子串
//开题时间：2022-10-04 17:05:15
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final String EMPTY = "";
        public static final int SZ = 58;

        public String minWindow(String s, String t) {
            int length = s.length();
            if (length < t.length())
                return EMPTY;

            int[] cntS = new int[SZ];
            int[] cntT = new int[SZ];
            for (int i = 0; i < s.length(); i++)
                cntS[s.charAt(i) - 'A']++;
            for (int i = 0; i < t.length(); i++)
                cntT[t.charAt(i) - 'A']++;
            if (!contains(cntS, cntT))
                return EMPTY;
            Arrays.fill(cntS, 0);

            //[l,r) not contain t
            int minLen = Integer.MAX_VALUE;
            String minS = EMPTY;
            for (int l = 0, r = 0; r < length; ) {
                cntS[s.charAt(r++) - 'A']++;
                while (contains(cntS, cntT)) {
                    if (minLen > r - l) {
                        minLen = r - l;
                        minS = s.substring(l, r);
                    }
                    cntS[s.charAt(l++) - 'A']--;
                }
            }

            return minS;
        }

        public String minWindow2(String s, String t) {
            int lengthS = s.length();
            int lengthT = t.length();
            if (lengthS < lengthT)
                return EMPTY;

            int[] cntS = new int[SZ];
            int[] cntT = new int[SZ];
            for (int i = 0; i < s.length(); i++)
                cntS[s.charAt(i) - 'A']++;
            for (int i = 0; i < lengthT; i++)
                cntT[t.charAt(i) - 'A']++;
            if (!contains(cntS, cntT))
                return EMPTY;
            Arrays.fill(cntS, 0);

            //[l,r) not contain t
            int cntValid = 0;
            int minLen = Integer.MAX_VALUE;
            String minS = EMPTY;
            for (int l = 0, r = 0; r < lengthS; ) {
                int i = s.charAt(r++) - 'A';
                if (cntT[i] > 0)
                    cntValid++;
                cntS[i]++;
                while (cntValid >= lengthT && contains(cntS, cntT)) {
                    if (minLen > r - l) {
                        minLen = r - l;
                        minS = s.substring(l, r);
                    }
                    int j = s.charAt(l++) - 'A';
                    if (cntT[j] > 0)
                        cntValid--;
                    cntS[j]--;
                }
            }

            return minS;
        }

        public static boolean contains(int[] cntS, int[] cntT) {
            for (int i = 0; i < SZ; i++)
                if (cntS[i] < cntT[i])
                    return false;

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}