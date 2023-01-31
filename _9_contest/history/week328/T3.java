package org.example.leetcode.problems._9_contest.history.week328;

import java.util.HashMap;
import java.util.Map;

//6293. Count the Number of Good Subarrays
public class T3 {
    public static void main(String[] args) {
        Solution solution = new T3().new Solution();
        System.out.println(solution.countGood(new int[]{3, 1, 4, 3, 2, 2, 4}, 2));
//        System.out.println(solution.countGood(new int[]{1, 1, 1, 1, 1}, 10));
    }

    class Solution {
        /*
        我的解题历程：
        - 比赛时，想了 5min ，向滑窗方向想了、但具体怎么实现还是没有思路，然后就去整 T4 了，我以为我可以的，结果 WA 了 2 次、发现打脸了，此时已经临近竞赛尾声，所以 T3 就搁了。
        - 晚上我又重新开始做这道题，用时 40 min（加上比赛时的）AC。思考过程是这样的：
          - 如果暴力的话，枚举每个子数组的复杂度为 $O(n^2)$，每个子数组统计元素对、可以用哈希表计数、每个元素值的元素对数为 $n*(n-1)/2$、合计所有的元素值的元素对数（复杂度为 $O(n)$）、若 $\ge k$ ，则此子数组为好子数组，总体复杂度为 $O(n^3)$，显然会 TLE ，那么有没有办法减少复杂度呢？
          - 我们发现：一个子数组左侧减一个元素或右侧加一个元素，就变成了另一个子数组，我们可以运用「滑动窗口」思想解题，具体的：
              - 先固定左侧向右扩大到元素对数 $\ge k$ ，再固定右侧向右缩小到元素对数 $< k$，此时我又想到了可以用「贡献法」，即当前子数组对答案的贡献为 `窗口左侧元素数 * 窗口右侧元素数` （具体的边界考虑就先不细究了）
             - 然后我们重复上述步骤（先固定左侧向右扩大到元素对数 $\ge k$ ，再固定右侧向右缩小到元素对数 $< k$），此时又要计算当前子数组对答案的贡献了，但是我发现当前子数组和之前的子数组对答案的贡献这两者会出现重叠的情况，需要去掉交集值，这一下子把问题变复杂的同时、也意味着总时间复杂度达到了 $O(n^2)$。既然这条路不通，那就另辟蹊径吧：
          - 我们也可以枚举左端点，合计每个左端点为开头的好子数组个数：
             - 先固定左侧向右扩大到元素对数 $\ge k$
             - 枚举每个左端点
                   - 答案加上右端点到末端的距离
                   - 固定右侧向右缩小一个单位（移除左端点）
                   - 固定左侧向右扩大到元素对数 $\ge k$
             - 尾处理：固定右侧（实为末端）向右缩小到元素对数 $< k$），答案加上缩小的距离
          - 再看题解，是采取的「枚举右端点」的思路，虽是相同的算法（滑窗+哈希计数），但是整体代码精简了很多。至此，我也发现似乎很多统计子数组个数的问题，都是枚举右端点比左端点容易。

        一点小收获：java 可以用 `merge` 函数，方便处理计数增减的同时，还可以利用返回值。
        */
        //「枚举左端点」：
        public long countGood9(int[] nums, int k) {
            long ans = 0;
            Map<Integer, Integer> val2cnt = new HashMap<>();
            int cnt = 0;
            int r = 0;
            int n = nums.length;
            while (r < n && cnt < k) {
                int numCnt = val2cnt.getOrDefault(nums[r], 0);
                cnt += numCnt;
                val2cnt.put(nums[r++], numCnt + 1);
            }
            int l = 0;
            while (r < n) {
                ans += n - r + 1;
                int numCnt = val2cnt.get(nums[l]) - 1;
                cnt -= numCnt;
                val2cnt.put(nums[l++], numCnt);
                while (r < n && cnt < k) {
                    numCnt = val2cnt.getOrDefault(nums[r], 0);
                    cnt += numCnt;
                    val2cnt.put(nums[r++], numCnt + 1);
                }
            }

            int tmp = l;
            while (l < n && cnt >= k) {
                int numCnt = val2cnt.get(nums[l]) - 1;
                cnt -= numCnt;
                val2cnt.put(nums[l++], numCnt);
            }

            return ans + l - tmp;
        }

        //☆☆☆☆☆「枚举右端点」：
        public long countGood(int[] nums, int k) {
            Map<Integer, Integer> val2cnt = new HashMap<>();
            long ans = 0;
            int cur = 0, l = 0;
            for (int x : nums) {
                cur += val2cnt.merge(x, 1, Integer::sum) - 1;
                while (cur - (val2cnt.get(nums[l]) - 1) >= k)
                    cur -= val2cnt.merge(nums[l++], -1, Integer::sum);
                if (cur >= k)
                    ans += l + 1;
            }
            return ans;
        }
    }
}
