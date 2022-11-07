//<p>给你一个有效的 <a href="https://baike.baidu.com/item/IPv4" target="_blank">IPv4</a> 地址&nbsp;<code>address</code>，返回这个 IP 地址的无效化版本。</p>
//
//<p>所谓无效化&nbsp;IP 地址，其实就是用&nbsp;<code>"[.]"</code>&nbsp;代替了每个 <code>"."</code>。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre><strong>输入：</strong>address = "1.1.1.1"
//<strong>输出：</strong>"1[.]1[.]1[.]1"
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre><strong>输入：</strong>address = "255.100.50.0"
//<strong>输出：</strong>"255[.]100[.]50[.]0"
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li>给出的&nbsp;<code>address</code>&nbsp;是一个有效的 IPv4 地址</li> 
//</ul>
//
//<div><li>👍 122</li><li>👎 0</li></div>
package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//1108.IP 地址无效化
//开题时间：2022-11-07 08:51:57
public class DefangingAnIpAddress {
    public static void main(String[] args) {
        Solution solution = new DefangingAnIpAddress().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String defangIPaddr(String address) {
            return address.replace(".", "[.]");
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}