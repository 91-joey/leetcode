//<p>给你一个由 <code>n</code> 个整数组成的数组&nbsp;<code>nums</code> ，和一个目标值 <code>target</code> 。请你找出并返回满足下述全部条件且<strong>不重复</strong>的四元组&nbsp;<code>[nums[a], nums[b], nums[c], nums[d]]</code>&nbsp;（若两个四元组元素一一对应，则认为两个四元组重复）：</p>
//
//<ul> 
// <li><code>0 &lt;= a, b, c, d&nbsp;&lt; n</code></li> 
// <li><code>a</code>、<code>b</code>、<code>c</code> 和 <code>d</code> <strong>互不相同</strong></li> 
// <li><code>nums[a] + nums[b] + nums[c] + nums[d] == target</code></li> 
//</ul>
//
//<p>你可以按 <strong>任意顺序</strong> 返回答案 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,0,-1,0,-2,2], target = 0
//<strong>输出：</strong>[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,2,2,2,2], target = 8
//<strong>输出：</strong>[[2,2,2,2]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 200</code></li> 
// <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1408</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 18.四数之和
// 开题时间：2022-10-24 10:57:35
public class FourSum {
  public static void main(String[] args) {
    Solution solution = new FourSum().new Solution();
    //        System.out.println(solution.fourSum(new int[]{-1, 0, 1, 2, -1, -4}, -1));
    System.out.println(solution.fourSum(new int[]{0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000}, 1000000000));
    System.out.println(-3E10 + "~" + 3E10);
    System.out.println(1_000_000_000 * 3);
    System.out.println(-1_000_000_000 * 3);
    // 溢出最小值-2147483648时，target必>0，-10^9 <= a <=b <= -573741824       -2*10^9 <= -(c+d) <= -1147483648
    System.out.println("[-2147483648,-1294967296]");
    System.out.println("[+1294967296,+2147483648]");
    System.out.println((-2147483648 + 1000000000) / 2);
    System.out.println(-573741825 * 2 - 1000_000_000);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
      List<List<Integer>> res = new ArrayList<>();
      Arrays.sort(nums);
      int len = nums.length;
      if (len < 4 ||
          target > (long) nums[len - 1] + nums[len - 2] + nums[len - 3] + nums[len - 4])
        return res;
      
      for (int i = 0; i < len - 3; ) {
        long ti = (long) target - nums[i];
        if (ti < (long) nums[i + 1] + nums[i + 2] + nums[i + 3]) break;
        if (ti > (long) nums[len - 1] + nums[len - 2] + nums[len - 3]) {
          i++;
          continue;
        }
        for (int j = i + 1; j < len - 2; ) {
          long tj = ti - nums[j];
          if (tj < nums[j + 1] + nums[j + 2]) break;
          if (tj > nums[len - 1] + nums[len - 2]) {
            j++;
            continue;
          }
          for (int l = j + 1, r = len - 1; l < r; ) {
            int sum = nums[l] + nums[r];
            if (sum == tj) {
              res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
              while (l < r && nums[l] == nums[++l]) ;
              while (l < r && nums[r] == nums[--r]) ;
            } else if (sum < tj)
              l++;
            else
              r--;
          }
          while (j < len - 2 && nums[j] == nums[++j]) ;
        }
        while (i < len - 3 && nums[i] == nums[++i]) ;
      }
      return res;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}