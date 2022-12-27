//<p>有 <code>n</code> 个网络节点，标记为&nbsp;<code>1</code>&nbsp;到 <code>n</code>。</p>
//
//<p>给你一个列表&nbsp;<code>times</code>，表示信号经过 <strong>有向</strong> 边的传递时间。&nbsp;<code>times[i] = (u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>)</code>，其中&nbsp;<code>u<sub>i</sub></code>&nbsp;是源节点，<code>v<sub>i</sub></code>&nbsp;是目标节点， <code>w<sub>i</sub></code>&nbsp;是一个信号从源节点传递到目标节点的时间。</p>
//
//<p>现在，从某个节点&nbsp;<code>K</code>&nbsp;发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回&nbsp;<code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<p><img alt="" src="https://assets.leetcode.com/uploads/2019/05/23/931_example_1.png" style="height: 220px; width: 200px;" /></p>
//
//<pre>
//<strong>输入：</strong>times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//<strong>输出：</strong>2
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 1
//<strong>输出：</strong>1
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 2
//<strong>输出：</strong>-1
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= k &lt;= n &lt;= 100</code></li> 
// <li><code>1 &lt;= times.length &lt;= 6000</code></li> 
// <li><code>times[i].length == 3</code></li> 
// <li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li> 
// <li><code>u<sub>i</sub> != v<sub>i</sub></code></li> 
// <li><code>0 &lt;= w<sub>i</sub> &lt;= 100</code></li> 
// <li>所有 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 对都 <strong>互不相同</strong>（即，不含重复边）</li> 
//</ul>
//
//<div><li>👍 621</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import org.example.leetcode.problems._3_common.tool.Tools;

import java.util.*;

//743.网络延迟时间
//开题时间：2022-12-27 12:15:37
public class NetworkDelayTime {
    public static void main(String[] args) {
        Solution solution = new NetworkDelayTime().new Solution();
        System.out.println(solution.networkDelayTime(Tools.to2DIntArray("[[2,1,1],[2,3,1],[3,4,1]]"), 4, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int networkDelayTime(int[][] times, int n, int k) {
            HashMap<Integer, Set<int[]>> v2vs = new HashMap<>();
            for (int[] time : times) {
                if (v2vs.containsKey(time[0]))
                    v2vs.get(time[0]).add(new int[]{time[1], time[2]});
                else {
                    HashSet<int[]> set = new HashSet<>();
                    set.add(new int[]{time[1], time[2]});
                    v2vs.put(time[0], set);
                }
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
            Set<int[]> set = v2vs.get(k);
            if (set == null)
                return -1;
            set.forEach(pq::offer);
            boolean[] vis = new boolean[n + 1];
            vis[k] = true;
            int[] mins = new int[n + 1];
            Arrays.fill(mins, Integer.MAX_VALUE);
            mins[k] = 0;
            set.forEach(arr -> {
                mins[arr[0]] = arr[1];
            });

            while (!pq.isEmpty()) {
                int[] poll = pq.poll();
                vis[poll[0]] = true;
                set = v2vs.get(poll[0]);
                if (set == null)
                    continue;
                for (int[] arr : set) {
                    if (!vis[arr[0]] && poll[1] + arr[1] < mins[arr[0]]) {
                        vis[arr[0]] = true;
                        mins[arr[0]] = poll[1] + arr[1];
                        if (pq.removeIf(a -> a[0] == arr[0]))
                            pq.offer(new int[]{arr[0], mins[arr[0]]});
                    }
                }
            }
            int max = Arrays.stream(mins).skip(1).max().getAsInt();
            return max == Integer.MAX_VALUE ? -1 : max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}