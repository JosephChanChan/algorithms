package greedy.algorithm;

import trees.BinaryHeap;

import java.io.*;

/**
 * Question Description:
 *  一位老木匠需要将一根长的木棒切成N段。每段的长度分别为L1,L2,......,LN（1 <= L1,L2,…,LN <= 1000，且均为整数）个长度单位。
 * 我们认为切割时仅在整数点处切且没有木材损失。
 * 木匠发现，每一次切割花费的体力与该木棒的长度成正比，不妨设切割长度为1的木棒花费1单位体力。
 * 例如：若N=3，L1 = 3,L2 = 4,L3 = 5，则木棒原长为12，木匠可以有多种切法，如：先将12切成3+9.，花费12体力，再将9切成4+5，
 * 花费9体力，一共花费21体力；还可以先将12切成4+8，花费12体力，再将8切成3+5，花费8体力，一共花费20体力。显然，后者比前者更省体力。
 * 那么，木匠至少要花费多少体力才能完成切割任务呢？
 *
 * Analysis:
 *  这个问题可以引出一个数据结构 哈夫曼树，这种树的构造涉及到贪心策略。
 *  先介绍一下哈夫曼树，
 *  给定n个权值作为n个叶子结点，构造一棵二叉树，若带权路径长度达到最小，称这样的二叉树为最优二叉树，也称为哈夫曼树(Huffman tree)。
 *  通常都被用来做一种最小代价编码。考虑一堆字符串 S{AAABBACCCDEEA}，将这堆字符串按0、1编码
 *  怎么编码能用最少的0、1位表示呢？
 *  哈夫曼算法编码出来的字符串已经被人证明是最优的。
 *  算法步骤：
 *  1、统计字符串中每个字符出现的频率，作为权值，得到一个元素集合
 *  2、在集合中每次选择 2 个权值最小的元素合并，合并的新节点为其2个元素的父节点，且权值为其和。
 *      合并的父节点为新节点重新归入集合中
 *  3、循环第 2 步，直到集合中只剩1个节点
 *  经过以上步骤形成一颗二叉树，为哈夫曼树。将树的左路径设为 0，右路径设为 1，从根出发到其每个叶子节点，
 *  中间的路径就是每个叶子节点的前缀码。如根 R 到叶子结点 A 有 3 条路径，分别为左右左，则前缀码为 010。
 *  将所有叶子结点按此方法计算可得出一个前缀码表。树的代价 WPL（Weighted Path Length of Tree） = 每个叶子节点的权值 * 该节点的路径长
 *
 *  那么这个问题为什么适用哈夫曼算法解决呢？
 *  可以将N个木块抽象看成 N 个带权值的元素。每个元素的权值就是其木块长度。
 *  这样建立的哈夫曼树就是最小代价的路径树，该树的所有非叶子结点恰好就是每次切割木头时所花费的体力。
 *  （可以自己画一下这棵树，然后模拟切割一下，就会发现。）
 *  因为所有非叶子结点都是代价最小的，自然按照这种顺序切割需要最少体力。
 *
 *  PS: 哈夫曼树不一定是二叉树，可以是多叉树，只需在算法中的第2步修改为选择n个最小权值元素。
 *  
 *  只需要证明哈夫曼树的最优性，该贪心策略即可得证。
 *  证明：
 *      由 W{W1,W2,W3 ... Wn} 这样一个集合构造成一颗哈夫曼树 H，同样这个集合构造成一颗最优的树 T。
 *  假设其中 W1，W2 的权值最小。按照算法，W1 W2会是 H 树中最深的 2 个叶子节点。如果 T 树中最深的2个叶子节点是 W3 W4。
 *  且 W1 <= W2 < W3 <= W4。由上面提到的 WPL 计算方法可知，H 树的 WPL < T 树的 WPL，与 T树的设定矛盾。
 *  所以 T 树中的最深 2个叶子节点只能是 W1 W2。按照此步骤可依次证明 T 树其余叶子结点与 H 树相同。
 *  哈夫曼树最优性得证。
 *
 * created by Joseph
 * at 2018/9/2 20:24
 */
public class WoodIncisionProblem {

    static int woodCount ;
    static int[] woodArray ;

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1 << 16);

        int power = 0;

        try {
            woodCount = Integer.parseInt(reader.readLine());
            woodArray = new int[woodCount];

            // 最小堆，方便选择最小的 2 个元素
            // PS: 此处使用了另外包的类，提交OJ时，需要将此类copy过来当做内部类使用，才能AC
            BinaryHeap minimumHeap = new BinaryHeap();

            for (int i = 0; i < woodCount; i++){
                woodArray[i] = Integer.parseInt(reader.readLine());
                minimumHeap.insert(woodArray[i]);
            }

            /*
                构造哈夫曼树的过程就像在操作森林。
                初始时，每颗元素都是一棵树，在每次选择2课最小的树，合并为一颗树，重新放入森林中。
                直到森林只有一棵树时结束。
                过程中每次合并得到的树的权值是切割时耗费的体力。
             */
            while (minimumHeap.heapSize() > 1){
                int w1 = minimumHeap.deleteMin();
                int w2 = minimumHeap.deleteMin();
                int f = w1 + w2;
                minimumHeap.insert(f);
                power += f;
            }

            System.out.println(power);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
