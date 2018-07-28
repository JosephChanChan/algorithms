/**
 *  给定一个n的数组，其中包含一些0，将0全部移动到数组后面，要求时间复杂度 O(n)
 *
 *  Created by Joseph on 2017/6/11.
 */
public class MoveZeros {

    static int[] arr = {0,7,0,2,3,0,0,8};

    /*
        该算法最大亮点在双指针，时间复杂度 O(n) 空间复杂度 O(1)
     */
    public static void main(String[] args){
        int i=0,j=0,k=0;
        while (arr[j]!=0){
            j++;
        }
        i = j;
        for(; i<arr.length; i++){
            if(arr[i]!=0){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }

        for(; k<arr.length; k++){
            System.out.print(arr[k]+"  ");
        }
    }
}
