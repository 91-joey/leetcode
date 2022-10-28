//<p>把符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：</p>
//
//<ul> 
// <li><code>arr.length &gt;= 3</code></li> 
// <li>存在下标 <code>i</code>（<code>0 &lt; i &lt; arr.length - 1</code>），满足 
//  <ul> 
//   <li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li> 
//   <li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li> 
//  </ul> </li> 
//</ul>
//
//<p>给出一个整数数组 <code>arr</code>，返回最长山脉子数组的长度。如果不存在山脉子数组，返回 <code>0</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [2,1,4,7,3,2,5]
//<strong>输出：</strong>5
//<strong>解释：</strong>最长的山脉子数组是 [1,4,7,3,2]，长度为 5。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>arr = [2,2,2]
//<strong>输出：</strong>0
//<strong>解释：</strong>不存在山脉子数组。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>你可以仅用一趟扫描解决此问题吗？</li> 
// <li>你可以用 <code>O(1)</code> 空间解决此问题吗？</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>动态规划</li><li>枚举</li></div></div><br><div><li>👍 250</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.slidingWindow_doublePointer;

//845.数组中的最长山脉
//开题时间：2022-10-27 08:48:30
public class LongestMountainInArray {
    public static void main(String[] args) {
        Solution solution = new LongestMountainInArray().new Solution();
//        System.out.println(solution.longestMountain2(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(solution.longestMountain4(new int[]{875, 884, 239, 731, 723, 685}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //枚举山顶+暴力   n^2 1
        public int longestMountain(int[] arr) {
            int len = arr.length;
            if (len < 3)
                return 0;

            int max = 0;
            for (int i = 1; i < len - 1; i++) {
                int l = i;
                if (arr[l] <= arr[--l])
                    continue;
                while (l > 0 && arr[l - 1] < arr[l])
                    l--;

                int r = i;
                if (arr[r] <= arr[++r])
                    continue;
                while (r < len - 1 && arr[r] > arr[r + 1])
                    r++;

                max = Math.max(max, r - l + 1);
            }
            return max;
        }


        //枚举山顶+剪枝   n*3 n*2
        public int longestMountain2(int[] arr) {
            int len = arr.length;
            if (len < 3)
                return 0;

            int[] l = new int[len];
            int[] r = new int[len];
            for (int i = 1; i < len - 1; i++)
                l[i] = arr[i - 1] < arr[i] ? l[i - 1] + 1 : 0;

            for (int i = len - 2; i > 0; i--)
                r[i] = arr[i] > arr[i + 1] ? r[i + 1] + 1 : 0;

            int max = 0;
            for (int i = 1; i < len - 1; i++) {
                if (l[i] != 0 && r[i] != 0)
                    max = Math.max(max, l[i] + r[i] + 1);
            }
            return max;
        }

        //枚举山顶+剪枝（优化）   n*2 n
        public int longestMountain3(int[] arr) {
            int len = arr.length;
            if (len < 3)
                return 0;

            int[] r = new int[len];
            for (int i = len - 2; i > 0; i--)
                r[i] = arr[i] > arr[i + 1] ? r[i + 1] + 1 : 0;

            int max = 0;
            for (int i = 1, l = 0; i < len - 1; i++) {
                l = arr[i - 1] < arr[i] ? l + 1 : 0;
                if (l != 0 && r[i] != 0)
                    max = Math.max(max, l + r[i] + 1);
            }
            return max;
        }

        //☆☆☆☆☆ 枚举山脚+双指针  n   1
        public int longestMountain4(int[] arr) {
            int max = 0;
            int len = arr.length;

            for (int l = 0; l < len - 2; ) {
                int r = l + 1;
                //上坡
                if (arr[l] < arr[r]) {
                    while (r < len - 1 && arr[r] < arr[r + 1])
                        r++;
                    if (r == len - 1) {
                        break;
                    } else if (arr[r] == arr[r + 1]) {
                        l = r + 1;
                        //下坡
                    } else {
                        do {
                            r++;
                        } while (r < len - 1 && arr[r] > arr[r + 1]);
                        max = Math.max(max, r - l + 1);
                        l = r;
                    }
                } else {
                    l = r;
                }
            }

            return max;
        }

        //枚举山顶+暴力（优化）
        public int longestMountain5(int[] arr) {
            int len = arr.length;
            int max = 0;

            for (int i = 1; i < len - 1; i++) {
                int l = i - 1;
                int r = i + 1;
                if (arr[l] < arr[i] && arr[i] > arr[r]) {
                    while (l > 0 && arr[l - 1] < arr[l]) l--;
                    while (r < len - 1 && arr[r] > arr[r + 1]) r++;
                    max = Math.max(max, r - l + 1);
                }
            }

            return max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}