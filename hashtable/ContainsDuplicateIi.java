//<p>给你一个整数数组&nbsp;<code>nums</code> 和一个整数&nbsp;<code>k</code> ，判断数组中是否存在两个 <strong>不同的索引</strong><em>&nbsp;</em><code>i</code>&nbsp;和<em>&nbsp;</em><code>j</code> ，满足 <code>nums[i] == nums[j]</code> 且 <code>abs(i - j) &lt;= k</code> 。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例&nbsp;1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3
//<strong>输出：</strong>true</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1
//<strong>输出：</strong>true</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [1,2,3,1,2,3], k<em> </em>=<em> </em>2
//<strong>输出：</strong>false</pre>
//
//<p>&nbsp;</p>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>滑动窗口</li></div></div><br><div><li>👍 514</li><li>👎 0</li></div>
package org.example.leetcode.problems.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//219.存在重复元素 II
//开题时间：2022-09-06 08:39:52
public class ContainsDuplicateIi {
    public static void main(String[] args) {
        Solution solution = new ContainsDuplicateIi().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //暴力迭代(双指针)  n^m 1
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            int length = nums.length;

            for (int i = 0; i < length - 1; i++) {
                int min = Math.min(i + k, length - 1);
                for (int j = i + 1; j <= min; j++) {
                    if (nums[i] == nums[j]) {
                        return true;
                    }
                }
            }

            return false;
        }

        //哈希表（hashmap）   n   n
        public boolean containsNearbyDuplicate2(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int e = nums[i];
                Integer lst = map.get(e);
                if (lst == null || i - lst > k) {
                    map.put(e, i);
                } else {
                    return true;
                }
            }

            return false;
        }

        //滑动窗口（hashset） n   k
        public boolean containsNearbyDuplicate3(int[] nums, int k) {
            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < nums.length; i++) {
                if (i > k)
                    set.remove(nums[i - k - 1]);
                if(!set.add(nums[i]))
                    return true;
            }

            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}