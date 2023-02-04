package org.example.leetcode.problems._9_contest.history.week318;

import java.util.Arrays;
import java.util.PriorityQueue;

// 6231. 雇佣 K 位工人的总代价
public class TotalCost {
  public long totalCost(int[] costs, int k, int candidates) {
    long ans = 0;
    int len = costs.length;
    if (candidates * 2 >= len) {
      Arrays.sort(costs);
      for (int i = 0; i < k; i++) {
        ans += costs[i];
      }
      return ans;
    }
    PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
      int diff = costs[o1] - costs[o2];
      return diff != 0 ? diff : o1 - o2;
    });
    for (int i = 0; i < candidates; i++) {
      q.offer(i);
      q.offer(len - 1 - i);
    }
    
    for (int l = candidates, r = len - 1 - candidates; k > 0 && l <= r; k--) {
      Integer poll = q.poll();
      ans += costs[poll];
      if (poll < l)
        q.offer(l++);
      else
        q.offer(r--);
    }
    
    for (; k > 0; k--) {
      Integer poll = q.poll();
      ans += costs[poll];
    }
    return ans;
  }
  
  //☆☆☆☆☆ 最小堆+对撞指针（优化后）
  public long totalCost2(int[] costs, int k, int candidates) {
    long ans = 0;
    int len = costs.length;
    if (candidates * 2 >= len) {
      Arrays.sort(costs);
      for (int i = 0; i < k; i++)
        ans += costs[i];
      return ans;
    }
    
    PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
      return costs[o1] != costs[o2] ?
          costs[o1] - costs[o2] :
          o1 - o2;
    });
    
    for (int i = 0; i < candidates; i++) {
      q.offer(i);
      q.offer(len - 1 - i);
    }
    
    for (int l = candidates, r = len - 1 - candidates; k > 0; k--) {
      Integer poll = q.poll();
      ans += costs[poll];
      if (l <= r)
        q.offer(poll < l ? l++ : r--);
    }
    
    return ans;
  }
  
  public long totalCost3(int[] costs, int k, int candidates) {
    long ans = 0;
    int len = costs.length;
    PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> {
      return costs[o1] != costs[o2] ?
          costs[o1] - costs[o2] :
          o1 - o2;
    });
    
    if (candidates * 2 >= len) {
      for (int i = 0; i < len; i++)
        q.offer(i);
    } else {
      for (int i = 0; i < candidates; i++) {
        q.offer(i);
        q.offer(len - 1 - i);
      }
    }
    
    for (int l = candidates, r = len - 1 - candidates; k > 0; k--) {
      Integer poll = q.poll();
      ans += costs[poll];
      if (l <= r)
        if (poll < l)
          q.offer(l++);
        else
          q.offer(r--);
    }
    
    return ans;
  }
}
