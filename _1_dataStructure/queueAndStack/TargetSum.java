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
package _1_dataStructure.queueAndStack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;

// 494.目标和
// 开题时间：2022-08-20 13:43:08
public class TargetSum {
  public static void main(String[] args) {
    Solution solution = new TargetSum().new Solution();
    // System.out.println(solution.findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3));
    //        System.out.println(solution.findTargetSumWays(new int[]{1, 0}, 1));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 1.BFS+Queue   2^n 2^n
    public int findTargetSumWays99(int[] nums, int target) {
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
    
    int ans = 0;
    private int[] nums;
    private int target;
    HashMap<String, Integer> memo;
    
    // dfs(使用全局变量维护)
    public int findTargetSumWays9(int[] nums, int target) {
      this.nums = nums;
      this.target = target;
      
      dfs(0, 0);
      
      return ans;
    }
    
    private void dfs(int i, int sum) {
      if (i == nums.length) {
        if (sum == target) {
          ans++;
        }
        return;
      }
      
      dfs(i + 1, sum + nums[i]);
      dfs(i + 1, sum - nums[i]);
    }
    
    // dfs(接收返回值处理（后序遍历）)
    public int findTargetSumWays8(int[] nums, int target) {
      this.nums = nums;
      this.target = target;
      
      return dfsWithRet(0, 0);
    }
    
    private int dfsWithRet(int i, int sum) {
      if (i == nums.length) {
        return sum == target ? 1 : 0;
      }
      
      return dfsWithRet(i + 1, sum + nums[i]) +
          dfsWithRet(i + 1, sum - nums[i]);
    }
    
    // 记搜
    public int findTargetSumWays7(int[] nums, int target) {
      this.nums = nums;
      this.target = target;
      memo = new HashMap<>();
      
      return dfsWithRetMemo(0, 0);
    }
    
    private int dfsWithRetMemo(int i, int sum) {
      String key = i + "_" + sum;
      if (memo.containsKey(key)) {
        return memo.get(key);
      }
      
      if (i == nums.length) {
        int value = sum == target ? 1 : 0;
        memo.put(key, value);
        return value;
      }
      
      int value = dfsWithRetMemo(i + 1, sum + nums[i]) +
          dfsWithRetMemo(i + 1, sum - nums[i]);
      memo.put(key, value);
      return value;
    }
    
    /*
     * ☆☆☆☆☆ 01背包（求方案数）[一维优化 + 常数优化]
     * 问题转化：将数组分为两部分（正数堆s1/负数堆s2），则有：
     *  ① s1+s2=sum
     *  ② s1-s2=t
     * ① 代入 ② 可得 s1=(sum+t)/2，s2=(sum-t)/2，我们取 min(s1,s2) 作为新的目标值 t（常数优化）
     * 问题转化为求数组子集元素和为 t 的数目
     *
     * 特判（(sum-t)/2同理）：
     *  - 0<=(sum+t)/2<=sum
     *  - (sum+t)/2为整数
     *
     * 状态定义：f[i][j]表示考虑前 i 个元素，元素和为 j 的方案数
     * 状态转移：f[i][j]=
     *            （不选）f[i-1][j]+
     *            （选取）f[i-1][j-nums[i-1]]
     * 初始化　：f[0][0]=1
     * 答案　　：f[n][t]
     */
    public int findTargetSumWays(int[] nums, int t) {
      int sum = Arrays.stream(nums).sum();
      if (sum < t || sum + t < 0 || (sum - t) % 2 != 0) {
        return 0;
      }
      
      t = (sum + (t > 0 ? -t : t)) / 2; // 常数优化
      int[] f = new int[t + 1];
      f[0] = 1;
      for (int x : nums) {
        // 倒序遍历（一维优化）
        for (int j = t; j >= x; j--) {
          f[j] += f[j - x];
        }
      }
      return f[t];
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}