//<p>给你一个有&nbsp;<code>n</code>&nbsp;个节点的 <strong>有向无环图（DAG）</strong>，请你找出所有从节点 <code>0</code>&nbsp;到节点 <code>n-1</code>&nbsp;的路径并输出（<strong>不要求按特定顺序</strong>）</p>
//
//<p>
// <meta charset="UTF-8" />&nbsp;<code>graph[i]</code>&nbsp;是一个从节点 <code>i</code> 可以访问的所有节点的列表（即从节点 <code>i</code> 到节点&nbsp;<code>graph[i][j]</code>存在一条有向边）。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/28/all_1.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>graph = [[1,2],[3],[3],[]]
//<strong>输出：</strong>[[0,1,3],[0,2,3]]
//<strong>解释：</strong>有两条路径 0 -&gt; 1 -&gt; 3 和 0 -&gt; 2 -&gt; 3
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/28/all_2.jpg" /></p>
//
//<pre>
//<strong>输入：</strong>graph = [[4,3,1],[3,2,4],[3],[4],[]]
//<strong>输出：</strong>[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>n == graph.length</code></li> 
// <li><code>2 &lt;= n &lt;= 15</code></li> 
// <li><code>0 &lt;= graph[i][j] &lt; n</code></li> 
// <li><code>graph[i][j] != i</code>（即不存在自环）</li> 
// <li><code>graph[i]</code> 中的所有元素 <strong>互不相同</strong></li> 
// <li>保证输入为 <strong>有向无环图（DAG）</strong></li> 
//</ul>
//
//<p>&nbsp;</p>
//
//<div><li>👍 361</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//797.所有可能的路径
//开题时间：2022-12-24 14:04:46
public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        Solution solution = new AllPathsFromSourceToTarget().new Solution();
        System.out.println(solution.allPathsSourceTarget(Tools.to2DIntArray("[[1,2],[3],[3],[]]")));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> allPathsSourceTarget9(int[][] graph) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            dfs(graph, ans, list, 0);
            return ans;
        }

        private void dfs(int[][] graph, List<List<Integer>> ans, List<Integer> list, int i) {
            ArrayList<Integer> copy = new ArrayList<>(list);
            copy.add(i);

            if (i == graph.length - 1) {
                ans.add(copy);
                return;
            }

            for (int j : graph[i])
                dfs(graph, ans, copy, j);
        }

        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> stack = new LinkedList<>();

        //☆☆☆☆☆ DFS + 回溯
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            dfs(graph, 0);
            return ans;
        }

        private void dfs(int[][] graph, int cur) {
            stack.offer(cur);
            if (cur == graph.length - 1)
                ans.add(new ArrayList<>(stack));
            else
                for (int i : graph[cur]) {
                    dfs(graph, i);
                    //回溯
                    stack.pollLast();
                }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}