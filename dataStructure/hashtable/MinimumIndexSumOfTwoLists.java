//<p>假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。</p>
//
//<p>你需要帮助他们用<strong>最少的索引和</strong>找出他们<strong>共同喜爱的餐厅</strong>。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入: </strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
//<strong>输出:</strong> ["Shogun"]
//<strong>解释:</strong> 他们唯一共同喜爱的餐厅是“Shogun”。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong>list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
//<strong>输出:</strong> ["Shogun"]
//<strong>解释:</strong> 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示:</strong></p>
//
//<ul> 
// <li><code>1 &lt;= list1.length, list2.length &lt;= 1000</code></li> 
// <li><code>1 &lt;= list1[i].length, list2[i].length &lt;= 30</code>&nbsp;</li> 
// <li><code>list1[i]</code> 和 <code>list2[i]</code> 由空格
//  <meta charset="UTF-8" />&nbsp;<code>' '</code>&nbsp;和英文字母组成。</li> 
// <li><code>list1</code> 的所有字符串都是 <strong>唯一</strong> 的。</li> 
// <li><code>list2</code> 中的所有字符串都是 <strong>唯一</strong> 的。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 224</li><li>👎 0</li></div>
package org.example.leetcode.problems.dataStructure.hashtable;

import java.util.*;

//599.两个列表的最小索引总和
//开题时间：2022-09-05 13:44:42
public class MinimumIndexSumOfTwoLists {
    public static void main(String[] args) {
        Solution solution = new MinimumIndexSumOfTwoLists().new Solution();
        System.out.println(Arrays.toString(solution.findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"})));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //迭代    n*m 1
        public String[] findRestaurant(String[] list1, String[] list2) {
            List<String> ans = new ArrayList<>();
            int min = Integer.MAX_VALUE - 1;

            for (int i = 0; i < list1.length; i++) {
                for (int j = 0; j < Math.min(min - i + 1, list2.length); j++) {
                    if (list1[i].equals(list2[j])) {
                        if (min != i + j) {
                            min = i + j;
                            ans = new ArrayList<>();
                        }
                        ans.add(list1[i]);
                    }
                }
            }

            return ans.toArray(new String[0]);
        }

        //hashtable n+m min(n,m)
        public String[] findRestaurant2(String[] list1, String[] list2) {
            int length1 = list1.length;
            int length2 = list2.length;
            //空间优化：哈希表存储长度较小的数组
            if (length1 < length2) {
                return findRestaurant(list2, list1);
            }

            Map<String, Integer> map = new HashMap<>();
            for (int i = 0; i < length2; i++) {
                map.put(list2[i], i);
            }

            List<String> ans = new ArrayList<>();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < length1; i++) {
                //时间优化：当前索引大于最小索引和，直接退出循环
                if (i > min) break;
                String s = list1[i];
                Integer idx = map.get(s);
                if (idx != null) {
                    int sum = i + idx;
                    if (sum < min) {
                        min = sum;
                        ans.clear();
                        ans.add(s);
                    } else if (sum == min) {
                        ans.add(s);
                    }
                }
            }

            return ans.toArray(new String[0]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}