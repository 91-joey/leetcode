//<p>给你两个字符串 <code>word1</code> 和 <code>word2</code> 。你需要按下述方式构造一个新字符串 <code>merge</code> ：如果 <code>word1</code> 或 <code>word2</code> 非空，选择 <strong>下面选项之一</strong> 继续操作：</p>
//
//<ul> 
// <li>如果 <code>word1</code> 非空，将 <code>word1</code> 中的第一个字符附加到 <code>merge</code> 的末尾，并将其从 <code>word1</code> 中移除。 </li>
//</ul>
//
//    <ul>
//    	<li>例如，<code>word1 = "abc" </code>且 <code>merge = "dv"</code> ，在执行此选项操作之后，<code>word1 = "bc"</code> ，同时 <code>merge = "dva"</code> 。</li>
//    </ul>
//    </li>
//    <li>如果 <code>word2</code> 非空，将 <code>word2</code> 中的第一个字符附加到 <code>merge</code> 的末尾，并将其从 <code>word2</code> 中移除。
//    <ul>
//    	<li>例如，<code>word2 = "abc" </code>且 <code>merge = ""</code> ，在执行此选项操作之后，<code>word2 = "bc"</code> ，同时 <code>merge = "a"</code> 。</li>
//    </ul>
//    </li>
//
//
//<p>返回你可以构造的字典序 <strong>最大</strong> 的合并字符串<em> </em><code>merge</code><em> 。</em></p>
//
//<p>长度相同的两个字符串 <code>a</code> 和 <code>b</code> 比较字典序大小，如果在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置，<code>a</code> 中字符在字母表中的出现顺序位于 <code>b</code> 中相应字符之后，就认为字符串 <code>a</code> 按字典序比字符串 <code>b</code> 更大。例如，<code>"abcd"</code> 按字典序比 <code>"abcc"</code> 更大，因为两个字符串出现不同的第一个位置是第四个字符，而 <code>d</code> 在字母表中的出现顺序位于 <code>c</code> 之后。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "cabaa", word2 = "bcaaa"
//<strong>输出：</strong>"cbcabaaaaa"
//<strong>解释：</strong>构造字典序最大的合并字符串，可行的一种方法如下所示：
//- 从 word1 中取第一个字符：merge = "c"，word1 = "abaa"，word2 = "bcaaa"
//- 从 word2 中取第一个字符：merge = "cb"，word1 = "abaa"，word2 = "caaa"
//- 从 word2 中取第一个字符：merge = "cbc"，word1 = "abaa"，word2 = "aaa"
//- 从 word1 中取第一个字符：merge = "cbca"，word1 = "baa"，word2 = "aaa"
//- 从 word1 中取第一个字符：merge = "cbcab"，word1 = "aa"，word2 = "aaa"
//- 将 word1 和 word2 中剩下的 5 个 a 附加到 merge 的末尾。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>word1 = "abcabc", word2 = "abdcaba"
//<strong>输出：</strong>"abdcabcabcaba"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= word1.length, word2.length &lt;= 3000</code></li> 
// <li><code>word1</code> 和 <code>word2</code> 仅由小写英文组成</li> 
//</ul>
//
//<div><li>👍 41</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

//1754.构造字典序最大的合并字符串
//开题时间：2022-12-24 10:09:03
public class LargestMergeOfTwoStrings {
    public static void main(String[] args) {
        Solution solution = new LargestMergeOfTwoStrings().new Solution();
//        System.out.println(solution.largestMerge("cabaa", "bcaaa"));
        System.out.println(solution.largestMerge("qqqqqqqqqeqeqqeeqqq", "qqqqqqqqeqqqeeqqeeqqqqqeq"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestMergeX(String word1, String word2) {
            char[] cs1 = word1.toCharArray();
            char[] cs2 = word2.toCharArray();
            int m = cs1.length;
            int n = cs2.length;
            StringBuilder sb = new StringBuilder(m + n);
            int i = m - 1, j = n - 1;
            while (0 <= i && 0 <= j) {
                if (cs1[i] == cs2[j]) {
                    int p = i - 1, q = j - 1;
                    while (p >= 0 && q >= 0 && cs1[p] == cs2[q] && cs1[p] <= cs1[p + 1]) {
                        p--;
                        q--;
                    }
                    if ((p < 0 && q >= 0 && cs2[q] >= cs2[q + 1]) || (p >= 0 && q >= 0 && cs1[p] < cs2[q])) {
                        while (i >= Math.max(p, 0))
                            sb.append(cs1[i--]);
                    } else
                        while (j >= Math.max(q, 0))
                            sb.append(cs2[j--]);
                } else
                    sb.append(cs1[i] < cs2[j] ? cs1[i--] : cs2[j--]);
            }
            if (j < 0) {
                while (i >= 0)
                    sb.append(cs1[i--]);
            } else
                while (j >= 0)
                    sb.append(cs2[j--]);

            return sb.reverse().toString();
        }

        public String largestMergeXX(String word1, String word2) {
            StringBuilder sb = new StringBuilder();

            if (word1.length() <= 0)
                sb.append(word2);
            else if (word2.length() <= 0)
                sb.append(word1);
            else if (word1.charAt(0) < word2.charAt(0))
                sb.append(word2.charAt(0)).append(largestMerge(word1, word2.substring(1)));
            else if (word1.charAt(0) > word2.charAt(0))
                sb.append(word1.charAt(0)).append(largestMerge(word1.substring(1), word2));
            else {
                String a = largestMerge(word1, word2.substring(1));
                String b = largestMerge(word1.substring(1), word2);
                sb.append(word1.charAt(0)).append(a.compareTo(b) < 0 ? b : a);
            }

            return sb.toString();
        }

        char[] cs1;
        char[] cs2;

        public String largestMergeXXX(String word1, String word2) {
            cs1 = word1.toCharArray();
            cs2 = word2.toCharArray();
            return helper(0, 0);
        }

        private String helper(int i, int j) {
            String ans = "";

            if (i >= cs1.length)
                ans += new String(cs2, j, cs2.length - j);
            else if (j >= cs2.length)
                ans += new String(cs1, i, cs1.length - i);
            else if (cs1[i] < cs2[j])
                ans += (cs2[j]) + (helper(i, j + 1));
            else if (cs1[i] > cs2[j])
                ans += (cs1[i]) + (helper(i + 1, j));
            else {
                String a = helper(i, j + 1);
                String b = helper(i + 1, j);
                ans += (cs1[i]) + (a.compareTo(b) < 0 ? b : a);
            }

            return ans;
        }

        //☆☆☆☆☆ 贪心 O((m+n)×max(m,n))
        public String largestMerge9(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            StringBuilder sb = new StringBuilder(m + n);
            while (0 < word1.length() + word2.length())
                if (word1.compareTo(word2) < 0) {
                    sb.append(word2.charAt(0));
                    word2 = word2.substring(1);
                } else {
                    sb.append(word1.charAt(0));
                    word1 = word1.substring(1);
                }
            return sb.toString();
        }

        public String largestMerge(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();
            StringBuilder sb = new StringBuilder(m + n);
            int i = 0, j = 0;
            while (i < m && j < n)
                sb.append(word1.substring(i).compareTo(word2.substring(j)) > 0 ?
                        word1.charAt(i++) :
                        word2.charAt(j++)
                );
            return sb.append(i < m ? word1.substring(i) : word2.substring(j)).toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}