//=================================-Solution-=================================

//==================================-Types-===================================
type ValueIndexesMap = Map<number, Array<number>>;

//----------------------------------Two-Sum-----------------------------------
function twoSum(nums: number[], target: number): number[] {
    const map: ValueIndexesMap = new Map();
    for (let index: number = 0; index < nums.length; index++) {
        const value: number = nums[index];
        insertValueIndex(map, value, index);
    }
    return getSumIndexes(map, target);
}
//-----------------------------Insert-Value-Index-----------------------------
function insertValueIndex(map: Map<number, Array<number>>,
                          value: number,
                          index: number): void {
    if (!map.has(value)) {
        map.set(value, new Array<number>());
    }
    const valueArray: Array<number> = map.get(value);
    valueArray.push(index)
    map.set(value, valueArray);
}
//-----------------------------Get-Remainder-Num------------------------------
function getRemainderNum(target: number, value: number): number {
    return target - value;
}
//------------------------------Get-Sum-Indexes-------------------------------
function getSumIndexes(map: ValueIndexesMap, target: number): number[] {
    for (const [value, indexes] of map.entries()) {
        const remainderNum: number = getRemainderNum(target, value);
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