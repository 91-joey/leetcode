//<p>单词的 <strong>缩写</strong> 需要遵循&nbsp;
// <起始字母>
//  <中间字母数>
//   <结尾字母>
//     这样的格式。如果单词只有两个字符，那么它就是它自身的 
//    <strong>缩写</strong> 。
//   </结尾字母>
//  </中间字母数>
// </起始字母></p>
//
//<p>以下是一些单词缩写的范例：</p>
//
//<ul> 
// <li><code>dog --&gt; d1g</code> 因为第一个字母 <code>'d'</code> 和最后一个字母 <code>'g'</code> 之间有 <code>1</code> 个字母</li> 
// <li><code>internationalization --&gt; i18n</code> 因为第一个字母 <code>'i'</code> 和最后一个字母 <code>'n'</code> 之间有 <code>18</code> 个字母</li> 
// <li><code>it --&gt; it</code> 单词只有两个字符，它就是它自身的 <strong>缩写</strong></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p>实现 <code>ValidWordAbbr</code> 类：</p>
//
//<ul> 
// <li><code>ValidWordAbbr(String[] dictionary)</code> 使用单词字典 <code>dictionary</code> 初始化对象</li> 
// <li><code>boolean isUnique(string word)</code> 如果满足下述任意一个条件，返回 <code>true</code> ；否则，返回 <code>false</code> ： 
//  <ul> 
//   <li>字典 <code>dictionary</code> 中没有任何其他单词的 <strong>缩写</strong> 与该单词 <code>word</code> 的 <strong>缩写</strong> 相同。</li> 
//   <li>字典 <code>dictionary</code> 中的所有 <strong>缩写</strong> 与该单词 <code>word</code> 的 <strong>缩写</strong> 相同的单词都与 <code>word</code> <strong>相同</strong> 。</li> 
//  </ul> </li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入</strong>
//["ValidWordAbbr", "isUnique", "isUnique", "isUnique", "isUnique", "isUnique"]
//[[["deer", "door", "cake", "card"]], ["dear"], ["cart"], ["cane"], ["make"], ["cake"]]
//<strong>输出
//</strong>[null, false, true, false, true, true]
//
//<strong>解释</strong>
//ValidWordAbbr validWordAbbr = new ValidWordAbbr(["deer", "door", "cake", "card"]);
//validWordAbbr.isUnique("dear"); // 返回 false，字典中的 "deer" 与输入 "dear" 的缩写都是 "d2r"，但这两个单词不相同
//validWordAbbr.isUnique("cart"); // 返回 true，字典中不存在缩写为 "c2t" 的单词
//validWordAbbr.isUnique("cane"); // 返回 false，字典中的 "cake" 与输入 "cane" 的缩写都是 "c2e"，但这两个单词不相同
//validWordAbbr.isUnique("make"); // 返回 true，字典中不存在缩写为 "m2e" 的单词
//validWordAbbr.isUnique("cake"); // 返回 true，因为 "cake" 已经存在于字典中，并且字典中没有其他缩写为 "c2e" 的单词
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= dictionary.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= dictionary[i].length &lt;= 20</code></li> 
// <li><code>dictionary[i]</code> 由小写英文字母组成</li> 
// <li><code>1 &lt;= word &lt;= 20</code></li> 
// <li><code>word</code> 由小写英文字母组成</li> 
// <li>最多调用 <code>5000</code> 次 <code>isUnique</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 17</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.HashMap;
import java.util.Map;

//288.单词的唯一缩写
//开题时间：2022-09-11 10:56:26
public class UniqueWordAbbreviation {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //hashmap
    class ValidWordAbbr {
        Map<String, String> map = new HashMap<>();

        public ValidWordAbbr(String[] dictionary) {
            for (String s : dictionary)
                map.merge(getAbbr(s), s, (oldS, newS) -> oldS.equals(s) ? oldS : "DISTINCT");
        }

        public boolean isUnique(String word) {
            String value = map.get(getAbbr(word));
            return value == null || value.equals(word);
        }

        private String getAbbr(String s) {
            int length = s.length();
            return length <= 2 ? s : s.charAt(0) + String.valueOf(length - 2) + s.charAt(length - 1);
        }
    }

    //array
    class ValidWordAbbr2 {
        String[] map = new String[12870];

        public ValidWordAbbr2(String[] dictionary) {
            for (String s : dictionary) {
                int idx = getAbbrIdx(s);
                if (map[idx] == null) {
                    map[idx] = s;
                } else if (!s.equals(map[idx])) {
                    map[idx] = "DISTINCT";
                }
            }
        }

        public boolean isUnique(String word) {
            String value = map[getAbbrIdx(word)];
            return value == null || value.equals(word);
        }

        private int getAbbrIdx(String s) {
            int length = s.length();
            return length <= 1 ? s.charAt(0) - 'a' : 26 + (s.charAt(0) - 'a') * 494 + (length - 2) * 26 + (s.charAt(length - 1) - 'a');
        }
    }

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
//leetcode submit region end(Prohibit modification and deletion)
}