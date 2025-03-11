//=================================-Imports-==================================
#include <iostream>
#include "vector"
#include "map"
//=================================-Solution-=================================
class Solution {
public:
    //------------------------------Two-Sum-----------------------------------
    std::vector<int> twoSum(std::vector<int>& nums, int target) {
        std::map<int, std::vector<int>> map = {};
        map = this->addValueIndexes(nums, map);
        return this->getSumIndexes(map, target);
    }
    //-------------------------Add-Value-Indexes------------------------------
    std::map<int, std::vector<int>> &addValueIndexes(const std::vector<int> &nums,
                                                     std::map<int, std::vector<int>> &map) {
        for (int index = 0; index < nums.size(); index++) {
            if (!map.contains(nums[index])) {
                map[nums[index]] = {};
            }
            map[nums[index]].push_back(index);
        }
        return map;
    }
    //--------------------------Get-Sum-Indexes-------------------------------
    std::vector<int> getSumIndexes(std::map<int, std::vector<int>> map, int target) {
        for (const auto& [value, indexes] : map) {
            int remainingNum = target - value;
            if (map.contains(remainingNum)) {
                if (value == remainingNum) {
                    if (indexes.size() >= 2) {
                        return {indexes[0], indexes[1]};
                    }
                } else {
                    std::vector secondaryNumIndexes = map[remainingNum];
                    return {indexes[0], secondaryNumIndexes[0]};
                }
            }
        }
        return {};
    }
};