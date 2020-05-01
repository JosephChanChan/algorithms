import java.util.Scanner;

/**
 * @author
 * @since
 *
 * 美团实习笔试
 *
 *
 */
public class Main2 {

    public static void main(String[] args) {
        Ali2 main2 = new Ali2();
        /*main2.calc();*/
        int a = -127, b = 1;
        for (int i = 0; i < 32; i++) {
            System.out.println(a & b);
            b = b << 1;
        }
    }

    private void calc() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String number = scanner.nextLine();
            String number1 = number;
            boolean negative = false;
            if (number.contains("-")) {
                negative = true;
                number1 = number.substring(1);
            }
            // 判断什么数
            StringBuilder builder;
            if (number.contains(".")) {
                builder = calcDecimal(number1);
            }
            else {
                builder = calcInt(number1);
            }
            if (negative) {
                builder.insert(0, "(");
                builder.append(")");
            }
            System.out.println(builder.toString());
        }
    }

    private StringBuilder calcInt(String number) {
        StringBuilder builder = calcComma(number);
        builder.append(".00");
        builder.insert(0, "$");
        return builder;
    }

    private StringBuilder calcDecimal(String number) {
        int min = Math.min(number.indexOf(".") + 3, number.length());
        number = number.substring(0, min);
        String intNum = number.substring(0, number.indexOf("."));
        StringBuilder builder = calcComma(intNum);
        String decimalNum = number.substring(number.indexOf("."), min);
        if (decimalNum.length() < 3) {
            decimalNum += "0";
        }
        builder.append(decimalNum);
        builder.insert(0, "$");
        return builder;
    }


    private StringBuilder calcComma(String num) {
        char[] chars = num.toCharArray();
        StringBuilder builder = new StringBuilder();
        int c = 1;
        for (int i = chars.length-1; i >= 0; i--) {
            builder.append(chars[i]);
            if (c >= 3 && c % 3==0) {
                builder.append(",");
            }
            c++;
        }
        if (builder.charAt(builder.length()-1) == ',') {
            builder.deleteCharAt(builder.length()-1);
        }
        builder.reverse();
        return builder;
    }


}
