//<p>现在你总共有 <code>numCourses</code> 门课需要选，记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>numCourses - 1</code>。给你一个数组&nbsp;<code>prerequisites</code> ，其中 <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> ，表示在选修课程 <code>a<sub>i</sub></code> 前 <strong>必须</strong> 先选修&nbsp;<code>b<sub>i</sub></code> 。</p>
//
//<ul> 
// <li>例如，想要学习课程 <code>0</code> ，你需要先完成课程&nbsp;<code>1</code> ，我们用一个匹配来表示：<code>[0,1]</code> 。</li> 
//</ul>
//
//<p>返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 <strong>任意一种</strong> 就可以了。如果不可能完成所有课程，返回 <strong>一个空数组</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]]
//<strong>输出：</strong>[0,1]
//<strong>解释：</strong>总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 <span><code>[0,1] 。</code></span>
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//<strong>输出：</strong>[0,2,1,3]
//<strong>解释：</strong>总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是&nbsp;<span><code>[0,1,2,3]</code></span> 。另一个正确的排序是&nbsp;<span><code>[0,2,1,3]</code></span> 。</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>numCourses = 1, prerequisites = []
//<strong>输出：</strong>[0]
//</pre>
//
//<p>&nbsp;</p> 
//<strong>提示：</strong>
//
//<ul> 
// <li><code>1 &lt;= numCourses &lt;= 2000</code></li> 
// <li><code>0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li> 
// <li><code>prerequisites[i].length == 2</code></li> 
// <li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li> 
// <li><code>a<sub>i</sub> != b<sub>i</sub></code></li> 
// <li>所有<code>[a<sub>i</sub>, b<sub>i</sub>]</code> <strong>互不相同</strong></li> 
//</ul>
//
//<div><li>👍 733</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.*;

//210.课程表 II
//开题时间：2022-12-31 17:23:24
public class CourseScheduleIi {
    public static void main(String[] args) {
        Solution solution = new CourseScheduleIi().new Solution();
        System.out.println(Arrays.toString(solution.findOrder(3, Tools.to2DIntArray("[[1,0],[1,2],[0,1]]"))));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //拓扑排序 + 邻接矩阵
        public int[] findOrder9(int numCourses, int[][] prerequisites) {
            int[] degree = new int[numCourses];
            boolean[][] graph = new boolean[numCourses][numCourses];
            for (int[] prerequisite : prerequisites) {
                graph[prerequisite[1]][prerequisite[0]] = true;
                degree[prerequisite[0]]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < degree.length; i++)
                if (degree[i] == 0)
                    q.offer(i);

            int[] ans = new int[numCourses];
            int i = 0;
            while (!q.isEmpty()) {
                int poll = q.poll();
                ans[i++] = poll;
                for (int j = 0; j < graph[poll].length; j++)
                    if (graph[poll][j])
                        if (--degree[j] == 0)
                            q.offer(j);
            }

            return i == numCourses ? ans : new int[0];
        }

        //☆☆☆☆☆ 拓扑排序（BFS + 贪心） + 邻接表    O(E+V) O(E+V)
        public int[] findOrder(int n, int[][] prerequisites) {
            int[] inDegree = new int[n];
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; i++)
                graph[i] = new ArrayList<>();
            for (int[] prerequisite : prerequisites) {
                graph[prerequisite[1]].add(prerequisite[0]);
                inDegree[prerequisite[0]]++;
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < inDegree.length; i++)
                if (inDegree[i] == 0)
                    q.offer(i);

            int[] ans = new int[n];
            int i = 0;
            while (!q.isEmpty()) {
                int poll = q.poll();
                ans[i++] = poll;
                for (int j : graph[poll])
                    if (--inDegree[j] == 0)
                        q.offer(j);
            }

            return i == n ? ans : new int[0];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}