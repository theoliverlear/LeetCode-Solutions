//=================================-Imports-==================================
import java.lang.StringBuilder;
import java.math.BigInteger;
//=================================-Solution-=================================
public class Solution {
    //--------------------------Add-Two-Numbers-------------------------------
    public ListNode addTwoNumbers(ListNode nodeOne, ListNode nodeTwo) {
        String nodeOneNumbers = getNodeNumbers(nodeOne);
        String nodeTwoNumbers = getNodeNumbers(nodeTwo);
        BigInteger numSum = addBigStrings(nodeOneNumbers, nodeTwoNumbers);
        StringBuilder numSumStringReversed = new StringBuilder(String.valueOf(numSum));
        numSumStringReversed.reverse();
        ListNode reversedNums = new ListNode();
        ListNode head = new ListNode();
        for (int charIndex = 0; charIndex < numSumStringReversed.length(); charIndex++) {
            char currentChar = numSumStringReversed.charAt(charIndex);
            String indexString = String.valueOf(currentChar);
            int indexValue = Integer.parseInt(indexString);
            reversedNums.val = indexValue;
            if (isSingleDigitString(numSumStringReversed)) {
                head = reversedNums;
                addEndingNode(reversedNums);
                break;
            }
            if (isLastChar(charIndex, numSumStringReversed)) {
                addEndingNode(reversedNums);
            } else {
                reversedNums.next = new ListNode();
                boolean isStartChar = charIndex == 0;
                if (isStartChar) {
                    head = reversedNums;
                }
                reversedNums = setNodeToNext(reversedNums);
            }
        }
        return head;
    }
    //--------------------------Set-Node-To-Next------------------------------
    private static ListNode setNodeToNext(ListNode reversedNums) {
        reversedNums = reversedNums.next;
        return reversedNums;
    }
    //--------------------------Add-Ending-Node-------------------------------
    private static void addEndingNode(ListNode reversedNums) {
        reversedNums.next = null;
    }
    //----------------------------Is-Last-Char--------------------------------
    private static boolean isLastChar(int index, StringBuilder numSumStringReversed) {
        return index == numSumStringReversed.length() - 1;
    }
    //-----------------------Is-Single-Digit-String---------------------------
    private static boolean isSingleDigitString(StringBuilder numSumStringReversed) {
        return numSumStringReversed.length() == 1;
    }
    //--------------------------Add-Big-Strings-------------------------------
    private static BigInteger addBigStrings(String nodeOneNumbers, String nodeTwoNumbers) {
        BigInteger nodeOneNumber = new BigInteger(nodeOneNumbers);
        BigInteger nodeTwoNumber = new BigInteger(nodeTwoNumbers);
        BigInteger numSum = nodeOneNumber.add(nodeTwoNumber);
        return numSum;
    }
    //--------------------------Get-Node-Numbers------------------------------
    private static String getNodeNumbers(ListNode nodeOne) {
        StringBuilder nodeOneNumbers = new StringBuilder();
        while (nodeOne != null) {
            String number = String.valueOf(nodeOne.val);
            nodeOneNumbers.insert(0, nodeOne.val);
            nodeOne = setNodeToNext(nodeOne);
        }
        return nodeOneNumbers.toString();
    }
}
//=============================-LeetCode-Models-==============================

//---------------------------------List-Node----------------------------------
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}