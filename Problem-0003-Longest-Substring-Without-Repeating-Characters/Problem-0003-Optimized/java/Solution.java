import java.util.ArrayList;
import java.util.List;

//=================================-Solution-=================================
class Solution {
    //--------------------Length-Of-Longest-Substring-------------------------
    public int lengthOfLongestSubstring(String fullString) {
        if (fullString.isEmpty()) {
            return 0;
        }
        int maxSize = 1;
        for (int i = 0; i < fullString.length(); i++) {
            List<Character> stringChars = new ArrayList<>();
            char baseChar = fullString.charAt(i);
            stringChars.add(baseChar);
            for (int j = i + 1; j < fullString.length(); j++) {
                char nextChar = fullString.charAt(j);
                if (stringChars.contains(nextChar)) {
                    break;
                } else {
                    stringChars.add(nextChar);
                    maxSize = Math.max(maxSize, stringChars.size());
                }
            }
        }
        return maxSize;
    }
}