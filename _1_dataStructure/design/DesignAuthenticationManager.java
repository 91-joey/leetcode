package _1_dataStructure.design;

import java.util.HashMap;

/**
 * 1797.设计一个验证系统 <br>
 * 开题时间：2023-02-09 08:39:40
 */
public class DesignAuthenticationManager {
  public static void main(String[] args) {
    // AuthenticationManager solution = new DesignAuthenticationManager().new AuthenticationManager();
    // System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // 哈希表（值存储开始时间和过期时间）
  class AuthenticationManager9 {
    HashMap<String, int[]> map = new HashMap<>();
    int timeToLive;
    
    public AuthenticationManager9(int timeToLive) {
      this.timeToLive = timeToLive;
    }
    
    public void generate(String tokenId, int currentTime) {
      map.put(tokenId, new int[]{currentTime, currentTime + timeToLive});
    }
    
    public void renew(String tokenId, int currentTime) {
      if (map.containsKey(tokenId) && map.get(tokenId)[1] > currentTime) {
        map.put(tokenId, new int[]{map.get(tokenId)[0], currentTime + timeToLive});
      }
    }
    
    public int countUnexpiredTokens(int currentTime) {
      int count = 0;
      for (String key : map.keySet()) {
        int[] period = map.get(key);
        if (period[0] <= currentTime && currentTime < period[1]) {
          count++;
        }
      }
      return count;
    }
  }
  
  // ☆☆☆ 哈希表（值存储过期时间）
  class AuthenticationManager {
    HashMap<String, Integer> map = new HashMap<>();
    int timeToLive;
    
    public AuthenticationManager(int timeToLive) {
      this.timeToLive = timeToLive;
    }
    
    public void generate(String tokenId, int currentTime) {
      map.put(tokenId, currentTime + timeToLive);
    }
    
    public void renew(String tokenId, int currentTime) {
      if (map.getOrDefault(tokenId, 0) > currentTime) {
        map.put(tokenId, currentTime + timeToLive);
      }
    }
    
    public int countUnexpiredTokens(int currentTime) {
      // int count = 0;
      // for (Integer val : map.values()) {
      //   if (currentTime < val) {
      //     count++;
      //   }
      // }
      // return count;
      return (int) map.values().stream()
          .filter(val -> currentTime < val)
          .count();
    }
  }
  
  // todo 哈希表 + 双向链表
  // todo 哈希表 + 懒更新
  // leetcode submit region end(Prohibit modification and deletion)
}