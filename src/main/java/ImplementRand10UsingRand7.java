/**
 * lc 470 medium
 *
 * Analysis:
 * 概率题
 *
 * 时间复杂度：O(1) 最坏 O(无穷)
 * 空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2021-04-15 14:05
 */
public class ImplementRand10UsingRand7 {

    public int rand10() {
        /*
            r7()-1 -> [0,6]         均匀概率
            [0,6]*7 -> [0,42]       均匀概率
            [0,42]+r7() -> [1,49]   均匀概率
            舍弃 [41,49]
            [1,40]%10 -> [0,9]      均匀概率
            [0,9]+1 -> [1,10]       均匀概率
        */
        int base = 49;
        while (base > 40) {
            base = (rand7()-1) * 7 + rand7();
        }
        // base [1, 40]
        return base % 10 + 1;
    }

    // mock
    int rand7() {
        return -1;
    }
}
