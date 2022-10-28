//<p>你的好友是一位健身爱好者。前段日子，他给自己制定了一份健身计划。现在想请你帮他评估一下这份计划是否合理。</p>
//
//<p>他会有一份计划消耗的卡路里表，其中&nbsp;<code>calories[i]</code>&nbsp;给出了你的这位好友在第&nbsp;<code>i</code>&nbsp;天需要消耗的卡路里总量。</p>
//
//<p>为了更好地评估这份计划，对于卡路里表中的每一天，你都需要计算他 「这一天以及之后的连续几天」 （共&nbsp;<code>k</code> 天）内消耗的总卡路里 <em>T：</em></p>
//
//<ul> 
// <li>如果&nbsp;<code>T &lt; lower</code>，那么这份计划相对糟糕，并失去 1 分；&nbsp;</li> 
// <li>如果 <code>T &gt; upper</code>，那么这份计划相对优秀，并获得 1 分；</li> 
// <li>否则，这份计划普普通通，分值不做变动。</li> 
//</ul>
//
//<p>请返回统计完所有&nbsp;<code>calories.length</code>&nbsp;天后得到的总分作为评估结果。</p>
//
//<p>注意：总分可能是负数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
//<strong>输出：</strong>0
//<strong>解释：</strong>calories[0], calories[1] &lt; lower 而 calories[3], calories[4] &gt; upper, 总分 = 0.</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>calories = [3,2], k = 2, lower = 0, upper = 1
//<strong>输出：</strong>1
//<strong>解释：</strong>calories[0] + calories[1] &gt; upper, 总分 = 1.
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>calories = [6,5,0,0], k = 2, lower = 1, upper = 5
//<strong>输出：</strong>0
//<strong>解释：</strong>calories[0] + calories[1] &gt; upper, calories[2] + calories[3] &lt; lower, 总分 = 0.
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= k &lt;= calories.length &lt;= 10^5</code></li> 
// <li><code>0 &lt;= calories[i] &lt;= 20000</code></li> 
// <li><code>0 &lt;= lower &lt;= upper</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>滑动窗口</li></div></div><br><div><li>👍 22</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

//1176.健身计划评估
//开题时间：2022-10-13 08:58:44
public class DietPlanPerformance {
    public static void main(String[] args) {
        Solution solution = new DietPlanPerformance().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //固长滑动窗口
        public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
            int sum = 0;
            for (int i = 0; i < k; i++)
                sum += calories[i];
            int score = 0;
            if (sum < lower)
                score--;
            else if (sum > upper)
                score++;

            for (int i = k; i < calories.length; i++) {
                sum += calories[i] - calories[i - k];
                if (sum < lower)
                    score--;
                else if (sum > upper)
                    score++;
            }

            return score;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}