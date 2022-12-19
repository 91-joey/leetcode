//<p>请你实现一个「数字乘积类」<code>ProductOfNumbers</code>，要求支持下述两种方法：</p>
//
//<p>1.<code>&nbsp;add(int num)</code></p>
//
//<ul> 
// <li>将数字&nbsp;<code>num</code>&nbsp;添加到当前数字列表的最后面。</li> 
//</ul>
//
//<p>2.<code> getProduct(int k)</code></p>
//
//<ul> 
// <li>返回当前数字列表中，最后&nbsp;<code>k</code>&nbsp;个数字的乘积。</li> 
// <li>你可以假设当前列表中始终 <strong>至少</strong> 包含 <code>k</code> 个数字。</li> 
//</ul>
//
//<p>题目数据保证：任何时候，任一连续数字序列的乘积都在 32-bit 整数范围内，不会溢出。</p>
//
//<p>&nbsp;</p>
//
//<p><strong>示例：</strong></p>
//
//<pre><strong>输入：</strong>
//["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct","getProduct","add","getProduct"]
//[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]
//
//<strong>输出：</strong>
//[null,null,null,null,null,null,20,40,0,null,32]
//
//<strong>解释：</strong>
//ProductOfNumbers productOfNumbers = new ProductOfNumbers();
//productOfNumbers.add(3);        // [3]
//productOfNumbers.add(0);        // [3,0]
//productOfNumbers.add(2);        // [3,0,2]
//productOfNumbers.add(5);        // [3,0,2,5]
//productOfNumbers.add(4);        // [3,0,2,5,4]
//productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
//productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
//productOfNumbers.getProduct(4); // 返回  0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
//productOfNumbers.add(8);        // [3,0,2,5,4,8]
//productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32 
//</pre>
//
//<p>&nbsp;</p>
//
//<p><strong>提示：</strong></p>
//
//<ul> 
// <li><code>add</code> 和 <code>getProduct</code>&nbsp;两种操作加起来总共不会超过&nbsp;<code>40000</code>&nbsp;次。</li> 
// <li><code>0 &lt;= num&nbsp;&lt;=&nbsp;100</code></li> 
// <li><code>1 &lt;= k &lt;= 40000</code></li> 
//</ul>
//
//<div><li>👍 89</li><li>👎 0</li></div>
package org.example.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

//1352.最后 K 个数的乘积
//开题时间：2022-12-19 18:20:17
public class ProductOfTheLastKNumbers {
    public static void main(String[] args) {
//        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
//        productOfNumbers.add(3); // [3]
//        productOfNumbers.add(0); // [3,0]
//        productOfNumbers.add(2); // [3,0,2]
//        productOfNumbers.add(5); // [3,0,2,5]
//        productOfNumbers.add(4); // [3,0,2,5,4]
//        productOfNumbers.getProduct(2); // 返回 20 。最后 2 个数字的乘积是 5 * 4 = 20
//        productOfNumbers.getProduct(3); // 返回 40 。最后 3 个数字的乘积是 2 * 5 * 4 = 40
//        productOfNumbers.getProduct(4); // 返回 0 。最后 4 个数字的乘积是 0 * 2 * 5 * 4 = 0
//        productOfNumbers.add(8); // [3,0,2,5,4,8] productOfNumbers.getProduct(2); // 返回 32 。最后 2 个数字的乘积是 4 * 8 = 32

//        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
//        productOfNumbers.add(1);
//        System.out.println("productOfNumbers.getProduct(1) = " + productOfNumbers.getProduct(1));
//        productOfNumbers.getProduct(1);
//        productOfNumbers.getProduct(1);
//        productOfNumbers.add(7);
//        productOfNumbers.add(6);
//        productOfNumbers.add(7);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //暴力
    class ProductOfNumbers9 {
        List<Integer> list = new ArrayList<>();

        public ProductOfNumbers9() {
        }

        public void add(int num) {
            list.add(num);
        }

        public int getProduct(int k) {
            int product = 1;
            for (int i = 0, idx = list.size() - 1; i < k; i++)
                product *= list.get(idx--);
            return product;
        }
    }

    //前缀对数和 + 记录 0 的最大索引
    class ProductOfNumbers8 {
        List<Double> list = new ArrayList<>();
        int idx0 = Integer.MIN_VALUE;

        public ProductOfNumbers8() {
            list.add(0.0);
        }

        public void add(int num) {
            if (num == 0) {
                idx0 = list.size() - 1;
                list.add(list.get(list.size() - 1));
            } else
                list.add(list.get(list.size() - 1) + Math.log(num) + 1e-10);
        }

        public int getProduct(int k) {
            int size = list.size();
            if (idx0 >= size - 1 - k)
                return 0;
            return (int) Math.exp(list.get(size - 1) - list.get(size - 1 - k));
        }
    }

    //前缀积 + 遇零清空集合
    class ProductOfNumbers {
        List<Integer> list = new ArrayList<>();

        public ProductOfNumbers() {
            list.add(1);
        }

        public void add(int num) {
            if (num == 0) {
                list = new ArrayList<>();
                list.add(1);
            } else
                list.add(list.get(list.size() - 1) * num);
        }

        public int getProduct(int k) {
            int size = list.size();
            if (size - 1 < k)
                return 0;
            return list.get(size - 1) / list.get(size - 1 - k);
        }
    }
/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
//leetcode submit region end(Prohibit modification and deletion)
}