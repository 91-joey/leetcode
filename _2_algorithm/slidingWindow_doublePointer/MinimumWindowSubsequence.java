//<p>给定字符串 <code>S</code> and <code>T</code>，找出 <code>S</code> 中最短的（连续）<strong>子串</strong> <code>W</code> ，使得 <code>T</code> 是 <code>W</code> 的 <strong>子序列</strong> 。</p>
//
//<p>如果 <code>S</code> 中没有窗口可以包含 <code>T</code> 中的所有字符，返回空字符串 <code>""</code>。如果有不止一个最短长度的窗口，返回开始位置最靠左的那个。</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>
//S = "abcdebdde", T = "bde"
//<strong>输出：</strong>"bcde"
//<strong>解释：</strong>
//"bcde" 是答案，因为它在相同长度的字符串 "bdde" 出现之前。
//"deb" 不是一个更短的答案，因为在窗口中必须按顺序出现 T 中的元素。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>注：</strong></p>
//
//<ul> 
// <li>所有输入的字符串都只包含小写字母。All the strings in the input will only contain lowercase letters.</li> 
// <li><code>S</code>&nbsp;长度的范围为&nbsp;<code>[1, 20000]</code>。</li> 
// <li><code>T</code>&nbsp;长度的范围为&nbsp;<code>[1, 100]</code>。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li><li>滑动窗口</li></div></div><br><div><li>👍 133</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

//727.最小窗口子序列
//开题时间：2022-10-12 12:10:08
public class MinimumWindowSubsequence {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubsequence().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力
        public String minWindow(String s1, String s2) {
            char[] charsS = s1.toCharArray();
            char[] charsT = s2.toCharArray();
            int start = 0;
            int end = Integer.MAX_VALUE;
            int lenS = charsS.length;
            outer:
            for (int l = 0, r = 0; l < lenS; l++) {
                //find first char of target
                int i = l;
                for (; i < lenS; i++)
                    if (charsS[i] == charsT[0]) {
                        l = i;
                        break;
                    }
                if (i == lenS)
                    break;
                r = l + 1;
                //find consecutive chars of target
                for (int j = 1; j < charsT.length; j++) {
                    for (i = r; i < lenS; i++)
                        if (charsS[i] == charsT[j]) {
                            r = i + 1;
                            break;
                        }
                    if (i == lenS)
                        break outer;
                }

                if (r - l < end - start) {
                    start = l;
                    end = r;
                }
            }

            return end == Integer.MAX_VALUE ? "" : s1.substring(start, end);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}