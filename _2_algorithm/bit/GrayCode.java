//<strong>n 位格雷码序列</strong> 是一个由 <code>2<sup>n</sup></code> 个整数组成的序列，其中：
//
//<ul> 
// <li>每个整数都在范围 <code>[0, 2<sup>n</sup> - 1]</code> 内（含 <code>0</code> 和 <code>2<sup>n</sup> - 1</code>）</li> 
// <li>第一个整数是 <code>0</code></li> 
// <li>一个整数在序列中出现 <strong>不超过一次</strong></li> 
// <li>每对 <strong>相邻</strong> 整数的二进制表示 <strong>恰好一位不同</strong> ，且</li> 
// <li><strong>第一个</strong> 和 <strong>最后一个</strong> 整数的二进制表示 <strong>恰好一位不同</strong></li> 
//</ul>
//
//<p>给你一个整数 <code>n</code> ，返回任一有效的 <strong>n 位格雷码序列</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 2
//<strong>输出：</strong>[0,1,3,2]
//<strong>解释：</strong>
//[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
//- 0<strong><em>0</em></strong> 和 0<em><strong>1</strong></em> 有一位不同
//- <em><strong>0</strong></em>1 和 <em><strong>1</strong></em>1 有一位不同
//- 1<em><strong>1</strong></em> 和 1<em><strong>0</strong></em> 有一位不同
//- <em><strong>1</strong></em>0 和 <em><strong>0</strong></em>0 有一位不同
//[0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
//- <em><strong>0</strong></em>0 和 <em><strong>1</strong></em>0 有一位不同
//- 1<em><strong>0</strong></em> 和 1<em><strong>1</strong></em> 有一位不同
//- <em><strong>1</strong></em>1 和 <em><strong>0</strong></em>1 有一位不同
//- 0<em><strong>1</strong></em> 和 0<em><strong>0</strong></em> 有一位不同
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1
//<strong>输出：</strong>[0,1]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 16</code></li> 
//</ul>
//
//<div><li>👍 565</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.bit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//89.格雷编码
//开题时间：2023-01-06 16:45:52
public class GrayCode {
    public static void main(String[] args) {
        Solution solution = new GrayCode().new Solution();
        System.out.println(solution.grayCode(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> grayCode9(int n) {
            ArrayList<Integer> ans = new ArrayList<>();
            ans.add(0);
            ans.add(1);

            for (int i = 1; i < n; i++)
                for (int mask = 1 << i, j = mask - 1; j >= 0; j--)
                    ans.add(ans.get(j) | mask);

            return ans;
        }

        //☆☆☆☆☆ 镜像反射法
        public List<Integer> grayCode8(int n) {
            ArrayList<Integer> ans = new ArrayList<>() {{
                add(0);
            }};

            for (int i = 0; i < n; i++)
                for (int mask = ans.size(), j = mask - 1; j >= 0; j--)
                    ans.add(ans.get(j) | mask);

            return ans;
        }

        //☆☆☆☆ 公式法
        public List<Integer> grayCode7(int n) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0, bound = 1 << n; i < bound; i++)
                ans.add(i ^ (i >> 1));
            return ans;
        }

        public List<Integer> grayCode(int n) {
            return Stream.iterate(0, i -> i + 1)
                    .limit(1 << n)
                    .mapToInt(i -> i ^ (i >> 1))
                    .boxed()
                    .toList();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}