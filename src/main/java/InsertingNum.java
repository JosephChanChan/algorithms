/**
 * @author Joseph
 * @since 2020-01-09 15:27
 */
public class InsertingNum {


    public static void main(String[] args) {
        InsertingNum insertingNum = new InsertingNum();
        int solution = insertingNum.solution(-261);
        System.out.println(solution);
    }

    public int solution(int N) {
        if (N == 0) return 50;

        if (N > 0) {
            return positiveCalc(N);
        }
        else {
            return negativeCalc(N);
        }
    }

    private int positiveCalc(int n) {
        // 千、百、十、个  尽量让前面的位数大
        StringBuilder builder = new StringBuilder();

        char[] chars = String.valueOf(n).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = Integer.parseInt(String.valueOf(chars[i]));
            if (5 > j) {
                builder.append(5).append(j);
            }
            else {
                builder.append(j);
            }
        }

        return Integer.parseInt(builder.toString());
    }

    private int negativeCalc(int n) {
        // 千、百、十、个  尽量让前面的位数小
        StringBuilder builder = new StringBuilder();

        boolean check = false;

        char[] chars = String.valueOf(Math.abs(n)).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = Integer.parseInt(String.valueOf(chars[i]));
            if (!check && 5 < j) {
                check = true;
                builder.append(5).append(j);
            }
            else {
                builder.append(j);
            }
        }

        return Math.negateExact(Integer.parseInt(builder.toString()));
    }

}
