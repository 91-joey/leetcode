//<p>给你 <code>n</code> 个长方体 <code>cuboids</code> ，其中第 <code>i</code> 个长方体的长宽高表示为 <code>cuboids[i] = [width<sub>i</sub>, length<sub>i</sub>, height<sub>i</sub>]</code>（<strong>下标从 0 开始</strong>）。请你从 <code>cuboids</code> 选出一个 <strong>子集</strong> ，并将它们堆叠起来。</p>
//
//<p>如果 <code>width<sub>i</sub> &lt;= width<sub>j</sub></code> 且 <code>length<sub>i</sub> &lt;= length<sub>j</sub></code> 且 <code>height<sub>i</sub> &lt;= height<sub>j</sub></code> ，你就可以将长方体 <code>i</code> 堆叠在长方体 <code>j</code> 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。</p>
//
//<p>返回 <strong>堆叠长方体</strong>&nbsp;<code>cuboids</code> 可以得到的 <strong>最大高度</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/12/12/image.jpg" style="width: 420px; height: 299px;" /></strong></p>
//
//<pre>
//<strong>输入：</strong>cuboids = [[50,45,20],[95,37,53],[45,23,12]]
//<strong>输出：</strong>190
//<strong>解释：</strong>
//第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
//第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
//第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
//总高度是 95 + 50 + 45 = 190 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>cuboids = [[38,25,45],[76,35,3]]
//<strong>输出：</strong>76
//<strong>解释：</strong>
//无法将任何长方体放在另一个上面。
//选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
//<strong>输出：</strong>102
//<strong>解释：</strong>
//重新排列长方体后，可以看到所有长方体的尺寸都相同。
//你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
//堆叠长方体的最大高度为 6 * 17 = 102 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == cuboids.length</code></li> 
// <li><code>1 &lt;= n &lt;= 100</code></li> 
// <li><code>1 &lt;= width<sub>i</sub>, length<sub>i</sub>, height<sub>i</sub> &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 66</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.Arrays;
import java.util.Comparator;

//1691.堆叠长方体的最大高度
//开题时间：2022-12-10 12:10:48
public class MaximumHeightByStackingCuboids {
    public static void main(String[] args) {
        Solution solution = new MaximumHeightByStackingCuboids().new Solution();
//        System.out.println(solution.maxHeight(Tools.to2DIntArray("[[50,45,20],[95,37,53],[45,23,12]]")));
        System.out.println(solution.maxHeight(Tools.to2DIntArray("[[92,47,83],[75,20,87],[68,12,83],[12,85,15],[16,24,47],[69,65,35],[96,56,93],[89,93,11],[86,20,41],[69,77,12],[83,80,97],[90,22,36]]")));
//        System.out.println(solution.maxHeight(Tools.to2DIntArray("[[12,76,13],[68,55,30],[48,85,52],[91,7,41],[29,65,35]]")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //dp(LIS)
        public int maxHeight(int[][] cuboids) {
            for (int[] cuboid : cuboids)
                Arrays.sort(cuboid);
            Arrays.sort(cuboids, Comparator
                    .<int[]>comparingInt(arr -> arr[0])
                    .thenComparingInt(arr -> arr[1])
                    .thenComparingInt(arr -> arr[2])
            );

            int n = cuboids.length;
            int[] f = new int[n];
            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++)
                    if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2])
                        f[i] = Math.max(f[i], f[j]);
                f[i] += cuboids[i][2];
                max = Math.max(max, f[i]);
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}