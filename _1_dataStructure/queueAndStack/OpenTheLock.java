//<p>你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： <code>'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'</code> 。每个拨轮可以自由旋转：例如把 <code>'9'</code> 变为&nbsp;<code>'0'</code>，<code>'0'</code> 变为 <code>'9'</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>
//
//<p>锁的初始数字为 <code>'0000'</code> ，一个代表四个拨轮的数字的字符串。</p>
//
//<p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>
//
//<p>字符串 <code>target</code> 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 <code>-1</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1:</strong></p>
//
//<pre>
//<strong>输入：</strong>deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//<strong>输出：</strong>6
//<strong>解释：</strong>
// 可能的移动序列为 "0000" -&gt; "1000" -&gt; "1100" -&gt; "1200" -&gt; "1201" -&gt; "1202" -&gt; "0202"。
// 注意 "0000" -&gt; "0001" -&gt; "0002" -&gt; "0102" -&gt; "0202" 这样的序列是不能解锁的，
// 因为当拨动到 "0102" 时这个锁就会被锁定。
//</pre>
//
//<p><strong>示例 2:</strong></p>
//
//<pre>
//<strong>输入:</strong> deadends = ["8888"], target = "0009"
//<strong>输出：</strong>1
//<strong>解释：</strong>把最后一位反向旋转一次即可 "0000" -&gt; "0009"。
//</pre>
//
//<p><strong>示例 3:</strong></p>
//
//<pre>
//<strong>输入:</strong> deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
//<strong>输出：</strong>-1
//<strong>解释：</strong>无法旋转到目标数字且不被锁定。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li> 
// <li><code><font face="monospace">deadends[i].length == 4</font></code></li> 
// <li><code><font face="monospace">target.length == 4</font></code></li> 
// <li><code>target</code> <strong>不在</strong> <code>deadends</code> 之中</li> 
// <li><code>target</code> 和 <code>deadends[i]</code> 仅由若干位数字组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 526</li><li>👎 0</li></div>
package _1_dataStructure.queueAndStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 752.打开转盘锁
// 开题时间：2022-08-16 11:42:37
public class OpenTheLock {
  public static void main(String[] args) {
    Solution solution = new OpenTheLock().new Solution();
    solution.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // 1.自解BFS（①int[] -> String②int[][][][] steps用于记录已访问节点和计算步数） r^n*n^2+m*n r^n*n
  class Solution {
    private final int[][] ROTATIONS = {
        {1, 0, 0, 0},
        {-1, 0, 0, 0},
        {0, 1, 0, 0},
        {0, -1, 0, 0},
        {0, 0, 1, 0},
        {0, 0, -1, 0},
        {0, 0, 0, 1},
        {0, 0, 0, -1},
    };
    
    public int openLock9(String[] deadends, String target) {
      int[][][][] steps = new int[10][10][10][10];
      Queue<int[]> queue = new LinkedList<>();
      int[] start = {0, 0, 0, 0};
      if (isInDeadends(deadends, start)) {
        return -1;
      }
      queue.offer(start);
      while (!queue.isEmpty()) {
        int[] head = queue.poll();
        String headString = "";
        for (int digit : head) {
          headString += digit;
        }
        if (headString.equals(target)) {
          return steps[head[0]][head[1]][head[2]][head[3]];
        }
        
        for (int[] rotation : ROTATIONS) {
          //                    -1 -> 9
          //                    10 -> 0
          int a = getRotatedDigit(rotation, head, 0);
          int b = getRotatedDigit(rotation, head, 1);
          int c = getRotatedDigit(rotation, head, 2);
          int d = getRotatedDigit(rotation, head, 3);
          int[] child = {a, b, c, d};
          if (!(a == 0 && b == 0 && c == 0 && d == 0) && steps[a][b][c][d] == 0 && !isInDeadends(deadends, child)) {
            steps[a][b][c][d] = steps[head[0]][head[1]][head[2]][head[3]] + 1;
            queue.offer(child);
          }
        }
      }
      return -1;
    }
    
    private int getRotatedDigit(int[] rotation, int[] start, int index) {
      return start[index] + rotation[index] == -1 ? 9 : (start[index] + rotation[index]) % 10;
    }
    
    private boolean isInDeadends(String[] deadends, int[] digits) {
      String s = "";
      for (int digit : digits) {
        s += digit;
      }
      for (String deadend : deadends) {
        if (s.equals(deadend)) {
          return true;
        }
      }
      return false;
    }
    
    //    2.官解一（BFS）：①char直接运算 ②Set记录已访问节点、int变量计算步数    r^n*n^2+m*n r^n*n+m
    public static final int CANNOT_OPEN_LOCK = -1;
    
    public int openLock8(String[] deadends, String target) {
      int steps = 0;
      
      String start = "0000";
      if (start.equals(target)) {
        return steps;
      }
      if (isInDeadends(deadends, start)) {
        return CANNOT_OPEN_LOCK;
      }
      Queue<String> queue = new LinkedList<>();
      queue.offer(start);
      
      Set<String> visited = new HashSet<>();
      visited.add(start);
      
      while (!queue.isEmpty()) {
        steps++;
        int size = queue.size();
        for (int i = 0; i < size; i++) {
          String status = queue.poll();
          for (String nextStatus : getChildStatuses(status, 10, 4)) {
            if (!visited.contains(nextStatus) && !isInDeadends(deadends, nextStatus)) {
              if (nextStatus.equals(target)) {
                return steps;
              }
              queue.offer(nextStatus);
              visited.add(nextStatus);
            }
          }
        }
      }
      return CANNOT_OPEN_LOCK;
    }
    
    private String[] getChildStatuses(String status, int radix, int num) {
      String[] childStatuses = new String[num * 2];
      int idx = 0;
      for (int i = 0; i < num; i++) {
        childStatuses[idx++] = getChildStatus(status, radix, i, 1);
        childStatuses[idx++] = getChildStatus(status, radix, i, -1);
      }
      return childStatuses;
    }
    
    private String getChildStatus(String status, int radix, int i, int offset) {
      char[] chars = status.toCharArray();
      
      int offsetDigit = chars[i] + offset;
      if (offsetDigit < '0') {
        offsetDigit = '0' + (offsetDigit - '0') % radix + radix;
      } else if (offsetDigit >= '0' + radix) {
        offsetDigit = '0' + (offsetDigit - '0') % radix;
      }
      chars[i] = (char) offsetDigit;
      
      return new String(chars);
    }
    
    private boolean isInDeadends(String[] deadends, String status) {
      for (String deadend : deadends) {
        if (status.equals(deadend)) {
          return true;
        }
      }
      return false;
    }
    
    
    public static final int[] DIRS = {1, -1};
    
    // 朴素bfs（访问数组 + 队列存数字数组）
    public int openLock7(String[] deadends, String target) {
      boolean[] vis = new boolean[10000];
      for (String s : deadends)
        vis[Integer.parseInt(s)] = true;
      
      if (vis[0])
        return -1;
      
      Queue<int[]> q = new LinkedList<>();
      q.offer(new int[]{0, 0, 0, 0});
      vis[0] = true;
      
      int step = 0;
      int t = Integer.parseInt(target);
      while (!q.isEmpty()) {
        for (int i = q.size(); i > 0; i--) {
          int[] poll = q.poll();
          if (poll[0] * 1000 + poll[1] * 100 + poll[2] * 10 + poll[3] == t)
            return step;
          for (int j = 0; j < 4; j++) {
            for (int dir : DIRS) {
              int rotated = (poll[j] + dir + 10) % 10;
              int[] copy = Arrays.copyOf(poll, 4);
              copy[j] = rotated;
              int newNum = copy[0] * 1000 + copy[1] * 100 + copy[2] * 10 + copy[3];
              if (!vis[newNum]) {
                vis[newNum] = true;
                q.offer(copy);
              }
            }
          }
        }
        step++;
      }
      
      return -1;
    }
    
    //☆☆☆☆☆ 双向bfs（相较于朴素bfs更省空间）
    public int openLock6(String[] deadends, String target) {
      HashSet<String> bans = new HashSet<>(Arrays.asList(deadends));
      String source = "0000";
      if (bans.contains(source))
        return -1;
      if (target.equals(source))
        return 0;
      
      Queue<String> q1 = new LinkedList<>(), q2 = new LinkedList<>();
      Set<String> vis1 = new HashSet<>(), vis2 = new HashSet<>();
      q1.offer(source);
      vis1.add(source);
      q2.offer(target);
      vis2.add(target);
      int step1 = 0, step2 = 0;
      while (!q1.isEmpty() && !q2.isEmpty()) {
        boolean reachTarget;
        if (q1.size() <= q2.size()) {
          reachTarget = bfs(q1, bans, vis1, vis2);
          step1++;
        } else {
          reachTarget = bfs(q2, bans, vis2, vis1);
          step2++;
        }
        if (reachTarget)
          return step1 + step2;
      }
      
      return -1;
    }
    
    private boolean bfs(Queue<String> q, Set<String> bans, Set<String> cur, Set<String> other) {
      for (int i = q.size(); i > 0; i--) {
        String poll = q.poll();
        for (int j = 0; j < poll.length(); j++) {
          for (int k = -1; k <= 1; k++) {
            if (k == 0) continue;
            char[] cs = poll.toCharArray();
            cs[j] = (char) ('0' + (cs[j] - '0' + k + 10) % 10);
            String s = new String(cs);
            if (!bans.contains(s) && !cur.contains(s))
              if (other.contains(s))
                return true;
              else {
                q.offer(s);
                cur.add(s);
              }
          }
        }
      }
      return false;
    }
    
    //    3.官解二：启发式搜索
    // todo
    public int openLock(String[] deadends, String target) {
      return -1;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}