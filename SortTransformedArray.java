//<p>给你一个已经<strong>&nbsp;排好序</strong>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;和整数&nbsp;<code>a</code>&nbsp;、&nbsp;<code>b</code>&nbsp;、&nbsp;<code>c</code>&nbsp;。对于数组中的每一个元素&nbsp;<code>nums[i]</code>&nbsp;，计算函数值&nbsp;<code>f(<em>x</em>) = <em>ax</em><sup>2</sup> + <em>bx</em> + c</code>&nbsp;，请 <em>按升序返回数组</em> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入: </strong>nums = [-4,-2,2,4], a = 1, b = 3, c = 5
//<strong>输出: </strong>[3,9,15,33]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入: </strong>nums = [-4,-2,2,4], a = -1, b = 3, c = 5
//<strong>输出: </strong>[-23,-5,1,7]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 200</code></li> 
// <li><code>-100 &lt;= nums[i], a, b, c &lt;= 100</code></li> 
// <li><code>nums</code>&nbsp;按照 <strong>升序排列</strong></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong>你可以在时间复杂度为&nbsp;<code>O(n)</code>&nbsp;的情况下解决这个问题吗？</p>
//
//<div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 65</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.Arrays;

//360.有序转化数组
//开题时间：2022-10-25 17:59:29
public class SortTransformedArray {
    public static void main(String[] args) {
        Solution solution = new SortTransformedArray().new Solution();
        System.out.println(solution.sortTransformedArray(new int[]{-4, -2, 2, 4}, 1, 3, 5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
            int len = nums.length;
            int[] ans = new int[len];
            if (a == 0)
                if (b >= 0)
                    for (int i = 0; i < len; i++)
                        ans[i] = b * nums[i] + c;
                else
                    for (int i = 0, j = len - 1; i < len; i++, j--)
                        ans[i] = b * nums[j] + c;
            else {
                int mid = -b / (a * 2);
                int idx = 0;
                if (a < 0) {
                    int l = 0, r = len - 1;
                    for (; l < r; ) {
                        //todo <=
                        ans[idx++] = mid - nums[l] <= nums[r] - mid ?
                                (a * nums[r] * nums[r] + b * nums[r--] + c) :
                                (a * nums[l] * nums[l] + b * nums[l++] + c);
                    }
                    ans[len - 1] = a * nums[r] * nums[r] + b * nums[r] + c;
                } else {
                    int search = Arrays.binarySearch(nums, mid);
                    if (search < 0)
                        search = -search - 1;

                    int l = search - 1, r = search;
                    for (; l >= 0 && r < len; ) {
                        ans[idx++] = mid - nums[l] <= nums[r] - mid ?
                                (a * nums[l] * nums[l] + b * nums[l--] + c) :
                                (a * nums[r] * nums[r] + b * nums[r++] + c);
                    }
                    if (l < 0) {
                        while (r < len)
                            ans[idx++] = a * nums[r] * nums[r] + b * nums[r++] + c;
                    } else {
                        while (l >= 0)
                            ans[idx++] = a * nums[l] * nums[l] + b * nums[l--] + c;
                    }
                }
            }

            return ans;
        }

        public int[] sortTransformedArray2(int[] nums, int a, int b, int c) {
            for (int i = 0; i < nums.length; i++)
                nums[i] = a * nums[i] * nums[i] + b * nums[i] + c;
            Arrays.sort(nums);
            return nums;
        }

        public int[] sortTransformedArray3(int[] nums, int a, int b, int c) {
            return Arrays.stream(nums).map(e -> a * e * e + b * e + c).sorted().toArray();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
//-3,387,2895,5367,5275,5275,6255,7317,8577,11127,11127,10995,15411,15411,18765,20745,22447,22447,24607,28797,33315,33315,33315,33087,35697,38161,40707,40455,40455,48561,48561,54667,54375,73785,81267,80911,88737,88365,92215,100557,100161,104257,117037,117037,144811,150211,150211,150211,149727,154725,160305,170727,170211,170211,176061,175537,175537,203397,203397,221685,240147,252237,265267,271905,307017,314155,313455,320667,320667,328677,343527,342795,351075,350335,358705,366417,366417,365661,365661,382087,382087,390045
