package binary.search;

/**
 * lc 69 easy
 *
 * @author Joseph
 * @since 2020-08-18 11:02
 */
public class Sqrt {

    public static void main(String[] args) {
        Sqrt test = new Sqrt();
        int x = 24;
        int i = test.newtonLogic(x);
        System.out.println(Math.sqrt(x));
        System.out.println(i);
    }

    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 2) return 1;
        return binarySearch(x);
    }

    private int binarySearch(int target) {
        int left = 0, right = target, ans = target;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int square = mid * mid;
            if (square == target) {
                return mid;
            }
            else if (square > target) {
                right = mid - 1;
            }
            else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    private int newtonLogic(double x) {
        /*
            牛顿迭代法
            xi+1 = (xi + c/xi)/2
         */
        double c = x, x1 = x;
        while (true){
            x = (x + c / x) / 2;
            if (Math.abs(x1 - x) < 1e-7) return (int) x;
            x1 = x;
        }
    }
}
