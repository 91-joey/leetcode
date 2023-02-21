package org.example.leetcode.problems._2_algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 39.组合总和 <br>
 * 开题时间：2023-02-10 12:05:16
 */
public class CombinationSum {
  public static void main(String[] args) {
    Solution solution = new CombinationSum().new Solution();
    System.out.println(solution);
    HashSet<int[]> set = new HashSet<>();
    set.add(new int[]{1, 2, 3});
    set.add(new int[]{1, 2, 3});
    set.forEach(System.out::println);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // import java.util.Collection;
  
  class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    Deque<Integer> comb = new LinkedList<>();
    
    // ☆☆☆☆☆ 排序 + 回溯 + 剪枝
    public List<List<Integer>> combinationSum9(int[] candidates, int target) {
      Arrays.sort(candidates);
      
      backtrack(candidates, target, 0);
      
      return ans;
    }
    
    private void backtrack(int[] candidates, int target, int begin) {
      if (target == 0) {
        ans.add(new ArrayList<>(comb));
        return;
      }
      for (int i = begin; i < candidates.length && candidates[i] <= target; i++) {
        comb.addLast(candidates[i]);
        backtrack(candidates, target - candidates[i], i);
        comb.removeLast();
      }
    }
    
    Set<List<Integer>> res = new HashSet<>();
    Deque<Integer> path = new MyLinkedList();
    
    // 回溯 + 哈希表 + 自定义集合类（重写 hashCode 和 equals 方法）
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      backtrack(candidates, target);
      
      return new ArrayList<>(res);
    }
    
    private void backtrack(int[] candidates, int target) {
      if (target < 0) {
        return;
      }
      if (target == 0) {
        res.add(new MyLinkedList(path));
        return;
      }
      for (int candidate : candidates) {
        path.addLast(candidate);
        backtrack(candidates, target - candidate);
        path.removeLast();
      }
    }
  }
  
  class MyLinkedList extends LinkedList<Integer> {
    public MyLinkedList() {
    }
    
    public MyLinkedList(Collection<? extends Integer> c) {
      super(c);
    }
    
    @Override
    public boolean equals(Object o) {
      // 法一：排序再比较
      ArrayList<Integer> list1 = new ArrayList<>(this);
      ArrayList<Integer> list2 = new ArrayList<>((List<Integer>) o);
      Collections.sort(list1);
      Collections.sort(list2);
      return list1.equals(list2);
  
      // 法二：哈希计数再比较
      // Map<Integer, Long> map1 = this.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      // Map<Integer, Long> map2 = ((List<Integer>) o).stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      // return map1.equals(map2);
  
      // 法三：数组计数再比较
      // int[] cnt = new int[41];
      // this.forEach(i -> cnt[i]++);
      // ((Iterable<Integer>) o).forEach(i -> cnt[i]--);
      // return Arrays.stream(cnt).allMatch(i -> i == 0);
    }
    
    @Override
    public int hashCode() {
      long hashCode = 1;
      for (int e : this)
        hashCode = (e * hashCode) % 1000000007;
      return (int) hashCode;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}