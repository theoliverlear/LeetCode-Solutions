import java.util.ArrayList;
import java.util.List;

class Solution {
    //--------------------Length-Of-Longest-Substring-------------------------
    public int lengthOfLongestSubstring(String fullString) {
        if (fullString.isEmpty()) {
            return 0;
        }
        int maxSize = 1;
        maxSize = this.lengthOfLongestSubstring(fullString, maxSize);
        return maxSize;
    }
    //--------------------Length-Of-Longest-Substring-------------------------
    public int lengthOfLongestSubstring(String fullString, int maxSize) {
        for (int i = 0; i < fullString.length(); i++) {
            List<Character> stringChars = new ArrayList<>();
            char baseChar = fullString.charAt(i);
            stringChars.add(baseChar);
            maxSize = this.getNextMaxSize(fullString, i, stringChars, maxSize);
        }
        return maxSize;
    }
    //-------------------------Get-Next-Max-Size------------------------------
    public int getNextMaxSize(String fullString,
                              int index,
                              List<Character> stringChars,
                              int maxSize) {
        for (int j = index + 1; j < fullString.length(); j++) {
            char nextChar = fullString.charAt(j);
            if (stringChars.contains(nextChar)) {
                break;
            } else {
                stringChars.add(nextChar);
                maxSize = Math.max(maxSize, stringChars.size());
            }
        }
        return maxSize;
    }
}