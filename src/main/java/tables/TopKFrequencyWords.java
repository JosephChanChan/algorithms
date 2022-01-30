package tables;

import java.util.*;

/**
 * lc 692 medium
 *
 * Analysis:
 *  这题Tag是TrieTree，但是用Trie树做会发现编码很麻烦，建完树统计完单词数量后，要写dfs遍历树取TopK。
 * 还不如直接用hash统计单词数，然后用堆取TopK。这题最后一个trick在于堆内序和输出要求不同，要想办法翻转一下。
 *
 * 时间复杂度：O(n*logK)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-02-05 11:27
 */
public class TopKFrequencyWords {

    Queue<Word> q ;
    Map<String, Integer> map = new HashMap<>();

    public List<String> topKFrequent(String[] words, int k) {
        q = new PriorityQueue<>(k, new MComp());
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            q.add(new Word(e.getValue(), e.getKey()));
            if (q.size() > k) q.poll();
        }
        List<String> ans = new ArrayList<>(k);
        while (!q.isEmpty()) {
            ans.add(q.poll().word);
        }
        // 堆内顺序是：按频率升序，频率相同按字典降序
        // 输出要求：按频率降序，频率相同按字典升序
        Collections.reverse(ans);
        return ans;
    }

    private class Word {
        int count = 0;
        String word ;
        public Word(int count, String word) {
            this.count = count;
            this.word = word;
        }
    }

    private class MComp implements Comparator<Word> {
        public int compare(Word w1, Word w2) {
            if (w1.count != w2.count) {
                return w1.count - w2.count;
            }
            // 字典序大的放到前面去
            return w2.word.compareTo(w1.word);
        }
    }


    /*
        第2种解法。速度更慢
        先统计单词次数并且分组并且维护所有次数的值。
        从高到低选出现次数高的值，拿对应出现次数的组内单词。
     */
    Queue<Integer> q2 = new PriorityQueue<>((o1, o2) -> o2-o1);
    Map<String, Integer> h = new HashMap();
    Map<Integer, Set<String>> h2 = new HashMap();

    public List<String> topKFrequent2(String[] words, int k) {
        for (String t : words) {
            int f = h.getOrDefault(t, 0);
            h.put(t, f+1);

            Set<String> now = h2.getOrDefault(f+1, new HashSet());
            now.add(t);
            h2.put(f+1, now);

            if (f > 0) {
                Set<String> old = h2.getOrDefault(f, new HashSet());
                old.remove(t);
            }

            q2.add(f+1);
        }
        List<String> ans = new ArrayList();
        for (int i = 0; i < k; ) {
            int f = q2.poll();
            if (h2.containsKey(f)) {
                Set<String> g = h2.get(f);
                List<String> list = new ArrayList(g.size());
                list.addAll(g);
                Collections.sort(list, (o1, o2) -> o1.compareTo((String)o2));

                for (int j = i, p = 0; p < list.size() && j < k; j++, p++) {
                    ans.add(list.get(p));
                    i++;
                }

                h2.remove(f);
            }
        }
        return ans;
    }
}
