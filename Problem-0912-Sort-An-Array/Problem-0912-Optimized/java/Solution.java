//=================================-Imports-==================================
import java.util.Arrays;
//=================================-Solution-=================================
public class Solution {
    //-----------------------------Sort-Array---------------------------------
    public int[] sortArray(int[] numsArray) {
        this.mergeSort(numsArray);
        return numsArray;
    }
    //-----------------------------Merge-Sort---------------------------------
    public void mergeSort(int[] numsArray) {
        if (numsArray.length <= 1) {
            return;
        }
        int middleIndex = numsArray.length / 2;
        int[] firstHalfArray = Arrays.copyOfRange(numsArray, 0, middleIndex);
        int[] secondHalfArray = Arrays.copyOfRange(numsArray, middleIndex, numsArray.length);
        this.mergeSort(firstHalfArray, secondHalfArray);
        this.merge(numsArray, firstHalfArray, secondHalfArray);
    }
    //-----------------------------Merge-Sort---------------------------------
    public void mergeSort(int[] firstHalfArray, int[] secondHalfArray) {
        this.mergeSort(firstHalfArray);
        this.mergeSort(secondHalfArray);
    }
    //-------------------------------Merge------------------------------------
    public void merge(int[] numsArray, int[] firstHalfArray, int[] secondHalfArray) {
        int firstIndex = 0, secondIndex = 0, mergesCount = 0;
        while (this.canDualMerge(firstHalfArray, secondHalfArray, firstIndex, secondIndex)) {
            int firstArrayValue = firstHalfArray[firstIndex];
            int secondArrayValue = secondHalfArray[secondIndex];
            if (firstArrayValue <= secondArrayValue) {
                numsArray[mergesCount++] = firstHalfArray[firstIndex++];
            } else {
                numsArray[mergesCount++] = secondHalfArray[secondIndex++];
            }
        }
        mergesCount = this.mergeRemainingElements(numsArray, firstHalfArray, firstIndex, mergesCount);
        this.mergeRemainingElements(numsArray, secondHalfArray, secondIndex, mergesCount);
    }
    //----------------------Merge-Remaining-Elements--------------------------
    public int mergeRemainingElements(int[] numsArray,
                                      int[] halfArray,
                                      int index,
                                      int mergesCount) {
        while (index < halfArray.length) {
            numsArray[mergesCount++] = halfArray[index++];
        }
        return mergesCount;
    }
    //---------------------------Can-Dual-Merge-------------------------------
    private boolean canDualMerge(int[] firstHalfArray,
                                 int[] secondHalfArray,
                                 int firstIndex,
                                 int secondIndex) {
        return firstIndex < firstHalfArray.length &&
                secondIndex < secondHalfArray.length;
    }
}
