//<p>数对 <code>(a,b)</code> 由整数 <code>a</code> 和 <code>b</code> 组成，其数对距离定义为 <code>a</code> 和 <code>b</code> 的绝对差值。</p>
//
//<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，数对由 <code>nums[i]</code> 和 <code>nums[j]</code> 组成且满足 <code>0 &lt;= i &lt; j &lt; nums.length</code> 。返回 <strong>所有数对距离中</strong> 第 <code>k</code> 小的数对距离。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,3,1], k = 1
//<strong>输出：</strong>0
//<strong>解释：</strong>数对和对应的距离如下：
//(1,3) -&gt; 2
//(1,1) -&gt; 0
//(3,1) -&gt; 2
// 距离第 1 小的数对是 (1,1) ，距离为 0 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,1,1], k = 2
//<strong>输出：</strong>0
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,6,1], k = 3
//<strong>输出：</strong>5
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == nums.length</code></li> 
// <li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li> 
// <li><code>1 &lt;= k &lt;= n * (n - 1) / 2</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>二分查找</li><li>排序</li></div></div><br><div><li>👍 391</li><li>👎 0</li></div>
package _2_algorithm.binarySearch;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

// 719.找出第 K 小的数对距离
// 开题时间：2022-10-15 11:53:10
public class FindKThSmallestPairDistance {
  public static void main(String[] args) {
    Solution solution = new FindKThSmallestPairDistance().new Solution();
    System.out.println(solution.smallestDistancePair5(new int[]{38, 33, 57, 65, 13, 2, 86, 75, 4, 56}, 26));
    //        System.out.println(solution.smallestDistancePair4(new int[]{62, 100, 4}, 2));
    //        System.out.println(solution.smallestDistancePair4(new int[]{1, 6, 1}, 3));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 暴力
    public int smallestDistancePair(int[] nums, int k) {
      int len = nums.length;
      int[] distances = new int[len * (len - 1) / 2];
      int idx = 0;
      for (int i = 0; i < len - 1; i++)
        for (int j = i + 1; j < len; j++)
          distances[idx++] = Math.abs(nums[j] - nums[i]);
      Arrays.sort(distances);
      return distances[k - 1];
    }
    
    // 计数排序
    public int smallestDistancePair2(int[] nums, int k) {
      int len = nums.length;
      int[] cnt = new int[100_0000];
      for (int i = 0; i < len - 1; i++)
        for (int j = i + 1; j < len; j++)
          cnt[Math.abs(nums[j] - nums[i])]++;
      int sum = 0;
      for (int i = 0; i < cnt.length; i++) {
        sum += cnt[i];
        if (sum >= k)
          return i;
      }
      return -1;
    }
    
    // 哈希表（超时）
    public int smallestDistancePair3(int[] nums, int k) {
      int len = nums.length;
      TreeMap<Integer, Integer> distance2cnt = new TreeMap<>();
      for (int i = 0; i < len - 1; i++)
        for (int j = i + 1; j < len; j++)
          distance2cnt.merge(Math.abs(nums[j] - nums[i]), 1, Integer::sum);
      int sum = 0;
      for (Map.Entry<Integer, Integer> entry : distance2cnt.entrySet()) {
        sum += entry.getValue();
        if (sum >= k)
          return entry.getKey();
      }
      return -1;
    }
    
    // 二分距离+二分数对个数  logD * nlogn (D=max-min)    1
    public int smallestDistancePair4(int[] nums, int k) {
      Arrays.sort(nums);
      
      int l = 0, r = nums[nums.length - 1] - nums[0];
      while (l < r) {
        int mid = l + r >> 1;
        int cnt = getNoGreaterThanPairCnt(nums, mid);
        if (cnt >= k)
          r = mid;
        else
          l = mid + 1;
      }
      
      return r;
    }
    
    private int getNoGreaterThanPairCnt(int[] nums, int distance) {
      int cnt = 0;
      
      for (int i = 0; i < nums.length - 1; i++) {
        int l = i + 1, r = nums.length;
        while (l < r) {
          int mid = l + r >> 1;
          if (distance < nums[mid] - nums[i])
            r = mid;
          else
            l = mid + 1;
        }
        cnt += r - i - 1;
      }
      
      return cnt;
    }
    
    
    //☆☆☆☆☆ 二分距离+双指针数对个数   n *（logn+logD） (D=max-min)    1
    public int smallestDistancePair5(int[] nums, int k) {
      Arrays.sort(nums);
      
      int l = 0, r = nums[nums.length - 1] - nums[0];
      while (l < r) {
        int mid = l + r >> 1;
        
        int cnt = 0;
        for (int i = 0, j = 1; j < nums.length; j++) {
          while (mid < nums[j] - nums[i])
            i++;
          cnt += j - i;
        }
        
        if (cnt >= k)
          r = mid;
        else
          l = mid + 1;
      }
      
      return r;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}