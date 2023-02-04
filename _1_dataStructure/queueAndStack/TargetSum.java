//<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>
//
//<p>向数组中的每个整数前添加&nbsp;<code>'+'</code> 或 <code>'-'</code> ，然后串联起所有整数，可以构造一个 <strong>表达式</strong> ：</p>
//
//<ul> 
// <li>例如，<code>nums = [2, 1]</code> ，可以在 <code>2</code> 之前添加 <code>'+'</code> ，在 <code>1</code> 之前添加 <code>'-'</code> ，然后串联起来得到表达式 <code>"+2-1"</code> 。</li> 
//</ul>
//
//<p>返回可以通过上述方法构造的、运算结果等于 <code>target</code> 的不同 <strong>表达式</strong> 的数目。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,1,1,1], target = 3
//<strong>输出：</strong>5
//<strong>解释：</strong>一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1], target = 1
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 20</code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 1000</code></li> 
// <li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li> 
// <li><code>-1000 &lt;= target &lt;= 1000</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 1349</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.queueAndStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 494.目标和
// 开题时间：2022-08-20 13:43:08
public class TargetSum {
  public static void main(String[] args) {
    Solution solution = new TargetSum().new Solution();
    System.out.println(solution.findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3));
    //        System.out.println(solution.findTargetSumWays(new int[]{1, 0}, 1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 1.BFS+Queue   2^n 2^n
    public int findTargetSumWays(int[] nums, int target) {
      int cnt = 0;
      int length = nums.length;
      Queue<Integer> queue = new ArrayDeque<>(1 << length);
      queue.offer(nums[0]);
      queue.offer(-nums[0]);
      for (int i = 1; i < length - 1; i++) {
        int size = queue.size();
        for (int j = 0; j < size; j++) {
          Integer poll = queue.poll();
          queue.offer(poll + nums[i]);
          queue.offer(poll - nums[i]);
        }
      }
      if (length > 1) {
        for (Integer integer : queue) {
          if (integer == target && nums[length - 1] == 0) {
            cnt += 2;
          } else if (integer + nums[length - 1] == target || integer - nums[length - 1] == target) {
            cnt++;
          }
        }
      } else {
        if (nums[0] == target || -nums[0] == target) {
          if (nums[0] == 0) {
            cnt += 2;
          } else {
            cnt++;
          }
        }
      }
      
      return cnt;
    }
    
    
    // 2.DFS   2^n 2^n
    public int findTargetSumWays2(int[] nums, int target) {
      int cnt = 0;
      
      for (Integer sum : DFS(nums, 0)) {
        if (sum == target) {
          cnt++;
        }
      }
      
      return cnt;
    }
    
    private List<Integer> DFS(int[] nums, int i) {
      int length = nums.length;
      List<Integer> list = new ArrayList<>(1 << (length - i));
      
      if (i == length - 1) {
        list.add(nums[i]);
        list.add(-nums[i]);
      } else {
        for (Integer sum : DFS(nums, i + 1)) {
          list.add(sum + nums[i]);
          list.add(sum - nums[i]);
        }
      }
      
      return list;
    }
    
    // 3.官解一：回溯  2^n n
    private int count = 0;
    
    public int findTargetSumWays3(int[] nums, int target) {
      backtrack(nums, target, 0, 0);
      return count;
    }
    
    private void backtrack(int[] nums, int target, int idx, int sum) {
      if (idx == nums.length) {
        if (sum == target) {
          count++;
        }
      } else {
        backtrack(nums, target, idx + 1, sum + nums[idx]);
        backtrack(nums, target, idx + 1, sum - nums[idx]);
      }
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}