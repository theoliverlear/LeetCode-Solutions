//=================================-Solution-=================================

//----------------------------------Two-Sum-----------------------------------
function twoSum(nums: number[], target: number): number[] {
    const map: Map<number, Array<number>> = new Map();
    for (let index: number = 0; index < nums.length; index++) {
        const value: number = nums[index];
        if (!map.has(value)) {
            map.set(value, new Array<number>());
        }
        const valueArray: Array<number> = map.get(value);
        valueArray.push(index)
        map.set(value, valueArray);
    }
    for (const [value, indexes] of map.entries()) {
        const remainderNum: number = target - value;
        if (map.has(remainderNum)) {
            switch (value) {
                case remainderNum:
                    if (indexes.length >= 2) {
                        return [indexes[0], indexes[1]];
                    }
                    break;
                default:
                    const secondaryIndexArray: number[] = map.get(remainderNum);
                    return [indexes[0], secondaryIndexArray[0]];
            }
        }
    }
    return null;
}