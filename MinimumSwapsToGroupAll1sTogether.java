//<p>给出一个二进制数组&nbsp;<code>data</code>，你需要通过交换位置，将数组中 <strong>任何位置</strong> 上的 1 组合到一起，并返回所有可能中所需&nbsp;<strong>最少的交换次数</strong>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> data = [1,0,1,0,1]
//<strong>输出:</strong> 1
//<strong>解释: </strong>
//有三种可能的方法可以把所有的 1 组合在一起：
//[1,1,1,0,0]，交换 1 次；
//[0,1,1,1,0]，交换 2 次；
//[0,0,1,1,1]，交换 1 次。
//所以最少的交换次数为 1。
//</pre>
//
//<p><strong>示例&nbsp; 2:</strong></p>
//
//<pre>
//<strong>输入：</strong>data =&nbsp;[0,0,0,1,0]
//<strong>输出：</strong>0
//<strong>解释： </strong>
//由于数组中只有一个 1，所以不需要交换。</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入：</strong>data =&nbsp;[1,0,1,0,1,0,0,1,1,0,1]
//<strong>输出：3
//解释：
//</strong>交换 3 次，一种可行的只用 3 次交换的解决方案是 [0,0,0,0,0,1,1,1,1,1,1]。
//</pre>
//
//<p><strong>示例 4:</strong></p>
//
//<pre>
//<strong>输入:</strong> data = [1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1]
//<strong>输出:</strong> 8
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= data.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>data[i]</code>&nbsp;==&nbsp;<code>0</code>&nbsp;or&nbsp;<code>1</code>.</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>滑动窗口</li></div></div><br><div><li>👍 79</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//1151.最少交换次数来组合所有的 1
//开题时间：2022-10-12 15:08:33
public class MinimumSwapsToGroupAll1sTogether {
    public static void main(String[] args) {
        Solution solution = new MinimumSwapsToGroupAll1sTogether().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
         * 求出数组中 1 的个数 size
         * 以 size 为滑动窗口固定长度，向右滑动，计算滑动窗口中 0 的最小值
         */
        //固长滑动窗口(计算滑动窗口中 0 的最小值)
        public int minSwaps(int[] data) {
            //求出数组中 1 的个数 size
            int size = 0;
            for (int e : data)
                size += e;

            //以 size 为滑动窗口固定长度，向右滑动，计算滑动窗口中 0 的最小值
            int min = size;
            int cnt0 = 0;
            for (int i = 0; i < size; i++)
                cnt0 += 1 - data[i];
            min = Math.min(min, cnt0);

            for (int i = size; i < data.length; i++) {
//                cnt0 += data[i - size] - 1;
//                cnt0 += 1 - data[i];
                cnt0 += data[i - size] - data[i];
                min = Math.min(min, cnt0);
            }

            return min;
        }

        //固长滑动窗口(计算滑动窗口中 1 的最大值)
        public int minSwaps2(int[] data) {
            //求出数组中 1 的个数 total1
            int total1 = 0;
            for (int e : data)
                total1 += e;

            //以 total1 为滑动窗口固定长度，向右滑动，计算滑动窗口中 1 的最大值
            int cnt1 = 0;
            for (int i = 0; i < total1; i++)
                cnt1 += data[i];
            int max1 = cnt1;

            int len = data.length;
            for (int i = total1; i < len; i++) {
                cnt1 += data[i] - data[i - total1];
                if (max1 < cnt1)
                    max1 = cnt1;
            }

            return total1 - max1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}