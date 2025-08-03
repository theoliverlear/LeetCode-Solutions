# Problem 0008 - String to Integer (atoi)
### [Link](https://leetcode.com/problems/string-to-integer-atoi/description/)

Implement the `myAtoi(string intString)` function, which converts a string to a 32-bit
signed integer.

The algorithm for `myAtoi(string intString)` is as follows:

1. Whitespace: Ignore any leading whitespace (`" "`).
2. Signedness: Determine the sign by checking if the next character is `'-'`
   or `'+'`, assuming positivity if neither present.
3. Conversion: Read the integer by skipping leading zeros until a non-digit
   character is encountered or the end of the string is reached. If no digits
   were read, then the result is 0.
4. Rounding: If the integer is out of the 32-bit signed integer range
   `[-2^31, 2^31 - 1]`, then round the integer to remain in the range. 
   Specifically, integers less than `-2^31` should be rounded to `-2^31`, and 
   integers greater than `2^31 - 1` should be rounded to `2^31 - 1`.

5. Return the integer as the final result.

## Tags

- String

## Optimized

| Language | Time Complexity | Memory Complexity | Approx. Runtime | Approx. Memory   | Link                                                |
|----------|-----------------|-------------------|-----------------|------------------|-----------------------------------------------------|
| **Java** | O(n)            | O(n)              | 14 ms - 5.15%   | 45.65 MB - 5.56% | [Link](./Problem-0008-Optimized/java/Solution.java) |

## Clean

| Language | Time Complexity | Memory Complexity | Approx. Runtime | Approx. Memory   | Link                                            |
|----------|-----------------|-------------------|-----------------|------------------|-------------------------------------------------|
| **Java** | O(n)            | O(n)              | 18 ms - 4.54%   | 45.36 MB - 5.56% | [Link](./Problem-0008-Clean/java/Solution.java) |