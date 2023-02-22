package _2_algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 1233.删除子文件夹 <br>
 * 开题时间：2023-02-08 09:05:18
 */
public class RemoveSubFoldersFromTheFilesystem {
  public static void main(String[] args) {
    Solution solution = new RemoveSubFoldersFromTheFilesystem().new Solution();
    System.out.println(solution);
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 排序 + 哈希表 + 字符串分割
    public List<String> removeSubfolders9(String[] folder) {
      Arrays.sort(folder);
      HashSet<String> set = new HashSet<>();
      for (String s : folder) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if (c == '/') {
            if (set.contains(sb.toString())) {
              break;
            }
          }
          sb.append(c);
        }
        set.add(sb.toString());
      }
      return new ArrayList<>(set);
    }
    
    public List<String> removeSubfolders8(String[] folder) {
      ArrayList<String> ans = new ArrayList<>();
      int n = folder.length;
      boolean[] isSubFolder = new boolean[n];
      Arrays.sort(folder);
      
      for (int i = 0; i < n; i++) {
        if (!isSubFolder[i]) {
          ans.add(folder[i]);
          for (int j = i + 1; j < n; j++) {
            if (!isSubFolder[j] && folder[j].startsWith(folder[i]) && folder[j].length() > folder[i].length() && folder[j].charAt(folder[i].length()) == '/') {
              isSubFolder[j] = true;
            }
          }
        }
      }
      
      return ans;
    }
    
    /*
     * ☆☆☆☆☆ 排序（利用 '/' 的字典序在字母之前的特性）
     *
     * 排序后数组以 (root{1}sub*)+ 的形式排列
     * 若分割符不是 '/'，字典序大于字母，则需要自定义排序
     */
    public List<String> removeSubfolders(String[] folder) {
      Arrays.sort(folder);
      ArrayList<String> ans = new ArrayList<>();
      ans.add(folder[0]);
      
      for (int i = 1; i < folder.length; i++) {
        String root = ans.get(ans.size() - 1);
        String cur = folder[i];
        if (!(root.length() < cur.length() && cur.startsWith(root) && cur.charAt(root.length()) == '/')) {
          ans.add(cur);
        }
      }
      
      return ans;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}