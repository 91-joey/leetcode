//<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;，判断它们是否是同构的。</p>
//
//<p>如果&nbsp;<code>s</code>&nbsp;中的字符可以按某种映射关系替换得到&nbsp;<code>t</code>&nbsp;，那么这两个字符串是同构的。</p>
//
//<p>每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入：</strong>s = <span><code>"egg", </code></span>t = <span><code>"add"</code></span>
//<strong>输出：</strong>true
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = <span><code>"foo", </code></span>t = <span><code>"bar"</code></span>
//<strong>输出：</strong>false</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = <span><code>"paper", </code></span>t = <span><code>"title"</code></span>
//<strong>输出：</strong>true</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<p>
// <meta charset="UTF-8" /></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li> 
// <li><code>t.length == s.length</code></li> 
// <li><code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;由任意有效的 ASCII 字符组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 512</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import java.util.HashMap;
import java.util.Map;

//205.同构字符串
//开题时间：2022-09-05 10:30:52
public class IsomorphicStrings {
    public static void main(String[] args) {
        Solution solution = new IsomorphicStrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.joey(hashtable*2)   n   m
        //执行用时：8 ms, 在所有 Java 提交中击败了86.44%的用户
        //内存消耗：40.9 MB, 在所有 Java 提交中击败了97.04%的用户
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> s2t = new HashMap<>();
            Map<Character, Character> t2s = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                char x = s.charAt(i);
                char y = t.charAt(i);
                Character val = s2t.get(x);
                if (val == null) {
                    if (t2s.containsKey(y)) {
                        return false;
                    }
                    s2t.put(x, y);
                    t2s.put(y, x);
                } else if (y != val) {
                    return false;
                }
            }

            return true;
        }

        //高分解（数组）   n   m
        public boolean isIsomorphic2(String s, String t) {
            int[] sArr = new int[128];
            int[] tArr = new int[128];

            for (int i = 0; i < s.length(); i++) {
                char x = s.charAt(i);
                char y = t.charAt(i);
                if (sArr[x] == tArr[y]) {
                    if (sArr[x] == 0) {
                        sArr[x] = i + 1;
                        tArr[y] = i + 1;
                    }
                } else {
                    return false;
                }
            }

            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}