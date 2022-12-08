//<p>给你一个二维整数数组 <code>envelopes</code> ，其中 <code>envelopes[i] = [w<sub>i</sub>, h<sub>i</sub>]</code> ，表示第 <code>i</code> 个信封的宽度和高度。</p>
//
//<p>当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。</p>
//
//<p>请计算 <strong>最多能有多少个</strong> 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。</p>
//
//<p><strong>注意</strong>：不允许旋转信封。</p> &nbsp;
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>envelopes = [[5,4],[6,4],[6,7],[2,3]]
//<strong>输出：</strong>3
//<strong>解释：</strong>最多信封的个数为 <span><code>3, 组合为: </code></span>[2,3] =&gt; [5,4] =&gt; [6,7]。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>envelopes = [[1,1],[1,1],[1,1]]
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= envelopes.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>envelopes[i].length == 2</code></li> 
// <li><code>1 &lt;= w<sub>i</sub>, h<sub>i</sub> &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 834</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;
import java.util.Comparator;

//354.俄罗斯套娃信封问题
//开题时间：2022-11-22 15:44:14
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        Solution solution = new RussianDollEnvelopes().new Solution();
//        System.out.println(solution.maxEnvelopes(new int[][]{
//                {5, 4},
//                {6, 4},
//                {6, 7},
//                {2, 3}
//        }));
        System.out.println(solution.maxEnvelopes(Tools.to2DIntArray("[[5,4],[6,4],[6,7],[2,3]]")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //TLE
        public int maxEnvelopesX(int[][] envelopes) {
            Arrays.sort(envelopes, Comparator.<int[]>comparingInt(i -> i[0]).thenComparingInt(i -> i[1]));
            int n = envelopes.length;
//            int size = envelopes.length;
//            int n = 1;
//            for (int i = 1; i < size; i++)
//                if (envelopes[i - 1][0] != envelopes[i][0])
//                    envelopes[n++] = envelopes[i];

            int max = 1;
            int[] maxDolls = new int[n];
            Arrays.fill(maxDolls, 1);
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++)
                    if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
                        if (maxDolls[j] >= maxDolls[i])
                            maxDolls[i] = maxDolls[j] + 1;
//                        maxDolls[i] = Math.max(maxDolls[i], maxDolls[j] + 1);
                if (maxDolls[i] > max)
                    max = maxDolls[i];
//                max = Math.max(max, maxDolls[i]);
            }
            return max;
        }

        //排序+二分+DP(LIS)
        public int maxEnvelopes(int[][] envelopes) {
            //按照「 `w` 升序，`h` 降序」排序，就不用考虑相同 `w` 时的情况了（即 `envelopes[i][0] > envelopes[j][0]` 可以舍去）。
            Arrays.sort(envelopes, Comparator.<int[]>comparingInt(i -> i[0]).thenComparing(Comparator.<int[]>comparingInt(i -> i[1]).reversed()));

            return lengthOfLIS(Arrays.stream(envelopes).mapToInt(env -> env[1]).toArray());
        }

        public int lengthOfLIS(int[] nums) {
            int[] tails = new int[nums.length];
            int res = 0;
            for (int num : nums) {
                int i = 0, j = res;
                while (i < j) {
                    int m = (i + j) / 2;
                    if (tails[m] < num) i = m + 1;
                    else j = m;
                }
                tails[i] = num;
                if (res == j) res++;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}