package simulation.algorithm;

import java.io.*;

/**
 * Created by Joseph on 2017/6/14.
 * 有一口井，井的高度为N，每隔1个单位它的宽度有变化。现在从井口往下面扔圆盘，如果圆盘的宽度大于井在某个高度的宽度，则圆盘被卡住（恰好等于的话会下去）。
 盘子有几种命运：1、掉到井底。2、被卡住。3、落到别的盘子上方。
 盘子的高度也是单位高度。给定井的宽度和每个盘子的宽度，求最终落到井内的盘子数量。
 */
public class ThrowDishes {

    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in),1<<16);
        PrintWriter writer = new PrintWriter(System.out);
        int[] wells = null;
        int count = 0;
        int n=0,m=0,c;
        boolean touchBottom = true;
        try {
            String[] split = reader.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            m = Integer.parseInt(split[1]);
            wells = new int[n];
            for(int i=0; i<n; i++){
                wells[i] = Integer.parseInt(reader.readLine());
            }

            c = wells.length-1;
            int j = 0;
            for(; j<m; j++){
                int diskBreatdh = Integer.parseInt(reader.readLine());
                int l=0;
                for(; l<=c; l++){
                    if(wells[l] < diskBreatdh){
                        touchBottom = false;
                        c = l-2;
                        if(l!=0){
                            count++;
                        }
                    }
                }
                if(touchBottom){
                    //该圆板到井底
                    c--;
                    count++;
                }
                if(c<0){
                    break;
                }
                touchBottom = true;
            }

            writer.print(count);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
