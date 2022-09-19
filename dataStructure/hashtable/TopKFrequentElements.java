//<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，请你返回其中出现频率前 <code>k</code> 高的元素。你可以按 <strong>任意顺序</strong> 返回答案。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入: </strong>nums = [1,1,1,2,2,3], k = 2
//<strong>输出: </strong>[1,2]
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入: </strong>nums = [1], k = 1
//<strong>输出: </strong>[1]</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>k</code> 的取值范围是 <code>[1, 数组中不相同的元素的个数]</code></li> 
// <li>题目数据保证答案唯一，换句话说，数组中前 <code>k</code> 个高频元素的集合是唯一的</li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你所设计算法的时间复杂度 <strong>必须</strong> 优于 <code>O(n log n)</code> ，其中 <code>n</code><em>&nbsp;</em>是数组大小。</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>分治</li><li>桶排序</li><li>计数</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 1316</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.hashtable;

import java.util.*;

//347.前 K 个高频元素
//开题时间：2022-09-11 08:12:53
public class TopKFrequentElements {
    public static void main(String[] args) {
        Solution solution = new TopKFrequentElements().new Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 2}, 2)));
//        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //TreeMap
        public int[] topKFrequent(int[] nums, int k) {
            //计数
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums)
                map.merge(num, 1, Integer::sum);
            //倒转键值，并按逆序排序
            Map<Integer, List<Integer>> treeMap = new TreeMap<>(Comparator.reverseOrder());
            for (Map.Entry<Integer, Integer> entry : map.entrySet())
                treeMap.merge(entry.getValue(), new ArrayList<>(List.of(entry.getKey())), (oldVal, val) -> {
                    oldVal.add(entry.getKey());
                    return oldVal;
                });
            //遍历，返回结果
            int[] ans = new int[k];
            int idx = 0;
            for (Integer integer : treeMap.keySet())
                for (Integer e : treeMap.get(integer)) {
                    if (idx >= k)
                        return ans;
                    ans[idx++] = e;
                }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}