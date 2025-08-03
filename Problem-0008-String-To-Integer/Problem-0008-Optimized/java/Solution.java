import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public int myAtoi(String intString) {
        if (intString.isEmpty()) {
            return 0;
        }
        intString = intString.trim();
        ExtractResult extractResult = extractValues(intString);
        if (extractResult == null) {
            return 0;
        }
        return getFromValidatedString(extractResult.intString(),
                                      extractResult.isNegative());
    }

    private static ExtractResult extractValues(String intString) {
        boolean negative = false;
        Pattern partitionPattern = Pattern.compile("\\s*([\\+|\\-]?)(\\D*)(\\d*)(\\D*)");
        Matcher partitionMatcher = partitionPattern.matcher(intString);
        if (partitionMatcher.find()) {
            if (partitionMatcher.group(1).contains("-")) {
                negative = true;
            }
            if (!partitionMatcher.group(2).isEmpty() || partitionMatcher.group(3).isEmpty()) {
                return null;
            }
            intString = partitionMatcher.group(3);
            Pattern trailingPattern = Pattern.compile("([0|\\s]*)(\\d*)");
            Matcher trailingMatcher = trailingPattern.matcher(intString);
            if (trailingMatcher.find()) {
                if (!trailingMatcher.group(1).isEmpty()) {
                    intString = trailingMatcher.group(2);
                }
            }
        }
        return new ExtractResult(intString, negative);
    }

    private record ExtractResult(String intString, boolean isNegative) { }

    private static int getFromValidatedString(String intString, boolean negative) {
        intString = new StringBuilder(intString).reverse().toString();
        if (intString.length() > 10) {
            if (negative) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        long sum = 0;
        int orderOfMag = 1;
        for (int i = 0; i < intString.length(); i++) {
            char singleChar = intString.charAt(i);
            singleChar -= 48;
            sum += (long) singleChar * orderOfMag;
            orderOfMag *= 10;
        }
        return getIntBySum(negative, sum);
    }

    private static int getIntBySum(boolean isNegative, long sum) {
        if (sum == Integer.MAX_VALUE) {
            if (isNegative) {
                return Integer.MIN_VALUE + 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (sum > Integer.MAX_VALUE) {
            if (isNegative) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (isNegative) {
            sum *= -1;
        }
        return (int) sum;
    }
}
