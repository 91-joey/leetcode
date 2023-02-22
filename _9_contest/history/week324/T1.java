package _9_contest.history.week324;

import java.util.HashMap;

// 6265. Count Pairs Of Similar Strings
public class T1 {
  public static void main(String[] args) {
    System.out.println(similarPairs(new String[]{"aba", "aabb", "abcd", "bac", "aabc"}));
  }
  
  // 哈希计数 + 状态压缩（位运算）+ 依次统计：
  public static int similarPairs9(String[] words) {
    int ans = 0;
    HashMap<Integer, Integer> state2cnt = new HashMap<>();
    for (String word : words) {
      int state = 0;
      for (int i = 0; i < word.length(); i++)
        state |= (1 << (word.charAt(i) - 'a'));
      ans += state2cnt.getOrDefault(state, 0);
      state2cnt.merge(state, 1, Integer::sum);
    }
    return ans;
  }
  
  // 哈希计数 + 状态压缩（位运算）+ 最后统计：
  public static int similarPairs(String[] words) {
    HashMap<Integer, Integer> state2cnt = new HashMap<>();
    for (String word : words) {
      int state = 0;
      for (int i = 0; i < word.length(); i++)
        state |= (1 << (word.charAt(i) - 'a'));
      state2cnt.merge(state, 1, Integer::sum);
    }
    
    int ans = 0;
    for (int cnt : state2cnt.values())
      ans += cnt * (cnt - 1) / 2;
    
    return ans;
  }
}
