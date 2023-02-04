//<p>给你三个正整数 <code>n</code>、<code>index</code> 和 <code>maxSum</code> 。你需要构造一个同时满足下述所有条件的数组 <code>nums</code>（下标 <strong>从 0 开始</strong> 计数）：</p>
//
//<ul> 
// <li><code>nums.length == n</code></li> 
// <li><code>nums[i]</code> 是 <strong>正整数</strong> ，其中 <code>0 &lt;= i &lt; n</code></li> 
// <li><code>abs(nums[i] - nums[i+1]) &lt;= 1</code> ，其中 <code>0 &lt;= i &lt; n-1</code></li> 
// <li><code>nums</code> 中所有元素之和不超过 <code>maxSum</code></li> 
// <li><code>nums[index]</code> 的值被 <strong>最大化</strong></li> 
//</ul>
//
//<p>返回你所构造的数组中的 <code>nums[index]</code> 。</p>
//
//<p>注意：<code>abs(x)</code> 等于 <code>x</code> 的前提是 <code>x &gt;= 0</code> ；否则，<code>abs(x)</code> 等于 <code>-x</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>n = 4, index = 2,  maxSum = 6
//<strong>输出：</strong>2
//<strong>解释：</strong>数组 [1,1,<strong>2</strong>,1] 和 [1,2,<strong>2</strong>,1] 满足所有条件。不存在其他在指定下标处具有更大值的有效数组。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>n = 6, index = 1,  maxSum = 10
//<strong>输出：</strong>3
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= maxSum &lt;= 10<sup>9</sup></code></li> 
// <li><code>0 &lt;= index &lt; n</code></li> 
//</ul>
//
//<div><li>👍 122</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

// 1802.有界数组中指定下标处的最大值
// 开题时间：2023-01-04 17:36:39
public class MaximumValueAtAGivenIndexInABoundedArray {
  public static void main(String[] args) {
    Solution solution = new MaximumValueAtAGivenIndexInABoundedArray().new Solution();
    //        System.out.println(solution.maxValue(4, 2, 6));
    System.out.println(solution.maxValue(3, 0, 815094800));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int maxValue9(int n, int index, int sum) {
      int l = (sum + n - 1) / n, r = sum;
      while (l < r) {
        int mid = ((r - l + 1) >> 1) + l;
        if (canFormArr(n, index, sum, mid))
          l = mid;
        else
          r = mid - 1;
      }
      return r;
    }
    
    //☆☆☆☆☆ 贪心 + 二分
    public int maxValue(int n, int index, int sum) {
      int l = (sum + n - 1) / n, r = sum;
      while (l < r) {
        int mid = ((r - l + 1) >> 1) + l;
        if (sum(index, mid) + sum(n - 1 - index, mid) + mid <= sum)
          l = mid;
        else
          r = mid - 1;
      }
      return r;
    }
    
    private long sum(int idx, long x) {
      return x - 1 >= idx ?
          (2 * x - idx - 1) * idx / 2 :
          x * (x - 3) / 2 + idx + 1;
    }
    
    private boolean canFormArr(int n, int index, int sum, int x) {
      return (long) n * x >= sum &&
          (long) x + getPartSum(index, x) + getPartSum(n - 1 - index, x) <= sum;
    }
    
    private long getPartSum(int index, int x) {
      if (x - 1 >= index)
        return (2L * x - index - 1) * index / 2;
      else
        return (long) x * (x - 1) / 2 + index + 1 - x;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}