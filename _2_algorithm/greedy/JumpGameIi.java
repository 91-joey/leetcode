//<p>给定一个长度为 <code>n</code> 的 <strong>0 索引</strong>整数数组 <code>nums</code>。初始位置为 <code>nums[0]</code>。</p>
//
//<p>每个元素 <code>nums[i]</code> 表示从索引 <code>i</code> 向前跳转的最大长度。换句话说，如果你在 <code>nums[i]</code> 处，你可以跳转到任意 <code>nums[i + j]</code> 处:</p>
//
//<ul> 
// <li><code>0 &lt;= j &lt;= nums[i]</code>&nbsp;</li> 
// <li><code>i + j &lt; n</code></li> 
//</ul>
//
//<p>返回到达&nbsp;<code>nums[n - 1]</code> 的最小跳跃次数。生成的测试用例可以到达 <code>nums[n - 1]</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [2,3,1,1,4]
//<strong>输出:</strong> 2
//<strong>解释:</strong> 跳到最后一个位置的最小跳跃数是 <span><code>2</code></span>。
//&nbsp;    从下标为 0 跳到下标为 1 的位置，跳&nbsp;<span><code>1</code></span>&nbsp;步，然后跳&nbsp;<span><code>3</code></span>&nbsp;步到达数组的最后一个位置。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [2,3,0,1,4]
//<strong>输出:</strong> 2
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 1000</code></li> 
//</ul>
//
//<div><li>👍 1926</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 45.跳跃游戏 II
// 开题时间：2023-01-21 17:00:44
public class JumpGameIi {
  public static void main(String[] args) {
    Solution solution = new JumpGameIi().new Solution();
    System.out.println(solution.jump(new int[]{1, 1, 1, 1}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // dp（内层逆序遍历）
    public int jump9(int[] nums) {
      int n = nums.length;
      int[] f = new int[n];
      Arrays.fill(f, 0x3f3f3f3f);
      f[0] = 0;
      for (int i = 1; i < n; i++)
        for (int j = i - 1; j >= 0; j--)
          if (f[j] < 0x3f3f3f3f && nums[j] >= i - j)
            f[i] = Math.min(f[i], f[j] + 1);
      return f[n - 1];
    }
    
    // dp优化（内层正序遍历（贪心））
    public int jump8(int[] nums) {
      int n = nums.length;
      int[] f = new int[n];
      Arrays.fill(f, 0x3f3f3f3f);
      f[0] = 0;
      for (int i = 1; i < n; i++)
        for (int j = 0; j < i; j++)
          if (f[j] < 0x3f3f3f3f && nums[j] >= i - j) {
            f[i] = f[j] + 1;
            break;
          }
      return f[n - 1];
    }
    
    // 贪心（反向查找出发位置）
    public int jump7(int[] nums) {
      int t = nums.length - 1;
      int ans = 0;
      while (t > 0) {
        int i = 0;
        while (i + nums[i] < t)
          i++;
        t = i;
        ans++;
      }
      return ans;
    }
    
    // 贪心（反向查找出发位置）+小优化（预处理最远可达位置）
    public int jump6(int[] nums) {
      for (int i = 0; i < nums.length; i++)
        nums[i] += i;
      
      int t = nums.length - 1;
      int ans = 0;
      while (t > 0) {
        int i = 0;
        while (nums[i] < t)
          i++;
        t = i;
        ans++;
      }
      return ans;
    }
    
    // 贪心（反向查找出发位置）+ 大优化（单调递增队列）
    public int jump5(int[] nums) {
      Deque<int[]> q = new LinkedList<>();
      for (int i = 0; i < nums.length - 1; i++) {
        int rightmost = nums[i] + i;
        if (q.isEmpty() || rightmost > q.peekLast()[1])
          q.offer(new int[]{i, rightmost});
      }
      
      int t = nums.length - 1;
      int ans = 0;
      while (!q.isEmpty()) {
        int[] poll = null;
        while (!q.isEmpty() && q.peekLast()[1] >= t)
          poll = q.pollLast();
        t = poll[0];
        ans++;
      }
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 贪心（正向查找最远可达位置）
     * 从起跳点 i 开始，(i,i+nums[i]]范围内的位置都可以作为新的起跳点
     * 我们贪心的选择其中具有最远可达位置的点 j ，作为新的起跳点（因为选择其余的点位，所能抵达的位置、j 同样能抵达）
     */
    public int jump(int[] nums) {
      int end = 0;// 新起跳点们范围的右边界
      int rightmost = 0;// 新起跳点们的最远可达位置
      int ans = 0;
      for (int i = 0; i < nums.length - 1; i++) {
        rightmost = Math.max(rightmost, nums[i] + i);
        if (i == end) {
          end = rightmost;
          ans++;
        }
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}