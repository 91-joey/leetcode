//<p>给你一个字符串数组 <code>words</code> 和一个字符串 <code>pref</code> 。</p>
//
//<p>返回 <code>words</code><em> </em>中以 <code>pref</code> 作为 <strong>前缀</strong> 的字符串的数目。</p>
//
//<p>字符串 <code>s</code> 的 <strong>前缀</strong> 就是&nbsp; <code>s</code> 的任一前导连续字符串。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>words = ["pay","<em><strong>at</strong></em>tention","practice","<em><strong>at</strong></em>tend"], <span><code>pref </code></span>= "at"
//<strong>输出：</strong>2
//<strong>解释：</strong>以 "at" 作为前缀的字符串有两个，分别是："<em><strong>at</strong></em>tention" 和 "<em><strong>at</strong></em>tend" 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>words = ["leetcode","win","loops","success"], <span><code>pref </code></span>= "code"
//<strong>输出：</strong>0
//<strong>解释：</strong>不存在以 "code" 作为前缀的字符串。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= words.length &lt;= 100</code></li> 
// <li><code>1 &lt;= words[i].length, pref.length &lt;= 100</code></li> 
// <li><code>words[i]</code> 和 <code>pref</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 16</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

import java.util.Arrays;

//2185.统计包含给定前缀的字符串
//开题时间：2023-01-08 09:50:05
public class CountingWordsWithAGivenPrefix {
    public static void main(String[] args) {
        Solution solution = new CountingWordsWithAGivenPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int prefixCount(String[] words, String pref) {
            return (int) Arrays.stream(words).filter(s->s.startsWith(pref)).count();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}