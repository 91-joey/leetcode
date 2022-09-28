package org.example.leetcode.problems.dataStructure.arrayAndString;

//26. 删除有序数组中的重复项
public class RemoveDuplicatesOfArray {
    //    1.自解(双指针)   n   1
    public int removeDuplicates(int[] nums) {
//        slow存放重复值组的第一个值
        int slow = 0;
//        fast找寻下一个重复值组
        for (int fast = 1; fast < nums.length; fast++) {
//            找到下一个重复值组
            if (nums[slow] != nums[fast]) {
//                增加索引值
//                存放重复值组的第一个值
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }

    //    1.官解(双指针)   n   1
    public int removeDuplicates2(int[] nums) {
//        slow存放重复值组的第一个值
        int slow = 1;
//        fast找寻下一个重复值组
        for (int fast = 1; fast < nums.length; fast++) {
//            找到下一个重复值组
            if (nums[fast - 1] != nums[fast]) {
//                增加索引值
//                存放重复值组的第一个值
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicatesOfArray().removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
//        0, 0, 1, 1, 1, 2, 2, 3, 3, 4
//        |  |
//        |     |
//           |  |
//        0, 1, 1, 1, 1, 2, 2, 3, 3, 4
//           |     |
//           |        |
//           |           |
//              |        |
//        0, 1, 2, 1, 1, 2, 2, 3, 3, 4
//              |           |
//              |              |
//                 |           |
//        0, 1, 2, 3, 1, 2, 2, 3, 3, 4
//                 |              |
//                 |                 |
//        0, 1, 2, 3, 4, 2, 2, 3, 3, 4
    }
}
