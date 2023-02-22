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
package _1_dataStructure.graph;

import _3_common.tool.Tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

// 743.网络延迟时间
// 开题时间：2022-12-27 12:15:37
public class NetworkDelayTime {
  public static void main(String[] args) {
    Solution solution = new NetworkDelayTime().new Solution();
    System.out.println(solution.networkDelayTime(Tools.to2DIntArray("[[2,1,1],[2,3,1],[3,4,1]]"), 4, 2));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int networkDelayTimeX(int[][] times, int n, int k) {
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
    
    public static final int INF = 0x3f3f3f3f;
    
    //「多源汇最短路」算法（Floyd算法）   n^3
    public int networkDelayTime9(int[][] times, int n, int k) {
      int[][] graph = new int[n + 1][n + 1];
      for (int[] ints : graph)
        Arrays.fill(ints, INF);
      for (int i = 1; i <= n; i++)
        graph[i][i] = 0;
      
      for (int[] time : times)
        graph[time[0]][time[1]] = time[2];
      
      floyd(graph, n);
      
      int max = Arrays.stream(graph[k]).skip(1).max().getAsInt();
      return max == INF ? -1 : max;
    }
    
    //「单源最短路」算法（朴素dijkstra算法）   n^2
    public int networkDelayTime8(int[][] times, int n, int k) {
      int[][] graph = new int[n + 1][n + 1];
      for (int[] ints : graph)
        Arrays.fill(ints, INF);
      for (int i = 1; i <= n; i++)
        graph[i][i] = 0;
      
      for (int[] time : times)
        graph[time[0]][time[1]] = time[2];
      
      int[] dist = new int[n + 1];
      Arrays.fill(dist, INF);
      dist[k] = 0;
      dijkstra(graph, dist, n);
      
      int max = Arrays.stream(dist).skip(1).max().getAsInt();
      return max == INF ? -1 : max;
    }
    
    //「单源最短路」算法（优先队列 + Dijkstra + 链式前向星存图法）   mlogn+n
    public int networkDelayTime7(int[][] times, int n, int k) {
      ArrayLinkedListGraph graph = new ArrayLinkedListGraph(n + 1, Math.min(6010, (n + 1) * n));
      for (int[] time : times)
        graph.add(time[0], time[1], time[2]);
      
      int[] dist = new int[n + 1];
      Arrays.fill(dist, INF);
      dist[k] = 0;
      dijkstra2(graph, dist, n, k);
      
      int max = Arrays.stream(dist).skip(1).max().getAsInt();
      return max > INF / 2 ? -1 : max;
    }
    
    //「单源最短路」算法（Bellman Ford + 邻接矩阵存图法）   n*m
    public int networkDelayTime_bellman(int[][] times, int n, int k) {
      int[][] graph = new int[n + 1][n + 1];
      for (int[] ints : graph)
        Arrays.fill(ints, INF);
      for (int i = 1; i <= n; i++)
        graph[i][i] = 0;
      for (int[] time : times)
        graph[time[0]][time[1]] = time[2];

            /*
            int[][] f = new int[n][n + 1];
            for (int[] ints : f)
                Arrays.fill(ints, INF);
            f[0][k] = 0;

            for (int i = 1; i < n; i++)
                for (int j = 1; j < n + 1; j++)
                    for (int p = 1; p < n + 1; p++)
                        f[i][j] = Math.min(f[i][j], f[i - 1][p] + graph[p][j]);

            int max = Arrays.stream(f[n - 1]).skip(1).max().getAsInt();
            */
      
      // region 动态规划之滚动数组
      int[] f = new int[n + 1];
      Arrays.fill(f, INF);
      f[k] = 0;
      
      for (int i = 1; i < n; i++)
        for (int j = 1; j < n + 1; j++)
          for (int p = 1; p < n + 1; p++)
            f[j] = Math.min(f[j], f[p] + graph[p][j]);
      
      int max = Arrays.stream(f).skip(1).max().getAsInt();
      // endregion
      
      return max > INF / 2 ? -1 : max;
    }
    
    
    //「单源最短路」算法（Bellman Ford + 枚举边）   n*m
    public int networkDelayTime_bellman2(int[][] times, int n, int k) {
      int[] f = new int[n + 1];
      Arrays.fill(f, INF);
      f[k] = 0;
      
      // 迭代 n 次
      for (int i = 1; i < n; i++)
        // 枚举边
        for (int[] time : times) {
          int start = time[0], end = time[1], weight = time[2];
          f[end] = Math.min(f[end], f[start] + weight);
        }
      
      int max = Arrays.stream(f).skip(1).max().getAsInt();
      return max > INF / 2 ? -1 : max;
    }
    
    // SPFA(Shortest Path Faster Algorithm) 算法（基于「队列」优化的 Bellman-Ford 算法）    通常情况下复杂度为 O(k∗m)，k 一般为 4 到 5，最坏情况下仍为 O(n∗m)
    public int networkDelayTime(int[][] times, int n, int k) {
      int[][] graph = new int[n + 1][n + 1];
      for (int[] ints : graph)
        Arrays.fill(ints, INF);
      for (int i = 1; i <= n; i++)
        graph[i][i] = 0;
      for (int[] time : times)
        graph[time[0]][time[1]] = time[2];
      
      int[] f = new int[n + 1];
      Arrays.fill(f, INF);
      f[k] = 0;
      
      // 通过「队列」来维护我们接下来要遍历边的起点
      Queue<Integer> q = new LinkedList<>();
      q.offer(k);
      boolean[] isInQueue = new boolean[n + 1];
      isInQueue[k] = true;
      
      while (!q.isEmpty()) {
        int mid = q.poll();
        isInQueue[mid] = false;
        for (int i = 1; i < n + 1; i++) {
          if (f[mid] + graph[mid][i] < f[i]) {
            f[i] = f[mid] + graph[mid][i];
            // 当某个顶点的最短距离更新之后，并且该顶点不在「队列」中，我们就将该顶点加入到「队列」中
            if (!isInQueue[i]) {
              isInQueue[i] = true;
              q.offer(i);
            }
          }
        }
      }
      
      int max = Arrays.stream(f).skip(1).max().getAsInt();
      return max > INF / 2 ? -1 : max;
    }
    
    private void dijkstra2(ArrayLinkedListGraph graph, int[] dist, int n, int k) {
      boolean[] vis = new boolean[n + 1];
      PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
      pq.offer(new Edge(k, 0));
      while (!pq.isEmpty()) {
        // 每次取未访问且距离最短的终点作为中转点
        Edge poll = pq.poll();
        if (vis[poll.end])
          continue;
        vis[poll.end] = true;
        // 枚举出边
        for (Edge edge : graph.outEdges(poll.end)) {
          if (dist[poll.end] + edge.weight < dist[edge.end]) {
            // 更新最短距离
            dist[edge.end] = dist[poll.end] + edge.weight;
            if (!vis[edge.end]) {
              edge.weight = dist[edge.end];
              pq.offer(edge);
            }
          }
        }
      }
    }
    
    private void dijkstra(int[][] graph, int[] dist, int n) {
      boolean[] vis = new boolean[n + 1];
      // 循环 n 次
      for (int i = 1; i <= n; i++) {
        int t = -1;
        // 每次取未访问且距离最短的终点作为中转点
        for (int j = 1; j <= n; j++)
          if (!vis[j] && (t == -1 || dist[j] < dist[t])) t = j;
        vis[t] = true;
        
        // 更新最短距离
        for (int j = 1; j <= n; j++)
          dist[j] = Math.min(dist[j], dist[t] + graph[t][j]);
      }
    }
    
    private void floyd(int[][] graph, int n) {
      for (int p = 1; p <= n; p++)
        for (int i = 1; i <= n; i++)
          for (int j = 1; j <= n; j++)
            graph[i][j] = Math.min(graph[i][j], graph[i][p] + graph[p][j]);
    }
  }
  
  class Edge {
    int end;
    int weight;
    
    public Edge(int end, int weight) {
      this.end = end;
      this.weight = weight;
    }
    
    public int getWeight() {
      return weight;
    }
  }
  
  class ArrayLinkedListGraph {
    // O(m)，m为边数
    int[] head;// 顶点对应链表的头边
    int[] end;// 边的终点
    int[] weight;// 边的权值
    int[] next;// 边的下一个边
    int idx = 1;// 边的索引
    
    
    public ArrayLinkedListGraph(int size) {
      this(size, size * (size - 1));
    }
    
    public ArrayLinkedListGraph(int size, int edgeSize) {
      head = new int[size];
      end = new int[edgeSize];
      weight = new int[edgeSize];
      next = new int[edgeSize];
    }
    
    // O(1)，链表头插法
    public void add(int start, int end, int weight) {
      this.end[idx] = end;
      this.weight[idx] = weight;
      next[idx] = head[start];
      head[start] = idx++;
    }
    
    // O(出度)
    public List<Edge> outEdges(int start) {
      ArrayList<Edge> list = new ArrayList<>();
      for (int i = head[start]; i != 0; i = next[i])
        list.add(new Edge(end[i], weight[i]));
      return list;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}