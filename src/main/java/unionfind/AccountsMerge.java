package unionfind;

import java.util.*;

/**
 * lc 721 medium
 *
 * Analysis:
 *  将同一个账户的邮箱分在一个集合内，并维护一个集合内的邮箱。
 * 遍历所有集合的根，同时拿出该集合内所有邮箱，拼出答案返回。
 *
 * n是不同邮箱地址数量
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2021-07-05 20:35
 */
public class AccountsMerge {

    // j1 -> [j1, j2...]
    Map<String, TreeSet<String>> roots = new HashMap();
    // child -> parent
    Map<String, String> parents = new HashMap();
    // 根归属账户
    Map<String, String> belong = new HashMap();

    List<List<String>> ans = new ArrayList();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 初始化根归属账户
        for (List<String> list : accounts) {
            for (int i = 1; i < list.size(); i++) {
                belong.put(list.get(i), list.get(0));

                TreeSet childs = roots.getOrDefault(list.get(i), new TreeSet());
                childs.add(list.get(i));
                roots.put(list.get(i), childs);

                parents.put(list.get(i), list.get(i));
            }
        }

        for (List<String> list : accounts) {
            for (int i = 1; i < list.size()-1; i++) {
                union(list.get(i), list.get(i+1));
            }
        }

        // 相同账户的邮件都归属为同一个集合
        for (Map.Entry<String, TreeSet<String>> e : roots.entrySet()) {
            String root = e.getKey();
            TreeSet<String> childs = e.getValue();
            List<String> g = new ArrayList(childs.size() + 1);
            g.add(belong.get(root));
            g.addAll(childs);
            ans.add(g);
        }
        return ans;
    }

    String find(String a) {
        if (parents.get(a).equals(a)) return a;

        String root = find(parents.get(a));
        parents.put(a, root);
        return root;
    }

    void union(String a, String b) {
        String p = find(a);
        String q = find(b);
        if (p.equals(q)) return;

        // p is root for q
        parents.put(q, p);

        // 合并q和p的子树
        TreeSet<String> childsQ = roots.get(q);
        if (null != childsQ && childsQ.size() > 0) {
            roots.get(p).addAll(childsQ);
            roots.remove(q);
        }
    }
}
