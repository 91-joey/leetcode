package org.example.leetcode.problems._1_dataStructure.arrayAndString;

//35. 搜索插入位置
public class SearchInsert {
    //    1.O(N)
    public int searchInsert1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    //    2.O(logN) 二分法
    public int searchInsert2(int[] nums, int target) {
        int l = 0;
        int r = nums.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if(target<=nums[mid]){
                r=mid;
            }else {
                l=mid+1;
            }
        }
        return l;
    }
}
