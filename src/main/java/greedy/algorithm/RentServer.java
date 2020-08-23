package greedy.algorithm;

import java.util.*;

/**
 * 顺丰2020校招笔试
 *
 * Question Description:
 *  小明有n台服务器，每台服务器带宽为ai，有k个客人来租服务器，每个客人有bi的预算和对服务器有ci的带宽需求，只能满足带宽才租服务器。
 * 小明比较贪钱，n台服务器能最多能租多少钱？
 *
 * Analysis:
 * 贪心，把i台服务器租给能满足带宽需求的且预算最多的客人，即 ai >= bi & max{ci}
 *
 * 时间复杂度：O(n*k)
 * 空间复杂度：O(n)
 *
 * @author Joseph
 * @since 2020-08-16 20:49
 */
public class RentServer {

    static int n, m ;
    static List<Integer> network ;
    static List<Customer> customers ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();

        if (n == 0 || m == 0) {
            System.out.println(0);
        }

        network = new ArrayList<>(n);
        customers = new ArrayList<>(m);

        for (int i = 0; i < n; i++) {
            network.add(scanner.nextInt());
        }
        for (int i = 0; i < m; i++) {
            Customer customer = new Customer();
            customer.required = scanner.nextInt();
            customer.profit = scanner.nextInt();
            customers.add(customer);
        }

        // 服务器按照带宽升序排序
        network.sort((o1, o2) -> o1 - o2);

        // 客户按利润降序
        customers.sort((o1, o2) -> o2.profit - o1.profit);

        int total = 0;
        boolean[] buy = new boolean[m];
        for (int i = 0; i < network.size(); i++) {
            // ai >= bi & max{ci}
            Integer ai = network.get(i);
            for (int j = 0; j < customers.size(); j++) {
                if (!buy[j] && ai >= customers.get(j).required) {
                    buy[j] = true;
                    total += customers.get(j).profit;
                    break;
                }
            }
        }
        System.out.println(total);
    }

    static class Customer {
        int required;
        int profit;
    }
}
