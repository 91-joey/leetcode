//<p>给你一个整数数组&nbsp;<code>arr</code>，请你检查是否存在两个整数&nbsp;<code>N</code> 和 <code>M</code>，满足&nbsp;<code>N</code>&nbsp;是&nbsp;<code>M</code>&nbsp;的两倍（即，<code>N = 2 * M</code>）。</p>
//
//<p>更正式地，检查是否存在两个下标&nbsp;<code>i</code> 和 <code>j</code> 满足：</p>
//
//<ul> 
// <li><code>i != j</code></li> 
// <li><code>0 &lt;= i, j &lt; arr.length</code></li> 
// <li><code>arr[i] == 2 * arr[j]</code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>arr = [10,2,5,3]
//<strong>输出：</strong>true
//<strong>解释：</strong>N<span><code> = 10</code></span> 是 M<span><code> = 5 的两倍</code></span>，即 <span><code>10 = 2 * 5 。</code></span>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>arr = [7,1,14,11]
//<strong>输出：</strong>true
//<strong>解释：</strong>N<span><code> = 14</code></span> 是 M<span><code> = 7 的两倍</code></span>，即 <span><code>14 = 2 * 7 </code></span>。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre><strong>输入：</strong>arr = [3,1,7,11]
//<strong>输出：</strong>false
//<strong>解释：</strong>在该情况下不存在 N 和 M 满足 N = 2 * M 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= arr.length &lt;= 500</code></li> 
// <li><code>-10^3 &lt;= arr[i] &lt;= 10^3</code></li> 
//</ul>
//
//<div><li>👍 78</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.*;

//1346.检查整数及其两倍数是否存在
//开题时间：2022-11-28 16:31:07
public class CheckIfNAndItsDoubleExist {
    public static void main(String[] args) {
        Solution solution = new CheckIfNAndItsDoubleExist().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //哈希映射
        public boolean checkIfExist9(int[] arr) {
            int n = arr.length;
            HashMap<Integer, Integer> val2idx = new HashMap<>(n);
            for (int i = 0; i < arr.length; i++)
                val2idx.put(arr[i], i);

            for (int i = 0; i < arr.length; i++) {
                Integer idx = val2idx.get(2 * arr[i]);
                if (idx != null && idx != i)
                    return true;
            }

            return false;
        }

        //☆☆☆☆☆ 哈希集合（提前返回）
        public boolean checkIfExist8(int[] arr) {
            int n = arr.length;
            HashSet<Integer> set = new HashSet<>();

            for (int e : arr) {
                if (set.contains(e) || set.contains(e * 4))
                    return true;
                else
                    set.add(e * 2);
            }

            return false;
        }

        //排序+二分
        public boolean checkIfExist(int[] arr) {
            List<Integer> neg = new ArrayList<>();
            List<Integer> pos = new ArrayList<>();
            for (int e : arr) {
                if (e >= 0)
                    pos.add(e);
                else
                    neg.add(-e);
            }

            return checkIfExist(neg) || checkIfExist(pos);
        }

        private boolean checkIfExist(List<Integer> list) {
            int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
            Arrays.sort(arr);

            int n = arr.length;
            for (int i = 0, l = i + 1; i < n; i++) {
                int target = arr[i] * 2;
                l = firstIdxGreaterOrEqualDouble(arr, l, n - 1, target);
                if (target == arr[l])
                    return true;
            }

            return false;
        }

        public static int firstIdxGreaterOrEqualDouble(int[] arr, int l, int r, int target) {
            while (l < r) {
                int mid = ((r - l) >> 1) + l;
                if (target <= arr[mid])
                    r = mid;
                else
                    l = mid + 1;
            }
            return r;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}