//<p>给你一个字符串 <code>s</code> ，请你统计并返回这个字符串中 <strong>回文子串</strong> 的数目。</p>
//
//<p><strong>回文字符串</strong> 是正着读和倒过来读一样的字符串。</p>
//
//<p><strong>子字符串</strong> 是字符串中的由连续字符组成的一个序列。</p>
//
//<p>具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "abc"
//<strong>输出：</strong>3
//<strong>解释：</strong>三个回文子串: "a", "b", "c"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>s = "aaa"
//<strong>输出：</strong>6
//<strong>解释：</strong>6个回文子串: "a", "a", "a", "aa", "aa", "aaa"</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 1000</code></li> 
// <li><code>s</code> 由小写英文字母组成</li> 
//</ul>
//
//<div><li>👍 1037</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//647.回文子串
//开题时间：2022-12-20 14:33:11
public class PalindromicSubstrings {
    public static void main(String[] args) {
        Solution solution = new PalindromicSubstrings().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //☆☆☆☆ 中心扩散法
        public int countSubstrings9(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;

            int ans = 1;
            for (int i = 0; i < n - 1; i++)
                ans += centerSpread(cs, i, i) + centerSpread(cs, i, i + 1);

            return ans;
        }

        private int centerSpread(char[] cs, int l, int r) {
            while (0 <= l && r < cs.length && cs[l] == cs[r]) {
                l--;
                r++;
            }
            return (r - l) / 2;
        }

        //DP（滚动数组）
        public int countSubstrings(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            boolean[] f = new boolean[n];

            int ans = n;
            for (int i = n - 2; i >= 0; i--)
                for (int j = n - 1; j >= i + 1; j--) {
                    f[j] = (j - i <= 2 || f[j - 1]) && cs[i] == cs[j];
                    if (f[j])
                        ans++;
                }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}