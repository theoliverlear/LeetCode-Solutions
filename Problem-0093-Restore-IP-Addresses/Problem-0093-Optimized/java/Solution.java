import java.util.*;

public class Solution {
    public List<String> restoreIpAddresses(String ipDigitString) {
        Map<Byte, Map<Byte, String>> indexLengthMap = new HashMap<>();
        byte numDigits = (byte) ipDigitString.length();
        for (byte i = 0; i < numDigits; i++) {
            byte maxChecks = 3;
            byte digitsRemaining = (byte) (numDigits - i + 1);
            if (digitsRemaining <= 3) {
                maxChecks = digitsRemaining;
            }
            for (byte j = 1; j <= maxChecks; j++) {
                String digitSubstring = ipDigitString.substring(i, Math.min(i + j, numDigits));
                boolean isContinuouslyInvalid = isContinuouslyInvalid(digitSubstring);
                if (isContinuouslyInvalid) {
                    break;
                }
                boolean isValidSubstring = validSubstring(digitSubstring);
                if (isValidSubstring) {
                    byte substringLength = (byte) digitSubstring.length();
                    Map<Byte, String> slotStrings = indexLengthMap.get(i);
                    if (slotStrings == null) {
                        slotStrings = new HashMap<>();
                    }
                    slotStrings.put(substringLength, digitSubstring);
                    indexLengthMap.put(i, slotStrings);
                }
            }
        }
        // O(1). Maximum fixed at 81 or 3^4.
        List<List<Byte>> pathsToEnd = new ArrayList<>();
        for (byte i = 3; i >= 1; i--) {
            byte index = 0;
            if (!existsInMap(indexLengthMap, index, i)) continue;
            for (byte j = 3; j >= 1; j--) {
                index = i;
                if (!existsInMap(indexLengthMap, index, j)) continue;
                for (byte k = 3; k >= 1; k--) {
                    index = (byte) (i + j);
                    if (!existsInMap(indexLengthMap, index, k)) continue;
                    for (byte l = 3; l >= 1; l--) {
                        index = (byte) (i + j + k);
                        if (!existsInMap(indexLengthMap, index, l))
                            continue;
                        boolean addsToTotal = i + j + k + l == numDigits;
                        if (addsToTotal) {
                            List<Byte> path = List.of(i, j, k, l);
                            pathsToEnd.add(path);
                        }
                    }
                }
            }
        }
        List<String> possibleIps = new ArrayList<>();
        for (List<Byte> path : pathsToEnd) {
            byte[] slotPositions = getPositionMap(path);
            String[] slotPositionDigits = getDigitMap(indexLengthMap, slotPositions, path);
            String fixedIpAddress = String.join(".", slotPositionDigits);
            possibleIps.add(fixedIpAddress);
        }
        return possibleIps;
    }

    private static String[] getDigitMap(Map<Byte, Map<Byte, String>> indexLengthMap,
                                        byte[] slotPositions,
                                        List<Byte> path) {
        String[] digitMap = new String[4];
        for (int i = 0; i < 4; i++) {
            digitMap[i] = indexLengthMap.get(slotPositions[i]).get(path.get(i));
        }
        return digitMap;
    }

    private static byte[] getPositionMap(List<Byte> path) {
        byte slotOnePosition = 0;
        byte slotTwoPosition = (byte) (path.get(0) + slotOnePosition);
        byte slotThreePosition = (byte) (path.get(1) + slotTwoPosition);
        byte slotFourPosition = (byte) (path.get(2) + slotThreePosition);
        return new byte[] {slotOnePosition,
                           slotTwoPosition,
                           slotThreePosition,
                           slotFourPosition};
    }

    private static boolean existsInMap(Map<Byte, Map<Byte, String>> indexLengthMap, byte index, byte length) {
        boolean containsSlot = indexLengthMap.containsKey(index) && indexLengthMap.get(index).containsKey(length);
        return containsSlot;
    }


    public static boolean isContinuouslyInvalid(String substring) {
        return substring.length() > 1 && substring.charAt(0) == '0';
    }

    public static boolean validSubstring(String digitSubstring) {
        if (digitSubstring.length() > 3 || digitSubstring.isEmpty()) {
            return false;
        }
        final String leadingZerosRegex = "[0]+[\\d]+";
        if (digitSubstring.matches(leadingZerosRegex) && !digitSubstring.equals("0")) {
            return false;
        }
        short digitSum = Short.parseShort(digitSubstring);
        return digitSum <= 255 && digitSum >= 0;
    }
}