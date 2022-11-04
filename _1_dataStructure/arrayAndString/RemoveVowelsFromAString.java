//<p>给你一个字符串 <code>s</code>&nbsp;，请你删去其中的所有元音字母&nbsp;<code>'a'</code>，<code>'e'</code>，<code>'i'</code>，<code>'o'</code>，<code>'u'</code>，并返回这个新字符串。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "leetcodeisacommunityforcoders"
//<strong>输出：</strong>"ltcdscmmntyfrcdrs"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aeiou"
//<strong>输出：</strong>""
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= S.length &lt;= 1000</code></li> 
// <li><code>s</code>&nbsp;仅由小写英文字母组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 18</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;


//1119.删去字符串中的元音
//开题时间：2022-11-04 08:27:00
public class RemoveVowelsFromAString {
    public static void main(String[] args) {
        Solution solution = new RemoveVowelsFromAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String removeVowels(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isNotVowel(c))
                    sb.append(c);
            }
            return sb.toString();
        }


        public boolean isNotVowel(char c) {
            return switch (c) {
                case 'a', 'e', 'i', 'o', 'u' -> false;
                default -> true;
            };
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}