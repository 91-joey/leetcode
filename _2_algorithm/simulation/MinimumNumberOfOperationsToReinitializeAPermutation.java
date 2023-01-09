//<p>给你一个偶数 <code>n</code>​​​​​​ ，已知存在一个长度为 <code>n</code> 的排列 <code>perm</code> ，其中 <code>perm[i] == i</code>​（下标 <strong>从 0 开始</strong> 计数）。</p>
//
//<p>一步操作中，你将创建一个新数组 <code>arr</code> ，对于每个 <code>i</code> ：</p>
//
//<ul> 
// <li>如果 <code>i % 2 == 0</code> ，那么 <code>arr[i] = perm[i / 2]</code></li> 
// <li>如果 <code>i % 2 == 1</code> ，那么 <code>arr[i] = perm[n / 2 + (i - 1) / 2]</code></li> 
//</ul>
//
//<p>然后将 <code>arr</code>​​ 赋值​​给 <code>perm</code> 。</p>
//
//<p>要想使 <code>perm</code> 回到排列初始值，至少需要执行多少步操作？返回最小的 <strong>非零</strong> 操作步数。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>1
//<strong>解释：</strong>最初，perm = [0,1]
//第 1&nbsp;步操作后，perm = [0,1]
//所以，仅需执行 1 步操作</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 4
//<strong>输出：</strong>2
//<strong>解释：</strong>最初，perm = [0,1,2,3]
//第 1&nbsp;步操作后，perm = [0,2,1,3]
//第 2&nbsp;步操作后，perm = [0,1,2,3]
//所以，仅需执行 2 步操作</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 6
//<strong>输出：</strong>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= n &lt;= 1000</code></li> 
// <li><code>n</code>​​​​​​ 是一个偶数</li> 
//</ul>
//
//<div><li>👍 43</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.simulation;

//1806.还原排列的最少操作步数
//开题时间：2023-01-09 09:37:51
public class MinimumNumberOfOperationsToReinitializeAPermutation {
    public static void main(String[] args) {
        Solution solution = new MinimumNumberOfOperationsToReinitializeAPermutation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //模拟
        public int reinitializePermutation9(int n) {
            int ans = 1;
            int[] perm = new int[n];
            for (int i = 0; i < n; i++)
                perm[i] = i;
            do {
                int[] arr = new int[n];
                for (int i = 0; i < n; i++)
                    if (i % 2 == 0)
                        arr[i] = perm[i / 2];
                    else
                        arr[i] = perm[n / 2 + (i - 1) / 2];

                if (isPermutation(arr))
                    return ans;

                ans++;
                perm = arr;
            } while (true);
        }

        //☆☆☆☆☆ 数学
        public int reinitializePermutation(int n) {
            if (n == 2)
                return 1;
            int ans = 1, pow2 = 2;
            while (pow2 != 1) {
                pow2 = 2 * pow2 % (n - 1);
                ans++;
            }
            return ans;
        }

        private boolean isPermutation(int[] arr) {
            for (int i = 0; i < arr.length; i++)
                if (i != arr[i])
                    return false;
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}