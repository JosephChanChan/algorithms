package dynamic.programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 还是找到一个最大的单增子序列，不过这次时间复杂度要求 O(n * logn)
 * //5 1 6 8 2 4 5 10
 * 算法原理: https://www.cnblogs.com/nbalive2001/p/4773131.html
 * <p>
 * Created by Joseph on 2017/11/17.
 */
public class LongestIncreasingSubsequence2 {

    static List<Integer> b = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 1 << 16);
        int n;
        int[] arr = null;
        n = Integer.parseInt(reader.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        //代表第1位负无穷
        b.add(-1);

        for (int i = 0; i < arr.length; i++) {
            //考虑把a[i]接到b数组中某个位置如x。x = max{b[x] < a[i]} b[x+1] = a[i]
            int x = binarySearchXinB(arr[i]);
            if (x == b.size() - 1) {
                b.add(arr[i]);
            } else {
                b.set(++x, arr[i]);
            }
        }

//        System.out.println("最大单增子序列长度为 "+b.size());
        System.out.println(b.size() - 1);
//        for (int j=0; j<b.size(); j++){
//            System.out.print(b.get(j)+" ");
//        }

    }

    private static int binarySearchXinB(int key) {
        if (b.size() == 1) {
            return 0;
        }

        int x;
        int top = 0, tail = b.size() - 1, mid = (top + tail) >> 1;

        x = findIndex(key, top, tail, mid);
        return x;
    }

    private static int findIndex(int key, int top, int tail, int mid) {
        while (top <= tail) {
            if (mid == 0) {
                top = ++mid;
            } else if (key > b.get(mid)) {
                top = ++mid;
            } else if (key < b.get(mid)) {
                tail = --mid;
            } else {
                break;
            }
            mid = (top + tail) >> 1;
        }

        if (mid > 0 && key <= b.get(mid)) {
            mid--;
        }
        return mid;
    }

}
