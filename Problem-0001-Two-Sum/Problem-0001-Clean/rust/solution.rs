//=================================-Imports-==================================
use std::collections::HashMap;

//=================================-Solution-=================================
impl Solution {
    //------------------------------Two-Sum-----------------------------------
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut map: HashMap<i32, Vec<i32>> = HashMap::new();
        Self::set_map_value_indexes(&nums, &mut map);
        return Self::get_sum_indexes(target, &mut map);
    }
    //-----------------------Set-Map-Value-Indexes----------------------------
    fn set_map_value_indexes(nums: &Vec<i32>, map: &mut HashMap<i32, Vec<i32>>) {
        for index in 0..nums.len() {
            if !map.contains_key(&nums[index]) {
                map.insert(nums[index], Vec::<i32>::new());
            }
            let possible_map_array: Option<&Vec<i32>> = map.get(&nums[index]);
            if (possible_map_array.is_some()) {
                let mut map_array: Vec<i32> = possible_map_array.unwrap().to_vec();
                map_array.push(index as i32);
                map.insert(nums[index], map_array);
            }
        }
    }
    //--------------------------Get-Sum-Indexes-------------------------------
    fn get_sum_indexes(target: i32, map: &mut HashMap<i32, Vec<i32>>) -> Vec<i32> {
        for (value, indexes) in map.iter() {
            let remainder_num: i32 = target - value;
            if map.contains_key(&remainder_num) {
                if value == &remainder_num {
                    if (indexes.len() >= 2) {
                        return Vec::from([indexes[0], indexes[1]]);
                    }
                } else {
                    let possible_secondary_index_array: Option<&Vec<i32>> = map.get(&remainder_num);
                    if (possible_secondary_index_array.is_some()) {
                        let secondary_index_array: Vec<i32> = possible_secondary_index_array.unwrap().to_vec();
                        return Vec::from([indexes[0], secondary_index_array[0]]);
                    }
                }
            }
        }
        return vec![];
    }
}
//==================================-Types-===================================
pub struct Solution;