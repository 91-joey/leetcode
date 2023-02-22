// 给你三个整数数组 <code>nums1</code>、<code>nums2</code> 和 <code>nums3</code> ，请你构造并返回一个 <strong>元素各不相同的</strong> 数组，且由 <strong>至少</strong> 在 <strong>两个</strong> 数组中出现的所有值组成<em>。</em>数组中的元素可以按 <strong>任意</strong> 顺序排列。
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
//<strong>输出：</strong>[3,2]
//<strong>解释：</strong>至少在两个数组中出现的所有值为：
//- 3 ，在全部三个数组中都出现过。
//- 2 ，在数组 nums1 和 nums2 中出现过。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
//<strong>输出：</strong>[2,3,1]
//<strong>解释：</strong>至少在两个数组中出现的所有值为：
//- 2 ，在数组 nums2 和 nums3 中出现过。
//- 3 ，在数组 nums1 和 nums2 中出现过。
//- 1 ，在数组 nums1 和 nums3 中出现过。
//</pre>
//
//<p><strong>示例 3：</strong></p>
//
//<pre>
//<strong>输入：</strong>nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
//<strong>输出：</strong>[]
//<strong>解释：</strong>不存在至少在两个数组中出现的值。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= nums1.length, nums2.length, nums3.length &lt;= 100</code></li> 
// <li><code>1 &lt;= nums1[i], nums2[j], nums3[k] &lt;= 100</code></li> 
//</ul>
//
//<div><li>👍 32</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 2032.至少在两个数组中出现的值
// 开题时间：2022-12-29 10:03:59
public class TwoOutOfThree {
  public static void main(String[] args) {
    Solution solution = new TwoOutOfThree().new Solution();
    System.out.println(solution.twoOutOfThree(
        new int[]{3, 1},
        new int[]{2, 3},
        new int[]{1, 2}
    ));
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // 双哈希表 + Stream过滤
    public List<Integer> twoOutOfThree9(int[] nums1, int[] nums2, int[] nums3) {
      Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
      Set<Integer> set3 = Arrays.stream(nums3).boxed().collect(Collectors.toSet());
      return Stream.concat(Arrays.stream(nums1).distinct().boxed().filter(num -> set2.contains(num) || set3.contains(num)),
              set2.stream().filter(set3::contains))
          .distinct()
          .toList();
    }
    
    // Stream分组计数
    public List<Integer> twoOutOfThree8(int[] nums1, int[] nums2, int[] nums3) {
      Map<Integer, Long> map = Stream.concat(Stream.concat(
                  Arrays.stream(nums1).distinct().boxed(),
                  Arrays.stream(nums2).distinct().boxed()),
              Arrays.stream(nums3).distinct().boxed())
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      ArrayList<Integer> ans = new ArrayList<>();
      map.forEach((k, v) -> {
        if (v >= 2)
          ans.add(k);
      });
      return ans;
    }
    
    //☆☆☆☆ Stream
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
      return Stream.of(nums1, nums2, nums3)
          .flatMap(arr -> Arrays.stream(arr).distinct().boxed())
          .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
          .entrySet().stream()
          .filter(e -> e.getValue() >= 2)
          .map(Map.Entry::getKey)
          .toList();
    }
    
    //☆☆☆☆☆ 哈希表位标记法
    public List<Integer> twoOutOfThree7(int[] nums1, int[] nums2, int[] nums3) {
      HashMap<Integer, Integer> map = new HashMap<>();
      for (int x : nums1) map.put(x, 1);
      for (int x : nums2) map.put(x, map.getOrDefault(x, 0) | 2);
      for (int x : nums3) map.put(x, map.getOrDefault(x, 0) | 4);
      
      return map.entrySet().stream()
          .filter(e -> (e.getValue() & (e.getValue() - 1)) != 0)
          .map(Map.Entry::getKey)
          .toList();
    }
    
    public List<Integer> twoOutOfThree6(int[] nums1, int[] nums2, int[] nums3) {
      HashSet<Integer> ans = new HashSet<>();
      HashSet<Integer> set = new HashSet<>();
      for (int x : nums1) set.add(x | 128);
      for (int x : nums2)
        if (set.contains(x | 128))
          ans.add(x);
        else
          set.add(x | 256);
      for (int x : nums3)
        if (set.contains(x | 128) || set.contains(x | 256))
          ans.add(x);
      
      return ans.stream().toList();
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}