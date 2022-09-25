//<p>给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。</p>
//
//<p>初始化&nbsp;A 和 B 的元素数量分别为&nbsp;<em>m</em> 和 <em>n</em>。</p>
//
//<p><strong>示例:</strong></p>
//
//<pre><strong>输入:</strong>
//A = [1,2,3,0,0,0], m = 3
//B = [2,5,6],       n = 3
//
//<strong>输出:</strong>&nbsp;[1,2,2,3,5,6]</pre>
//
//<p><strong>说明:</strong></p>
//
//<ul> 
// <li><code>A.length == n + m</code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 155</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//面试题 10.01.合并排序的数组
//开题时间：2022-09-25 14:30:07
public class SortedMergeLcci {
    public static void main(String[] args) {
        Solution solution = new SortedMergeLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //双指针   m*2+n   1
        public void merge(int[] A, int m, int[] B, int n) {
            System.arraycopy(A, 0, A, n, m);

            int i = 0, l = n, r = 0;
            int length = A.length;
            for (; l < length && r < n; i++) {
                if (A[l] <= B[r]) A[i] = A[l++];
                else A[i] = B[r++];
            }

            while (l < length) A[i++] = A[l++];
            while (r < n) A[i++] = B[r++];
        }

        //双指针（逆向排序：先大后小）   m+n   1
        public void merge2(int[] A, int m, int[] B, int n) {
            for (m--, n--; 0 <= m && 0 <= n; )
                A[m + n + 1] = A[m] >= B[n] ? A[m--] : B[n--];
            //只要补上剩余的 B数组 即可，A数组不用补，就在那里。
            while (0 <= n)
                A[m + n + 1] = B[n--];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}