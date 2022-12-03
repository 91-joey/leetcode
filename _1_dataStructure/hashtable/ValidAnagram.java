//<p>给定两个字符串 <code><em>s</em></code> 和 <code><em>t</em></code> ，编写一个函数来判断 <code><em>t</em></code> 是否是 <code><em>s</em></code> 的字母异位词。</p>
//
//<p><strong>注意：</strong>若&nbsp;<code><em>s</em></code> 和 <code><em>t</em></code><em>&nbsp;</em>中每个字符出现的次数都相同，则称&nbsp;<code><em>s</em></code> 和 <code><em>t</em></code><em>&nbsp;</em>互为字母异位词。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1:</strong></p>
//
//<pre>
//<strong>输入:</strong> <em>s</em> = "anagram", <em>t</em> = "nagaram"
//<strong>输出:</strong> true
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> <em>s</em> = "rat", <em>t</em> = "car"
//<strong>输出: </strong>false</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length, t.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>s</code> 和 <code>t</code>&nbsp;仅包含小写字母</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶:&nbsp;</strong>如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>
//
//<div><li>👍 702</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

//242.有效的字母异位词
//开题时间：2022-12-03 14:05:17
public class ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isAnagram9(String s, String t) {
            int[] freq = new int[123];

            for (int i = 0; i < s.length(); i++)
                freq[s.charAt(i)]++;

            for (int i = 0; i < t.length(); i++)
                if (freq[t.charAt(i)]-- <= 0)
                    return false;

            for (int i = 97; i < freq.length; i++)
                if (freq[i] > 0)
                    return false;

            return true;
        }

        //官解：先判断长度是否相同
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length())
                return false;

            int[] freq = new int[123];
            for (int i = 0; i < s.length(); i++)
                freq[s.charAt(i)]++;

            for (int i = 0; i < t.length(); i++)
                if (freq[t.charAt(i)]-- <= 0)
                    return false;

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}