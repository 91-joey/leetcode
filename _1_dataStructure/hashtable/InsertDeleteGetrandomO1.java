//<p>实现<code>RandomizedSet</code> 类：</p>
//
//<div class="original__bRMd"> 
// <div> 
//  <ul> 
//   <li><code>RandomizedSet()</code> 初始化 <code>RandomizedSet</code> 对象</li> 
//   <li><code>bool insert(int val)</code> 当元素 <code>val</code> 不存在时，向集合中插入该项，并返回 <code>true</code> ；否则，返回 <code>false</code> 。</li> 
//   <li><code>bool remove(int val)</code> 当元素 <code>val</code> 存在时，从集合中移除该项，并返回 <code>true</code> ；否则，返回 <code>false</code> 。</li> 
//   <li><code>int getRandom()</code> 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 <strong>相同的概率</strong> 被返回。</li> 
//  </ul> 
// </div>
//</div>
//
//<p>你必须实现类的所有函数，并满足每个函数的 <strong>平均</strong> 时间复杂度为 <code>O(1)</code> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre>
//<strong>输入</strong>
//["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
//[[], [1], [2], [2], [], [1], [2], []]
//<strong>输出</strong>
//[null, true, false, true, 2, true, false, 2]
//
//<strong>解释</strong>
// RandomizedSet randomizedSet = new RandomizedSet();
// randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
// randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
// randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
// randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
// randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
// randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
// randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li> 
// <li>最多调用 <code>insert</code>、<code>remove</code> 和 <code>getRandom</code> 函数 <code>2 *&nbsp;</code><code>10<sup>5</sup></code> 次</li> 
// <li>在调用 <code>getRandom</code> 方法时，数据结构中 <strong>至少存在一个</strong> 元素。</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>数学</li><li>随机化</li></div></div><br><div><li>👍 598</li><li>👎 0</li></div>
package _1_dataStructure.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

// 380.O(1) 时间插入、删除和获取随机元素
// 开题时间：2022-09-11 12:18:41
public class InsertDeleteGetrandomO1 {
  public static void main(String[] args) {
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  // 自解
  class RandomizedSet {
    Set<Integer> set = new HashSet<>();
    List<Integer> list = null;
    boolean modified = false;
    Random random = new Random();
    
    public RandomizedSet() {
    }
    
    public boolean insert(int val) {
      if (!set.contains(val)) {
        set.add(val);
        modified = true;
        return true;
      }
      return false;
    }
    
    public boolean remove(int val) {
      if (set.contains(val)) {
        set.remove(val);
        modified = true;
        return true;
      }
      return false;
    }
    
    public int getRandom() {
      if (modified) {
        list = new ArrayList<>(set);
        modified = false;
      }
      return list.get(random.nextInt(list.size()));
    }
  }
  
  // GJ：删除元素时，1.待删除元素所在索引处值->末尾元素值 2.删除末尾元素
  class RandomizedSet2 {
    Map<Integer, Integer> val2idx = new HashMap<>();
    List<Integer> vals = new ArrayList<>();
    Random random = new Random();
    
    public RandomizedSet2() {
    }
    
    public boolean insert(int val) {
      if (!val2idx.containsKey(val)) {
        val2idx.put(val, vals.size());
        vals.add(val);
        return true;
      }
      return false;
    }
    
    public boolean remove(int val) {
      if (val2idx.containsKey(val)) {
        Integer idx = val2idx.get(val);
        int lst = vals.size() - 1;
        Integer lstVal = vals.get(lst);
        vals.set(idx, lstVal);
        vals.remove(lst);
        val2idx.put(lstVal, idx);
        val2idx.remove(val);
        return true;
      }
      return false;
    }
    
    public int getRandom() {
      return vals.get(random.nextInt(vals.size()));
    }
  }
  
  /**
   * Your RandomizedSet object will be instantiated and called as such:
   * RandomizedSet obj = new RandomizedSet();
   * boolean param_1 = obj.insert(val);
   * boolean param_2 = obj.remove(val);
   * int param_3 = obj.getRandom();
   */
  // leetcode submit region end(Prohibit modification and deletion)
}