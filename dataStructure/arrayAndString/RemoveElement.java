package org.example.leetcode.problems.dataStructure.arrayAndString;

//27. 移除元素
public class RemoveElement {
    //    1.快慢指针  n*2   1
    public int removeElement1(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    //    2.快慢指针(增强for)  n*1   1
    public int removeElement2(int[] nums, int val) {
        int idx = 0;
        for (int num : nums) {
            if (num != val) {
                nums[idx++] = num;
            }
        }
        return idx;
    }

//    01230067
//    71230067
//    71236067
//    71236
//    3.双指针优化  n   1
    public int removeElement3(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveElement().removeElement1(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }
}
