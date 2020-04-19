package array;

/**
 * 26. 删除排序数组中的重复项
 */
public class L026_RemoveDuplicatesFromSortedArray {

    /**
     * 双指针法
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        // 不重复元素的下标
        int index = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[index]) {
                index++;
                nums[index] = nums[j];
            }
        }
        // 下标+1，为个数
        return index + 1;
    }
    
}
