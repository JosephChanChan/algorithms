
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Joseph
 * @since 2020-01-12 19:08
 */
public class BitMapTest {


    public static void main(String[] args) {
        /*int[] arr = {
                9, 6, 5, 4, 18, 76, 28, 99, 72, 27,
                99, 64, 28, 39, 71, 28, 47, 28, 85, 94
        };

        Map<Integer, Integer> occurrenceNum = new HashMap<>(arr.length);

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            occurrenceNum.put(arr[i], 0);
        }

        BitMap bitMap = new BitMap(max);

        for (int j = 0; j < arr.length; j++) {
            if (bitMap.contains(arr[j])) {
                occurrenceNum.put(arr[j], occurrenceNum.get(arr[j]) + 1);
            }
            else {
                bitMap.put(arr[j]);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = occurrenceNum.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.println(entry.getKey() + " ::: " + entry.getValue());
        }*/
        BitSet bitSet = new BitSet();
        bitSet.set(12);
        System.out.println(bitSet.get(12));
        bitSet.set(100000000);
        System.out.println(bitSet.get(100000000));
    }



}
