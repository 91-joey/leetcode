package _2_algorithm.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 269.火星词典 <br>
 * 开题时间：2023-02-24 17:05:27
 */
public class AlienDictionary {
  public static void main(String[] args) {
    Solution solution = new AlienDictionary().new Solution();
    System.out.println(solution.alienOrder(new String[]{"ac", "ab", "zc", "zb"}));
    // System.out.println(solution.alienOrder(new String[]{"ab","adc"}));
    // System.out.println(solution.alienOrder(new String[]{"wnlb"}));
    // System.out.println(solution.alienOrder(new String[]{"abc","ab"}));
    // System.out.println(solution.alienOrder(new String[]{"z", "z"}));
    // System.out.println(solution.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // import java.util.Collection;
  class Solution {
    // 拓扑排序
    public String alienOrder9(String[] words) {
      ArrayList<Integer>[] g = new ArrayList[26];
      Arrays.setAll(g, i -> new ArrayList<>());
      HashSet<Character> set = new HashSet<>();
      words[0].chars().forEach(c -> set.add((char) c));
      
      for (int i = 1; i < words.length; i++) {
        boolean found = false;
        for (int l = 0, r = 0; l < words[i - 1].length() || r < words[i].length(); l++, r++) {
          if (r >= words[i].length()) {
            if (!found) {
              return "";
            }
          } else if (l >= words[i - 1].length()) {
            set.add(words[i].charAt(r));
          } else {
            char a = words[i - 1].charAt(l);
            char b = words[i].charAt(r);
            set.add(b);
            if (!found && a != b) {
              g[a - 'a'].add(b - 'a');
              found = true;
            }
          }
        }
      }
      
      int[] deg = new int[26];
      boolean[] valid = new boolean[26];
      for (int u = 0; u < g.length; u++) {
        if (!g[u].isEmpty()) {
          valid[u] = true;
        }
        for (Integer v : g[u]) {
          deg[v]++;
        }
      }
      
      LinkedList<Integer> q = new LinkedList<>();
      for (int i = 0; i < 26; i++) {
        if (valid[i] && deg[i] == 0) {
          q.add(i);
        }
      }
      
      StringBuilder ans = new StringBuilder();
      while (!q.isEmpty()) {
        int u = q.removeFirst();
        ans.append((char) (u + 'a'));
        for (int v : g[u]) {
          deg[v]--;
          if (deg[v] == 0) {
            q.add(v);
          }
        }
      }
      
      for (Character c : set) {
        if (ans.indexOf(String.valueOf(c)) == -1) {
          ans.append(c);
        }
      }
      
      return Arrays.stream(deg).allMatch(d -> d == 0) ? ans.toString() : "";
    }
    
    
    /*
     * 拓扑排序（优化）
     * 字母看成是有向图的顶点，设前一个字符串与后一个字符串从左到右第一个不同的字母分别为 a、b，则有 a -> b 的一条边，求此有向图的拓扑序（有环时（即字符串列表排序错误），返回空串）
     * 注意：
     *  - 像 {"abc","ab"} 这种后一个字符串为前一个字符串的前缀、而前一个字符串的长度大于后一个字符串，同样是字符串列表排序错误的情况，返回空串
     *  - 答案需包含所有字符串中出现的字母，但不必包含所有 26 个小写英文字母
     */
    public String alienOrder8(String[] words) {
      HashMap<Character, Set<Character>> g = new HashMap<>();
      HashMap<Character, Integer> deg = new HashMap<>();
      
      // 建图（邻接表）、更新入度表
      words[0].chars().forEach(c -> g.putIfAbsent((char) c, new HashSet<>()));
      for (int i = 1; i < words.length; i++) {
        boolean found = false;
        int n = words[i - 1].length();
        int m = words[i].length();
        for (int l = 0, r = 0; l < n || r < m; l++, r++) {
          if (r >= m) {
            if (!found) {
              return "";
            }
            g.putIfAbsent(words[i - 1].charAt(l), new HashSet<>());
          } else if (l >= n) {
            g.putIfAbsent(words[i].charAt(r), new HashSet<>());
          } else {
            char a = words[i - 1].charAt(l);
            char b = words[i].charAt(r);
            g.putIfAbsent(a, new HashSet<>());
            g.putIfAbsent(b, new HashSet<>());
            if (!found && a != b) {
              if (g.get(a).add(b)) {
                deg.merge(b, 1, Integer::sum);
              }
              found = true;
            }
          }
        }
      }
      
      // 初始化队列（加入入度为 0 的字符）
      LinkedList<Character> q = new LinkedList<>();
      Set<Character> letterSet = g.keySet();
      for (Character c : letterSet) {
        if (!deg.containsKey(c)) {
          q.offer(c);
        }
      }
      
      // bfs 拓扑排序
      StringBuilder ans = new StringBuilder();
      while (!q.isEmpty()) {
        char u = q.poll();
        ans.append(u);
        for (char v : g.get(u)) {
          if (deg.merge(v, -1, Integer::sum) == 0) {
            q.add(v);
          }
        }
      }
      
      return ans.length() == letterSet.size() ? ans.toString() : "";
    }
    
    public String alienOrder(String[] words) {
      /*
       * 遍历字符串列表
       *  - 两两比较，设前者为 a，后者为 b：
       *    - 若 b 是 a 的前缀、而 a 长度大于 b ，则字母顺序不合法，输出空串
       *    - 以两者第一处不同的字母
       *      - 建立有向边关系：a_i -> b_i
       *      - 维护入度数组：deg[b_i]++
       *  - 维护字母集（并不是所有 26 个小写英文字母都需要输出）
       *
       * 遍历字母集，初始化队列，建立拓扑序
       * 若无环（即入度数组元素值均为 0），则输出拓扑序，否则输出空串
       */
    
      HashSet<Character> set = new HashSet<>();
      int n = 123;
      ArrayList<Integer>[] g = new ArrayList[n];
      Arrays.setAll(g, i -> new ArrayList<>());
      int[] deg = new int[n];
      words[0].chars().forEach(c -> set.add((char) c));
      for (int i = 1; i < words.length; i++) {
        char[] cs1 = words[i - 1].toCharArray();
        char[] cs2 = words[i].toCharArray();
        int p = 0;
        int q = 0;
        while (p < cs1.length && q < cs2.length) {
          if (cs1[p] != cs2[q]) {
            g[cs1[p]].add((int) cs2[q]);
            deg[cs2[q]]++;
            break;
          }
          p++;
          q++;
        }
        if (cs1.length > cs2.length && q >= cs2.length) {
          return "";
        }
      
        for (char c : cs2) {
          set.add(c);
        }
      }
    
      LinkedList<Integer> q = new LinkedList<>();
      for (Character c : set) {
        if (deg[c] == 0) {
          q.add(Integer.valueOf(c));
        }
      }
    
      List<Integer> list = getTopologicalSort(g, q, deg, set.size());
      return list == null ?
          "" :
          list.stream()
              .map(i -> String.valueOf((char) i.intValue()))
              .collect(Collectors.joining());
    }
    
    public List<Integer> getTopologicalSort(Collection<Integer>[] g, Queue<Integer> q, int[] deg, int n) {
      List<Integer> ans = new ArrayList<>();
      while (!q.isEmpty()) {
        int u = q.poll();
        ans.add(u);
        for (int v : g[u]) {
          if (--deg[v] == 0) {
            q.offer(v);
          }
        }
      }
    
      return ans.size() == n ? ans : null;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}