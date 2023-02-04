package org.example.leetcode.problems._9_contest.history.week322;

import java.util.Arrays;
import java.util.HashMap;

// 6254. 划分技能点相等的团队
public class T2 {
  public static void main(String[] args) {
  
  }
  
  public long dividePlayers9(int[] skill) {
    Arrays.sort(skill);
    
    int n = skill.length;
    for (int l = 1, r = n - 2, sum = skill[0] + skill[n - 1]; l < r; l++, r--)
      if (skill[l] + skill[r] != sum)
        return -1;
    
    long sum = 0;
    for (int l = 0, r = n - 1; l < r; l++, r--)
      sum += (long) skill[l] * skill[r];
    
    return sum;
  }
  
  public long dividePlayers8(int[] skill) {
    Arrays.sort(skill);
    
    int n = skill.length;
    int sum = skill[0] + skill[n - 1];
    long ans = (long) skill[0] * skill[n - 1];
    for (int l = 1, r = n - 2; l < r; l++, r--) {
      if (skill[l] + skill[r] != sum)
        return -1;
      ans += (long) skill[l] * skill[r];
    }
    
    return ans;
  }
  
  public long dividePlayers7(int[] skill) {
    HashMap<Integer, Integer> val2cnt = new HashMap<>();
    
    int sum = Arrays.stream(skill).sum();
    int n = skill.length;
    // 团队技能点之和应为整数
    if (sum % (n / 2) != 0)
      return -1;
    
    // 技能点计数
    for (int s : skill)
      val2cnt.merge(s, 1, Integer::sum);
    
    int target = sum / (n / 2);
    long ans = 0;
    for (int s : skill) {
      // 已经被一个团队选中
      if (val2cnt.get(s) <= 0)
        continue;
      
      Integer t = val2cnt.get(target - s);
      // 无法找到匹配的玩家
      if (t == null || t <= 0)
        return -1;
      
      // 更新技能点计数
      val2cnt.merge(s, -1, Integer::sum);
      val2cnt.merge(target - s, -1, Integer::sum);
      
      // 求取 化学反应 之和
      ans += (long) s * (target - s);
    }
    
    return ans;
  }
  
  public long dividePlayers(int[] skill) {
    int[] val2cnt = new int[1001];
    
    int sum = Arrays.stream(skill).sum();
    int n = skill.length;
    // 团队技能点之和应为整数
    if (sum % (n / 2) != 0)
      return -1;
    
    // 技能点计数
    for (int s : skill)
      val2cnt[s]++;
    
    int target = sum / (n / 2);
    long ans = 0;
    for (int s : skill) {
      // 已经被一个团队选中
      if (val2cnt[s] <= 0)
        continue;
      
      // 无法找到匹配的玩家
      if (val2cnt[target - s] <= 0)
        return -1;
      
      // 更新技能点计数
      val2cnt[s]--;
      val2cnt[target - s]--;
      
      // 求取 化学反应 之和
      ans += (long) s * (target - s);
    }
    
    return ans;
  }
}
