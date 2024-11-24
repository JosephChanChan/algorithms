package string;

/**
 * lc 334 easy
 *
 * Analysis:
 *  递归处理。从尾部递归，一直到数组头部时，将头部元素与在数组中首尾对应的元素交换。
 *  就是双指针法，头指针与尾指针结合交换元素。
 *  用循环遍历方式在代码实现上会更简单。
 *
 * 时间复杂度：O(n/2)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2019/8/31 20:04
 */
public class ReverseString {


    public static void main(String[] args) {
        String string = "Hello";
        char[] s = string.toCharArray();
        ReverseString reverseString = new ReverseString();
        reverseString.reverseString(s);
    }

    private void reverseString(char[] s) {
        doReverse(s.length - 1, s);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
        }
    }

    private void doReverse(int index, char[] s) {
        if (index < 0) {
            return;
        }
        doReverse(index-1, s);
        if (index >= s.length >> 1) {
            return;
        }
        swap(index, (s.length-1) - index, s);
    }

    private void swap(int i, int j, char[] s) {
        char item4Index = s[i];
        char beReplaced = s[j];
        s[j] = item4Index;
        s[i] = beReplaced;
    }


}
