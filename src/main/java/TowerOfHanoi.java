package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joseph at 2018/1/7 0007 18:57.
 *  汉诺塔问题
 */
public class TowerOfHanoi {

    //栈A 4 3 2 1
    static Stack A = new Stack("A","0",3,2,1);
    //栈B
    static Stack B = new Stack("B","1");
    //栈C
    static Stack C = new Stack("C","2");

    static Map<Integer,MapInfo> mapInfos = new HashMap<>();

    public static void main(String[] args) {
        //初始化位置信息
        mapInfos.put(1,new MapInfo(0));
        mapInfos.put(2,new MapInfo(0));
        mapInfos.put(3,new MapInfo(0));

        /**
         * 目标：将A的全部数字移动到C上。遵循以下规则：
         *  1、每一个栈的数字严格遵循从小到大顺序，即升序
         *  2、每次移动数字只能移动 1 个数字。
         */

        //第1步，发现移动4个数字，数量大于1，递归
        recursionMove(0,A.stackSize()-1,A,C,B);
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
        if (top < target.littleSeeTop()){
            //该数字能直接放入目标栈
            target.push(top);

            markMapInfo(top,target);
        }
        else {
            //找到目标栈中待移动数字的下标，从它开始到栈顶的元素全部移动到另一个栈
            int index = target.lastLessIndex(top);
            //将目标栈的数字移到备栈，源栈此时当备栈
            recursionMove(0,index,target,standby,origin);
            //目标栈数字全部移动完毕，此时可将源栈数字移动到目标栈
            target.push(top);

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

    /** 栈存放元素 */
    private static class Stack{

        //栈编号
        private int no = 0;

        private String name = "";

        List<Integer> elements = new ArrayList<>();

        public Stack(Integer... elements){
            if (null != elements && elements.length > 0){
                for (Integer element : elements){
                    this.elements.add(0,element);
                }
            }
        }

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
            else if (elements.get(0) > element){
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

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
