//<p>给你一个字符串 <code>s</code> ，仅反转字符串中的所有元音字母，并返回结果字符串。</p>
//
//<p>元音字母包括 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code>，且可能以大小写两种形式出现。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "hello"
//<strong>输出：</strong>"holle"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "leetcode"
//<strong>输出：</strong>"leotcede"</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li> 
// <li><code>s</code> 由 <strong>可打印的 ASCII</strong> 字符组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 268</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

//345.反转字符串中的元音字母
//开题时间：2022-10-23 18:27:29
public class ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution solution = new ReverseVowelsOfAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseVowels(String s) {
            char[] chars = s.toCharArray();
            for (int l = 0, r = chars.length - 1; l < r; ) {
                while (l < r && isNotVowel(chars[l])) l++;
                while (l < r && isNotVowel(chars[r])) r--;
                if (l < r) {
                    char tmp = chars[l];
                    chars[l++] = chars[r];
                    chars[r--] = tmp;
                }
            }
            return new String(chars);
        }

        public static boolean isNotVowel(char c) {
            return switch (c) {
                case 'a', 'e', 'i', 'o', 'u',
                        'A', 'E', 'I', 'O', 'U' -> false;
                default -> true;
            };
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}