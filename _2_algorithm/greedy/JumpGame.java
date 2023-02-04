//<p>给定一个非负整数数组&nbsp;<code>nums</code> ，你最初位于数组的 <strong>第一个下标</strong> 。</p>
//
//<p>数组中的每个元素代表你在该位置可以跳跃的最大长度。</p>
//
//<p>判断你是否能够到达最后一个下标。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,3,1,1,4]
//<strong>输出：</strong>true
//<strong>解释：</strong>可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
//</pre>
//
//<p><strong>示例&nbsp;2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,2,1,0,4]
//<strong>输出：</strong>false
//<strong>解释：</strong>无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 2162</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

// 55.跳跃游戏
// 开题时间：2023-01-20 17:55:24
public class JumpGame {
  public static void main(String[] args) {
    Solution solution = new JumpGame().new Solution();
    System.out.println(solution.canJump(new int[]{3, 2, 1, 0, 4}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 线性dp（内层正序遍历）
    public boolean canJump9(int[] nums) {
      int n = nums.length;
      boolean[] f = new boolean[n];
      f[0] = true;
      for (int i = 1; i < n; i++)
        for (int j = 0; j < i; j++)
          if (f[j] && nums[j] >= i - j) {
            f[i] = true;
            break;
          }
      return f[n - 1];
    }
    
    // 线性dp优化（内层逆序遍历）
    public boolean canJump8(int[] nums) {
      int n = nums.length;
      boolean[] f = new boolean[n];
      f[0] = true;
      for (int i = 1; i < n; i++)
        for (int j = i - 1; j >= 0; j--)
          if (f[j] && nums[j] >= i - j) {
            f[i] = true;
            break;
          }
      return f[n - 1];
    }
    
    /*
     * ☆☆☆☆☆ 贪心：维护最远可达位置
     *  若当前索引 i <= 最远可达位置 rightmost ，则索引 i 是可达的，我们更新 rightmost
     *      rightmost >= n -1，能够到达最后一个下标，提前返回结果 true
     *  反之，若当前索引 i > 最远可达位置 rightmost ，则索引 i 是不可达的，之后的索引同样是不可达的，提前返回结果 false
     */
    public boolean canJump(int[] nums) {
      for (int i = 0, rightmost = 0; i <= rightmost; i++)
        if ((rightmost = Math.max(rightmost, i + nums[i])) >= nums.length - 1)
          return true;
      return false;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}