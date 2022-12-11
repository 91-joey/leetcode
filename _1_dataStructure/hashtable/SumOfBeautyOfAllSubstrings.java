//<p>一个字符串的 <strong>美丽值</strong>&nbsp;定义为：出现频率最高字符与出现频率最低字符的出现次数之差。</p>
//
//<ul> 
// <li>比方说，<code>"abaacc"</code>&nbsp;的美丽值为&nbsp;<code>3 - 1 = 2</code>&nbsp;。</li> 
//</ul>
//
//<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，请你返回它所有子字符串的&nbsp;<strong>美丽值</strong>&nbsp;之和。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>s = "aabcb"
//<b>输出：</b>5
//<strong>解释：</strong>美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>s = "aabcbaa"
//<b>输出：</b>17
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= s.length &lt;=<sup> </sup>500</code></li> 
// <li><code>s</code>&nbsp;只包含小写英文字母。</li> 
//</ul>
//
//<div><li>👍 26</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.Arrays;
import java.util.IntSummaryStatistics;

//1781.所有子字符串美丽值之和
//开题时间：2022-12-12 03:50:24
public class SumOfBeautyOfAllSubstrings {
    public static void main(String[] args) {
        Solution solution = new SumOfBeautyOfAllSubstrings().new Solution();
        System.out.println(solution.beautySum("aabcb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 前缀和 + 计数 + 暴力枚举
         *   n*26+n*n*26
         *   n*26
         */
        public int beautySum9(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            int[][] freq = new int[n + 1][26];
            for (int i = 0; i < n; i++) {
                System.arraycopy(freq[i], 0, freq[i + 1], 0, 26);
                int j = cs[i] - 'a';
                freq[i + 1][j]++;
            }

            int sum = 0;
            for (int i = 0; i < n - 2; i++) {
                for (int j = i + 2; j < n; j++) {
                    int[] tmp = new int[26];
                    for (int k = 0; k < 26; k++)
                        tmp[k] = freq[j + 1][k] - freq[i][k];
                    sum += Arrays.stream(tmp).max().getAsInt() - Arrays.stream(tmp).filter(x -> x > 0).min().getAsInt();
                }
            }
            return sum;
        }

        //☆☆☆☆☆ 直接双层循环 + 计数
        public int beautySum8(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;

            int sum = 0;
            for (int i = 0; i < n - 2; i++) {
                int[] arr = new int[26];
                for (int j = i; j < n; j++) {
                    arr[cs[j] - 'a']++;
                    IntSummaryStatistics statistics =
                            Arrays.stream(arr)
                                    .filter(x -> x > 0)
                                    .summaryStatistics();
                    sum += statistics.getMax() - statistics.getMin();
                }
            }

            return sum;
        }

        public int beautySum(String s) {
            char[] cs = s.toCharArray();
            int n = cs.length;

            int sum = 0;
            for (int i = 0; i < n - 2; i++) {
                int[] tmp = new int[26];
                int idx = cs[i] - 'a';
                tmp[idx]++;
                int max = idx;
                int min = idx;
                for (int j = i + 1; j < n; j++) {
                    idx = cs[j] - 'a';
                    tmp[idx]++;
                    for (int k = 0; k < 26; k++) {
                        if (tmp[k] == 0) continue;
                        if (tmp[max] < tmp[k])
                            max = k;
                        else if (tmp[k] < tmp[min])
                            min = k;
                    }
                    sum += tmp[max] - tmp[min];
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}