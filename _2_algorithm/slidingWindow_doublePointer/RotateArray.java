//<p>给你一个数组，将数组中的元素向右轮转 <code>k</code><em>&nbsp;</em>个位置，其中&nbsp;<code>k</code><em>&nbsp;</em>是非负数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入:</strong> nums = [1,2,3,4,5,6,7], k = 3
//<strong>输出:</strong> <span><code>[5,6,7,1,2,3,4]</code></span>
//<strong>解释:</strong>
// 向右轮转 1 步: <span><code>[7,1,2,3,4,5,6]</code></span>
// 向右轮转 2 步: <span><code>[6,7,1,2,3,4,5]
//</code></span>向右轮转 3 步: <span><code>[5,6,7,1,2,3,4]</code></span>
//</pre>
//
//<p><strong>示例&nbsp;2:</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,-100,3,99], k = 2
//<strong>输出：</strong>[3,99,-1,-100]
//<strong>解释:</strong> 
// 向右轮转 1 步: [99,-1,-100,3]
// 向右轮转 2 步: [3,99,-1,-100]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
// <li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>尽可能想出更多的解决方案，至少有 <strong>三种</strong> 不同的方法可以解决这个问题。</li> 
// <li>你可以使用空间复杂度为&nbsp;<code>O(1)</code> 的&nbsp;<strong>原地&nbsp;</strong>算法解决这个问题吗？</li> 
//</ul>
//
//<ul> 
//</ul>
//
//<ul> 
//</ul>
//
//<div><li>👍 1691</li><li>👎 0</li></div>
package _2_algorithm.slidingWindow_doublePointer;

// 189.轮转数组
// 开题时间：2023-01-20 09:29:36
public class RotateArray {
  public static void main(String[] args) {
    Solution solution = new RotateArray().new Solution();
    //        solution.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    solution.rotate(new int[]{1, 2, 3, 4, 5, 6}, 4);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 使用额外数组，注意最后不能直接修改引用、需要进行数组拷贝
    public void rotate9(int[] nums, int k) {
      int n = nums.length;
      k = k % n;
      int[] ans = new int[n];
      for (int i = 0; i < n; i++)
        ans[(i + k) % n] = nums[i];
      System.arraycopy(ans, 0, nums, 0, n);
    }
    
    // 环状替换 + 使用访问标记数组
    public void rotate8(int[] nums, int k) {
      int n = nums.length;
      k = k % n;
      boolean[] vis = new boolean[n];
      for (int i = 0; i < n; i++) {
        if (!vis[i])
          for (int j = (i + k) % n, pre = nums[i]; !vis[j]; j = (j + k) % n) {
            int tmp = nums[j];
            nums[j] = pre;
            pre = tmp;
            vis[j] = true;
          }
      }
    }
    
    //☆☆☆☆ 环状替换 + 计数最大公约数作为迭代上限，小技巧：使用「do while」结构，可以先执行一次逻辑
    public void rotate7(int[] nums, int k) {
      int n = nums.length;
      k = k % n;
      int cnt = gcd(n, k);
      for (int i = 0; i < cnt; i++) {
        int pre = i;
        int preVal = nums[pre];
        do {
          int cur = (pre + k) % n;
          
          int tmp = nums[cur];
          nums[cur] = preVal;
          preVal = tmp;
          
          pre = cur;
        } while (pre != i);
      }
    }
    
    /*
     * ☆☆☆☆☆ 双指针（ 3 次数组翻转）
     * 事实：当我们将数组的元素向右移动 k 次后，尾部 k%n 个元素会移动至数组头部，其余元素向后移动 k%n 个位置。
     *
     * 实现：
     *  1.翻转所有元素，这样尾部 k % n 个元素会移动至数组头部（顺序相反），其余元素向后移动 k % n 个位置（顺序相反）。
     *  2.翻转 [0, (k % n) -1] 区间的元素
     *  2.翻转 [k % n,   n -1] 区间的元素
     */
    public void rotate(int[] nums, int k) {
      int n = nums.length;
      k %= n;
      reverse(nums, 0, n - 1);
      reverse(nums, 0, k - 1);
      reverse(nums, k, n - 1);
    }
    
    /**
     * 翻转数组
     */
    public void reverse(int[] nums, int l, int r) {
      while (l < r) {
        int tmp = nums[l];
        nums[l++] = nums[r];
        nums[r--] = tmp;
      }
    }
    
    public static int gcd(int a, int b) {
      return b != 0 ?
          gcd(b, a % b) :
          a;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}