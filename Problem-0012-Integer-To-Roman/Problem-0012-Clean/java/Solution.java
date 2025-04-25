//=================================-Solution-=================================
public class Solution {
    //----------------------------Int-To-Roman--------------------------------
    public String intToRoman(int num) {
        return new RomanNumeral(num).getRomanNumeral();
    }
}
//==============================-Roman-Numeral-===============================
class RomanNumeral {
    int baseNum;
    String romanNumeral;
    //===========================-Constructors-===============================
    public RomanNumeral(int num) {
        this.baseNum = num;
        this.romanNumeral = this.intToRoman(num);
    }
    //=============================-Methods-==================================

    //----------------------------Int-To-Roman--------------------------------
    public String intToRoman(int num) {
        return this.intToRoman(num, "");
    }
    //----------------------------Int-To-Roman--------------------------------
    public String intToRoman(int num, String romanNum) {
        if (num == 0) {
            return romanNum;
        }
        if (this.isSubtractForm(num)) {
            int subtractNum = this.getSubtractNum(num);
            romanNum += this.getSubtractFormRomanNum(subtractNum);
            num -= subtractNum;
            romanNum = this.intToRoman(num, romanNum);
            return romanNum;
        }
        int nearestBase = this.getNearestBase(num);
        String nextRomanNum = this.getRomanNumFromBase(nearestBase);
        num -= nearestBase;
        romanNum += nextRomanNum;
        romanNum = this.intToRoman(num, romanNum);
        return romanNum;
    }
    //-------------------Get-Subtract-Form-Roman-Number-----------------------
    public String getSubtractFormRomanNum(int num) {
        int subtractNum = this.getSubtractNum(num);
        return switch (subtractNum) {
            case 900 -> "CM";
            case 400 -> "CD";
            case 90 -> "XC";
            case 40 -> "XL";
            case 9 -> "IX";
            case 4 -> "IV";
            default -> throw new IllegalArgumentException("Invalid subtract num: " + num);
        };
    }
    //------------------------Get-Subtract-Number-----------------------------
    public int getSubtractNum(int num) {
        if (num >= 900) {
            return 900;
        } else if (num >= 400) {
            return 400;
        } else if (num >= 90) {
            return 90;
        } else if (num >= 40) {
            return 40;
        } else if (num >= 9) {
            return 9;
        } else if (num >= 4) {
            return 4;
        } else {
            throw new IllegalArgumentException("Invalid Subtract Num: " + num);
        }
    }
    //--------------------------Is-Subtract-Form------------------------------
    public boolean isSubtractForm(int num) {
        String numString = String.valueOf(num);
        int firstNum = Integer.parseInt(String.valueOf(numString.charAt(0)));
        return firstNum == 4 || firstNum == 9;
    }
    //---------------------Get-Roman-Number-From-Base-------------------------
    public String getRomanNumFromBase(int base) {
        return switch (base) {
            case 1000 -> "M";
            case 500 -> "D";
            case 100 -> "C";
            case 50 -> "L";
            case 10 -> "X";
            case 5 -> "V";
            case 1 -> "I";
            default -> throw new IllegalArgumentException("Invalid base: " + base);
        };
    }
    //--------------------------Get-Nearest-Base------------------------------
    public int getNearestBase(int num) {
        if (num >= 1000) {
            return 1000;
        } else if (num >= 500) {
            return 500;
        } else if (num >= 100) {
            return 100;
        } else if (num >= 50) {
            return 50;
        } else if (num >= 10) {
            return 10;
        } else if (num >= 5) {
            return 5;
        } else {
            return 1;
        }
    }
    //=============================-Getters-==================================
    public String getRomanNumeral() {
        return this.romanNumeral;
    }
}