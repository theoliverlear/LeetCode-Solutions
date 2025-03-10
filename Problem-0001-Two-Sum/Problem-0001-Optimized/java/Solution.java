import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//=================================-Solution-=================================
public class Solution {
    //------------------------------Two-Sum-----------------------------------
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> valueIndexMap = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            if (!valueIndexMap.containsKey(nums[index])) {
                valueIndexMap.put(nums[index], new ArrayList<>());
            }
            valueIndexMap.get(nums[index]).add(index);
        }
        for (Map.Entry<Integer, List<Integer>> entry : valueIndexMap.entrySet()) {
            int primaryNum = entry.getKey();
            int remainderNum = target - primaryNum;
            if (valueIndexMap.containsKey(remainderNum)) {
                if (primaryNum == remainderNum) {
                    return new int[] {entry.getValue().get(0), entry.getValue().get(1)};
                } else {
                    return new int[] {entry.getValue().getFirst(), valueIndexMap.get(remainderNum).getFirst()};
                }
            }
        }
        return null;
    }
}