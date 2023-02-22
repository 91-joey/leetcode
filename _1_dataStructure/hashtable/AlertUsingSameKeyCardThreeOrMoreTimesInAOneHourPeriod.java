package _1_dataStructure.hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * 1604.警告一小时内使用相同员工卡大于等于三次的人 <br>
 * 开题时间：2023-02-07 08:33:07
 */
public class AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {
  public static void main(String[] args) {
    Solution solution = new AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 哈希表 + 有序集合 + 排序
    public List<String> alertNames9(String[] keyName, String[] keyTime) {
      ArrayList<String> ans = new ArrayList<>();
      
      Map<String, TreeSet<Integer>> map = new HashMap<>();
      for (int i = 0; i < keyName.length; i++) {
        if (map.containsKey(keyName[i])) {
          map.get(keyName[i]).add(getMinutes(keyTime[i]));
        } else {
          map.put(keyName[i], new TreeSet<>());
          map.get(keyName[i]).add(getMinutes(keyTime[i]));
        }
      }
      
      for (Map.Entry<String, TreeSet<Integer>> entry : map.entrySet()) {
        TreeSet<Integer> timeSet = entry.getValue();
        for (Integer time : timeSet) {
          if (timeSet.subSet(time, time + 61).size() >= 3) {
            ans.add(entry.getKey());
            break;
          }
        }
      }
      
      ans.sort(String::compareTo);
      return ans;
    }
  
    // 哈希表 + 排序 + 滑窗
    public List<String> alertNames8(String[] keyName, String[] keyTime) {
      Map<String, List<Integer>> map = new HashMap<>();
      for (int i = 0; i < keyName.length; i++) {
        if (!map.containsKey(keyName[i])) {
          map.put(keyName[i], new ArrayList<>());
        }
        map.get(keyName[i]).add(getMinutes(keyTime[i]));
      }
      
      ArrayList<String> ans = new ArrayList<>();
      for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
        List<Integer> list = entry.getValue();
        list.sort(Integer::compareTo);
        for (int l = 0, r = 0; r <= list.size(); ) {
          if (r - l >= 3) {
            ans.add(entry.getKey());
            break;
          }
          if (r >= list.size()) {
            break;
          }
          if (list.get(r) - list.get(l) <= 60) {
            r++;
          } else {
            l++;
          }
        }
      }
      
      ans.sort(String::compareTo);
      return ans;
    }
    
    // ☆☆☆☆☆ 哈希表 + 排序 + 遍历
    public List<String> alertNames(String[] keyName, String[] keyTime) {
      Map<String, List<Integer>> map = new HashMap<>();
      for (int i = 0; i < keyName.length; i++) {
        map.putIfAbsent(keyName[i], new ArrayList<>());
        map.get(keyName[i]).add(getMinutes(keyTime[i]));
      }
      
      ArrayList<String> ans = new ArrayList<>();
      for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
        List<Integer> list = entry.getValue();
        Collections.sort(list);
        for (int i = 2; i < list.size(); i++) {
          if (list.get(i) - list.get(i - 2) <= 60) {
            ans.add(entry.getKey());
            break;
          }
        }
      }
      
      Collections.sort(ans);
      return ans;
    }
    
    private Integer getMinutes(String s) {
      /*
      String[] split = s.split(":");
      int hour = Integer.parseInt(split[0]);
      int minute = Integer.parseInt(split[1]);
      return hour * 60 + minute;
      */
      
      // return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
      
      return 60 * (10 * (s.charAt(0) - '0') + s.charAt(1) - '0') + 10 * (s.charAt(3) - '0') + s.charAt(4) - '0';
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}