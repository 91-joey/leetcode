//<p>给你两个长度相同的字符串，<code>s</code> 和 <code>t</code>。</p>
//
//<p>将 <code>s</code>&nbsp;中的第&nbsp;<code>i</code>&nbsp;个字符变到&nbsp;<code>t</code>&nbsp;中的第 <code>i</code> 个字符需要&nbsp;<code>|s[i] - t[i]|</code>&nbsp;的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。</p>
//
//<p>用于变更字符串的最大预算是&nbsp;<code>maxCost</code>。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。</p>
//
//<p>如果你可以将 <code>s</code> 的子字符串转化为它在 <code>t</code> 中对应的子字符串，则返回可以转化的最大长度。</p>
//
//<p>如果 <code>s</code> 中没有子字符串可以转化成 <code>t</code> 中对应的子字符串，则返回 <code>0</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abcd", t = "bcdf", maxCost = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>s<strong> </strong>中的<strong> </strong>"abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abcd", t = "cdef", maxCost = 3
//<strong>输出：</strong>1
//<strong>解释：</strong>s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为<span><code> 1。</code></span>
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abcd", t = "acde", maxCost = 0
//<strong>输出：</strong>1
//<strong>解释：</strong>a -&gt; a, cost = 0，字符串未发生变化，所以最大长度为 1。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length, t.length &lt;= 10^5</code></li> 
// <li><code>0 &lt;= maxCost &lt;= 10^6</code></li> 
// <li><code>s</code> 和&nbsp;<code>t</code>&nbsp;都只含小写英文字母。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>字符串</li><li>二分查找</li><li>前缀和</li><li>滑动窗口</li></div></div><br><div><li>👍 179</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

//1208.尽可能使字符串相等
//开题时间：2022-10-07 12:17:32
public class GetEqualSubstringsWithinBudget {
    public static void main(String[] args) {
        Solution solution = new GetEqualSubstringsWithinBudget().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 1.r++
         * 2.l++ if > maxCost
         * 3.run step 1
         */
        public int equalSubstring(String s, String t, int maxCost) {
            int l = 0;
            int r = 0;
            while (r < s.length()) {
                maxCost -= Math.abs(s.charAt(r) - t.charAt(r++));
                if (maxCost < 0)
                    maxCost += Math.abs(s.charAt(l) - t.charAt(l++));
            }
            return r - l;
        }

        public int equalSubstring2(String s, String t, int maxCost) {
            int l = 0;
            int r = 0;
            char[] charsS = s.toCharArray();
            char[] charsT = t.toCharArray();
            while (r < charsS.length) {
                maxCost -= Math.abs(charsS[r] - charsT[r++]);
                if (maxCost < 0)
                    maxCost += Math.abs(charsS[l] - charsT[l++]);
            }
            return r - l;
        }

        /*
        我们可以采取「空间换时间」的方法进行优化：
            由于字符串最坏情况下，会遍历 2 遍，因此存入「字符数组」中
            另外，左右指针每次都需要计算「字符转换开销」，因此存入「字符转换开销数组」中
        */
        public int equalSubstring3(String s, String t, int maxCost) {
            int l = 0;
            int r = 0;
            char[] charsS = s.toCharArray();
            char[] charsT = t.toCharArray();
            int length = charsS.length;
            int[] costs = new int[length];
            for (int i = 0; i < length; i++)
                costs[i] = Math.abs(charsS[i] - charsT[i]);

            while (r < length) {
                maxCost -= costs[r++];
                if (maxCost < 0)
                    maxCost += costs[l++];
            }
            return r - l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}