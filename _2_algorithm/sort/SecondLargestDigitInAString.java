//<p>给你一个混合字符串&nbsp;<code>s</code>&nbsp;，请你返回 <code>s</code>&nbsp;中 <strong>第二大 </strong>的数字，如果不存在第二大的数字，请你返回 <code>-1</code>&nbsp;。</p>
//
//<p><strong>混合字符串 </strong>由小写英文字母和数字组成。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>s = "dfa12321afd"
//<b>输出：</b>2
//<b>解释：</b>出现在 s 中的数字包括 [1, 2, 3] 。第二大的数字是 2 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>s = "abc1111"
//<b>输出：</b>-1
//<b>解释：</b>出现在 s 中的数字只包含 [1] 。没有第二大的数字。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;= 500</code></li> 
// <li><code>s</code>&nbsp;只包含小写英文字母和（或）数字。</li> 
//</ul>
//
//<div><li>👍 28</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort;

//1796.字符串中第二大的数字
//开题时间：2022-12-03 11:01:21
public class SecondLargestDigitInAString {
    public static void main(String[] args) {
        Solution solution = new SecondLargestDigitInAString().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //计数排序
        public int secondHighest9(String s) {
            int[] freq = new int[10];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c))
                    freq[c - '0']++;
            }

            for (int i = freq.length - 1; i >= 0; i--)
                if (freq[i] > 0)
                    for (int j = i - 1; j >= 0; j--)
                        if (freq[j] > 0)
                            return j;

            return -1;
        }

        //一次遍历，维护第一个和第二个最大值
        public int secondHighest(String s) {
            int first = -1, second = -1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    int num = c - '0';
                    if (second < num && num < first)
                        second = num;
                    else if (first < num) {
                        second = first;
                        first = num;
                    }
                }
            }
            return second;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}