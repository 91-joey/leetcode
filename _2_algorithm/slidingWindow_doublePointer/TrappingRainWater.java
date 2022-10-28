//<p>给定&nbsp;<code>n</code> 个非负整数表示每个宽度为 <code>1</code> 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png" style="height: 161px; width: 412px;" /></p>
//
//<pre>
//<strong>输入：</strong>height = [0,1,0,2,1,0,1,3,2,1,2,1]
//<strong>输出：</strong>6
//<strong>解释：</strong>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>height = [4,2,0,3,2,5]
//<strong>输出：</strong>9
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == height.length</code></li> 
// <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>栈</li><li>数组</li><li>双指针</li><li>动态规划</li><li>单调栈</li></div></div><br><div><li>👍 3897</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

import java.util.LinkedList;

//42.接雨水
//开题时间：2022-10-24 15:20:34
public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        System.out.println(solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //单调递减队列
        public int trap(int[] height) {
            LinkedList<Integer> q = new LinkedList<>();
            for (int i = 2; i < height.length; i++) {
                while (!q.isEmpty() && q.peekLast() < height[i]) q.pollLast();
                q.offerLast(height[i]);
            }

            int volume = 0;
            for (int i = 1, maxL = height[0]; i < height.length - 1; i++) {
                maxL = Math.max(maxL, height[i - 1]);
                Integer maxR = q.peekFirst();
                int min = Math.min(maxL, maxR);
                if (min > height[i])
                    volume += min - height[i];
                if (maxR == height[i + 1]) q.pollFirst();
            }
            return volume;
        }

        //DP
        public int trap2(int[] height) {
            int len = height.length;
            int volume = 0;
            if (len < 3) return volume;
            //1. 正向遍历，得到 maxL[]，maxL[i]=max(maxL[0]...maxL[i])
            int[] maxL = new int[len];
            maxL[0] = height[0];
            for (int i = 1; i < len - 1; i++)
                maxL[i] = Math.max(maxL[i - 1], height[i]);
            //2. 反向遍历，得到 maxR[]，maxR[i]=max(maxR[i]...maxR[len-1])
            int[] maxR = new int[len];
            maxR[len - 1] = height[len - 1];
            for (int i = len - 2; i > 0; i--)
                maxR[i] = Math.max(maxR[i + 1], height[i]);
            //3. volume[i]=min(maxL[i],maxR[i])-height[i]
            for (int i = 1; i < len - 1; i++)
                volume += Math.min(maxL[i], maxR[i]) - height[i];

            return volume;
        }

        //DP enhance
        public int trap3(int[] height) {
            int len = height.length;
            int volume = 0;
            if (len < 3) return volume;
            //1. 反向遍历，得到 maxR[]，maxR[i]=max(maxR[i]...maxR[len-1])
            int[] maxR = new int[len];
            maxR[len - 1] = height[len - 1];
            for (int i = len - 2; i > 0; i--)
                maxR[i] = Math.max(maxR[i + 1], height[i]);
            //2. volume[i]=min(maxL[i],maxR[i])-height[i]
            int maxL = height[0];
            for (int i = 1; i < len - 1; i++) {
                maxL = Math.max(maxL, height[i]);
                volume += Math.min(maxL, maxR[i]) - height[i];
            }

            return volume;
        }

        //todo 单调栈
        //todo 双指针
    }
//leetcode submit region end(Prohibit modification and deletion)
}