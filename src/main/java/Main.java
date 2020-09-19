import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> ans = new LinkedList<>();
    static int left = 1000, right = 3999;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line  = scanner.nextLine();
        char[] chars = line.toCharArray();
        int length = 3;

        List<Integer> nums = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (isNum(chars[i])) {
                builder.append(chars[i]);
            }
            else {
                if (builder.length() == 4) {
                    nums.add(Integer.parseInt(builder.toString()));
                }
                builder = new StringBuilder();
            }
        }
        if (builder.length() == 4) {
            nums.add(Integer.parseInt(builder.toString()));
        }
        for (Integer n : nums) {
            if (n >= left && n <= right) {
                ans.add(n);
            }
        }
        commit();
    }

    private static boolean isNum(char c) {
        return (c >= '0' && c <= '9');
    }

    private static void commit() {
        if (ans.size() == 0) {
            System.out.print("");
            return;
        }
        ans = ans.stream().distinct().collect(Collectors.toList());
        for (int i = 0; i < ans.size(); i++) {
            if (i == ans.size()-1) {
                System.out.print(ans.get(i));
            }
            else {
                System.out.print(ans.get(i)+" ");
            }
        }
    }
}
