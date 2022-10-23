//<p>给你一个整数数组 <code>nums</code> ，判断是否存在三元组 <code>[nums[i], nums[j], nums[k]]</code> 满足 <code>i != j</code>、<code>i != k</code> 且 <code>j != k</code> ，同时还满足 <code>nums[i] + nums[j] + nums[k] == 0</code> 。请</p>
//
//<p>你返回所有和为 <code>0</code> 且不重复的三元组。</p>
//
//<p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>
//
//<p>&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
//<strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
//<strong>解释：</strong>
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,1,1]
//<strong>输出：</strong>[]
//<strong>解释：</strong>唯一可能的三元组和不为 0 。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [0,0,0]
//<strong>输出：</strong>[[0,0,0]]
//<strong>解释：</strong>唯一可能的三元组和为 0 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>3 &lt;= nums.length &lt;= 3000</code></li> 
// <li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 5329</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.*;

//15.三数之和
//开题时间：2022-10-22 17:58:04
public class ThreeSum {
    public static void main(String[] args) {
        Solution solution = new ThreeSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //哈希去重
        public List<List<Integer>> threeSum(int[] nums) {
            Set<List<Integer>> res = new HashSet<>();
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                for (int l = i + 1, r = len - 1; l < r; ) {
                    int sum = nums[l] + nums[r];
                    if (nums[i] + sum == 0)
                        res.add(List.of(nums[i], nums[l++], nums[r--]));
                    else if (nums[i] + sum < 0)
                        l++;
                    else
                        r--;
                }
            }
            return res.stream().toList();
        }

        //手动比较去重（繁琐版）
        public List<List<Integer>> threeSum2(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                if (nums[i] > 0)
                    break;
                if (i > 0 && nums[i - 1] == nums[i])
                    continue;
                for (int l = i + 1, r = len - 1, target = -nums[i]; l < r; ) {
                    if (l > i + 1 && nums[l - 1] == nums[l]) {
                        l++;
                        continue;
                    }
                    if (r < len - 1 && nums[r + 1] == nums[r]) {
                        r--;
                        continue;
                    }
                    int sum = nums[l] + nums[r];
                    if (sum == target) {
//                        res.add(List.of(nums[i], nums[l++], nums[r--]));
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[l++]);
                        list.add(nums[r--]);
                        res.add(list);
                    } else if (sum < target)
                        l++;
                    else
                        r--;
                }
            }
            return res;
        }


        //手动比较去重（精简版）
        public List<List<Integer>> threeSum3(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            int len = nums.length;
            for (int i = 0; i < len - 2 && nums[i] <= 0; i++) {
                if (i > 0 && nums[i - 1] == nums[i])
                    continue;
                for (int l = i + 1, r = len - 1, target = -nums[i]; l < r; ) {
                    int sum = nums[l] + nums[r];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[++l]) ;
                        while (l < r && nums[r] == nums[--r]) ;
                    } else if (sum < target)
                        l++;
                    else
                        r--;
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}