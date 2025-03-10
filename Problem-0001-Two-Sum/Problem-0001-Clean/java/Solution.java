import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//=================================-Solution-=================================
public class Solution {
    //------------------------------Two-Sum-----------------------------------
    public int[] twoSum(int[] nums, int target) {
        ValueIndexMap valueMap = new ValueIndexMap(nums, target);
        return valueMap.getSumIndexes();
    }
}
//=============================-Value-Index-Map-==============================
class ValueIndexMap {
    public int[] nums;
    public int target;
    public Map<Integer, List<Integer>> valueIndexMap;
    //===========================-Constructors-===============================
    public ValueIndexMap(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        this.initializeMap();
    }
    //=============================-Methods-==================================

    //---------------------------Initialize-Map-------------------------------
    private void initializeMap() {
       this.valueIndexMap = new HashMap<>();
        for (int index = 0; index < this.nums.length; index++) {
            int valueAtIndex = this.nums[index];
            this.addNumToMap(valueAtIndex, index);
        }
    }

    //--------------------------Get-Sum-Indexes-------------------------------
    public int[] getSumIndexes() {
        for (Map.Entry<Integer, List<Integer>> entry : this.valueIndexMap.entrySet()) {
            int primaryNum = entry.getKey();
            int remainderNum = this.getRemainderNumber(primaryNum);
            if (this.containsValue(remainderNum)) {
                if (primaryNum == remainderNum) {
                    return this.getValueIndexes(primaryNum);
                } else {
                    return this.getValueIndexes(primaryNum, remainderNum);
                }
            }
        }
        return null;
    }
    //-------------------------Get-Value-Indexes------------------------------
    private int[] getValueIndexes(int value) {
        List<Integer> valueIndexes = this.valueIndexMap.get(value);
        int primaryNum = valueIndexes.get(0);
        int secondaryNum = valueIndexes.get(1);
        return new int[] {primaryNum, secondaryNum};
    }
    //-------------------------Get-Value-Indexes------------------------------
    private int[] getValueIndexes(int primaryValue, int secondaryValue) {
        List<Integer> primaryValueIndexes = this.valueIndexMap.get(primaryValue);
        List<Integer> secondaryValueIndexes = this.valueIndexMap.get(secondaryValue);
        int primaryNum = primaryValueIndexes.getFirst();
        int secondaryNum = secondaryValueIndexes.getFirst();
        return new int[] {primaryNum, secondaryNum};
    }
    //-------------------------Add-Numbers-To-Map-----------------------------
    private void addNumToMap(int value, int index) {
        if (!this.containsValue(value)) {
            this.valueIndexMap.put(value, new ArrayList<>());
        }
        this.valueIndexMap.get(value).add(index);
    }
    //---------------------------Contains-Value-------------------------------
    private boolean containsValue(int value) {
        return this.valueIndexMap.containsKey(value);
    }
    //------------------------Get-Remaining-Number----------------------------
    private int getRemainderNumber(int primaryNumber) {
        return this.target - primaryNumber;
    }
}