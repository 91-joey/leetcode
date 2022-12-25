//<p>有一个立方体房间，其长度、宽度和高度都等于 <code>n</code> 个单位。请你在房间里放置 <code>n</code> 个盒子，每个盒子都是一个单位边长的立方体。放置规则如下：</p>
//
//<ul> 
// <li>你可以把盒子放在地板上的任何地方。</li> 
// <li>如果盒子 <code>x</code> 需要放置在盒子 <code>y</code> 的顶部，那么盒子 <code>y</code> 竖直的四个侧面都 <strong>必须</strong> 与另一个盒子或墙相邻。</li> 
//</ul>
//
//<p>给你一个整数 <code>n</code> ，返回接触地面的盒子的 <strong>最少</strong> 可能数量<em>。</em></p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/24/3-boxes.png" style="width: 135px; height: 143px;" /></p>
//
//<pre>
//<strong>输入：</strong>n = 3
//<strong>输出：</strong>3
//<strong>解释：</strong>上图是 3 个盒子的摆放位置。
//这些盒子放在房间的一角，对应左侧位置。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/24/4-boxes.png" style="width: 135px; height: 179px;" /></p>
//
//<pre>
//<strong>输入：</strong>n = 4
//<strong>输出：</strong>3
//<strong>解释：</strong>上图是 3 个盒子的摆放位置。
//这些盒子放在房间的一角，对应左侧位置。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/24/10-boxes.png" style="width: 271px; height: 257px;" /></p>
//
//<pre>
//<strong>输入：</strong>n = 10
//<strong>输出：</strong>6
//<strong>解释：</strong>上图是 10 个盒子的摆放位置。
//这些盒子放在房间的一角，对应后方位置。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li> 
//</ul>
//
//<div><li>👍 69</li><li>👎 0</li></div>
package org.example.leetcode.problems._2_algorithm.greedy;

//1739.放置盒子
//开题时间：2022-12-25 17:13:54
public class BuildingBoxes {
    public static void main(String[] args) {
        Solution solution = new BuildingBoxes().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //找规律
        public int minimumBoxes9(int n) {
            int ans = 0, total = 0;
            for (int i = 1; total + ans + i <= n; i++) {
                ans += i;
                total += ans;
            }
            for (int i = 1; total < n; i++) {
                ans++;
                total += i;
            }
            return ans;
        }

        //数学优化
        public int minimumBoxes(int n) {
            int x = (int) Math.cbrt(6L * n);
            int i = (int) ((long) x * (x + 1) * (x + 2) / 6);
            if (i > n) {
                x--;
                i = (int) ((long) x * (x + 1) * (x + 2) / 6);
            }
            int y = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * (n - i))) / 2);
            return x * (x + 1) / 2 + y;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}