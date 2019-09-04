import java.util.*;

/**
 *  汉诺塔问题
 *  目标：将A的全部数字移动到C上。遵循以下规则：
 *      1、每一个栈的数字严格遵循从小到大顺序，即升序
 *      2、每次移动数字只能移动 1 个数字。
 *
 *  算法思路：
 *      如果将 (1,2,3) 移动到 C 栈上，则必须先将(1,2)移动到B栈上，才能把3从A取出来放入到C中。
 *      据此可以推理出，如果将(1,2)移动到B栈上，需将1移动到C栈，才能将2从A取出放入B.
 *      设现有 N 个数字需要移动到C上，则欲将N移到C上，需先移动N-1到B，欲移N-1到B，需移N-2到C
 *      可以无限细分下去，直到移动的数字为1可直接移动。
 *      这符合一个递归的过程。
 *      那么这就可以了吗？还忽略了1步，按上述步骤，为了将N移到C，N-1移到了B，N-2移到了C，实际算法执行的过程中
 *      是从栈顶取元素即将N-2移到C，再将N-1移到B，可以发现只按上述步骤执行，只是满足将N-1移到B，N-1的确到了B但是N-2还在C上，
 *      并没有考虑将N-2移回B上即N-1上，此时N移到C，发现N比N-2大不能直接放入，这就有问题。
 *      所以每次移动数字后，需检查从该数字向上直到1的数字全部移动回当前栈上，为下一个数字N移动到C空出位置。
 *      如果N-1向上N-2到1是多个数，这相当于将N-2个数从某个栈移到一个栈，与原问题性质一样的一个子问题，可以递归解决。
 *
 *      如何证明这个算法的正确性？
 *      实际上述步骤将N个数字，交叉放置在另外2个栈上，每放置一个数字后将该数字上的所有数字拉回该栈它的上面，为原栈中下一个数字
 *      移动做准备，当N被放置到目标栈后，N会发起递归，将N-1到1的全部数字放到目标栈N的上面，当程序结束时，问题便解决了。
 *
 *      算法时间复杂度：
 *          直接看分析不出来，我调试程序发现，N=30时，运行时间为42秒，N每增加1，运行时间 * 2，
 *          当 N=40时，运行时间为 42 * 2^10 秒。所以时间复杂度应为 2^N 指数级。
 *
 * Created by Joseph at 2018/1/7 0007 18:57.
 */
public class TowerOfHanoi {

    //栈A 4 3 2 1
    static Stack A = new Stack("A","0");
    //栈B
    static Stack B = new Stack("B","1");
    //栈C
    static Stack C = new Stack("C","2");

    static Map<Integer,MapInfo> mapInfos = new HashMap<>();

    public static void main(String[] args) {
        for (int i=29; i>=1; --i){
            A.push(i);
            mapInfos.put(i,new MapInfo(0));
        }

        long start = System.currentTimeMillis();
        //第1步，发现移动N个数字，数量大于1，递归
        recursionMove(0,A.stackSize()-1,A,C,B);

        C.traversal();

        System.out.println();
        System.out.println((System.currentTimeMillis() - start) / 1000);
    }

    private static void recursionMove(int start,int end,Stack origin,Stack target,Stack standby){
        if (origin.stackSize() < 0){
            //源栈为空不需计算
            return;
        }

        if (end - start > 0){
            //需要移动的数字大于1，不能直接移动，递归
            recursionMove(start,end-1,origin,standby,target);
        }

        Integer top = origin.top();
        if (target.canPush(top)){
            //该数字能直接放入目标栈
            target.push(top);

            //只有入栈的情况才会引起栈的数字不合法（不符合升序），所以只需检查入栈的栈情况
//            target.checkValid();
//            showStack();
            markMapInfo(top,target);
        }

        /**
         * 此次递归后检查队列完整性
         */
        MapInfo mapInfo = mapInfos.get(top - 1);
        if (null != mapInfo){
            //位置信息不为空代表此数字上面有数字需要归队
            int curStack = mapInfo.getCurStack();
            int temp = 0;
            if (0 == curStack){
                temp = A.indexOfElement(top - 1);
                recursionMove(0,temp,A,target,B == target ? C : B);
            }
            else if (1 == curStack){
                temp = B.indexOfElement(top - 1);
                recursionMove(0,temp,B,target,A == target ? C : A);
            }
            else {
                temp = C.indexOfElement(top - 1);
                recursionMove(0,temp,C,target,A == target ? B : A);
            }
        }

    }

    //记录当前数字位置信息
    private static void markMapInfo(int num,Stack stack){
        MapInfo mapInfo = mapInfos.get(num);
        mapInfo.setCurStack(stack.getNo());
        mapInfos.put(num,mapInfo);
    }

    //打印3个栈的数字排列情况
    private static void showStack(){
        System.out.println("\n\nA栈：");
        A.traversal();
        System.out.println("\r\nB栈：");
        B.traversal();
        System.out.println("\r\nC栈：");
        C.traversal();
    }

    /** 栈存放元素 */
    private static class Stack{

        //栈编号
        private int no = 0;

        private String name = "";

        List<Integer> elements = new ArrayList<>();

        public Stack(String name,String no,Integer... elements){
            this.name = name;
            this.no = Integer.parseInt(no);
            if (null != elements && elements.length > 0){
                for (Integer element : elements){
                    this.elements.add(0,element);
                }
            }
        }

        //只是看一下栈顶元素
        public Integer littleSeeTop(){
            return elements.get(0);
        }

        //此数字是否可入栈
        public boolean canPush(int element){
            if (elements.size() == 0){
                return true;
            }
            else if (littleSeeTop() > element){
                return true;
            }
            return false;
        }

        //返回栈顶元素
        public Integer top(){
            Integer top = elements.get(0);
            elements.remove(0);
            return top;
        }

        public int stackSize(){
            return elements.size();
        }

        //压栈
        public void push(int element){
            elements.add(0,element);
        }

        //是否栈顶元素？可用来判断该元素上方是否有元素
        public boolean isHead(int element){
            return elements.get(0).equals(element);
        }

        //从上往下遍历栈，返回最后一个小于 beCompare 数字的下标
        public int lastLessIndex(int beCompare){
            for (int i=0; i<elements.size(); ++i){
                if (elements.get(i) > beCompare){
                    return --i;
                }
            }
            return 0;
        }

        //遍历此栈
        public void traversal(){
            elements.forEach(element -> {
                System.out.print(element + "  ");
//                System.out.println(element);
            });
        }

        //遍历栈找到 beCompare 所在的位置
        public int indexOfElement(int beCompare){
            for (int i=0; i<elements.size(); ++i){
                if (elements.get(i).equals(beCompare)){
                    return i;
                }
            }
            return 0;
        }

        //从栈底向栈顶遍历检查栈中数字的合法性
        public void checkValid(){
            for (int i = elements.size()-1; i > 0; i--){
                if (elements.get(i) < elements.get(i-1)){
                    throw new RuntimeException("栈 "+getName()+" 的数字不符合题目要求升序!!");
                }
            }
        }

        public int getNo() {
            return no;
        }

        public String getName() {
            return name;
        }

    }

    /** 节点数字的位置信息 */
    private static class MapInfo{

        //当前节点所在的栈 0是A栈，1是B栈，2是C栈
        private int curStack;

        public MapInfo(int curStack){
            this.curStack = curStack;
        }

        public int getCurStack() {
            return curStack;
        }

        public void setCurStack(int curStack) {
            this.curStack = curStack;
        }

    }
}
