/**
 * @author Joseph
 * @since 2020-03-11 18:20
 *
 * leetcode 11 medium
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * Analysis:
 *  暴力穷举版本，从左到右组合每一个矩形的面积，记录下最大的，时间复杂度为O(n^2)。
 *  双指针非常巧妙，从网上看得解题报告。讲解下我理解的原理：
 *  设 m，n 2个指针分别指向2条线，它们相距 l 宽度，h(x) 为x线的高度。
 *  若 h(m) <= h(n)，此时面积是 area = h(m) * l。
 *  如果指针需要往中间移动，若移动n到n1，看看会怎么样。
 *  不管 h(n1) 小于还是等于还是大于 h(m) 最终面积都会比原先的 area 小。
 *  因为 area1 = min{h(m), h(n1)} * (l-1)。所以选择移动 n 并没有好处，我们看看移动 m。
 *  移到 m1，if h(m1) <= h(m) 面积都会更小，if h(m1) - h(m) > 1 那面积就会比之前大，
 *  意思就是由于宽度缩小了，但是现在的高度比之前更高了，就有可能新的面积大于之前的面积，只要新的高度和之前的高度的差大于缩小的宽度1。
 *  总结：只能移动较短的那条线。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class MostWaterInContainer {

    /* AC 2ms */
    public int maxArea(int[] heightArray) {
        int maxArea = 0, height, weight ;
        int left = 0, right = heightArray.length - 1;

        while (left < right) {
            int leftHeight = heightArray[left];
            int rightHeight = heightArray[right];
            height = Math.min(leftHeight, rightHeight);
            weight = right - left;
            if (height * weight > maxArea) {
                maxArea = height * weight;
            }
            if (leftHeight <= rightHeight) {
                left++;
            }
            else {
                right--;
            }
        }
        return maxArea;
    }



}
