//<p>给你一个数组&nbsp;<code>nums</code>&nbsp;，数组中只包含非负整数。定义&nbsp;<code>rev(x)</code>&nbsp;的值为将整数&nbsp;<code>x</code>&nbsp;各个数字位反转得到的结果。比方说&nbsp;<code>rev(123) = 321</code>&nbsp;，&nbsp;<code>rev(120) = 21</code>&nbsp;。我们称满足下面条件的下标对&nbsp;<code>(i, j)</code> 是&nbsp;<strong>好的</strong>&nbsp;：</p>
//
//<ul> 
// <li><code>0 &lt;= i &lt; j &lt; nums.length</code></li> 
// <li><code>nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])</code></li> 
//</ul>
//
//<p>请你返回好下标对的数目。由于结果可能会很大，请将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<b>取余</b>&nbsp;后返回。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><b>输入：</b>nums = [42,11,1,97]
//<b>输出：</b>2
//<b>解释：</b>两个坐标对为：
// - (0,3)：42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121 。
// - (1,2)：11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><b>输入：</b>nums = [13,10,35,24,76]
//<b>输出：</b>4
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 64</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.hashtable;

import java.util.HashMap;
import java.util.Map;

//1814.统计一个数组中好对子的数目
//开题时间：2023-01-17 12:30:48
public class CountNicePairsInAnArray {
    public static void main(String[] args) {
        Solution solution = new CountNicePairsInAnArray().new Solution();
        System.out.println(solution);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //ab,cd ab+dc   ba+cd   10(a+d)+b+c==10(b+c)+a+d 10(a+d-b-c)+b+c-a-d==0 9a-9b-9c+9d==0 a-b-c+d==0 a+d==b+c
        public int countNicePairs9(int[] nums) {
            long ans = 0;
            Map<Integer, Integer> diff2cnt = new HashMap<>();
            for (int x : nums) {
                x -= Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());
                ans += diff2cnt.merge(x, 1, Integer::sum) - 1;
            }
            return (int) (ans % 1000000007);
        }

        /*
         * ☆☆☆☆☆ 式子变换 + 哈希表
         * a+rev(b)=b+rev(a) 等价于 a-rev(a)=b-rev(b)
         */
        public int countNicePairs(int[] nums) {
            long ans = 0;
            Map<Integer, Integer> diff2cnt = new HashMap<>();
            for (int x : nums)
                ans += diff2cnt.merge(x - reverse(x), 1, Integer::sum) - 1;
            return (int) (ans % 1000000007);
        }

        /**
         * 反转数字（十进制）
         */
        public int reverse(int x) {
            int rev = 0;
            for (int tmp = x; tmp != 0; tmp /= 10)
                rev = rev * 10 + tmp % 10;
            return rev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}