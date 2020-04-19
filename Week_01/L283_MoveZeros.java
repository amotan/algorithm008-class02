package array;

import java.util.Arrays;

/**
 * leetcode 283 move zeros
 * https://leetcode-cn.com/problems/move-zeroes/
 */

public class L283_MoveZeros {


    public static void main(String[] args) {

        int[] nums = {0,1,0,3,12};

        new L283_MoveZeros().moveZeroes(nums);
        new L283_MoveZeros().moveZeroesSwap(nums);


    }

    /**
     * 双指针法
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        // 记录非0元素的下标
        int index = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i]!=0) {
                // 非零元素挪到前面
                nums[index] = nums[i];
                // 如果下标不相等，则上次遍历时移动过元素，当前元素需要置为0
                if(i!=index) {
                    nums[i] = 0;
                }
                index++;
            }
        }
    }

    /**
     * 交换法
     * @param nums
     */
    public void moveZeroesSwap(int[] nums) {
        // 记录非0元素的下标
        int index = 0;
        for(int i=0;i<nums.length;i++) {
            // 如果当前不为0，则交换到第一个元素到位置，以此类推
            if(nums[i]!=0) {
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                index ++;
            }
        }

    }

    /**
     * 滚雪球法
     * @param nums
     */
    public void moveZerosSnowBalls(int[] nums) {

        // 记录遍历到到0元素到个数
        int snowBallSize = 0;

        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                snowBallSize++;
            }
            else if (snowBallSize > 0) {
                int t = nums[i];
                nums[i]=0;
                // 把非0元素挪到snowBallSize个零元素之前的位置上去
                nums[i-snowBallSize]=t;
            }
        }
    }

}
