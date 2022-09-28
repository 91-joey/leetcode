package org.example.leetcode.problems.dataStructure.arrayAndString;

//283. 移动零
public class MoveZeroes {
//    1.2次遍历(后补零) n   1
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int idx = 0;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                nums[idx++] = nums[i];
            }
        }
        for (int i = idx; i < length; i++) {
            nums[i] = 0;
        }
    }

//    2.双指针(移动零)   n   1
    public void moveZeroes2(int[] nums) {
//        储存0的索引
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                if(slow!=fast){
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow++;
            }
        }
    }

    public static void main(String[] args) {
//        new MoveZeroes().moveZeroes(new int[]{0, 1, 0, 3, 12});
//        new MoveZeroes().moveZeroes(new int[]{2, 1});
//        new MoveZeroes().moveZeroes(new int[]{1, 0, 1});
//        new MoveZeroes().moveZeroes(new int[]{0, 1, 0, 0, 2});
        new MoveZeroes().moveZeroes2(new int[]{1, 1, 0, 1, 2});
    }
//    10038
//    13008
//    13800
}
