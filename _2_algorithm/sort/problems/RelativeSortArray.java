//<p>给你两个数组，<code>arr1</code> 和&nbsp;<code>arr2</code>，<code>arr2</code>&nbsp;中的元素各不相同，<code>arr2</code> 中的每个元素都出现在&nbsp;<code>arr1</code>&nbsp;中。</p>
//
//<p>对 <code>arr1</code>&nbsp;中的元素进行排序，使 <code>arr1</code> 中项的相对顺序和&nbsp;<code>arr2</code>&nbsp;中的相对顺序相同。未在&nbsp;<code>arr2</code>&nbsp;中出现过的元素需要按照升序放在&nbsp;<code>arr1</code>&nbsp;的末尾。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//<strong>输出：</strong>[2,2,2,1,4,3,3,9,6,7,19]
//</pre>
//
//<p><strong>示例 &nbsp;2:</strong></p>
//
//<pre>
//<strong>输入：</strong>arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
//<strong>输出：</strong>[22,28,8,6,17,44]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr1.length, arr2.length &lt;= 1000</code></li> 
// <li><code>0 &lt;= arr1[i], arr2[i] &lt;= 1000</code></li> 
// <li><code>arr2</code>&nbsp;中的元素&nbsp;<code>arr2[i]</code>&nbsp;&nbsp;<strong>各不相同</strong>&nbsp;</li> 
// <li><code>arr2</code> 中的每个元素&nbsp;<code>arr2[i]</code>&nbsp;都出现在&nbsp;<code>arr1</code>&nbsp;中</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>计数排序</li><li>排序</li></div></div><br><div><li>👍 235</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.sort.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//1122.数组的相对排序
//开题时间：2022-09-27 13:04:44
public class RelativeSortArray {
    public static void main(String[] args) {
        Solution solution = new RelativeSortArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //计数排序 之 略显笨拙
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            int range = 1001;
            int[] counting = new int[range];
            for (int e : arr2)
                counting[e]++;
            //计数 共同元素
            for (int e : arr1)
                if (counting[e] != 0)
                    counting[e]++;

            int length1 = arr1.length;
            int[] sorted = new int[length1];
            int idx = 0;
            //排序 共同元素
            for (int e : arr2) {
                do {
                    sorted[idx++] = e;
                    counting[e]--;
                } while (counting[e] != 1);
                counting[e] = -1;
            }

            //计数 不同元素
            for (int i : arr1)
                if (counting[i] != -1)
                    counting[i]++;
            //排序 不同元素
            for (int i = 0; i < range; i++)
                while (counting[i] > 0) {
                    sorted[idx++] = i;
                    counting[i]--;
                }

            return sorted;
        }

        //计数排序 之 略显机智
        public int[] relativeSortArray2(int[] arr1, int[] arr2) {
            //确定计数范围
            int min = arr1[0];
            int max = arr1[0];
            for (int e : arr1) {
                if (e < min) min = e;
                else if (max < e) max = e;
            }
            int[] counting = new int[max - min + 1];
            //计数
            for (int e : arr1)
                counting[e - min]++;

            int[] sorted = new int[arr1.length];
            int idx = 0;
            //排序 共同元素
            for (int e : arr2) {
                int i = e - min;
                do {
                    sorted[idx++] = e;
                    counting[i]--;
                } while (counting[i] != 0);
            }

            //排序 不同元素
            for (int i = 0; i < counting.length; i++) {
                if (counting[i] != 0) {
                    int e = min + i;
                    do {
                        sorted[idx++] = e;
                        counting[i]--;
                    } while (counting[i] != 0);
                }
            }

            return sorted;
        }

        //自定义排序
        public int[] relativeSortArray3(int[] arr1, int[] arr2) {
            Map<Integer, Integer> val2idx = new HashMap<>();
            for (int i = 0; i < arr2.length; i++)
                val2idx.put(arr2[i], i);

            int defaultValue = 1001;
            return Arrays.stream(arr1).boxed().sorted((o1, o2) -> {
                if (val2idx.containsKey(o1) || val2idx.containsKey(o2)) {
                    return val2idx.getOrDefault(o1, defaultValue) -
                            val2idx.getOrDefault(o2, defaultValue);
                }
                return o1 - o2;
            }).mapToInt(Integer::valueOf).toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}