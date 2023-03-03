package _1_dataStructure.hashtable;

import _3_common.entity.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 1487.保证文件名唯一 <br>
 * 开题时间：2023-03-03 08:15:10
 */
public class MakingFileNamesUnique {
  public static void main(String[] args) {
    Solution solution = new MakingFileNamesUnique().new Solution();
    System.out.println(Arrays.toString(solution.getFolderNames(new String[]{"wano", "wano", "wano", "wano"})));
    // System.out.println(Arrays.toString(solution.getFolderNames(new String[]{"gta", "gta(1)", "gta", "avalon"})));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 理解有误：输入为 "kaido(1)" ，输出为 "kaido(1)(1)" 也是可以的
    public String[] getFolderNamesX(String[] names) {
      int n = names.length;
      String[] ans = new String[n];
      HashMap<String, Pair<Integer, HashSet<Integer>>> map = new HashMap<String, Pair<Integer, HashSet<Integer>>>();
      
      for (int i = 0; i < n; i++) {
        String s = names[i];
        int idx = 1;
        if (s.charAt(s.length() - 1) == ')') {
          int j = s.lastIndexOf('(');
          if (j >= s.length() - 4 && j <= s.length() - 3 && j >= 0) {
            idx = Integer.parseInt(s.substring(j + 1, s.length() - 1));
            s = s.substring(0, j);
          }
        }
        if (map.containsKey(s)) {
          Pair<Integer, HashSet<Integer>> pair = map.get(s);
          HashSet<Integer> set = pair.getValue();
          if (!set.contains(idx)) {
            set.add(idx);
            ans[i] = s + "(" + idx + ")";
          } else {
            Integer start = pair.getKey();
            for (; start < 100; start++) {
              if (!set.contains(start)) {
                set.add(idx);
                ans[i] = s + "(" + start + ")";
                map.put(s, new Pair<Integer, HashSet<Integer>>(start + 1, set));
                break;
              }
            }
          }
        } else {
          ans[i] = s;
          map.put(s, new Pair<Integer, HashSet<Integer>>(idx, new HashSet<Integer>()));
        }
      }
      
      return ans;
    }
    
    // 哈希表
    public String[] getFolderNames(String[] names) {
      int n = names.length;
      String[] ans = new String[n];
      HashMap<String, Integer> map = new HashMap<>();
      for (int i = 0; i < n; i++) {
        if (map.containsKey(names[i])) {
          for (int idx = map.get(names[i]); ; idx++) {
            String s = names[i] + "(" + idx + ")";
            if (!map.containsKey(s)) {
              map.put(names[i], idx + 1); // 关键 1 ：存储最新的可用数字后缀，避免当输入数组元素相同时、内层的循环
              map.put(s, 1); // 关键 2 ：新字符串也要存储
              ans[i] = s;
              break;
            }
          }
        } else {
          map.put(names[i], 1);
          ans[i] = names[i];
        }
      }
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}