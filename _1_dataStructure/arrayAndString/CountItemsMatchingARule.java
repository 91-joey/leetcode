//<p>给你一个数组 <code>items</code> ，其中&nbsp;<code>items[i] = [type<sub>i</sub>, color<sub>i</sub>, name<sub>i</sub>]</code> ，描述第 <code>i</code> 件物品的类型、颜色以及名称。</p>
//
//<p>另给你一条由两个字符串&nbsp;<code>ruleKey</code> 和 <code>ruleValue</code> 表示的检索规则。</p>
//
//<p>如果第 <code>i</code> 件物品能满足下述条件之一，则认为该物品与给定的检索规则 <strong>匹配</strong> ：</p>
//
//<ul> 
// <li><code>ruleKey == "type"</code> 且 <code>ruleValue == type<sub>i</sub></code> 。</li> 
// <li><code>ruleKey == "color"</code> 且 <code>ruleValue == color<sub>i</sub></code> 。</li> 
// <li><code>ruleKey == "name"</code> 且 <code>ruleValue == name<sub>i</sub></code> 。</li> 
//</ul>
//
//<p>统计并返回 <strong>匹配检索规则的物品数量</strong> 。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
//<strong>输出：</strong>1
//<strong>解释：</strong>只有一件物品匹配检索规则，这件物品是 ["computer","silver","lenovo"] 。
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
//<strong>输出：</strong>2
//<strong>解释：</strong>只有两件物品匹配检索规则，这两件物品分别是 ["phone","blue","pixel"] 和 ["phone","gold","iphone"] 。注意，["computer","silver","phone"] 未匹配检索规则。</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>1 &lt;= items.length &lt;= 10<sup>4</sup></code></li> 
// <li><code>1 &lt;= type<sub>i</sub>.length, color<sub>i</sub>.length, name<sub>i</sub>.length, ruleValue.length &lt;= 10</code></li> 
// <li><code>ruleKey</code> 等于 <code>"type"</code>、<code>"color"</code> 或 <code>"name"</code></li> 
// <li>所有字符串仅由小写字母组成</li> 
//</ul>
//
//<div><div>Related Topics</div><div><li>数组</li><li>字符串</li></div></div><br><div><li>👍 79</li><li>👎 0</li></div>
package _1_dataStructure.arrayAndString;

import java.util.List;

// 1773.统计匹配检索规则的物品数量
// 开题时间：2022-11-02 11:58:59
public class CountItemsMatchingARule {
  public static void main(String[] args) {
    Solution solution = new CountItemsMatchingARule().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
      int cnt = 0;

            /* switch
            int idx = switch (ruleKey) {
                case "type" -> 0;
                case "color" -> 1;
                case "name" -> 2;
                default -> -1;
            };
            */

            /* if-else
            int idx = 0;
            if (ruleKey.charAt(0) == 'c')
                idx = 1;
            else if (ruleKey.charAt(0) == 'n')
                idx = 2;
            */
      
      // 三元运算符
      int idx = ruleKey.charAt(0) == 't' ?
          0 :
          ruleKey.charAt(0) == 'c' ?
              1 :
              2;
      
      for (List<String> item : items)
        if (item.get(idx).equals(ruleValue))
          cnt++;
      
      return cnt;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}