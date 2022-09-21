//<p>给你一个长度为 <code>n</code> 的整数数组 <code>score</code> ，其中 <code>score[i]</code> 是第 <code>i</code> 位运动员在比赛中的得分。所有得分都 <strong>互不相同</strong> 。</p>
//
//<p>运动员将根据得分 <strong>决定名次</strong> ，其中名次第 <code>1</code> 的运动员得分最高，名次第 <code>2</code> 的运动员得分第 <code>2</code> 高，依此类推。运动员的名次决定了他们的获奖情况：</p>
//
//<ul> 
// <li>名次第 <code>1</code> 的运动员获金牌 <code>"Gold Medal"</code> 。</li> 
// <li>名次第 <code>2</code> 的运动员获银牌 <code>"Silver Medal"</code> 。</li> 
// <li>名次第 <code>3</code> 的运动员获铜牌 <code>"Bronze Medal"</code> 。</li> 
// <li>从名次第 <code>4</code> 到第 <code>n</code> 的运动员，只能获得他们的名次编号（即，名次第 <code>x</code> 的运动员获得编号 <code>"x"</code>）。</li> 
//</ul>
//
//<p>使用长度为 <code>n</code> 的数组 <code>answer</code> 返回获奖，其中 <code>answer[i]</code> 是第 <code>i</code> 位运动员的获奖情况。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>score = [5,4,3,2,1]
//<strong>输出：</strong>["Gold Medal","Silver Medal","Bronze Medal","4","5"]
//<strong>解释：</strong>名次为 [1<sup>st</sup>, 2<sup>nd</sup>, 3<sup>rd</sup>, 4<sup>th</sup>, 5<sup>th</sup>] 。</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>score = [10,3,8,9,4]
//<strong>输出：</strong>["Gold Medal","5","Bronze Medal","Silver Medal","4"]
//<strong>解释：</strong>名次为 [1<sup>st</sup>, 5<sup>th</sup>, 3<sup>rd</sup>, 2<sup>nd</sup>, 4<sup>th</sup>] 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == score.length</code></li> 
// <li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= score[i] &lt;= 10<sup>6</sup></code></li> 
// <li><code>score</code> 中的所有值 <strong>互不相同</strong></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 187</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//506.相对名次
//开题时间：2022-09-21 14:57:44
public class RelativeRanks {

    public static void main(String[] args) {
        Solution solution = new RelativeRanks().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public static final String FIRST = "Gold Medal";
        public static final String SECOND = "Silver Medal";
        public static final String THIRD = "Bronze Medal";

        //笨办法
        public String[] findRelativeRanks(int[] score) {
            int length = score.length;
            String[] ans = new String[length];
            int idx = 0;
            for (int i : score) ans[idx++] = String.valueOf(i);

            Arrays.sort(score);

            for (int i = 0; i < length; i++) {
                if (ans[i].equals(String.valueOf(score[length - 1]))) {
                    ans[i] = FIRST;
                } else if (1 < length && ans[i].equals(String.valueOf(score[length - 2]))) {
                    ans[i] = SECOND;
                } else if (2 < length && ans[i].equals(String.valueOf(score[length - 3]))) {
                    ans[i] = THIRD;
                } else {
                    for (int j = 0; j < length - 3; j++) {
                        if (ans[i].equals(String.valueOf(score[j]))) {
                            ans[i] = String.valueOf(length - j);
                        }
                    }
                }
            }

            return ans;
        }

        //哈希映射
        public String[] findRelativeRanks2(int[] score) {
            int[] clone = score.clone();
            int length = score.length;
            String[] ans = new String[length];

            Arrays.sort(score);
//            shellSort2(score);
            Map<Integer, Integer> val2rank = new HashMap<>();
            for (int i = 0; i < length; i++) {
                val2rank.put(score[i], i);
            }

            for (int i = 0; i < length; i++) {
                Integer rank = val2rank.get(clone[i]);
                if (rank == length - 1) ans[i] = FIRST;
                else if (rank == length - 2) ans[i] = SECOND;
                else if (rank == length - 3) ans[i] = THIRD;
                else ans[i] = String.valueOf(length - rank);
            }

            return ans;
        }

        //数组
        public String[] findRelativeRanks3(int[] score) {
            String[] prizes = {FIRST, SECOND, THIRD};
            int length = score.length;
            int[][] val2idx = new int[length][2];
            for (int i = 0; i < length; i++) {
                val2idx[i][0] = score[i];
                val2idx[i][1] = i;
            }

            Arrays.sort(val2idx, (a, b) -> b[0] - a[0]);

            String[] ans = new String[length];
            for (int i = 0; i < length; i++) {
                if (i < 3) ans[val2idx[i][1]] = prizes[i];
                else ans[val2idx[i][1]] = String.valueOf(i + 1);
            }

            return ans;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)
}