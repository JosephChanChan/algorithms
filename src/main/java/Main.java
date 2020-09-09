import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static int n, m ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        int[] a = new int[n];
        Int[] copy = new Int[n];
        int i ;
        for (i = 0; i < a.length; i++) {
            a[i] = scanner.nextInt();
            copy[i] = new Int(a[i]);
        }

        Arrays.sort(copy, ((o1, o2) -> o1.v - o2.v));

        // old -> newIdx
        Map<Integer, Integer> map = new HashMap<>();
        for (i = 0; i < a.length; i++) {
            for (int j = 0; j < copy.length; j++) {
                if (a[i] == copy[j].v) map.put(a[i], j);
            }
        }

        if (n == 0) System.out.println(0);
        else if (n == 2) {
            System.out.println(copy[1]);
            System.out.println(copy[0]);
        }

        int l = 0, r = a.length - 2;
        for (i = 0; i < a.length; i++) {
            // 删除i，中位数是？
            // 二分判断，每次二分得到m
            int m = (l + r) >> 1;
            Integer newIdx = map.get(a[i]);
            if (m < newIdx) {
                System.out.println(copy[m]);
            }
            else if (m > newIdx) {
                System.out.println(copy[m + 1]);
            }
            else {
                System.out.println(copy[m]);
            }
        }
    }

    static class Int {
        int v;

        @Override
        public boolean equals(Object obj) {
            Int i = (Int) obj;
            return (i == this);
        }

        public Int(int i) {
            this.v = i;
        }
    }
}
