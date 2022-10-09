//<p>给定一个整数数组 <code>arr</code>&nbsp;，返回 <code>arr</code>&nbsp;的&nbsp;<em>最大湍流子数组的<strong>长度</strong></em><strong>&nbsp;</strong>。</p>
//
//<p>如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是&nbsp;<strong>湍流子数组</strong>&nbsp;。</p>
//
//<p>更正式地来说，当 <code>arr</code>&nbsp;的子数组&nbsp;<code>A[i], A[i+1], ..., A[j]</code>&nbsp;满足仅满足下列条件时，我们称其为<em>湍流子数组</em>：</p>
//
//<ul> 
// <li>若&nbsp;<code>i &lt;= k &lt; j</code>&nbsp;： </li>
//</ul>
//
//    <ul>
//    	<li>当 <code>k</code>&nbsp;为奇数时，&nbsp;<code>A[k] &gt; A[k+1]</code>，且</li>
//    	<li>当 <code>k</code> 为偶数时，<code>A[k] &lt; A[k+1]</code>；</li>
//    </ul>
//    </li>
//    <li><strong>或 </strong>若&nbsp;<code>i &lt;= k &lt; j</code>&nbsp;：
//    <ul>
//    	<li>当 <code>k</code> 为偶数时，<code>A[k] &gt; A[k+1]</code>&nbsp;，且</li>
//    	<li>当 <code>k</code>&nbsp;为奇数时，&nbsp;<code>A[k] &lt; A[k+1]</code>。</li>
//    </ul>
//    </li>
//
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [9,4,2,10,7,8,8,1,9]
//<strong>输出：</strong>5
//<strong>解释：</strong>arr[1] &gt; arr[2] &lt; arr[3] &gt; arr[4] &lt; arr[5]</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [4,8,12,16]
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [100]
//<strong>输出：</strong>1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 4 * 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>动态规划</li><li>滑动窗口</li></div></div><br><div><li>👍 212</li><li>👎 0</li></div>
package org.example.leetcode.problems;

//978.最长湍流子数组
//开题时间：2022-10-09 14:03:22
public class LongestTurbulentSubarray {

    public static void main(String[] args) {
        Solution solution = new LongestTurbulentSubarray().new Solution();
//        System.out.println(solution.maxTurbulenceSize(new int[]{0, 8, 45, 88, 48, 68, 28, 55, 17, 24}));
//        System.out.println(solution.maxTurbulenceSize3(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println(solution.maxTurbulenceSize3(new int[]{37, 199, 60, 296, 257, 248, 115, 31, 273, 176}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxTurbulenceSize(int[] arr) {
            //[l,r) is turbulence
            int max = 1;
            int len = arr.length;
            int l = 0;

            if (len > 1 && arr[0] == arr[1])
                l = 1;
            for (int r = 2; r < len; r++) {
                if (arr[r] == arr[r - 1] || Integer.signum(arr[r] - arr[r - 1]) != -Integer.signum(arr[r - 1] - arr[r - 2])) {
                    max = Math.max(max, r - l);
                    if (arr[r] == arr[r - 1])
                        l = r;
                    else
                        l = r - 1;
                }
            }
            max = Math.max(max, len - l);

            return max;
        }


        public static final int EQUAL = 2;

        public int maxTurbulenceSize2(int[] arr) {
            //[l,r) is turbulence
            int max = 1;
            int len = arr.length;
            for (int i = len - 1; i > 0; i--) {
                if (arr[i] > arr[i - 1])
                    arr[i] = 1;
                else if (arr[i] < arr[i - 1])
                    arr[i] = -1;
                else
                    arr[i] = EQUAL;
            }

            int l = 0;

            if (len > 1 && arr[1] == EQUAL)
                l = 1;
            for (int r = 2; r < len; r++) {
                if (arr[r] != -arr[r - 1]) {
                    max = Math.max(max, r - l);
                    if (arr[r] == EQUAL)
                        l = r;
                    else
                        l = r - 1;
                }
            }
            max = Math.max(max, len - l);

            return max;
        }

        //DP
        public int maxTurbulenceSize3(int[] arr) {
            int max = 1;
            int len = arr.length;
            if (len == 1)
                return max;

            for (int i = len - 1; i > 0; i--)
                arr[i] = arr[i] - arr[i - 1];
            arr[0] = 0;

            for (int i = 1, step = 0; i < len; i++) {
                if ((arr[i] > 0 && arr[i - 1] < 0) ||
                        (arr[i] < 0 && arr[i - 1] > 0))
                    step++;
                else if (arr[i] == 0)
                    step = 1;
                else
                    step = 2;

                max = Math.max(max, step);
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}