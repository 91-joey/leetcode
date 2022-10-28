//<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数目标值 <code>target</code>，请你在该数组中找出 <strong>和为目标值 </strong><em><code>target</code></em>&nbsp; 的那&nbsp;<strong>两个</strong>&nbsp;整数，并返回它们的数组下标。</p>
//
//<p>你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。</p>
//
//<p>你可以按任意顺序返回答案。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [2,7,11,15], target = 9
//<strong>输出：</strong>[0,1]
//<strong>解释：</strong>因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,2,4], target = 6
//<strong>输出：</strong>[1,2]
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums = [3,3], target = 6
//<strong>输出：</strong>[0,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
// <li><code>-10<sup>9</sup> &lt;= target &lt;= 10<sup>9</sup></code></li> 
// <li><strong>只会存在一个有效答案</strong></li> 
//</ul>
//
//<p><strong>进阶：</strong>你可以想出一个时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法吗？</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li></div></div><br><div><li>👍 15243</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.HashMap;
import java.util.Map;

//1.两数之和
//开题时间：2022-09-05 08:45:10
public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new TwoSum().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //数组中同一个元素在答案里不能重复出现。
        //只会存在一个有效答案
        //1.暴力枚举 n^2 1
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length - 1; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (target == nums[i] + nums[j]) {
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{};
        }

        //2.自解（复杂）(hashtable)  n   n
        public int[] twoSum2(int[] nums, int target) {
            Map<Integer, Pair> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int key = target - nums[i];
                if (key != nums[i] && map.containsKey(key))
                    return new int[]{i, map.get(key).firstIdx};

                if (!map.containsKey(nums[i])) {
                    map.putIfAbsent(nums[i], new Pair(i));
                } else {
                    map.get(nums[i]).secondIdx = i;
                }
            }

            Pair pair = map.get(target / 2);
            return new int[]{pair.firstIdx, pair.secondIdx};
        }

        //3.高分解(hashtable) n   n
        public int[] twoSum3(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                int key = target - nums[i];
                if (map.containsKey(key))
                    return new int[]{i, map.get(key)};
                map.put(nums[i], i);
            }

            return new int[0];
        }

        public class Pair {
            public int firstIdx;
            public int secondIdx;

            public Pair(int firstIdx) {
                this.firstIdx = firstIdx;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}