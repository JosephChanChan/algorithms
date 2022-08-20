/**
 * lc 223 medium
 *
 * Analysis:
 *  重点是搞懂重叠矩形的2个对角点的计算方法。
 *  类似题目还有华为机试题目求3个矩形的相交面积。
 *
 *  时间复杂度：O(1)
 *  空间复杂度：O(1)
 *
 * @author Joseph
 * @since 2022/5/1
 */
public class CalcRectangleOverlapSpace {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        /*
            重叠矩形计算：
            1.算出重叠矩形的左下角,右上角
                lx = max(ax1, bx1)
                ly = max(ay1, by1)
                rx = min(ax2, bx2)
                ry = min(ay2, by2)
            如果2个矩形有重叠，则lx < rx && ly < ry
        */

        int aSpace = Math.abs(ax1-ax2) * Math.abs(ay1-ay2);
        int bSpace = Math.abs(bx1-bx2) * Math.abs(by1-by2);

        int lx = Math.max(ax1, bx1);
        int ly = Math.max(ay1, by1);
        int rx = Math.min(ax2, bx2);
        int ry = Math.min(ay2, by2);

        if (lx < rx && ly < ry) {
            int m = Math.abs(lx-rx) * Math.abs(ly-ry);
            return aSpace + bSpace - m;
        }
        return aSpace + bSpace;
    }
}
