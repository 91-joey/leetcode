package org.example.leetcode.problems.algorithm.sort.algorithm.others;

import org.example.leetcode.problems.algorithm.sort.algorithm.Swap;
import org.example.leetcode.problems.algorithm.sort.algorithm.n.Count;

import static org.example.leetcode.problems.algorithm.sort.algorithm.n.Count.count;

/*
 * 意大利面排序（相当于最后倒序遍历的伪计数排序）
 *    首先，取一把未煮的意大利面，对于数组中的每一个数字，剪一根对应长度的意大利面条，用这根面条代表这个数字。
 *    用一只手拿着这一把面条，将其竖着立在桌子上，保证每一根的底部都碰到桌面。另一只手从面条的上方缓缓下落。显然，这只手碰到的第一根面条就是最长的，将其取出放至首位。重复此过程直至所有的面条取完，数字就完成了排序。
 */
public class Spaghetti {
    public static void main(String[] args) {
        Swap.sortHard(Spaghetti::spaghettiSort);
    }

    public static void spaghettiSort(int[] arr) {
        //用面条的长度记录数字大小。
        Count.CountingAndMin countingAndMin = count(arr);
        int[] counting = countingAndMin.counting;
        int min = countingAndMin.min;

        //右手从高处落下，碰到的第一根面条对应的数字就是最大的数字，重复此过程直到排序完成
        for (int i = arr.length - 1, cntIdx = counting.length - 1; i >= 0; cntIdx--) {
            int val = cntIdx + min;
            while (counting[cntIdx] != 0) {
                arr[i--] = val;
                counting[cntIdx]--;
            }
        }
    }
}
