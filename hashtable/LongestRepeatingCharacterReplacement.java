//<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 <code>k</code> 次。</p>
//
//<p>在执行上述操作后，返回包含相同字母的最长子字符串的长度。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "ABAB", k = 2
//<strong>输出：</strong>4
//<strong>解释：</strong>用两个'A'替换为两个'B',反之亦然。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "AABABBA", k = 1
//<strong>输出：</strong>4
//<strong>解释：</strong>
//将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
//子串 "BBBB" 有最长重复字母, 答案为 4。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>s</code> 仅由大写英文字母组成</li> 
// <li><code>0 &lt;= k &lt;= s.length</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 686</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import java.util.HashMap;
import java.util.Map;

//424.替换后的最长重复字符
//开题时间：2022-09-06 11:03:17
public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        Solution solution = new LongestRepeatingCharacterReplacement().new Solution();
        System.out.println(solution.characterReplacement2("ABAB", 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //双指针+哈希表   n   n
        public int characterReplacement(String s, int k) {
            Map<Character, Integer> map = new HashMap<>();
            int max = 1;
            int sum = 0;
            int start = 0;
            int length = s.length();
            int maxCharCnt = 0;
            for (int i = 0; i < length; i++) {
                char c = s.charAt(i);
                map.merge(c, 1, Integer::sum);
                maxCharCnt = Math.max(maxCharCnt, map.get(c));
                sum++;
                if (k < sum - maxCharCnt) {
                    max = Math.max(max, sum - 1);
                    map.compute(s.charAt(start), (character, integer) -> integer - 1);
                    start++;
                    sum--;
                }
            }

            max = Math.max(max, length - start);
            return max;
        }

        //双指针+数组   n   A
        public int characterReplacement2(String s, int k) {
            int length = s.length();
            int maxCharCnt = 1;
            int max = 1;
            int[] freqs = new int[26];
            int l = 0;
            char[] chars = s.toCharArray();

            for (int r = 0; r < length; r++) {
                int freq = ++freqs[chars[r] - 'A'];
                maxCharCnt = Math.max(maxCharCnt, freq);
                if (k <= r - l - maxCharCnt) {
                    max = Math.max(max, r - l);
                    freqs[chars[l] - 'A']--;
                    l++;
                }
            }

            max = Math.max(max, length - l);
            return max;
        }

        public int characterReplacement3(String s, int k) {
            int length = s.length();
            int max = 1;
            if (k == 0) {
                for (int i = 0, j = 1; j < length; j++) {
                    if (max > length - 1 - i) {
                        break;
                    }
                    if (s.charAt(j) != s.charAt(j - 1)) {
                        max = Math.max(max, j - i);
                        i = j;
                    } else if (j == length - 1) {
                        max = Math.max(max, j - i + 1);
                    }
                }
                return max;
            }

            Entry[] entries = new Entry[2];
            entries[0] = new Entry(s.charAt(0), 1);
            int lastCommonLength = 1;
            for (int i = 1; i < length; i++) {
                char cPre = s.charAt(i - 1);
                char c = s.charAt(i);

                if (entries[0].c == c) {
                    entries[0].cnt++;
                } else if (entries[1] == null) {
                    entries[1] = new Entry(c, 1);
                } else if (entries[1].c == c) {
                    entries[1].cnt++;
                } else {
                    max = Math.max(max, entries[0].cnt + entries[1].cnt);
                    entries[0] = new Entry(cPre, lastCommonLength);
                    entries[1] = new Entry(c, 1);
                }

                if (entries[1] != null && k == Math.min(entries[0].cnt, entries[1].cnt)) {
                    max = Math.max(max, entries[0].cnt + entries[1].cnt);
                    entries[0].cnt--;
                }

                if (c == cPre) {
                    lastCommonLength++;
                } else {
                    lastCommonLength = 1;
                }
            }

            if (entries[1] == null) {
                return length;
            }

            max = Math.max(max, entries[0].cnt + entries[1].cnt);

            return max;
        }

        class Entry {
            public char c;
            public int cnt;

            public Entry(char c, int cnt) {
                this.c = c;
                this.cnt = cnt;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}