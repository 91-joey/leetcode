//<p>给定两个单词&nbsp;<code>word1</code>&nbsp;和
// <meta charset="UTF-8" />&nbsp;<code>word2</code>&nbsp;，返回使得
// <meta charset="UTF-8" />&nbsp;<code>word1</code>&nbsp;和&nbsp;
// <meta charset="UTF-8" />&nbsp;<code>word2</code><em>&nbsp;</em><strong>相同</strong>所需的<strong>最小步数</strong>。</p>
//
//<p><strong>每步&nbsp;</strong>可以删除任意一个字符串中的一个字符。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入:</strong> word1 = "sea", word2 = "eat"
//<strong>输出:</strong> 2
//<strong>解释:</strong> 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
//</pre>
//
//<p><strong>示例 &nbsp;2:</strong></p>
//
//<pre>
//<b>输入：</b>word1 = "leetcode", word2 = "etco"
//<b>输出：</b>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p> 
//<meta charset="UTF-8" />
//
//<ul> 
// <li><code>1 &lt;= word1.length, word2.length &lt;= 500</code></li> 
// <li><code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;只包含小写英文字母</li> 
//</ul>
//
//<div><li>👍 512</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//583.两个字符串的删除操作
//开题时间：2022-12-05 14:53:11
public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {
        Solution solution = new DeleteOperationForTwoStrings().new Solution();
        System.out.println(solution.minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String a, String b) {
            int m = a.length();
            int n = b.length();

            if (m > n) return minDistance(b, a);

            int max = 0;
            int size = m + 1;
            int[] f = new int[size];
            int[] g = new int[size];

            for (int i = 1; i < size; i++) {
                char c = a.charAt(i - 1);
                int idx = b.lastIndexOf(c);
                if (idx != -1) {
                    f[i] = 1;
                    g[i] = idx;
                    for (int j = i - 1; j > 0; j--) {
                        idx = b.indexOf(c, g[j] + 1);
                        if (idx != -1 && (f[i] < f[j] + 1 || (f[i] == f[j] + 1 && idx < g[i]))) {
                            f[i] = f[j] + 1;
                            g[i] = idx;
                        }
                    }
                }
                max = Math.max(max, f[i]);
            }

            return m + n - max * 2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}