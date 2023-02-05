package org.example.leetcode.problems._9_contest.week331;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

// 6345. Rearranging Fruits
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution.minCost9(new int[]{
            3350, 1104, 2004, 1577, 1365, 2088, 2249, 1948, 2621, 750, 31, 2004, 1749, 3365, 3350, 3843, 3365, 1656, 3168, 3106, 2820, 3557, 1095, 2446, 573, 2464, 2172, 1326, 2712, 467, 1104, 1446, 1577, 53, 2492, 2638, 1200, 2997, 3454, 2492, 1926, 1452, 2712, 446, 2997, 2820, 750, 2529, 3847, 656, 272, 3873, 530, 1749, 1743, 251, 3847, 31, 251, 515, 2858, 126, 2491}
        , new int[]{
            530, 1920, 2529, 2317, 1969, 2317, 1095, 2249, 2858, 2636, 3772, 53, 3106, 2638, 1267, 1926, 2882, 515, 3772, 1969, 3454, 2446, 656, 2621, 1365, 1743, 3557, 1656, 3447, 446, 1098, 1446, 467, 2636, 1088, 1098, 2882, 1088, 1326, 644, 3873, 3843, 3926, 1920, 2464, 2088, 205, 1200, 1267, 272, 925, 925, 2172, 2491, 3168, 644, 1452, 573, 1948, 3926, 205, 126, 3447}
    ));
    // System.out.println(solution.minCost(new int[]{
    //     84, 80, 43, 8, 80, 88, 43, 14, 100, 88}, new int[]{
    //     32, 32, 42, 68, 68, 100, 42, 84, 14, 8
    // }));
    System.out.println((837 - 31) / 106);
  }
  
  class Solution {
    // 屎山代码
    public long minCost9(int[] basket1, int[] basket2) {
      HashMap<Integer, Integer> map1 = new HashMap<>();
      HashMap<Integer, Integer> map2 = new HashMap<>();
      HashSet<Integer> set = new HashSet<>();
      for (int x : basket1) {
        map1.merge(x, 1, Integer::sum);
        set.add(x);
      }
      for (int x : basket2) {
        map2.merge(x, 1, Integer::sum);
        set.add(x);
      }
      
      long minVal = Integer.MAX_VALUE;
      for (Integer x : set) {
        // 新增
        minVal = Math.min(minVal, x);
        if (map1.containsKey(x) && map2.containsKey(x)) {
          // 删除：此处有误，工具人不限于两数组均有的元素，所有元素都可以
          // minVal = Math.min(minVal, x);
          int min = Math.min(map1.get(x), map2.get(x));
          
          map1.merge(x, -min, Integer::sum);
          map2.merge(x, -min, Integer::sum);
        }
      }
      
      LinkedList<Integer> dq1 = new LinkedList<>();
      LinkedList<Integer> dq2 = new LinkedList<>();
      for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
        Integer cnt = entry.getValue();
        if (cnt % 2 != 0) {
          return -1;
        }
        for (int i = 0; i < cnt / 2; i++) {
          dq1.offer(entry.getKey());
        }
      }
      for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
        Integer cnt = entry.getValue();
        if (cnt % 2 != 0) {
          return -1;
        }
        for (int i = 0; i < cnt / 2; i++) {
          dq2.offer(entry.getKey());
        }
      }
      
      dq1.sort(Comparator.naturalOrder());
      dq2.sort(Comparator.naturalOrder());
      
      long ans = 0;
      minVal = 2 * minVal;
      while (!dq1.isEmpty()) {
        int max = dq1.peekFirst();
        int min = dq2.peekFirst();
        int cost = Math.min(max, min);
        if (cost < minVal) {
          ans += cost;
          if (cost == max) {
            dq1.pollFirst();
            dq2.pollLast();
          } else {
            dq2.pollFirst();
            dq1.pollLast();
          }
        } else {
          break;
        }
      }
      return ans + dq1.size() * minVal;
    }
    
    /*
     * ☆☆☆☆☆ 哈希表 + 优先队列 + 贪心
     * 将两数组无法匹配的元素及其频率储存在一个哈希表中，相当精妙：
     *  - 频率为正值，表示数组1的元素无法在数组2中找到匹配
     *  - 频率为 0 值，表示此元素已匹配完毕
     *  - 频率为负值，类似正值
     * 后续将哈希表中元素转存（相同元素多次存储）时，取「频率的绝对值 / 2
     */
    public long minCost(int[] basket1, int[] basket2) {
      HashMap<Integer, Integer> val2cnt = new HashMap<>();
      for (int i = 0; i < basket1.length; i++) {
        val2cnt.merge(basket1[i], 1, Integer::sum);
        val2cnt.merge(basket2[i], -1, Integer::sum);
      }
      
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      int min = Integer.MAX_VALUE;
      for (Map.Entry<Integer, Integer> entry : val2cnt.entrySet()) {
        Integer cnt = entry.getValue();
        if (cnt % 2 != 0) {
          return -1;
        }
        
        Integer val = entry.getKey();
        min = Math.min(min, val);
        for (int i = Math.abs(cnt / 2); i > 0; i--) {
          pq.offer(val);
        }
      }
      
      long ans = 0;
      min *= 2;
      for (int i = pq.size() / 2; i > 0; i--) {
        ans += Math.min(pq.poll(), min);
      }
      return ans;
    }
  }
}
