import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return this.permute(nums, 0, 0, new ArrayList<>());
    }

    public List<List<Integer>> permute(int[] nums,
                                       int targetIndex,
                                       int swapIndex,
                                       List<List<Integer>> permutations) {
        boolean reachedLastSwap = swapIndex == nums.length - 1;
        boolean reachedLastIndex = targetIndex == nums.length - 1;
        if (reachedLastSwap && reachedLastIndex) {
            addAllNums(nums, permutations);
            return permutations;
        }
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        for (int i = swapIndex; i < nums.length; i++) {
            swap(numsCopy, targetIndex, i);
            int newStartIndex = targetIndex + 1;
            this.permute(numsCopy, newStartIndex, newStartIndex, permutations);
        }
        return permutations;
    }

    public static void addAllNums(int[] nums, List<List<Integer>> numsList) {
        List<Integer> numsAsList = new ArrayList<>();
        for (int num : nums) {
            numsAsList.add(num);
        }
        numsList.add(numsAsList);
    }

    public static void swap(int[] array, int initialIndex, int swapIndex) {
        int initialNum = array[initialIndex];
        int swapNum = array[swapIndex];
        array[swapIndex] = initialNum;
        array[initialIndex] = swapNum;
    }
}