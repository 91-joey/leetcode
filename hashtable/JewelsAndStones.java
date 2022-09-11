//<p>&nbsp;给你一个字符串 <code>jewels</code>&nbsp;代表石头中宝石的类型，另有一个字符串 <code>stones</code> 代表你拥有的石头。&nbsp;<code>stones</code>&nbsp;中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。</p>
//
//<p>字母区分大小写，因此 <code>"a"</code> 和 <code>"A"</code> 是不同类型的石头。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>jewels = "aA", stones = "aAAbbbb"
//<strong>输出：</strong>3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>jewels = "z", stones = "ZZ"
//<strong>输出：</strong>0<strong>
//</strong></pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;=&nbsp;jewels.length, stones.length &lt;= 50</code></li> 
// <li><code>jewels</code> 和 <code>stones</code> 仅由英文字母组成</li> 
// <li><code>jewels</code> 中的所有字符都是 <strong>唯一的</strong></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 715</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import java.util.HashSet;
import java.util.Set;

//771.宝石与石头
//开题时间：2022-09-10 08:28:40
public class JewelsAndStones {
    public static void main(String[] args) {
        Solution solution = new JewelsAndStones().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //1.hashset m+n m
        public int numJewelsInStones(String jewels, String stones) {
            char[] charsJ = jewels.toCharArray();
            Set<Character> set = new HashSet<>();
            for (char c : charsJ) {
                set.add(c);
            }

            char[] charsS = stones.toCharArray();
            int cnt = 0;
            for (char c : charsS)
                if (set.contains(c))
                    cnt++;

            return cnt;
        }

        //2.array   m+n 58
        public int numJewelsInStones2(String jewels, String stones) {
            char[] charsJ = jewels.toCharArray();
            boolean[] set = new boolean[58];
            for (char c : charsJ) {
                set[c - 'A'] = true;
            }

            char[] charsS = stones.toCharArray();
            int cnt = 0;
            for (char c : charsS)
                if (set[c - 'A'])
                    cnt++;

            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}