//<p>给你一个字符串&nbsp;<code>sequence</code>&nbsp;，如果字符串 <code>word</code>&nbsp;连续重复&nbsp;<code>k</code>&nbsp;次形成的字符串是&nbsp;<code>sequence</code>&nbsp;的一个子字符串，那么单词&nbsp;<code>word</code> 的 <strong>重复值为 <code>k</code></strong><strong> </strong>。单词 <code>word</code>&nbsp;的 <strong>最</strong><strong>大重复值</strong>&nbsp;是单词&nbsp;<code>word</code>&nbsp;在&nbsp;<code>sequence</code>&nbsp;中最大的重复值。如果&nbsp;<code>word</code>&nbsp;不是&nbsp;<code>sequence</code>&nbsp;的子串，那么重复值&nbsp;<code>k</code>&nbsp;为 <code>0</code> 。</p>
//
//<p>给你一个字符串 <code>sequence</code>&nbsp;和 <code>word</code>&nbsp;，请你返回 <strong>最大重复值&nbsp;<code>k</code> </strong>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>sequence = "ababc", word = "ab"
//<b>输出：</b>2
//<strong>解释：</strong>"abab" 是 "<strong>abab</strong>c" 的子字符串。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>sequence = "ababc", word = "ba"
//<b>输出：</b>1
//<strong>解释：</strong>"ba" 是 "a<strong>ba</strong>bc" 的子字符串，但 "baba" 不是 "ababc" 的子字符串。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>sequence = "ababc", word = "ac"
//<b>输出：</b>0
//<strong>解释：</strong>"ac" 不是 "ababc" 的子字符串。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= sequence.length &lt;= 100</code></li> 
// <li><code>1 &lt;= word.length &lt;= 100</code></li> 
// <li><code>sequence</code> 和&nbsp;<code>word</code>&nbsp;都只包含小写英文字母。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>字符串</li><li>字符串匹配</li></div></div><br><div><li>👍 63</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//1668.最大重复子字符串
//开题时间：2022-11-03 10:31:16
public class MaximumRepeatingSubstring {
    public static void main(String[] args) {
        Solution solution = new MaximumRepeatingSubstring().new Solution();
//        System.out.println(solution.maxRepeating2("ababc", "ab"));
        System.out.println(solution.maxRepeating2("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力
        public int maxRepeating(String sequence, String word) {
            if (!sequence.contains(word))
                return 0;

            int max = sequence.length() / word.length();
            while (true) {
                if (sequence.contains(word.repeat(max)))
                    return max;
                max--;
            }
        }

        public int maxRepeating2(String sequence, String word) {
            /*int max = 0;
            char[] chars = word.toCharArray();
            int len = chars.length;
            int l = sequence.indexOf(chars[0]), r = l + 1;
            if (l == -1)
                return 0;
            for (; r < sequence.length(); ) {
                char c = sequence.charAt(r);
                if (c == chars[(r - l) % len])
                    r++;
                else {
                    max = Math.max(max, (r - l) / len);
                    l = sequence.indexOf(chars[0], r - len);
                    if (l == -1)
                        break;
                    r = l + 1;
                }
            }

            if (l != -1)
                max = Math.max(max, (r - l) / len);
            return max;*/
            return 0;
        }

        public int maxRepeating3(String sequence, String word) {
            int max = 1;
            while (sequence.contains(word.repeat(max))) {
                max++;
            }
            return max - 1;
        }

        public int maxRepeating4(String sequence, String word) {
            int max = 0;
            StringBuilder sb = new StringBuilder(word);
            while (sequence.contains(sb.toString())) {
                sb.append(word);
                max++;
            }
            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}