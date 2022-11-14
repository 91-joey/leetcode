//<p>给你一个由小写英文字母组成的字符串 <code>s</code> ，请你找出并返回第一个出现 <strong>两次</strong> 的字母。</p>
//
//<p><strong>注意：</strong></p>
//
//<ul> 
// <li>如果 <code>a</code> 的 <strong>第二次</strong> 出现比 <code>b</code> 的 <strong>第二次</strong> 出现在字符串中的位置更靠前，则认为字母 <code>a</code> 在字母 <code>b</code> 之前出现两次。</li> 
// <li><code>s</code> 包含至少一个出现两次的字母。</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>s = "abccbaacz"
//<strong>输出：</strong>"c"
//<strong>解释：</strong>
//字母 'a' 在下标 0 、5 和 6 处出现。
//字母 'b' 在下标 1 和 4 处出现。
//字母 'c' 在下标 2 、3 和 7 处出现。
//字母 'z' 在下标 8 处出现。
//字母 'c' 是第一个出现两次的字母，因为在所有字母中，'c' 第二次出现的下标是最小的。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>s = "abcdd"
//<strong>输出：</strong>"d"
//<strong>解释：</strong>
//只有字母 'd' 出现两次，所以返回 'd' 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= s.length &lt;= 100</code></li> 
// <li><code>s</code> 由小写英文字母组成</li> 
// <li><code>s</code> 包含至少一个重复字母</li> 
//</ul>
//
//<div><li>👍 13</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

//2351.第一个出现两次的字母
//开题时间：2022-11-14 14:58:27
public class FirstLetterToAppearTwice {
    public static void main(String[] args) {
        Solution solution = new FirstLetterToAppearTwice().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //计数（数组）
        public char repeatedCharacter9(String s) {
            int[] freqs = new int[123];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (freqs[c]++ == 1)
                    return c;
            }
            return '\u0000';
        }

        //计数（位运算）
        public char repeatedCharacter(String s) {
            for (int i = 0, freq = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (freq == (freq |= 1 << (c & 31)))
                    return c;
            }
            return '\u0000';
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}