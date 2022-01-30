package string;

/**
 * lc 12 medium
 *
 * Analysis:
 * 两种方法都是常数时间和空间。因为题目限定了num范围在[1,3999]
 *
 * 时间复杂度：O(1)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-03 21:52
 */
public class IntegerToRoman {

    public String intToRoman(int num) {
        return null;
    }

    String goodMethod(int num) {
        /*
            结合题目给的7个基本字符映射，结合特殊数字4 9一共有13种case
            对于数字num，从大到小判断是否有字符可以满足映射它的一部分数字
            例如 3542 可以先用M 满足1000，2542再用M 满足 1000 以此类推直到千位的数字被满足
            542，可以有D直接满足500
            42，不满50，但是可以用X满足10，所以是4个连续的X
        */
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] chars = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        String ans = "";
        for (int i = 0; num > 0; ) {
            if (num >= nums[i]) {
                ans += chars[i];
                num -= nums[i];
                continue;
            }
            i++;
        }
        return ans;
    }

    String digitApart(int num) {
        /*
            数位分离思想，先将每个数字和存在数位数组，单独处理个位、十位、百位、千位
            对特殊数字计算，4 9
        */
        String n = String.valueOf(num);
        int[] d = new int[n.length()];
        for (int i = 0; num > 0; i++) {
            d[i] = num%10;
            num /= 10;
        }

        String ans = "";
        for (int i = d.length-1; i >= 0; i--) {
            ans += mapping(d[i], (int) Math.pow(10, i));
        }
        return ans;
    }

    String mapping(int num, int d) {
        if (num == 0) return "";

        if (num == 4) {
            num *= d;
            switch(num) {
                case 4: return "IV";
                case 40: return "XL";
                case 400: return "CD";
            }
        }
        if (num == 9) {
            num *= d;
            switch(num) {
                case 9: return "IX";
                case 90: return "XC";
                case 900: return "CM";
            }
        }
        num *= d;
        switch(num) {
            case 1: return "I";
            case 5: return "V";
            case 10: return "X";
            case 50: return "L";
            case 100: return "C";
            case 500: return "D";
            case 1000: return "M";
        }
        if (num < 5) {
            if (num == 2) return "II";
            if (num == 3) return "III";
        }
        else if (num < 10) {
            if (num == 6) return "VI";
            if (num == 7) return "VII";
            if (num == 8) return "VIII";
        }
        else if (num < 50) {
            if (num == 20) return "XX";
            if (num == 30) return "XXX";
        }
        else if (num < 100) {
            if (num == 60) return "LX";
            if (num == 70) return "LXX";
            if (num == 80) return "LXXX";
        }
        else if (num < 500) {
            if (num == 200) return "CC";
            if (num == 300) return "CCC";
        }
        else if (num < 1000) {
            if (num == 600) return "DC";
            if (num == 700) return "DCC";
            if (num == 800) return "DCCC";
        }
        else {
            if (num == 2000) return "MM";
            if (num == 3000) return "MMM";
        }
        return "-1";
    }
}
