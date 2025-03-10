//=================================-Imports-==================================
import java.lang.StringBuilder;
import java.math.BigInteger;
//=================================-Solution-=================================
public class Solution {
    public ListNode addTwoNumbers(ListNode nodeOne, ListNode nodeTwo) {
        StringBuilder nodeOneNumbers = new StringBuilder();
        while (nodeOne != null) {
            String number = String.valueOf(nodeOne.val);
            nodeOneNumbers.insert(0, nodeOne.val);
            nodeOne = nodeOne.next;
        }
        StringBuilder nodeTwoNumbers = new StringBuilder();
        while (nodeTwo != null) {
            String number = String.valueOf(nodeTwo.val);
            nodeTwoNumbers.insert(0, nodeTwo.val);
            nodeTwo = nodeTwo.next;
        }
        BigInteger nodeOneNumber = new BigInteger(nodeOneNumbers.toString());
        BigInteger nodeTwoNumber = new BigInteger(nodeTwoNumbers.toString());
        BigInteger numSum = nodeOneNumber.add(nodeTwoNumber);
        StringBuilder numSumStringReversed = new StringBuilder(String.valueOf(numSum));
        numSumStringReversed.reverse();
        ListNode reversedNums = new ListNode();
        ListNode head = new ListNode();
        for (int charIndex = 0; charIndex < numSumStringReversed.length(); charIndex++) {
            String indexString = String.valueOf(numSumStringReversed.charAt(charIndex));
            int indexValue = Integer.parseInt(indexString);
            reversedNums.val = indexValue;
            if (numSumStringReversed.length() == 1) {
                head = reversedNums;
                reversedNums.next = null;
                break;
            }
            if (charIndex == numSumStringReversed.length() - 1) {
                reversedNums.next = null;
            } else {
                reversedNums.next = new ListNode();
                if (charIndex == 0) {
                    head = reversedNums;
                }
                reversedNums = reversedNums.next;
            }
        }
        return head;
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