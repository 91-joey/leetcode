//<p>给定一个数组&nbsp;<code>nums</code>&nbsp;，如果&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>nums[i] &gt; 2*nums[j]</code>&nbsp;我们就将&nbsp;<code>(i, j)</code>&nbsp;称作一个<strong><em>重要翻转对</em></strong>。</p>
//
//<p>你需要返回给定数组中的重要翻转对的数量。</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入</strong>: [1,3,2,3,1]
//<strong>输出</strong>: 2
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入</strong>: [2,4,3,5,1]
//<strong>输出</strong>: 3
//</pre>
//
//<p><strong>注意:</strong></p>
//
//<ol> 
// <li>给定数组的长度不会超过<code>50000</code>。</li> 
// <li>输入数组中的所有数字都在32位整数的表示范围内。</li> 
//</ol>
//
//<div><li>👍 383</li><li>👎 0</li></div>
package _2_algorithm.divideAndConquer.problems;

// 493.翻转对
// 开题时间：2022-11-11 17:09:47
public class ReversePairs {
  public static void main(String[] args) {
    Solution solution = new ReversePairs().new Solution();
    //        Tools.sortHard(arr -> {
    //            //nums[i] <= 2 * nums[j]
    //            //nums[i]/2 <= nums[j]
    //            int[] sorted = Arrays.stream(arr).boxed().sorted((o1, o2) -> o1 - 2 * o2).mapToInt(Integer::intValue).toArray();
    //            System.arraycopy(sorted, 0, arr, 0, arr.length);
    //        });
    System.out.println(solution.reversePairs(new int[]{1, 3, 2, 3, 1}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // TLE
    public int reversePairs9(int[] nums) {
      int len = nums.length;
      long[] doubles = new long[len];
      for (int i = 0; i < len; i++)
        doubles[i] = (long) nums[i] << 1;
      
      int cnt = 0;
      for (int i = 0; i < len - 1; i++)
        for (int j = i + 1; j < len; j++)
          if (nums[i] > doubles[j])
            cnt++;
      return cnt;
    }
    
    int cnt;
    
    // 基于归并排序    nlogn   n
    public int reversePairs(int[] nums) {
      mergeSort(nums, 0, nums.length - 1, new int[nums.length]);
      return cnt;
    }
    
    private void mergeSort(int[] arr, int start, int end, int[] tmp) {
      if (start >= end)
        return;
      
      int mid = start + end >> 1;
      mergeSort(arr, start, mid, tmp);
      mergeSort(arr, mid + 1, end, tmp);
      
      // region 关键逻辑
      for (int l = start, r = mid + 1; l <= mid; l++) {
        while (r <= end && arr[l] > (long) arr[r] << 1)
          r++;
        cnt += r - mid - 1;
      }
      // endregion
      
      System.arraycopy(arr, start, tmp, start, end - start + 1);
      for (int i = start, l = start, r = mid + 1; i <= end; i++) {
        if (l > mid)
          arr[i] = tmp[r++];
        else if (r > end || tmp[l] <= tmp[r]) {
          arr[i] = tmp[l++];
        } else
          arr[i] = tmp[r++];
      }
    }
    
  }
  // leetcode submit region end(Prohibit modification and deletion)
}