package _9_contest.history.week325;

// 6270. Take K of Each Character From Left and Right
public class T2 {
  public static void main(String[] args) {
    System.out.println(takeCharacters("aabaaaacaabc", 2));
    System.out.println(takeCharacters("a", 0));
  }
  
  // 滑窗
  public static int takeCharacters9(String s, int k) {
    char[] cs = s.toCharArray();
    int[] freq = new int[100];
    // 哈希计数
    for (char c : cs)
      freq[c]++;
    
    // 判断能否取到
    for (int i = 97; i < freq.length; i++)
      if (freq[i] < k)
        return -1;
    
    int max = 0;
    int n = cs.length;
    int l = 0, r = 0;
    // 滑窗
    while (r < n) {
      if (--freq[cs[r]] < k) {
        max = Math.max(max, r - l);
        while (freq[cs[r]] < k)
          ++freq[cs[l++]];
      }
      r++;
    }
    // 尾处理
    max = Math.max(max, r - l);
    
    return n - max;
  }
  
  // 按原意模拟，双指针
  public static int takeCharacters(String s, int k) {
    char[] cs = s.toCharArray();
    int[] freq = new int[100];
    int n = cs.length, j = n;
    
    while (freq[97] < k || freq[98] < k || freq[99] < k) {
      if (j <= 0) return -1;
      freq[cs[--j]]++;
    }
    
    int ans = n - j;
    for (int i = 0; i < n && j < n; i++) {
      freq[cs[i]]++;
      while (j < n && freq[cs[j]] > k)
        freq[cs[j++]]--;
      ans = Math.min(ans, i + 1 + n - j);
    }
    
    return ans;
  }
}
