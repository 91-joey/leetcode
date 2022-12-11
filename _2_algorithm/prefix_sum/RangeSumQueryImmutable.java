//<p>给定一个整数数组 &nbsp;<code>nums</code>，处理以下类型的多个查询:</p>
//
//<ol> 
// <li>计算索引&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;（包含 <code>left</code> 和 <code>right</code>）之间的 <code>nums</code> 元素的 <strong>和</strong> ，其中&nbsp;<code>left &lt;= right</code></li> 
//</ol>
//
//<p>实现 <code>NumArray</code> 类：</p>
//
//<ul> 
// <li><code>NumArray(int[] nums)</code> 使用数组 <code>nums</code> 初始化对象</li> 
// <li><code>int sumRange(int i, int j)</code> 返回数组 <code>nums</code>&nbsp;中索引&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;之间的元素的 <strong>总和</strong> ，包含&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code>&nbsp;两点（也就是&nbsp;<code>nums[left] + nums[left + 1] + ... + nums[right]</code>&nbsp;)</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>
//["NumArray", "sumRange", "sumRange", "sumRange"]
//[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
//<strong>输出：
//</strong>[null, 1, -1, -3]
//
//<strong>解释：</strong>
//NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
//numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
//numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
//numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>5</sup>&nbsp;&lt;= nums[i] &lt;=&nbsp;10<sup>5</sup></code></li> 
// <li><code>0 &lt;= i &lt;= j &lt; nums.length</code></li> 
// <li>最多调用 <code>10<sup>4</sup></code> 次 <code>sumRange</code><strong> </strong>方法</li> 
//</ul>
//
//<div><li>👍 518</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.prefix_sum;

//303.区域和检索 - 数组不可变
//开题时间：2022-12-12 03:39:42
public class RangeSumQueryImmutable {
    public static void main(String[] args) {
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {
        int[] prefixSum;

        public NumArray(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 1; i < prefixSum.length; i++)
                prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
//leetcode submit region end(Prohibit modification and deletion)
}