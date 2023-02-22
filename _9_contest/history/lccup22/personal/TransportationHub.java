package _9_contest.history.lccup22.personal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// LCP 62. 交通枢纽
public class TransportationHub {
  public static void main(String[] args) {
    //        System.out.println(transportationHub(new int[][]{{0,1},{0,3},{1,3},{2,0},{2,3}}));
    //        System.out.println(transportationHub(new int[][]{{0,3},{1,0},{1,3},{2,0},{3,0},{3,2}}));
    //        System.out.println(transportationHub(new int[][]{{2, 5}, {4, 3}, {2, 3}}));
    System.out.println(transportationHub(new int[][]{{0, 1}, {0, 3}, {1, 3}, {2, 0}, {2, 3}}));
  }
  
  public static int transportationHub(int[][] path) {
    // 全部出现过的数字
    Set<Integer> set = new HashSet<>();
    for (int[] ints : path) {
      for (int i : ints) {
        set.add(i);
      }
    }
    
    // 起点 到 终点集 的映射
    Map<Integer, Set<Integer>> start2end = new HashMap<>();
    for (int[] ints : path) {
      if (start2end.containsKey(ints[0])) {
        start2end.get(ints[0]).add(ints[1]);
      } else {
        Set<Integer> set2 = new HashSet<>();
        set2.add(ints[1]);
        start2end.put(ints[0], set2);
      }
    }
    
    outer:
    for (Integer hub : set) {
      if (!start2end.containsKey(hub)) {
        for (Integer i : set) {
          if (!i.equals(hub)) {
            Set<Integer> ends = start2end.get(i);
            if (ends == null || !ends.contains(hub))
              continue outer;
          }
        }
        return hub;
      }
    }
    
    return -1;
  }
  
  // 入出度（哈希存储）
  public static int transportationHub2(int[][] path) {
    // 所有地点
    Set<Integer> set = new HashSet<>();
    // 有出站的所有地点
    Set<Integer> outs = new HashSet<>();
    // 入度
    Map<Integer, Integer> val2ins = new HashMap<>();
    for (int[] ints : path) {
      outs.add(ints[0]);
      val2ins.merge(ints[1], 1, Integer::sum);
      set.add(ints[0]);
      set.add(ints[1]);
    }
    
    int cnt = set.size() - 1;
    for (Integer hub : set)
      // 出度 = 0 && 入度 = 地点数 - 1
      if (!outs.contains(hub) && val2ins.get(hub) == cnt)
        return hub;
    
    return -1;
  }
  
  final int n = 1001;
  // 有出站的所有地点
  boolean[] outs = new boolean[n];
  // 入度
  int[] val2ins = new int[n];
  
  // 入出度（数组存储）
  public int transportationHub3(int[][] path) {
    // 所有地点
    Set<Integer> set = new HashSet<>();
    for (int[] ints : path) {
      outs[ints[0]] = true;
      val2ins[ints[1]]++;
      set.add(ints[0]);
      set.add(ints[1]);
    }
    
    int cnt = set.size() - 1;
    //        for (Integer hub : set)
    //            //出度 = 0 && 入度 = 地点数 - 1
    //            if (!outs[hub] && val2ins[hub] == cnt)
    //                return hub;
    
    for (int i = 0; i < n; i++) {
      // 出度 = 0 && 入度 = 地点数 - 1
      if (!outs[i] && val2ins[i] == cnt)
        return i;
    }
    
    return -1;
  }
}
