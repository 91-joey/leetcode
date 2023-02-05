package org.example.leetcode.problems._9_contest.week331;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

//
public class T4 {
  public static void main(String[] args) {
    Solution solution = new T4().new Solution();
    System.out.println(solution.minCost(new int[]{
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
    public long minCost(int[] basket1, int[] basket2) {
      HashMap<Integer, Integer> map1 = new HashMap<>();
      HashMap<Integer, Integer> map2 = new HashMap<>();
      HashSet<Integer> set = new HashSet<>();
      for (int i : basket1) {
        map1.put(i, map1.getOrDefault(i, 0) + 1);
        set.add(i);
      }
      for (int i : basket2) {
        map2.put(i, map2.getOrDefault(i, 0) + 1);
        set.add(i);
      }
      
      long minVal = Integer.MAX_VALUE;
      for (Integer val : set) {
        if (map1.containsKey(val)) {
          if (map2.containsKey(val)) {
            minVal = Math.min(minVal, val);
            int a = map1.get(val);
            int b = map2.get(val);
            int min = Math.min(a, b);
            
            if (a == b) {
              map1.remove(val);
              map2.remove(val);
            } else if (a == min) {
              map1.remove(val);
              map2.put(val, b - min);
            } else {
              map2.remove(val);
              map1.put(val, a - min);
            }
          }
        }
      }
      
      LinkedList<Long> pq1 = new LinkedList<>();
      LinkedList<Long> pq2 = new LinkedList<>();
      for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
        Integer cnt = entry.getValue();
        if (cnt % 2 != 0) {
          return -1;
        }
        for (int i = 0; i < cnt / 2; i++) {
          pq1.offer(Long.valueOf(entry.getKey()));
        }
      }
      for (Map.Entry<Integer, Integer> entry : map2.entrySet()) {
        Integer cnt = entry.getValue();
        if (cnt % 2 != 0) {
          return -1;
        }
        for (int i = 0; i < cnt / 2; i++) {
          pq2.offer(Long.valueOf(entry.getKey()));
        }
      }
      
      pq1.sort(Comparator.naturalOrder());
      pq2.sort(Comparator.naturalOrder());
      
      long res = 0;
      minVal = 2 * minVal;
      while (!pq1.isEmpty()) {
        long max = pq1.peekFirst();
        long min = pq2.peekFirst();
        long cost = Math.min(max, min);
        if (cost < minVal) {
          res += cost;
          if (cost == max) {
            pq1.pollFirst();
            pq2.pollLast();
          } else {
            pq2.pollFirst();
            pq1.pollLast();
          }
        } else {
          break;
        }
      }
      return res + (pq1.isEmpty() ? 0 : pq1.size() * minVal);
    }
  }
}
