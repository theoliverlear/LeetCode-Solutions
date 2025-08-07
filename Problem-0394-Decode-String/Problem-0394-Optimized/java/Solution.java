import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public String decodeString(String encodedString) {
        if (isDecodedString(encodedString)) {
            return encodedString;
        }
        final String repeatPatternRegex = "([a-z]*)(\\d+)\\[(\\D+)\\](.*)";
        final String isNestedRegex = ".*\\[.[^\\]]*\\[.*\\].*\\].*";
        if (encodedString.matches(isNestedRegex)) {
            encodedString = getNestedEncodedString(encodedString);
        } else if (encodedString.matches(repeatPatternRegex)) {
            encodedString = getRepeatString(encodedString, repeatPatternRegex);
        }
        return this.decodeString(encodedString);
    }

    private static String getRepeatString(String encodedString,
                                          String repeatPatternRegex) {
        Pattern repeatPattern = Pattern.compile(repeatPatternRegex);
        Matcher repeatMatcher = repeatPattern.matcher(encodedString);
        if (repeatMatcher.find()) {
            String leadingLetters = repeatMatcher.group(1);
            int multiplier = Integer.parseInt(repeatMatcher.group(2));
            String repeatContent = repeatMatcher.group(3);
            String restOfString = repeatMatcher.group(4);
            encodedString = leadingLetters + repeatContent.repeat(multiplier) + restOfString;
        }
        return encodedString;
    }

    private String getNestedEncodedString(String encodedString) {
        final String findNestedRegex = "\\d+\\[[\\D]*([a-z]*\\d+\\[[a-z]+\\])(.*)";
        Pattern nestedPattern = Pattern.compile(findNestedRegex);
        Matcher nestedMatcher = nestedPattern.matcher(encodedString);
        if (nestedMatcher.find()) {
            String nestedRepeat = nestedMatcher.group(1);
            String decodedRepeat = this.decodeString(nestedRepeat);
            encodedString = encodedString.replace(nestedRepeat, decodedRepeat);
        }
        return encodedString;
    }

    private static boolean isDecodedString(String encodedString) {
        return !encodedString.contains("[") && !encodedString.contains("]");
    }
}