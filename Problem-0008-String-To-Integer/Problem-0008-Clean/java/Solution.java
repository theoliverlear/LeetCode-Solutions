import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public int myAtoi(String intString) {
        return new IntegerExtractor(intString).getExtractedInt();
    }
}

class IntegerExtractor {
    private String intString;
    private boolean isNegative;
    private int extractedInt;
    private boolean zeroResultant;
    public IntegerExtractor(String intString) {
        this.intString = intString;
        this.extractInteger();
    }

    private void detectNegative() {
        Pattern signPattern = Pattern.compile("\\s*([\\+|\\-]?)(.*)");
        Matcher signMatcher = signPattern.matcher(this.intString);
        if (signMatcher.find()) {
            if (signMatcher.group(1).contains("-")) {
                this.isNegative = true;
            }
        }
    }

    private void extractInteger() {
        this.detectNegative();
        this.sanitizeIntString();
        if (this.zeroResultant) {
            this.extractedInt = 0;
        } else {
            this.getIntFromSanitizedString();
        }
    }

    private void sanitizeIntString() {
        Pattern partitionPattern = Pattern.compile("\\s*([\\+|\\-]?)(\\D*)(\\d*)(\\D*)");
        Matcher partitionMatcher = partitionPattern.matcher(this.intString);
        if (partitionMatcher.find()) {
            if (!partitionMatcher.group(2).isEmpty() || partitionMatcher.group(3).isEmpty()) {
                this.zeroResultant = true;
            }
            this.intString = partitionMatcher.group(3);
            this.sanitizeTrailingValues();
        }
    }

    private void sanitizeTrailingValues() {
        Pattern trailingPattern = Pattern.compile("([0|\\s]*)(\\d*)");
        Matcher trailingMatcher = trailingPattern.matcher(this.intString);
        if (trailingMatcher.find()) {
            if (!trailingMatcher.group(1).isEmpty()) {
                this.intString = trailingMatcher.group(2);
            }
        }
    }

    private void getIntFromSanitizedString() {
        this.intString = new StringBuilder(this.intString).reverse().toString();
        final int MAX_INT_DIGITS = 10;
        if (this.intString.length() > MAX_INT_DIGITS) {
            if (this.isNegative) {
                this.extractedInt = Integer.MIN_VALUE;
            } else {
                this.extractedInt = Integer.MAX_VALUE;
            }
            return;
        }
        long sum = 0;
        int orderOfMag = 1;
        final int DIGITS_OFFSET = 48;
        for (int i = 0; i < this.intString.length(); i++) {
            char singleChar = this.intString.charAt(i);
            singleChar -= DIGITS_OFFSET;
            sum += (long) singleChar * orderOfMag;
            orderOfMag *= 10;
        }
        this.extractedInt = this.getIntFromSum(sum);
    }

    private int getIntFromSum(long sum) {
        if (sum == Integer.MAX_VALUE) {
            if (this.isNegative) {
                return Integer.MIN_VALUE + 1;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (sum > Integer.MAX_VALUE) {
            if (this.isNegative) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (this.isNegative) {
            sum *= -1;
        }
        return (int) sum;
    }

    public int getExtractedInt() {
        return this.extractedInt;
    }
}