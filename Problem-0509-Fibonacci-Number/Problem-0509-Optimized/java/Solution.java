//=================================-Imports-==================================
import java.util.HashMap;
import java.util.Map;

//=================================-Solution-=================================
public class Solution {
    //-----------------------------Fibonacci----------------------------------
    public int fib(int fibNum) {
        return this.recursiveFibonacci(fibNum);
    }
    //------------------------Recursive-Fibonacci-----------------------------
    public int recursiveFibonacci(int fibNum) {
        if (fibNum == 0 || fibNum == 1) {
            return fibNum;
        }
        Map<Integer, Integer> previousFibs = new HashMap<>();
        int currentFib = this.recursiveFibonacci(fibNum - 1, previousFibs) +
                         this.recursiveFibonacci(fibNum - 2, previousFibs);
        return currentFib;
    }
    //------------------------Recursive-Fibonacci-----------------------------
    public int recursiveFibonacci(int fibNum,
                                  Map<Integer, Integer> previousFibs) {
        if (fibNum == 0 || fibNum == 1) {
            return fibNum;
        }
        if (previousFibs.containsKey(fibNum)) {
            return previousFibs.get(fibNum);
        }
        int currentFib = this.recursiveFibonacci(fibNum - 1, previousFibs) +
                         this.recursiveFibonacci(fibNum - 2, previousFibs);
        previousFibs.put(fibNum, currentFib);
        return currentFib;
    }
}
