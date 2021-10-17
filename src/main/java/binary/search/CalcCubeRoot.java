package binary.search;

/**
 * 计算一个float数的立方根，float可以是负数、正数
 *
 * @author Joseph
 * @since 2021-07-06 10:19
 */
public class CalcCubeRoot {

    float precision = 0.000000001f;

    public static void main(String[] args) {
        CalcCubeRoot m = new CalcCubeRoot();
        float ans = m.binary(-9.0f);
        System.out.println(ans);
    }

    float binary(float a) {
        boolean neg = a < 0;

        if (neg) a = -a;

        float l = 0.0f, r = a;

        while (l < r) {
            float m = (r-l)/2.0f+l;
            System.out.println("m is "+m);
            float cube = (m*m*m);
            if (Math.abs(cube-a) <= precision) return neg ? -m : m;
            if (cube < a) {
                l = m;
                System.out.println("cube less is "+cube);
            }
            else {
                r = m;
                System.out.println("cube greater is "+cube);
            }
        }
        if (Math.abs((l*l*l)-a) <= precision) return neg ? -l : l;
        return neg ? -r : r;
    }

}
