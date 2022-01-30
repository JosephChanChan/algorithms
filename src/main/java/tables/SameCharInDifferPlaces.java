package tables;

import java.util.*;

/**
 * @author Joseph
 * @since 2021-02-28 21:36
 */
public class SameCharInDifferPlaces {


    /*
        一旦需要将元素进行按特征归类，就用hash
        所有字母异位词都具有相同数量相同字符的特征
        将特征建模成Term类，复写hashCode & equals 方法
     */
    List<List<String>> term(String[] strs) {
        List<List<String>> ans = new LinkedList<>();

        Map<Term, List<String>> map = new HashMap<>();
        for (String term : strs) {
            Term t = new Term(term);
            List<String> list = null;
            if (!map.containsKey(t)) {
                list = new ArrayList<>();
                map.put(t, list);
                ans.add(list);
            }
            else {
                list = map.get(t);
            }
            list.add(term);
        }
        return ans;
    }

    class Term {
        int count ;
        int[] c = new int[26];

        public Term(String term) {
            this.count = term.length();
            for (int i = 0; i < term.length(); i++) {
                this.c[term.charAt(i)-'a']++;
            }
        }

        public int hashCode() {
            int hash = 0;
            for (int i = 0; i < c.length; i++) {
                if (c[i] > 0) {
                    hash += c[i] * 31;
                }
            }
            return hash;
        }

        public boolean equals(Object obj) {
            Term t = (Term) obj;
            if (t.count != this.count) return false;
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 0) continue;
                if (t.c[i] != this.c[i]) return false;
            }
            return true;
        }
    }

    /*
        一旦需要将元素进行按特征归类，就用hash
        字母异位词有什么特征？
        eat or ate or tea
        都有相同数量的
        如果将字符按字典序排列后，全部异位词都会变为同个单词 a e t 3个字符
        所以字母异位词都是具有相同数量的相同字符的随机排列
    */
    List<List<String>> hash(String[] strs) {
        List<List<String>> ans = new LinkedList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String term : strs) {
            char[] c = term.toCharArray();
            Arrays.sort(c);
            List<String> list = map.getOrDefault(new String(c), new ArrayList<>());
            list.add(term);
            if (list.size() == 1) {
                ans.add(list);
                map.put(new String(c), list);
            }
        }
        return ans;
    }
}
