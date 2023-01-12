//<p>给你一个整数 <code>n</code> ，对于&nbsp;<code>0 &lt;= i &lt;= n</code> 中的每个 <code>i</code> ，计算其二进制表示中 <strong><code>1</code> 的个数</strong> ，返回一个长度为 <code>n + 1</code> 的数组 <code>ans</code> 作为答案。</p>
//
//<p>&nbsp;</p>
//
//<div class="original__bRMd"> 
// <div> 
//  <p><strong>示例 1：</strong></p> 
// </div>
//</div>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>[0,1,1]
//<strong>解释：</strong>
//0 --&gt; 0
//1 --&gt; 1
//2 --&gt; 10
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 5
//<strong>输出：</strong>[0,1,1,2,1,2]
//<strong>解释：</strong>
//0 --&gt; 0
//1 --&gt; 1
//2 --&gt; 10
//3 --&gt; 11
//4 --&gt; 100
//5 --&gt; 101
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>0 &lt;= n &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<p><strong>进阶：</strong></p>
//
//<ul> 
// <li>很容易就能实现时间复杂度为 <code>O(n log n)</code> 的解决方案，你可以在线性时间复杂度 <code>O(n)</code> 内用一趟扫描解决此问题吗？</li> 
// <li>你能不使用任何内置函数解决此问题吗？（如，C++ 中的&nbsp;<code>__builtin_popcount</code> ）</li> 
//</ul>
//
//<div><li>👍 1126</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.dp;

//338.比特位计数
//开题时间：2022-12-06 17:30:01
public class CountingBits {
    public static void main(String[] args) {
        Solution solution = new CountingBits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //利用 `n & (n-1)` 能够每次消去末尾的一个 1
        public int[] countBits9(int n) {
            int[] ans = new int[n + 1];

            for (int i = 0; i < ans.length; i++)
                for (int j = i; j != 0; j &= (j - 1))
                    ans[i]++;

            return ans;
        }

        //寻找倒数一个 0 的位置
        public int[] countBits8(int n) {
            int[] ans = new int[n + 1];

            for (int i = 1; i < ans.length; i++) {
                int cnt = -1;
                for (int j = i - 1; j != 0; j >>= 1)
                    if ((j & 1) == 0)
                        break;
                    else
                        cnt++;
                ans[i] = ans[i - 1] - cnt;
            }

            return ans;
        }

        /*
         * i为偶数，则与 f[i/2] 相同
         * i为奇数，则为 f[i-1] + 1
         */
        public int[] countBits7(int n) {
            int[] ans = new int[n + 1];

            for (int i = 1; i < ans.length; i++) {
                if ((i & 1) == 0)
                    ans[i] = ans[i >> 1];
                else
                    ans[i] = ans[i - 1] + 1;
            }

            return ans;
        }

        //☆☆☆☆☆ 法七简化：DP（最低有效位）
        public int[] countBits6(int n) {
            int[] ans = new int[n + 1];

            for (int i = 1; i < ans.length; i++)
                ans[i] = ans[i >> 1] + (i & 1);

            return ans;
        }

        public int[] countBits5(int n) {
            int[] ans = new int[n + 1];

            for (int i = 1; i < ans.length; i++)
                ans[i] = Integer.bitCount(i);

            return ans;
        }

        //DP（最高有效位）
        public int[] countBits4(int n) {
            int[] ans = new int[n + 1];

            for (int i = 1, highBit = 0; i < ans.length; i++) {
                if ((i & (i - 1)) == 0)
                    highBit = i;
                ans[i] = ans[i - highBit] + 1;
            }

            return ans;
        }

        //☆☆☆☆☆ DP（最低设置位）
        public int[] countBits(int n) {
            int[] ans = new int[n + 1];

            for (int i = 1, highBit = 0; i < ans.length; i++)
                ans[i] = ans[i & (i - 1)] + 1;

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}