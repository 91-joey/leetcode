//<p>给你个整数数组&nbsp;<code>arr</code>，其中每个元素都 <strong>不相同</strong>。</p>
//
//<p>请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。</p>
//
//<p>每对元素对 <code>[a,b</code>] 如下：</p>
//
//<ul> 
// <li><code>a ,&nbsp;b</code>&nbsp;均为数组&nbsp;<code>arr</code>&nbsp;中的元素</li> 
// <li><code>a &lt; b</code></li> 
// <li><code>b - a</code>&nbsp;等于 <code>arr</code> 中任意两个元素的最小绝对差</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [4,2,1,3]
//<strong>输出：</strong>[[1,2],[2,3],[3,4]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [1,3,6,10,15]
//<strong>输出：</strong>[[1,3]]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [3,8,-10,23,19,-4,-14,27]
//<strong>输出：</strong>[[-14,-10],[19,23],[23,27]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= arr.length &lt;= 10^5</code></li> 
// <li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li> 
//</ul>
//
//<div><li>👍 133</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//1200.最小绝对差
//开题时间：2022-12-21 10:44:56
public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        Solution solution = new MinimumAbsoluteDifference().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> minimumAbsDifference(int[] arr) {
            Arrays.sort(arr);

            int min = Integer.MAX_VALUE;
            for (int i = 1; i < arr.length; i++)
                min = Math.min(min, arr[i] - arr[i - 1]);

            ArrayList<List<Integer>> ans = new ArrayList<>();
            for (int i = 1; i < arr.length; i++)
                if (min == arr[i] - arr[i - 1])
                    ans.add(List.of(arr[i - 1], arr[i]));

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}