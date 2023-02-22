//<p><strong>未知</strong> 整数数组 <code>arr</code> 由 <code>n</code> 个非负整数组成。</p>
//
//<p>经编码后变为长度为 <code>n - 1</code> 的另一个整数数组 <code>encoded</code> ，其中 <code>encoded[i] = arr[i] XOR arr[i + 1]</code> 。例如，<code>arr = [1,0,2,1]</code> 经编码后得到 <code>encoded = [1,2,3]</code> 。</p>
//
//<p>给你编码后的数组 <code>encoded</code> 和原数组 <code>arr</code> 的第一个元素 <code>first</code>（<code>arr[0]</code>）。</p>
//
//<p>请解码返回原数组 <code>arr</code> 。可以证明答案存在并且是唯一的。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例 1：</strong></p>
//
//<pre>
//<strong>输入：</strong>encoded = [1,2,3], first = 1
//<strong>输出：</strong>[1,0,2,1]
//<strong>解释：</strong>若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
//</pre>
//
//<p><strong>示例 2：</strong></p>
//
//<pre>
//<strong>输入：</strong>encoded = [6,2,7,3], first = 4
//<strong>输出：</strong>[4,2,0,7,4]
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li> 
// <li><code>encoded.length == n - 1</code></li> 
// <li><code>0 &lt;= encoded[i] &lt;= 10<sup>5</sup></code></li> 
// <li><code>0 &lt;= first &lt;= 10<sup>5</sup></code></li> 
//</ul>
//
//<div><li>👍 106</li><li>👎 0</li></div>
package _2_algorithm.bit;

// 1720.解码异或后的数组
// 开题时间：2022-11-05 08:51:32
public class DecodeXoredArray {
  public static void main(String[] args) {
    Solution solution = new DecodeXoredArray().new Solution();
  }
  
  // leetcode submit region begin(Prohibit modification and deletion)
  class Solution {
    // a^b=c,已知a、c，求b
    public int[] decode2(int[] encoded, int first) {
      int[] decoded = new int[encoded.length + 1];
      decoded[0] = first;
      for (int i = 0; i < encoded.length; i++)
        decoded[i + 1] = reverseXor(decoded[i], encoded[i]);
      return decoded;
    }
    
    // 11010^01011=10001
    private int reverseXor(int a, int r) {
      int b = 0;
      for (int mask = 1; mask <= 150000; mask <<= 1) {
        int incre = a & mask;
        if ((r & mask) == mask) {
          if (incre != mask)
            b += mask;
        } else
          b += incre;
      }
      return b;
    }
    
    /*
     * encoded[i] = arr[i] ^ arr[i + 1]
     * arr[i] ^ encoded[i] = arr[i] ^ arr[i] ^ arr[i + 1]
     * arr[i] ^ encoded[i] = arr[i + 1]
     * arr[i + 1] = arr[i] ^ encoded[i]
     */
    public int[] decode(int[] encoded, int first) {
      int[] arr = new int[encoded.length + 1];
      arr[0] = first;
      for (int i = 0; i < encoded.length; i++)
        arr[i + 1] = arr[i] ^ encoded[i];
      return arr;
    }
  }
  // leetcode submit region end(Prohibit modification and deletion)
}