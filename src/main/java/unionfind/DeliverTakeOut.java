package unionfind;

import java.util.*;

/**
 * 美团2020校招笔试
 *
 * Question Description:
 *  小团是美团外卖的区域配送负责人，众所周知，外卖小哥一般都会同时配送若干单，小团在接单时希望把同一个小区的单子放在一起，然后由一名骑手统一配送。
 * 但是由于订单是叠在一起的，所以，他归类订单时只能知道新订单和已有的某个订单的小区是相同的，他觉得这样太麻烦了，所以希望你帮他写一个程序解决这个问题。
 * 即给出若干个形如a b的关系，表示a号订单和b号订单是同一个小区的 ，请你把同一个小区的订单按照编号顺序排序，并分行输出，优先输出最小的订单编号较小的小区订单集合。
 * 订单的编号是1到n。(可能存在同时出现a b和b a这样的关系,也有可能出现a a这样的关系)
 *
 *  输入第一行是两个正整数n，m，表示接受的订单数量和已知的关系数量。(1<=n，m<=10000)
 * 接下来有m行，每行两个正整数a和b，表示a号订单和b号订单属于同一个小区(1<=a,b<=n);
 *
 * 输出第一行包含一个整数x，表示这些订单共来自x个不同的小区。
 * 接下来的输出包含x行，每行表示输出若干个订单编号，表示这些订单属于同一个小区，按照订单编号升序输出。优先输出订单编号较小的小区。
 * 样例输入
 * 5 5
 * 1 2
 * 2 2
 * 3 1
 * 4 2
 * 5 4
 * 样例输出
 * 1
 * 1 2 3 4 5
 *
 * Analysis:
 * 主要是用并查集思想，将有关系的订单都合并起来，最后的输出有点麻烦，遍历建成根数组标识每个订单所属的根，
 * 再遍历每个订单归并到所属的根中。用LinkedHashMap 记录最小的订单号所属的根，先插入linked中输出时就能满足 "优先输出订单编号较小的小区"
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-15 18:08
 */
public class DeliverTakeOut {

    static int n, m, count = 0;
    static int[] parent, height, root ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        // 父节点数组，下标是订单号，标识当前订单关联哪个父节点
        parent = new int[n + 1];
        height = new int[n + 1];
        int i ;
        for (i = 0; scanner.hasNextLine(); i++) {
            String line = scanner.nextLine();
            if (line.equals("")) continue;
            if (line.equals(" ")) break;
            String[] arr = line.split(" ");
            int a = Integer.parseInt(arr[0]);
            int b = Integer.parseInt(arr[1]);

            /*
                并查集，建完树后查看parent数组有多少个根
             */
            union(a, b, parent, height);
        }

        // O(nlogn) 建root[]
        root = new int[parent.length];
        for (i = 1; i < parent.length; i++) {
            if (parent[i] == 0) continue;
            root[i] = findRoot(i, parent);
        }

        List<Integer> list ;
        Map<Integer, List<Integer>> map = new LinkedHashMap<>(n);
        // 用linked记录订单号小的所属的根，优先插入容器中
        for (i = 1; i < root.length; i++) {
            if (root[i] == 0) {
                list = map.get(i);
            }
            else if (null == (list = map.get(root[i]))) {
                list = new LinkedList<>();
                map.put(root[i] == 0 ? i : root[i], list);
            }
            list.add(i);
        }

        System.out.println(map.size());
        map.forEach((key, value) -> {
            value.forEach(order -> System.out.print(order + " "));
            System.out.println();
        });
    }

    private static int findRoot(int node, int[] parent) {
        while (0 != parent[node]) {
            node = parent[node];
        }
        return node;
    }

    private static void union(int node1, int node2, int[] parent, int[] height) {
        if (node1 == node2) return;
        int root1 = findRoot(node1, parent);
        int root2 = findRoot(node2, parent);
        // 根相同代表在同一个集合
        if (root1 == root2) return;
        // 两个节点根都是自己 代表两个独立的节点
        if (root1 == node1 && root2 == node2) {
            parent[node1] = node2;
            height[node2]++;
        }
        else if (height[root1] > height[root2]) {
            parent[root2] = root1;
        }
        else {
            parent[root1] = root2;
        }
    }

}
