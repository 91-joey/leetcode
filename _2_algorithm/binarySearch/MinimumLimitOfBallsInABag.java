//<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个袋子里球的数目。同时给你一个整数&nbsp;<code>maxOperations</code>&nbsp;。</p>
//
//<p>你可以进行如下操作至多&nbsp;<code>maxOperations</code>&nbsp;次：</p>
//
//<ul> 
// <li>选择任意一个袋子，并将袋子里的球分到&nbsp;2 个新的袋子中，每个袋子里都有 <strong>正整数</strong>&nbsp;个球。 </li>
//</ul>
//
//    <ul>
//    	<li>比方说，一个袋子里有 <code>5</code> 个球，你可以把它们分到两个新袋子里，分别有 <code>1</code> 个和 <code>4</code> 个球，或者分别有 <code>2</code> 个和 <code>3</code> 个球。</li>
//    </ul>
//    </li>
//
//
//<p>你的开销是单个袋子里球数目的 <strong>最大值</strong>&nbsp;，你想要 <strong>最小化</strong>&nbsp;开销。</p>
//
//<p>请你返回进行上述操作后的最小开销。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [9], maxOperations = 2
//<b>输出：</b>3
//<b>解释：</b>
//- 将装有 9 个球的袋子分成装有 6 个和 3 个球的袋子。[<strong>9</strong>] -&gt; [6,3] 。
//- 将装有 6 个球的袋子分成装有 3 个和 3 个球的袋子。[<strong>6</strong>,3] -&gt; [3,3,3] 。
// 装有最多球的袋子里装有 3 个球，所以开销为 3 并返回 3 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [2,4,8,2], maxOperations = 4
//<b>输出：</b>2
//<strong>解释：</strong>
//- 将装有 8 个球的袋子分成装有 4 个和 4 个球的袋子。[2,4,<strong>8</strong>,2] -&gt; [2,4,4,4,2] 。
//- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,<strong>4</strong>,4,4,2] -&gt; [2,2,2,4,4,2] 。
//- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,<strong>4</strong>,4,2] -&gt; [2,2,2,2,2,4,2] 。
//- 将装有 4 个球的袋子分成装有 2 个和 2 个球的袋子。[2,2,2,2,2,<strong>4</strong>,2] -&gt; [2,2,2,2,2,2,2,2] 。
// 装有最多球的袋子里装有 2 个球，所以开销为 2 并返回 2 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<b>输入：</b>nums = [7,17], maxOperations = 2
//<b>输出：</b>7
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>1 &lt;= maxOperations, nums[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 130</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.binarySearch;

import java.util.Arrays;

// 1760.袋子里最少数目的球
// 开题时间：2022-12-20 10:43:58
public class MinimumLimitOfBallsInABag {
  public static void main(String[] args) {
    Solution solution = new MinimumLimitOfBallsInABag().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
      int l = 1, r = Arrays.stream(nums).max().getAsInt();
      while (l < r) {
        int mid = ((r - l) >> 1) + l;
        if (maxOperations >= getOperations(nums, mid))
          r = mid;
        else
          l = mid + 1;
      }
      return r;
    }
    
    private long getOperations(int[] nums, int max) {
      long operations = 0;
      for (int x : nums)
        operations += (x - 1) / max;
      return operations;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}