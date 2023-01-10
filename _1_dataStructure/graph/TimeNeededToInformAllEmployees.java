//<p>公司里有 <code>n</code> 名员工，每个员工的 ID 都是独一无二的，编号从 <code>0</code> 到 <code>n - 1</code>。公司的总负责人通过 <code>headID</code> 进行标识。</p>
//
//<p>在 <code>manager</code> 数组中，每个员工都有一个直属负责人，其中 <code>manager[i]</code> 是第 <code>i</code> 名员工的直属负责人。对于总负责人，<code>manager[headID] = -1</code>。题目保证从属关系可以用树结构显示。</p>
//
//<p>公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。</p>
//
//<p>第 <code>i</code> 名员工需要 <code>informTime[i]</code> 分钟来通知它的所有直属下属（也就是说在 <code>informTime[i]</code> 分钟后，他的所有直属下属都可以开始传播这一消息）。</p>
//
//<p>返回通知所有员工这一紧急消息所需要的 <strong>分钟数</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>n = 1, headID = 0, manager = [-1], informTime = [0]
//<strong>输出：</strong>0
//<strong>解释：</strong>公司总负责人是该公司的唯一一名员工。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/03/08/graph.png" style="height: 174px; width: 404px;" /></p>
//
//<pre>
//<strong>输入：</strong>n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
//<strong>输出：</strong>1
//<strong>解释：</strong>id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
//上图显示了公司员工的树结构。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= n &lt;= 10^5</code></li> 
// <li><code>0 &lt;= headID &lt; n</code></li> 
// <li><code>manager.length == n</code></li> 
// <li><code>0 &lt;= manager[i] &lt; n</code></li> 
// <li><code>manager[headID] == -1</code></li> 
// <li><code>informTime.length&nbsp;== n</code></li> 
// <li><code>0 &lt;= informTime[i] &lt;= 1000</code></li> 
// <li>如果员工 <code>i</code> 没有下属，<code>informTime[i] == 0</code> 。</li> 
// <li>题目 <strong>保证</strong> 所有员工都可以收到通知。</li> 
//</ul>
//
//<div><li>👍 116</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.graph;

import java.util.ArrayList;
import java.util.List;

//1376.通知所有员工所需的时间
//开题时间：2023-01-10 18:01:37
public class TimeNeededToInformAllEmployees {
    public static void main(String[] args) {
        Solution solution = new TimeNeededToInformAllEmployees().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int max = 0;

        //自顶向下 + 建树
        public int numOfMinutes9(int n, int headID, int[] manager, int[] informTime) {
            List<Integer>[] g = new ArrayList[n];
            for (int i = 0; i < n; i++)
                g[i] = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if (i != headID)
                    g[manager[i]].add(i);

            dfs(g, informTime, headID, 0);

            return max;
        }

        private void dfs(List<Integer>[] g, int[] informTime, int i, int time) {
            if (informTime[i] == 0) {
                max = Math.max(max, time);
                return;
            }

            for (int j : g[i])
                dfs(g, informTime, j, time + informTime[i]);
        }

        //自底向上 + 记忆化
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            int max = 0;
            int[] memo = new int[n];

            for (int i = 0; i < n; i++)
                if (informTime[i] == 0)
                    max = Math.max(max, memoDfs(manager, informTime, memo, i));

            return max;
        }

        private int memoDfs(int[] manager, int[] informTime, int[] memo, int i) {
            if (i == -1)
                return 0;

            if (memo[i] != 0)
                return memo[i];

            memo[i] += memoDfs(manager, informTime, memo, manager[i]) + informTime[i];
            return memo[i];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}