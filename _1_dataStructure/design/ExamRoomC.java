//<p>在考场里，一排有&nbsp;<code>N</code>&nbsp;个座位，分别编号为&nbsp;<code>0, 1, 2, ..., N-1</code>&nbsp;。</p>
//
//<p>当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)</p>
//
//<p>返回&nbsp;<code>ExamRoom(int N)</code>&nbsp;类，它有两个公开的函数：其中，函数&nbsp;<code>ExamRoom.seat()</code>&nbsp;会返回一个&nbsp;<code>int</code>&nbsp;（整型数据），代表学生坐的位置；函数&nbsp;<code>ExamRoom.leave(int p)</code>&nbsp;代表坐在座位 <code>p</code> 上的学生现在离开了考场。每次调用&nbsp;<code>ExamRoom.leave(p)</code>&nbsp;时都保证有学生坐在座位&nbsp;<code>p</code>&nbsp;上。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入：</strong>["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
//<strong>输出：</strong>[null,0,9,4,2,null,5]
//<strong>解释：</strong>
// ExamRoom(10) -&gt; null
// seat() -&gt; 0，没有人在考场里，那么学生坐在 0 号座位上。
// seat() -&gt; 9，学生最后坐在 9 号座位上。
// seat() -&gt; 4，学生最后坐在 4 号座位上。
// seat() -&gt; 2，学生最后坐在 2 号座位上。
// leave(4) -&gt; null
// seat() -&gt; 5，学生最后坐在 5 号座位上。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ol> 
// <li><code>1 &lt;= N &lt;= 10^9</code></li> 
// <li>在所有的测试样例中&nbsp;<code>ExamRoom.seat()</code>&nbsp;和&nbsp;<code>ExamRoom.leave()</code>&nbsp;最多被调用&nbsp;<code>10^4</code>&nbsp;次。</li> 
// <li>保证在调用&nbsp;<code>ExamRoom.leave(p)</code>&nbsp;时有学生正坐在座位 <code>p</code> 上。</li> 
//</ol>
//
//<div><li>👍 176</li><li>👎 0</li></div>
package _1_dataStructure.design;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeSet;

// 855.考场就座
// 开题时间：2022-12-30 13:07:01
public class ExamRoomC {
  public static void main(String[] args) {
    ExamRoom e = new ExamRoom(10);
    //        System.out.println(e.seat());
    //        System.out.println(e.seat());
    //        System.out.println(e.seat());
    //        System.out.println(e.seat());
    //        e.leave(4);
    //        System.out.println(e.seat());
    
    System.out.println(e.seat());
    System.out.println(e.seat());
    System.out.println(e.seat());
    e.leave(0);
    e.leave(4);
    System.out.println(e.seat());
    System.out.println(e.seat());
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class ExamRoomX {
    PriorityQueue<int[]> pq;
    int cnt = 0;
    int n;
    
    public ExamRoomX(int n) {
      pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[0] - arr[1]).thenComparingInt(arr -> arr[0]));
      this.n = n;
    }
    
    public int seat() {
      cnt++;
      if (cnt == 1)
        return 0;
      else if (cnt == 2) {
        pq.offer(new int[]{0, n - 1});
        return n - 1;
      }
      
      int[] poll = pq.poll();
      
      int mid = ((poll[1] - poll[0]) >> 1) + poll[0];
      pq.offer(new int[]{poll[0], mid});
      pq.offer(new int[]{mid, poll[1]});
      
      return mid;
    }
    
    public void leave(int p) {
      pq.removeIf(arr -> arr[0] == p || arr[1] == p);
      int start = 0, end = 0;
      Iterator<int[]> it = pq.iterator();
      while (it.hasNext()) {
        int[] next = it.next();
        if (next[0] == p) {
          end = next[1];
          it.remove();
        } else if (next[1] == p) {
          start = next[0];
          it.remove();
        }
      }
      pq.offer(new int[]{start, end});
    }
  }
  
  class ExamRoomX2 {
    boolean[] seated;
    int cnt = 0;
    int n;
    
    public ExamRoomX2(int n) {
      seated = new boolean[n];
      this.n = n;
    }
    
    public int seat() {
      int max = 0;
      int ans = 0;
      if (cnt++ > 0) {
        int pre = 0;
        for (int i = 0; i < n; i++) {
          if (seated[i]) {
            if (pre == 0 && !seated[pre]) {
              max = i;
            } else if (max < (i - pre) / 2) {
              max = (i - pre) / 2;
              ans = (pre + i) / 2;
            }
            pre = i;
          }
        }
        if (!seated[n - 1] && max < n - 1 - pre)
          ans = n - 1;
      }
      seated[ans] = true;
      return ans;
    }
    
    public void leave(int p) {
      cnt--;
      seated[p] = false;
    }
  }
  
  /*
   * 有序集合
   * 时间复杂度：
   *      seat() : O(n)
   *      leave(): O(logn)
   * 空间复杂度：O(n)
   */
  class ExamRoom9 {
    TreeSet<Integer> set = new TreeSet<>();
    int n;
    
    public ExamRoom9(int n) {
      this.n = n;
    }
    
    public int seat() {
      int ans = 0;
      if (set.size() > 0) {
        int pre = set.first();
        int max = pre;
        for (int i : set) {
          if (max < (i - pre) / 2) {
            max = (i - pre) / 2;
            ans = (pre + i) / 2;
          }
          pre = i;
        }
        if (max < n - 1 - set.last())
          ans = n - 1;
      }
      set.add(ans);
      return ans;
    }
    
    public void leave(int p) {
      set.remove(p);
    }
  }
  
  
  /*
   * ☆☆☆☆☆ 有序集合 + 优先队列 + 延迟删除
   * 时间复杂度：
   *      seat() : O(logn)
   *      leave(): O(logn)
   * 空间复杂度：O(n)
   */
  static class ExamRoom {
    TreeSet<Integer> set = new TreeSet<>();
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> (arr[0] - arr[1]) / 2).thenComparingInt(arr -> arr[0]));
    int n;
    
    public ExamRoom(int n) {
      this.n = n;
    }
    
    public int seat() {
      int size = set.size();
      // 考场无人
      if (size == 0) {
        set.add(0);
        return 0;
      }
      int first = set.first();
      int last = n - 1 - set.last();
      // 考场 2 人及以上，学生可以坐在0号、n-1号、或者两个学生之间
      while (size >= 2) {
        int[] poll = pq.poll();
        // 判断区间是否已删除
        if (!set.contains(poll[0]) || !set.contains(poll[1]) || set.higher(poll[0]) != poll[1]) continue;
        int dist = (poll[1] - poll[0]) / 2;
        // 学生坐在0号、n-1号时更优
        if (dist <= first || dist < last) {
          pq.offer(poll);
          break;
        }
        int seat = (poll[1] + poll[0]) / 2;
        set.add(seat);
        pq.offer(new int[]{poll[0], seat});
        pq.offer(new int[]{seat, poll[1]});
        return seat;
      }
      int l = 0, r = first, seat = l;
      if (first < last) {
        l = set.last();
        r = n - 1;
        seat = r;
      }
      set.add(seat);
      pq.offer(new int[]{l, r});
      return seat;
    }
    
    public void leave(int p) {
      // 延迟删除
      if (p != set.first() && p != set.last())
        pq.add(new int[]{set.lower(p), set.higher(p)});
      set.remove(p);
    }
  }
  /**
   * Your ExamRoom object will be instantiated and called as such:
   * ExamRoom obj = new ExamRoom(n);
   * int param_1 = obj.seat();
   * obj.leave(p);
   */
  // leetcode submit region end(Prohibit modification and deletion)
}