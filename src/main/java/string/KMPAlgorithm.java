package main.java.string;

/**
 * Created by Joseph on 2017/11/14.
 *
 * KMP算法:
 *  当2个字符串匹配时，穷举法步骤是两个指针 i, j 分别指向原串和模式串，当 2 个字符相同时指针分别往后移动，但若出现字符不匹配时，
 *  i 指针回到原来的 i+1 位置，j 指针则回到模式串的第 0 位置，继续匹配，这样做的效率太低，O(n*m)
 *  并且浪费了原有一部分字符串匹配的事实（因为 i 和 j 指针都同时移动了 n 位)
 *  KMP算法就是解决只回退 j 指针，不回退 i 指针，大大降低时间复杂度，O(n+m)，利用了原有一部分字符串匹配的事实，
 *  将 j 指针尽量回退到一个有效位置
 *
 * 关于next数组求解算法可看:
 *   @see_https://www.cnblogs.com/tangzhengyue/p/4315393.html
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        String resource = "BBC ABCDAB ABCDABCDABDE";
        String target = "ABCDABD";

        char[] strText = resource.toCharArray();
        char[] strTarget = target.toCharArray();

        int[] next = calcNextArr(strTarget);

        int s = -1;
        for (int i = 0, j = 0; i < strText.length && j < strTarget.length; ){
            if (strText[i] == strTarget[j]){
                if (s == -1){
                    s = i;
                    if (j != 0){
                       s = i - j;
                    }
                }
                ++i;
                if (j++ == strTarget.length-1){
                    System.out.println("target串匹配成功，下标："+s+" "+(s+strTarget.length-1));
                }
            }
            else {
                s = -1;
                // 当某个字符不匹配了，i不回退，只回退j，j回退位置由next数组得出
                j = next[j];
                if (j == -1){
                    // j是-1模式串不能再向前回溯了，原串向后移动
                    i++; j++;
                }
            }
        }
    }

    private static int[] calcNextArr(char[] target){
        assert (null == target) : "target array must not be null!";
        assert (target.length < 1) : "target array must have elements!";

        int k = -1, j = 0;
        int[] next = new int[target.length];
        next[0] = -1;

        // 假设前j位已求出，求j+1位
        for ( ; j < target.length - 1; ){
            if (k == -1 || target[k] == target[j]){
                // 如果j+1已失配情况下，k+1等于j+1的话，即使回退到k+1匹配还是失败，所以干脆回到next[k+1]
                if (target[k+1] == target[j+1]){
                    next[++j] = next[++k];
                }
                else {
                    next[++j] = ++k;
                }
            }
            else {
                k = next[k];
            }
        }

        return next;
    }

}
